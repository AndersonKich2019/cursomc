package com.andersonkich.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersonkich.cursomc.domain.Cidade;
import com.andersonkich.cursomc.domain.Cliente;
import com.andersonkich.cursomc.domain.Endereco;
import com.andersonkich.cursomc.domain.enuns.TipoCliente;
import com.andersonkich.cursomc.dto.ClienteDto;
import com.andersonkich.cursomc.dto.ClienteNewDto;
import com.andersonkich.cursomc.repositories.ClienteRepository;
import com.andersonkich.cursomc.repositories.EnderecoRepository;
import com.andersonkich.cursomc.services.exceptions.DataIntegrityException;
import com.andersonkich.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Cliente> findAll(){
		List<Cliente> list = repository.findAll();
		return list;
	}
	
	@Transactional//para salvar o cliente e o endereco na mesma transação
	public Cliente insert(Cliente obj) {//Post
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());//Salva os enderecos do cliente na tabela enderecos
		return obj;
	}
	
	public Cliente update(Cliente obj) {//Put
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);//O save tambem serve para atualizar
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque a entidades relacionadas");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);//Sobrecarga de metodo
		
		/*Integer page        = Informar qual a pagina eu quero acessar(a contagem de paginas comeca com zero)
		 *Integer linePerPage = Informar quantas linhas por pagina
		 *String orderBy      = Informar por qual campo eu quero ordenar (exemplo: nome, id, etc...)
		 *String direction    = Informar qual direcao eu quero (ASC ou DESC)
		 *PageRequest pageRequest = Objeto que prepara a consulta da pagina 
		 */
	}
	
	public Cliente fromDto(ClienteDto objDto) {
		 return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDto(ClienteNewDto objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()));
		Cidade cid = new Cidade(objDto.getCidade(), null, null);
		Endereco end1 = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end1);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() == null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() == null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		 return cli; 

	}
}

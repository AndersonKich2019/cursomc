package com.andersonkich.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.dto.CategoriaDto;
import com.andersonkich.cursomc.repositories.CategoriaRepository;
import com.andersonkich.cursomc.services.exceptions.DataIntegrityException;
import com.andersonkich.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Categoria> findAll(){
		List<Categoria> list = repository.findAll();
		return list;
	}
	
	public Categoria insert(Categoria obj) {//Post
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update(Categoria obj) {//Put
		findById(obj.getId());
		return repository.save(obj);//O save tambem serve para atualizar
		
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);//Sobrecarga de metodo
		
		/*Integer page        = Informar qual a pagina eu quero acessar(a contagem de paginas comeca com zero)
		 *Integer linePerPage = Informar quantas linhas por pagina
		 *String orderBy      = Informar por qual campo eu quero ordenar (exemplo: nome, id, etc...)
		 *String direction    = Informar qual direcao eu quero (ASC ou DESC)
		 *PageRequest pageRequest = Objeto que prepara a consulta da pagina 
		 */
	}
	
	public Categoria fromDto(CategoriaDto objDto) {
		
		return new Categoria(objDto.getId(), objDto.getNome());//Converter objDto em obj

	}
	
	
}

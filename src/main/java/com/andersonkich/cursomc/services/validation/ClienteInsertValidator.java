package com.andersonkich.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.andersonkich.cursomc.domain.Cliente;
import com.andersonkich.cursomc.domain.enuns.TipoCliente;
import com.andersonkich.cursomc.dto.ClienteNewDto;
import com.andersonkich.cursomc.repositories.ClienteRepository;
import com.andersonkich.cursomc.resources.excetions.FieldMessage;
import com.andersonkich.cursomc.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "cpf invalido"));
		}
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "cnpj invalido"));
		}
		
		Cliente objEmail = repository.findByEmail(objDto.getEmail());
		if(objEmail != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		Cliente objCpf = repository.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if(objCpf != null) {
			list.add(new FieldMessage("cpfOuCnpj", "Cpf ou Cnpj ja existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
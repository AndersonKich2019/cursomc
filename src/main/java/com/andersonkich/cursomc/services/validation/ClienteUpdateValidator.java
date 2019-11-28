package com.andersonkich.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.andersonkich.cursomc.domain.Cliente;
import com.andersonkich.cursomc.dto.ClienteDto;
import com.andersonkich.cursomc.repositories.ClienteRepository;
import com.andersonkich.cursomc.resources.excetions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {
	@Override
	public void initialize(ClienteUpdate ann) {
	}


	@Autowired
	private HttpServletRequest request;//Para buscar id pela URI
	
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));//Pega o id da uri
		
		Cliente objEmail = repository.findByEmail(objDto.getEmail());
		if(objEmail != null && objEmail.getId() != uriId) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
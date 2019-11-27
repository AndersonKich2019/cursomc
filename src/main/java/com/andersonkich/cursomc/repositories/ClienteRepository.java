package com.andersonkich.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.andersonkich.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly=true)//Fica mais rapido
	Cliente findByEmail(String email);//Faz uma busca automatica pelo campo email(Padrao de nomes)
	
	@Transactional(readOnly=true)
	Cliente findByCpfOuCnpj(String cpfOuCnpj);
	
	
	
}

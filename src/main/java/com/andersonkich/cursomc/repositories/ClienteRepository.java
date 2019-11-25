package com.andersonkich.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andersonkich.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}

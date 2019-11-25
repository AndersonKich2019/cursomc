package com.andersonkich.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonkich.cursomc.domain.ItemPedido;

@Repository
public interface ItemPedidoReposirory extends JpaRepository<ItemPedido, Integer> {

}

package com.github.lara.sodre.ms.pedidos.repository;

import com.github.lara.sodre.ms.pedidos.entites.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

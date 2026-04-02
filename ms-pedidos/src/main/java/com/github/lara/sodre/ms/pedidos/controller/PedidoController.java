package com.github.lara.sodre.ms.pedidos.controller;

import com.github.lara.sodre.ms.pedidos.dto.PedidoDTO;
import com.github.lara.sodre.ms.pedidos.repository.ItemDoPedidoRepository;
import com.github.lara.sodre.ms.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    //buscar
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos(){

        List<PedidoDTO> list = pedidoService.findAllPedidos();

        return ResponseEntity.ok(list);
    }

    //buscar pelo "id"
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable Long id){

        PedidoDTO pedidoDTO =pedidoService.findPedidoById(id);

        return ResponseEntity.ok(pedidoDTO);
    }

    //criar
    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody @Valid PedidoDTO pedidoDTO){

        pedidoDTO = pedidoService.savePedido(pedidoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pedidoDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pedidoDTO);
    }


    //atualizar valores
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id, @RequestBody @Valid PedidoDTO pedidoDTO){

        pedidoDTO = pedidoService.updatePedido(id, pedidoDTO);

        return ResponseEntity.ok(pedidoDTO);
    }


    //deletar pedidos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id){

        pedidoService.deletePedidoById(id);

        return ResponseEntity.noContent().build();
    }

}

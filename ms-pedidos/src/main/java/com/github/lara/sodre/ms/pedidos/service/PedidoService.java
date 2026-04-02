package com.github.lara.sodre.ms.pedidos.service;

import com.github.lara.sodre.ms.pedidos.dto.ItemDoPedidoDTO;
import com.github.lara.sodre.ms.pedidos.dto.PedidoDTO;
import com.github.lara.sodre.ms.pedidos.entites.ItemDoPedido;
import com.github.lara.sodre.ms.pedidos.entites.Pedido;
import com.github.lara.sodre.ms.pedidos.entites.Status;
import com.github.lara.sodre.ms.pedidos.exceptions.ResourceNotFoundException;
import com.github.lara.sodre.ms.pedidos.repository.ItemDoPedidoRepository;
import com.github.lara.sodre.ms.pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemDoPedidoRepository itemDoPedidoRepository;

    //pegar pelo "id"
    @Transactional(readOnly = true)
    public List<PedidoDTO> findAllPedidos(){

        return pedidoRepository.findAll()
                .stream().map(PedidoDTO::new).toList();
    }

    //pegar pedido pelo "id"
    @Transactional(readOnly = true)
    public PedidoDTO findPedidoById(Long id){

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("Recurso não encontrado. ID: "+ id)
        );

        return new PedidoDTO(pedido);
    }


    //criar pedido
    @Transactional
    public PedidoDTO savePedido(PedidoDTO pedidoDTO){

        Pedido pedido = new Pedido();

        pedido.setData(LocalDate.now());
        pedido.setStatus(Status.CRIADO);
        mapDtoToPedido(pedidoDTO, pedido);
        pedido.calcularValorTotalDoPedido();
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTO(pedido);
    }


    //mapear - metodo criado do save
    private void mapDtoToPedido(PedidoDTO pedidoDTO, Pedido pedido){

        pedido.setNome(pedidoDTO.getNome());
        pedido.setCpf(pedidoDTO.getCpf());

        for (ItemDoPedidoDTO itemDTO : pedidoDTO.getItens()){

            ItemDoPedido itemDoPedido = new ItemDoPedido();

            itemDoPedido.setQuantidade(itemDTO.getQuantidade());
            itemDoPedido.setDescricao(itemDTO.getDescricao());
            itemDoPedido.setPrecoUnitario(itemDTO.getPrecoUnitario());
            itemDoPedido.setPedido(pedido);

            pedido.getItens().add(itemDoPedido);
        }
    }


    //update
    @Transactional
    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO){

        try{
            Pedido pedido = pedidoRepository.getReferenceById(id);

            pedido.getItens().clear();
            pedido.setData(LocalDate.now());
            pedido.setStatus(Status.CRIADO);
            mapDtoToPedido(pedidoDTO, pedido);
            pedido = pedidoRepository.save(pedido);

            return new PedidoDTO(pedido);
        }catch (EntityNotFoundException e){

            throw new ResourceNotFoundException("Recurso não encontrado ID: " + id);
        }

    }

    @Transactional
    public void deletePedidoById(Long id){

        if(!pedidoRepository.existsById(id)){

            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        pedidoRepository.deleteById(id);
    }
}////

package com.github.lara.sodre.ms.pedidos.dto;
import com.github.lara.sodre.ms.pedidos.entites.ItemDoPedido;
import com.github.lara.sodre.ms.pedidos.entites.Pedido;
import com.github.lara.sodre.ms.pedidos.entites.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;

    @NotBlank(message = "Nome requerido")
    @Size(min = 3, max = 100, message = "O nome deve conter de 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "CPF requerido")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpf;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal valorTotal;

    //a lista não pode estar vazia
    @NotEmpty(message = "Pedido deve ter pelo menos um item")
    private List<@Valid ItemDoPedidoDTO> itens = new ArrayList<>();


    public PedidoDTO(Pedido pedido) {
        id = pedido.getId();
        nome = pedido.getNome();
        cpf = pedido.getCpf();
        data = pedido.getData();
        status = pedido.getStatus();
        valorTotal = pedido.getValorTotal();

        for(ItemDoPedido item : pedido.getItens()){
            ItemDoPedidoDTO itemDTO = new ItemDoPedidoDTO(item);

            itens.add(itemDTO);
        }
    }

}

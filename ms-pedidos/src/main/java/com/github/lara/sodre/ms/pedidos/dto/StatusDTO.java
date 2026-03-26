package com.github.lara.sodre.ms.pedidos.dto;

import com.github.lara.sodre.ms.pedidos.entites.ItemDoPedido;
import com.github.lara.sodre.ms.pedidos.entites.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {

    private Status status;

}

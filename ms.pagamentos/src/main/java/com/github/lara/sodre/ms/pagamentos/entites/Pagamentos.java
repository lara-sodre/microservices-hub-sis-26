package com.github.lara.sodre.ms.pagamentos.entites;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tb_pagamento")
public class Pagamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 16)
    private String numeroCartao;

    @Column(nullable = false, length = 5)
    private String validade;

    @Column(nullable = false, length = 3)
    String codigoSeguranca;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private Long pedidoId;

}

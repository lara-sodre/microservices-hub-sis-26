package com.github.lara.sodre.ms.pedidos.entites;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column - Vão definir as colunas no DataBase
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 11) //cpf tem que ser tamanho 11
    private String cpf;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Status status;

    //Valor calculado
    private BigDecimal valorTotal;

    //Relacionamento
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDoPedido> itens = new ArrayList<>();


    public void calcularValorTotalDoPedido(){

        this.valorTotal = this.itens.stream()
                .map(i -> i.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

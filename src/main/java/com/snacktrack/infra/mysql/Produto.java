package com.snacktrack.infra.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produto_id")
    private Long id;
    private String nome;
    private Integer estoque;
    private Integer limiteEstoque;
    private LocalDate validade;
    private Double valor;
    @ManyToMany
    private List<RoteiroProducao> roteiros;
}

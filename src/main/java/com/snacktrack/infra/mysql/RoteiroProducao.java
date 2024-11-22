package com.snacktrack.infra.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RoteiroProducao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roteiro_id")
    private Long id;
    private String nome;
    private LocalTime tempoEstimado;
    @OneToMany
    private List<PassoRoteiro> passos;
    @ManyToMany
    private List<Produto> produtos;
}

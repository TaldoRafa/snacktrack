package com.snacktrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoteiroProducaoDTO {
    private Long id;
    private String nome;
    private LocalTime tempoEstimado;
    private List<PassoRoteiroDTO> passosRoteiro;
}

package com.snacktrack.dto.mapper;

import com.snacktrack.dto.PassoRoteiroDTO;
import com.snacktrack.dto.RoteiroProducaoDTO;
import com.snacktrack.infra.mysql.PassoRoteiro;
import com.snacktrack.infra.mysql.RoteiroProducao;
import org.springframework.stereotype.Component;

@Component
public class RoteiroProducaoMapper {
    public RoteiroProducaoDTO toDTO(RoteiroProducao roteiroProducao) {

        return RoteiroProducaoDTO.builder()
                .id(roteiroProducao.getId())
                .nome(roteiroProducao.getNome())
                .tempoEstimado(roteiroProducao.getTempoEstimado())
                .passosRoteiro(roteiroProducao.getPassos()
                        .stream()
                        .map(p -> PassoRoteiroDTO.builder()
                                .id(p.getId())
                                .descricaoRoteiro(p.getDescricaoRoteiro())
                                .build()
                        )
                        .toList()
                )
                .build();
    }

    public RoteiroProducao toEntity(RoteiroProducaoDTO roteiroProducaoDTO) {
        return RoteiroProducao.builder()
                .id(roteiroProducaoDTO.getId())
                .nome(roteiroProducaoDTO.getNome())
                .tempoEstimado(roteiroProducaoDTO.getTempoEstimado())
                .passos(roteiroProducaoDTO.getPassosRoteiro().stream()
                        .map(p -> {
                                    PassoRoteiro passoRoteiro = new PassoRoteiro();
                                    passoRoteiro.setId(p.getId());
                                    passoRoteiro.setDescricaoRoteiro(p.getDescricaoRoteiro());
                                    return passoRoteiro;
                                }
                        )
                        .toList()
                )
                .build();
    }
}

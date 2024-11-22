package com.snacktrack.dto.mapper;

import com.snacktrack.dto.ProdutoDTO;
import com.snacktrack.dto.RoteiroProducaoDTO;
import com.snacktrack.infra.mysql.Produto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoMapper {
    public ProdutoDTO toDTO(Produto produto) {
        if (produto == null) return null;

        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .validade(produto.getValidade())
                .estoque(produto.getEstoque())
                .limiteEstoque(produto.getLimiteEstoque())
                .build();
    }

    public Produto toEntity(ProdutoDTO produtoDTO) {
        if (produtoDTO == null) return null;

        return Produto.builder()
                .id(produtoDTO.getId())
                .nome(produtoDTO.getNome())
                .estoque(produtoDTO.getEstoque())
                .limiteEstoque(produtoDTO.getLimiteEstoque())
                .validade(produtoDTO.getValidade())
                .valor(produtoDTO.getValor())
                .build();
    }
}

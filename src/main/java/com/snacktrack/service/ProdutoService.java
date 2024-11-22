package com.snacktrack.service;

import com.snacktrack.dto.ProdutoDTO;
import com.snacktrack.dto.mapper.ProdutoMapper;
import com.snacktrack.infra.exception.BadRequestException;
import com.snacktrack.infra.mysql.Produto;
import com.snacktrack.infra.mysql.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProdutoDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ProdutoDTO findById(Long id) {
        return mapper.toDTO(findByIdOrThrowBadRequestException(id));
    }

    public Produto findByIdOrThrowBadRequestException(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Produto not Found"));
    }

    public List<ProdutoDTO> findAllByRoteiroId(Long id) {
        return repository.findAllByRoteiros_Id(id).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto produto = mapper.toEntity(produtoDTO);
        produto.setId(null);
        return mapper.toDTO(repository.save(produto));
    }

    public ProdutoDTO replace(ProdutoDTO produtoDTO) {
        Produto savedProduto = findByIdOrThrowBadRequestException(produtoDTO.getId());
        Produto produto = mapper.toEntity(produtoDTO);
        produto.setId(savedProduto.getId());
        produto.setRoteiros(savedProduto.getRoteiros());

        return mapper.toDTO(repository.save(produto));
    }
}

package com.snacktrack.service;

import com.snacktrack.dto.ProdutoDTO;
import com.snacktrack.dto.RoteiroProducaoDTO;
import com.snacktrack.dto.mapper.RoteiroProducaoMapper;
import com.snacktrack.infra.exception.BadRequestException;
import com.snacktrack.infra.mysql.Produto;
import com.snacktrack.infra.mysql.RoteiroProducao;
import com.snacktrack.infra.mysql.repository.PassoRoteiroRepository;
import com.snacktrack.infra.mysql.repository.ProdutoRepository;
import com.snacktrack.infra.mysql.repository.RoteiroProducaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoteiroProducaoService {
    private final RoteiroProducaoRepository repository;
    private final RoteiroProducaoMapper mapper;
    private final ProdutoService produtoService;
    private final PassoRoteiroRepository passoRoteiroRepository;
    private final ProdutoRepository produtoRepository;

    public RoteiroProducaoService(RoteiroProducaoRepository repository, RoteiroProducaoMapper mapper, ProdutoService produtoService, PassoRoteiroRepository passoRoteiroRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.produtoService = produtoService;
        this.passoRoteiroRepository = passoRoteiroRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<RoteiroProducaoDTO> listAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public RoteiroProducaoDTO findById(Long id) {
        return mapper.toDTO(findByIdOrThrowBadRequestException(id));
    }

    public RoteiroProducao findByIdOrThrowBadRequestException(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Roteiro not Found"));
    }

    public List<RoteiroProducaoDTO> findAllByProdutoId(Long id) {
        return repository.findAllByProdutos_Id(id).stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    public RoteiroProducaoDTO save(RoteiroProducaoDTO roteiroProducaoDTO) {
        RoteiroProducao roteiroProducao = mapper.toEntity(roteiroProducaoDTO);
        roteiroProducao.setPassos(
                roteiroProducao.getPassos().stream()
                        .map(passoRoteiroRepository::save)
                        .toList()
        );
        return mapper.toDTO(repository.save(roteiroProducao));
    }

    public RoteiroProducaoDTO replace(RoteiroProducaoDTO roteiroProducaoDTO) {
        RoteiroProducao savedRoteiro = findByIdOrThrowBadRequestException(roteiroProducaoDTO.getId());
        RoteiroProducao roteiro = mapper.toEntity(roteiroProducaoDTO);

        roteiro.setId(savedRoteiro.getId());
        roteiro.setProdutos(savedRoteiro.getProdutos());

        passoRoteiroRepository.saveAll(roteiro.getPassos());

        return mapper.toDTO(repository.save(roteiro));
    }

    public void linksProdutoById(Long id, Long idProduto) {
        RoteiroProducao roteiroProducao = findByIdOrThrowBadRequestException(id);
        Produto produto = produtoService.findByIdOrThrowBadRequestException(idProduto);

        roteiroProducao.getProdutos().add(produto);
        produto.getRoteiros().add(roteiroProducao);

        repository.save(roteiroProducao);
        produtoRepository.save(produto);
    }
}

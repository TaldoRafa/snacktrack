package com.snacktrack.controller;

import com.snacktrack.dto.ProdutoDTO;
import com.snacktrack.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<ProdutoDTO>> listAll() {
        return ResponseEntity.ok(produtoService.listAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @GetMapping(path = "roteiro/{id}")
    private ResponseEntity<List<ProdutoDTO>> findAllByRoteiroId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findAllByRoteiroId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produto) {
        return new ResponseEntity<>(produtoService.save(produto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> replace(@RequestBody ProdutoDTO produto) {
        return ResponseEntity.ok(produtoService.replace(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

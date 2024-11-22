package com.snacktrack.controller;

import com.snacktrack.dto.RoteiroProducaoDTO;
import com.snacktrack.service.RoteiroProducaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roteiros")
public class RoteiroProducaoController {
    private final RoteiroProducaoService roteiroProducaoService;

    public RoteiroProducaoController(RoteiroProducaoService roteiroProducaoService) {
        this.roteiroProducaoService = roteiroProducaoService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<RoteiroProducaoDTO>> listAll() {
        return ResponseEntity.ok(roteiroProducaoService.listAll());
    }

    @GetMapping(path ="/{id}")
    private ResponseEntity<RoteiroProducaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roteiroProducaoService.findById(id));
    }

    @GetMapping(path = "produto/{id}")
    private ResponseEntity<List<RoteiroProducaoDTO>> findAllByRoteiroId(@PathVariable Long id) {
        return ResponseEntity.ok(roteiroProducaoService.findAllByProdutoId(id));
    }

    @PostMapping
    public ResponseEntity<RoteiroProducaoDTO> save(@RequestBody RoteiroProducaoDTO roteiroProducaoDTO) {
        return new ResponseEntity<>(roteiroProducaoService.save(roteiroProducaoDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RoteiroProducaoDTO> replace(@RequestBody RoteiroProducaoDTO roteiroProducaoDTO) {
        return ResponseEntity.ok(roteiroProducaoService.replace(roteiroProducaoDTO));
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roteiroProducaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/vincula/produto/{idProduto}")
    public ResponseEntity<Void> linksProduto(@PathVariable Long id, @PathVariable Long idProduto) {
        roteiroProducaoService.linksProdutoById(id, idProduto);
        return ResponseEntity.noContent().build();
    }
}

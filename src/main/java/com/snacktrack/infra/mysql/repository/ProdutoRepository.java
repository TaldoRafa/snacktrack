package com.snacktrack.infra.mysql.repository;

import com.snacktrack.infra.mysql.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByRoteiros_Id(Long id);
}

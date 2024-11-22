package com.snacktrack.infra.mysql.repository;

import com.snacktrack.infra.mysql.RoteiroProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoteiroProducaoRepository extends JpaRepository<RoteiroProducao, Long> {
    List<RoteiroProducao> findAllByProdutos_Id(Long id);
}

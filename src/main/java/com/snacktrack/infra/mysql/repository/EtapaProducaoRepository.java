package com.snacktrack.infra.mysql.repository;

import com.snacktrack.infra.mysql.EtapaProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapaProducaoRepository extends JpaRepository<EtapaProducao, Long> {
}

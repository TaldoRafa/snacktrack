package com.snacktrack.infra.mysql.repository;

import com.snacktrack.infra.mysql.PassoRoteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassoRoteiroRepository extends JpaRepository<PassoRoteiro, Long> {
}

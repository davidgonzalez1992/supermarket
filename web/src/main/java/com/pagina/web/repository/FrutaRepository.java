package com.pagina.web.repository;

import com.pagina.web.model.Fruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrutaRepository extends JpaRepository<Fruta, Long> {
    Fruta findByTipo(String tipo);
}

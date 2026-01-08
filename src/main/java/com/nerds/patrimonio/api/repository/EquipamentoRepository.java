package com.nerds.patrimonio.api.repository;

import com.nerds.patrimonio.api.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    boolean existsByNumeroSerie(String numeroSerie);
}
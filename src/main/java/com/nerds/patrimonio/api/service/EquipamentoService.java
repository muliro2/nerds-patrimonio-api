package com.nerds.patrimonio.api.service;

import com.nerds.patrimonio.api.dto.EquipamentoDTO;
import com.nerds.patrimonio.api.exception.RegraNegocioException;
import com.nerds.patrimonio.api.model.Equipamento;
import com.nerds.patrimonio.api.repository.EquipamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public List<Equipamento> listarTodos() {
        return repository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipamento não encontrado com ID: " + id));
    }

    public Equipamento salvar(EquipamentoDTO dto) {
        if (repository.existsByNumeroSerie(dto.numeroSerie())) {
            throw new RegraNegocioException("Já existe um equipamento cadastrado com o número de série: " + dto.numeroSerie());
        }

        Equipamento equipamento = Equipamento.builder()
                .nome(dto.nome())
                .tipo(dto.tipo())
                .numeroSerie(dto.numeroSerie())
                .build();
        
        return repository.save(equipamento);
    }

    public Equipamento atualizar(Long id, EquipamentoDTO dto) {
        Equipamento equipamento = buscarPorId(id);
        
        if (!equipamento.getNumeroSerie().equals(dto.numeroSerie()) && 
            repository.existsByNumeroSerie(dto.numeroSerie())) {
            throw new RegraNegocioException("Conflito: Número de série já pertence a outro equipamento.");
        }

        equipamento.setNome(dto.nome());
        equipamento.setTipo(dto.tipo());
        equipamento.setNumeroSerie(dto.numeroSerie());

        return repository.save(equipamento);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Equipamento não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }
}
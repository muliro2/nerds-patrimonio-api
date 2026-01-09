package com.nerds.patrimonio.api;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerds.patrimonio.api.dto.EquipamentoDTO;
import com.nerds.patrimonio.api.model.Equipamento;
import com.nerds.patrimonio.api.enums.TipoEquipamento;
import com.nerds.patrimonio.api.repository.EquipamentoRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NerdsPatrimonioApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EquipamentoRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();
{
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Deve cadastrar um equipamento com sucesso (Retorna 201)")
    void deveCadastrarEquipamento() throws Exception {
        EquipamentoDTO dto = new EquipamentoDTO(
            "Monitor Teste Unitario",
            TipoEquipamento.INFORMATICA,
            "12345678",
            LocalDate.now()
        );

        mockMvc.perform(post("/api/equipamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Monitor Teste Unitario"))
                .andExpect(jsonPath("$.numeroTombamento").value("12345678"));
    }

    @Test
    @DisplayName("Não deve cadastrar equipamento com tombamento duplicado (Retorna 409)")
    void naoDeveCadastrarDuplicado() throws Exception {
        Equipamento existente = Equipamento.builder()
                .nome("PC Existente")
                .tipo(TipoEquipamento.INFORMATICA)
                .dataAdicao(LocalDate.now())
                .numeroTombamento("UFC-DUPLICADO")
                .build();
        repository.save(existente);

        EquipamentoDTO novoDto = new EquipamentoDTO(
            "PC Novo Clonado",
            TipoEquipamento.INFORMATICA,
            "UFC-DUPLICADO",
            LocalDate.now()
        );

        mockMvc.perform(post("/api/equipamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoDto)))
                .andExpect(status().isConflict()) // Espera erro 409
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Deve listar todos os equipamentos (Retorna 200)")
    void deveListarEquipamentos() throws Exception {
        repository.save(Equipamento.builder().nome("Item A").tipo(TipoEquipamento.OUTRO).numeroTombamento("T1").dataAdicao(LocalDate.now()).build());
        repository.save(Equipamento.builder().nome("Item B").tipo(TipoEquipamento.OUTRO).numeroTombamento("T2").dataAdicao(LocalDate.now()).build());

        mockMvc.perform(get("/api/equipamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Não deve cadastrar com Enum inválido (Retorna 400)")
    void naoDeveCadastrarEnumInvalido() throws Exception {
        String jsonInvalido = """
            {
                "nome": "Cadeira Quebrada",
                "tipo": "TIPO_INEXISTENTE",
                "dataAdicao": "2026-01-08",
                "numeroTombamento": "12345678"
            }
        """;

        mockMvc.perform(post("/api/equipamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInvalido))
                .andExpect(status().isBadRequest());
    }
}
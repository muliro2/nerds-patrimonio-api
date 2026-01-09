package com.nerds.patrimonio.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.nerds.patrimonio.api.enums.TipoEquipamento;

@Entity
@Table(name = "tb_equipamentos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEquipamento tipo;

    @Column(nullable = false, unique = true)
    private String numeroTombamento;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataAdicao;
}
package br.com.postech.techchallenge.entities;

import br.com.postech.techchallenge.entities.enums.EstadosEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Endereco implements PoliticaEntity {

    @Id
    @Column(nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false, length = 16)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private EstadosEnum estado;
}

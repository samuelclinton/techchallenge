package br.com.postech.techchallenge.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "eletrodomesticos")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Eletrodomestico implements PoliticaEntity {

    @Id
    @Column(nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String potencia;

    @Column(nullable = false)
    private String marca;
}

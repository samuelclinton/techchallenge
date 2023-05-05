package br.com.postech.techchallenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String estado;
}

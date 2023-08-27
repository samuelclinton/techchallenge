package br.com.postech.techchallenge.domain.model;

import br.com.postech.techchallenge.domain.model.enums.Voltagem;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Eletrodomestico implements DomainEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String codigo = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Float potencia;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Voltagem voltagem;

    @Column(nullable = false)
    private String marca;

    @ManyToMany
    private Set<Pessoa> usuarios = new HashSet<>();

    public Float calcularConsumo(Integer minutosEmUso) {
        var consumo = (this.potencia * minutosEmUso) / 60;
        return consumo / 1000;
    }

    public void adicionarUsuario(Pessoa usuario) {
        this.usuarios.add(usuario);
    }

    public void removerUsuario(Pessoa usuario) {
        this.usuarios.remove(usuario);
    }

}

package br.com.postech.techchallenge.domain.model;

import br.com.postech.techchallenge.domain.model.enums.Estado;
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
public class Endereco implements DomainEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String codigo = UUID.randomUUID().toString();

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
    private Estado estado;

    @ManyToOne
    private Pessoa responsavel;

    @ManyToMany(mappedBy = "enderecos")
    private Set<Pessoa> residentes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Eletrodomestico> eletrodomesticos = new HashSet<>();

    public void adicionarEletrodomestico(Eletrodomestico eletrodomestico) {
        this.eletrodomesticos.add(eletrodomestico);
    }

    public void removerEletrodomestico(Eletrodomestico eletrodomestico) {
        this.eletrodomesticos.remove(eletrodomestico);
    }

}

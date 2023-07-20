package br.com.postech.techchallenge.domain.model;

import br.com.postech.techchallenge.domain.model.enums.Sexo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Pessoa implements DomainEntity {

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
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(nullable = false, unique = true)
    private String cpf;

    @CreationTimestamp
    private Instant dataCadastro;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Endereco> enderecos = new HashSet<>();

    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.getResidentes().add(this);
    }

    public void removerEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
        endereco.getResidentes().remove(this);
    }

}

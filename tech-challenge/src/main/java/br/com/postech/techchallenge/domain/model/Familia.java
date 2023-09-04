package br.com.postech.techchallenge.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Familia implements DomainEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private final String codigo = UUID.randomUUID().toString();

    @OneToOne
    private Pessoa responsavel;

    @OneToMany
    private Set<Pessoa> avos = new HashSet<>();

    @OneToMany
    private Set<Pessoa> pais = new HashSet<>();

    @OneToMany
    private Set<Pessoa> filhos = new HashSet<>();

    @OneToMany
    private Set<Pessoa> netos = new HashSet<>();

    public void adicionarMembro(Pessoa membro) {
        switch (membro.getNivelHierarquicoFamiliar()) {
            case AVOS -> this.avos.add(membro);
            case PAIS -> this.pais.add(membro);
            case FILHOS -> this.filhos.add(membro);
            case NETOS -> this.netos.add(membro);
        }
    }

    public void removerMembro(Pessoa membro) {
        switch (membro.getNivelHierarquicoFamiliar()) {
            case AVOS -> this.avos.remove(membro);
            case PAIS -> this.pais.remove(membro);
            case FILHOS -> this.filhos.remove(membro);
            case NETOS -> this.netos.remove(membro);
        }
    }

    public Set<Pessoa> pegarTodosOsFamiliares() {
        final var familiares = new HashSet<Pessoa>();
        familiares.addAll(this.avos);
        familiares.addAll(this.pais);
        familiares.addAll(this.filhos);
        familiares.addAll(this.netos);
        return familiares;
    }

    public Familia(Pessoa membro) {
        this.responsavel = membro;
        adicionarMembro(membro);
    }

}

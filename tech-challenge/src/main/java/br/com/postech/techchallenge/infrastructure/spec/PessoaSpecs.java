package br.com.postech.techchallenge.infrastructure.spec;

import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.filter.PessoaFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PessoaSpecs {

    public static Specification<Pessoa> usandoFiltro(PessoaFilter pessoaFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            if (pessoaFilter.getNome() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), pessoaFilter.getNome()));
            }

            if (pessoaFilter.getDataNascimento() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dataNascimento"), pessoaFilter.getDataNascimento()));
            }

            if (pessoaFilter.getGenero() != null) {
                predicates.add(criteriaBuilder.equal(root.get("genero"), pessoaFilter.getGenero()));
            }

            if (pessoaFilter.getCpf() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cpf"), pessoaFilter.getCpf()));
            }

            if (pessoaFilter.getTipoDeUsuario() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipoDeUsuario"), pessoaFilter.getTipoDeUsuario()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

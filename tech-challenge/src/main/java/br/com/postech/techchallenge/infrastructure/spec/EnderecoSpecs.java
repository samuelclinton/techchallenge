package br.com.postech.techchallenge.infrastructure.spec;

import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.repository.filter.EnderecoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class EnderecoSpecs {

    public static Specification<Endereco> usandoFiltro(EnderecoFilter enderecoFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            if (enderecoFilter.getRua() != null) {
                predicates.add(criteriaBuilder.equal(root.get("rua"), enderecoFilter.getRua()));
            }

            if (enderecoFilter.getNumero() != null) {
                predicates.add(criteriaBuilder.equal(root.get("numero"), enderecoFilter.getNumero()));
            }

            if (enderecoFilter.getBairro() != null) {
                predicates.add(criteriaBuilder.equal(root.get("bairro"), enderecoFilter.getBairro()));
            }

            if (enderecoFilter.getCidade() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cidade"), enderecoFilter.getCidade()));
            }

            if (enderecoFilter.getEstado() != null) {
                predicates.add(criteriaBuilder.equal(root.get("estado"), enderecoFilter.getEstado()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

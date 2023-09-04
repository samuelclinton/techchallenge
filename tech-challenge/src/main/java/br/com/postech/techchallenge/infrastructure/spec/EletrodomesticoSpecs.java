package br.com.postech.techchallenge.infrastructure.spec;

import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.repository.filter.EletrodomesticoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class EletrodomesticoSpecs {

    public static Specification<Eletrodomestico> usandoFiltro(EletrodomesticoFilter eletrodomesticoFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            if (eletrodomesticoFilter.getNome() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), eletrodomesticoFilter.getNome()));
            }

            if (eletrodomesticoFilter.getModelo() != null) {
                predicates.add(criteriaBuilder.equal(root.get("modelo"), eletrodomesticoFilter.getModelo()));
            }

            if (eletrodomesticoFilter.getPotenciaMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("potencia"),
                        eletrodomesticoFilter.getPotenciaMax()));
            }

            if (eletrodomesticoFilter.getPotenciaMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("potencia"),
                        eletrodomesticoFilter.getPotenciaMin()));
            }

            if (eletrodomesticoFilter.getVoltagem() != null) {
                predicates.add(criteriaBuilder.equal(root.get("voltagem"), eletrodomesticoFilter.getVoltagem()));
            }

            if (eletrodomesticoFilter.getMarca() != null) {
                predicates.add(criteriaBuilder.equal(root.get("marca"), eletrodomesticoFilter.getMarca()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

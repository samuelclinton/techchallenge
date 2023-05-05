package br.com.postech.techchallenge.repositories.specification;

import br.com.postech.techchallenge.controllers.filtros.PessoaFiltro;
import br.com.postech.techchallenge.entities.Pessoa;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

public class PessoaSpecification {

  public static Specification<Pessoa> consultaDinamicaComFiltro(PessoaFiltro filtro) {

    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (filtro.getId() != null) {
        predicados.add(criteriaBuilder.equal(root.get("id"), filtro.getId()));
      }

      if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
        predicados.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
            "%".concat(filtro.getNome().toLowerCase()).concat("%")));
      }

//      if (filtro.getSexo() != null) {
//        predicados.add(root.get("sexo"),filtro.getSexo());
//      }

      if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
        predicados.add(criteriaBuilder.like(root.get("cpf"),
            "%".concat(filtro.getCpf()).concat("%")));
      }

      if (filtro.getParentesco() != null && !filtro.getParentesco().isEmpty()) {
        predicados.add(criteriaBuilder.like(root.get("parentesco"),
            "%".concat(filtro.getParentesco()).concat("%")));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}


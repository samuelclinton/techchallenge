package br.com.postech.techchallenge.repositories.specification;

import br.com.postech.techchallenge.controllers.filtros.PessoaFiltro;
import br.com.postech.techchallenge.entities.Pessoa;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Locale;

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

      if (filtro.getSexo() != null) {
        predicados.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("sexo")),
          filtro.getSexo().toString().toUpperCase(Locale.ROOT)));
      }

      if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
        predicados.add(criteriaBuilder.like(root.get("cpf"),
            "%".concat(filtro.getCpf()).concat("%")));
      }

      if ( filtro.getDataNascimento() != null && !filtro.getDataNascimento().isEmpty()) {
        predicados.add(criteriaBuilder.like(root.get("dataNascimento"),
          "%".concat(filtro.getDataNascimento()).concat("%")));
      }

      if (filtro.getParentesco() != null && !filtro.getParentesco().isEmpty()) {
        predicados.add(criteriaBuilder.like(root.get("parentesco"),
            "%".concat(filtro.getParentesco()).concat("%")));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}


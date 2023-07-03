package br.com.postech.techchallenge.domain.repository;

import br.com.postech.techchallenge.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByCodigo(String codigo);
    List<Pessoa> findAllByParente(Pessoa parente);

}


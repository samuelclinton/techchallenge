package br.com.postech.techchallenge.repositories;

import br.com.postech.techchallenge.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PessoaRepositoryJpa extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

  Optional<Pessoa> findByCpf(String cpf);

  Optional<Pessoa> findByNome(String nome);
}


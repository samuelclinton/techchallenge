package br.com.postech.techchallenge.repositories;

import br.com.postech.techchallenge.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepositoryJpa extends JpaRepository<Pessoa, Long> {

  Optional<Pessoa> findByCpf(String cpf);
}


package br.com.postech.techchallenge.repositories;

import br.com.postech.techchallenge.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepositoryJpa extends JpaRepository<Pessoa, Long> { }


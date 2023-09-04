package br.com.postech.techchallenge.domain.repository;

import br.com.postech.techchallenge.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>, JpaSpecificationExecutor<Endereco> {
    Optional<Endereco> findByCodigo(String codigo);
}

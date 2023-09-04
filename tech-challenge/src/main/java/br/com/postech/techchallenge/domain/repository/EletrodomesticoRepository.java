package br.com.postech.techchallenge.domain.repository;

import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long>,
        JpaSpecificationExecutor<Eletrodomestico> {
    Optional<Eletrodomestico> findByCodigo(String codigo);
}

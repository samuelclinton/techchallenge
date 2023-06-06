package br.com.postech.techchallenge.repositories.specification;

import br.com.postech.techchallenge.entities.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, String> {
}


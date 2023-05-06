package br.com.postech.techchallenge.services.padrao_regras;

import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.exceptions.http409.RegraCpfUnicoException;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RegraCpfUnico implements PadraoRegrasDeNegocio<Pessoa> {

  @Autowired
  private PessoaRepositoryJpa repositoryJpa;

  @Override
  public void executarRegraDeNegocio(Pessoa entidade) {
    var cpf = entidade.getCpf();

    var existeCpfIgual = this.repositoryJpa.findByCpf(cpf)
      .filter(entityDoDatabase -> entityDoDatabase.getId() != entidade.getId())
      .isPresent();

    if (existeCpfIgual) {
      throw new RegraCpfUnicoException(cpf);
    }
  }
}

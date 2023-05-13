package br.com.postech.techchallenge.services.padrao_regras;

import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.exceptions.http409.RegraCpfUnicoException;
import br.com.postech.techchallenge.exceptions.http409.RegraNomeUnicoException;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RegraNomeUnico implements PadraoRegrasDeNegocio<Pessoa> {

  @Autowired
  private PessoaRepositoryJpa repositoryJpa;

  @Override
  public void executarRegraDeNegocio(Pessoa entidade) {
    var nome = entidade.getNome();

    var existeNomeIgual = this.repositoryJpa.findByNome(nome)
      .filter(entityDoDatabase -> entityDoDatabase.getId() != entidade.getId())
      .isPresent();

    if (existeNomeIgual) {
      throw new RegraNomeUnicoException(nome);
    }
  }
}

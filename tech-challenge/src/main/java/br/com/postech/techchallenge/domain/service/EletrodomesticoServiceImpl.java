package br.com.postech.techchallenge.domain.service;


import br.com.postech.techchallenge.domain.data.DomainEntityUtils;
import br.com.postech.techchallenge.domain.exception.EletrodomesticoNaoEncontradoException;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.repository.EletrodomesticoRepository;
import br.com.postech.techchallenge.domain.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EletrodomesticoServiceImpl implements EletrodomesticoService {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private DomainEntityUtils domainEntityUtils;

    @Override
    @Transactional
    public Eletrodomestico buscar(String codigoEletrodomestico) {
        return eletrodomesticoRepository.findByCodigo(codigoEletrodomestico)
                .orElseThrow(() -> new EletrodomesticoNaoEncontradoException(codigoEletrodomestico));
    }

    @Override
    @Transactional
    public List<Eletrodomestico> listar() {
        return eletrodomesticoRepository.findAll();
    }

    @Override
    @Transactional
    public Eletrodomestico cadastrar(Endereco endereco, Eletrodomestico eletrodomestico) {
        endereco.adicionarEletrodomestico(eletrodomestico);
        endereco.getResidentes().forEach(eletrodomestico::adicionarUsuario);
        enderecoRepository.save(endereco);
        return eletrodomestico;
    }

    @Override
    @Transactional
    public Eletrodomestico atualizar(String codigoEletrodomestico, Eletrodomestico eletrodomestico) {
        final var eletrodomesticoAtual = buscar(codigoEletrodomestico);
        domainEntityUtils.copiarPropriedades(eletrodomestico, eletrodomesticoAtual, "usuarios");
        return eletrodomesticoRepository.save(eletrodomesticoAtual);
    }

    @Override
    @Transactional
    public void deletar(String codigoEndereco, String codigoEletrodomestico) {
        final var eletrodomestico = buscar(codigoEletrodomestico);
        final var endereco = enderecoService.buscar(codigoEndereco);

        endereco.removerEletrodomestico(eletrodomestico);

        final var usuarios = new ArrayList<>(eletrodomestico.getUsuarios());
        usuarios.forEach(eletrodomestico::removerUsuario);

        eletrodomesticoRepository.delete(eletrodomestico);
    }

}

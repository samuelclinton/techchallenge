package br.com.postech.techchallenge.domain.service;


import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.repository.EletrodomesticoRepository;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.domain.exception.EletrodomesticoNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EletrodomesticoServiceImpl implements EletrodomesticoService {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @Autowired
    private DomainEntityMapper<EletrodomesticoInput, EletrodomesticoOutput, Eletrodomestico> mapper;

    @Override
    public EletrodomesticoOutput buscar(String id) {
        final var eletrodomesticos = buscarOuLancarException(id);
        return mapper.converterEntidadeParaOutput(eletrodomesticos, EletrodomesticoOutput.class);
    }

    private Eletrodomestico buscarOuLancarException(String codigo) {
        return eletrodomesticoRepository.findByCodigo(codigo).orElseThrow(() -> new EletrodomesticoNaoEncontradoException(codigo));
    }

    @Override
    public List<EletrodomesticoOutput> listar() {
        final var eletrodomesticos = eletrodomesticoRepository.findAll();
        return mapper.converterEntidadesParaListaDeOutputs(eletrodomesticos, EletrodomesticoOutput.class);
    }

    @Override
    public EletrodomesticoOutput cadastrar(EletrodomesticoInput eletrodomesticoInput) {
        final var eletrodomestico = eletrodomesticoRepository.save(mapper.converterInputParaEntidade(eletrodomesticoInput, Eletrodomestico.class));
        return mapper.converterEntidadeParaOutput(eletrodomestico, EletrodomesticoOutput.class);
    }

    @Override
    public EletrodomesticoOutput atualizar(String id, EletrodomesticoInput eletrodomesticoInput) {
        final var eletrodomesticoAtual = buscarOuLancarException(id);
        final var novoEletrodomestico = mapper.converterInputParaEntidade(eletrodomesticoInput, Eletrodomestico.class);
        BeanUtils.copyProperties(novoEletrodomestico, eletrodomesticoAtual);
        eletrodomesticoRepository.save(eletrodomesticoAtual);
        return mapper.converterEntidadeParaOutput(eletrodomesticoAtual, EletrodomesticoOutput.class);
    }

    @Override
    public void deletar(String id) {
        final var eletrodomestico = buscarOuLancarException(id);
        eletrodomesticoRepository.delete(eletrodomestico);
    }
}

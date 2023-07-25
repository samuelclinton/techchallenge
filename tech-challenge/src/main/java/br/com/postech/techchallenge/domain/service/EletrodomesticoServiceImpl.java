package br.com.postech.techchallenge.domain.service;


import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.exception.EletrodomesticoNaoEncontradoException;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.repository.EletrodomesticoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EletrodomesticoServiceImpl implements EletrodomesticoService {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @Autowired
    private DomainEntityMapper<EletrodomesticoInput, EletrodomesticoOutput, Eletrodomestico> mapper;

    @Override
    public Eletrodomestico buscar(String codigo) {
        return eletrodomesticoRepository.findByCodigo(codigo).orElseThrow(() -> new EletrodomesticoNaoEncontradoException(codigo));
    }

    @Override
    public EletrodomesticoOutput buscarEConverterParaOutput(String id) {
        final var eletrodomesticos = buscar(id);
        return mapper.mapearEntidadeParaOutput(eletrodomesticos, EletrodomesticoOutput.class);
    }

    @Override
    public List<EletrodomesticoOutput> listar() {
        final var eletrodomesticos = eletrodomesticoRepository.findAll();
        return mapper.mapearEntidadesParaListaDeOutputs(eletrodomesticos, EletrodomesticoOutput.class);
    }

    @Override
    @Transactional
    public EletrodomesticoOutput cadastrar(EletrodomesticoInput eletrodomesticoInput) {
        final var eletrodomestico = eletrodomesticoRepository.save(mapper.mapearInputParaEntidade(eletrodomesticoInput, Eletrodomestico.class));
        return mapper.mapearEntidadeParaOutput(eletrodomestico, EletrodomesticoOutput.class);
    }

    @Override
    @Transactional
    public EletrodomesticoOutput atualizar(String id, EletrodomesticoInput eletrodomesticoInput) {
        final var eletrodomesticoAtual = buscar(id);
        BeanUtils.copyProperties(eletrodomesticoInput, eletrodomesticoAtual);
        eletrodomesticoRepository.save(eletrodomesticoAtual);
        return mapper.mapearEntidadeParaOutput(eletrodomesticoAtual, EletrodomesticoOutput.class);
    }

    @Override
    @Transactional
    public void deletar(String id) {
        final var eletrodomestico = buscar(id);
        eletrodomesticoRepository.delete(eletrodomestico);
    }
}

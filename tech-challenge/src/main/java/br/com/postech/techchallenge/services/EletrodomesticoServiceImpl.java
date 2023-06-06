package br.com.postech.techchallenge.services;


import br.com.postech.techchallenge.dtos.responses.EletrodomesticoDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.EletrodomesticoDtoRequest;
import br.com.postech.techchallenge.entities.Eletrodomestico;
import br.com.postech.techchallenge.exceptions.http404.EletrodomesticoNaoEncontradoException;
import br.com.postech.techchallenge.mappers.PoliticaMapper;
import br.com.postech.techchallenge.repositories.specification.EletrodomesticoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EletrodomesticoServiceImpl implements EletrodomesticoService {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @Autowired
    private PoliticaMapper<EletrodomesticoDtoRequest, EletrodomesticoDtoResponse, Eletrodomestico> mapper;

    @Override
    public EletrodomesticoDtoResponse buscar(String id) {
        final var eletrodomesticos = buscarOuLancarException(id);
        return mapper.converterEntidadeParaDtoResponse(eletrodomesticos, EletrodomesticoDtoResponse.class);
    }

    private Eletrodomestico buscarOuLancarException(String id) {
        return eletrodomesticoRepository.findById(id).orElseThrow(() -> new EletrodomesticoNaoEncontradoException(id));
    }

    @Override
    public List<EletrodomesticoDtoResponse> listar() {
        final var eletrodomesticos = eletrodomesticoRepository.findAll();
        return mapper.converterEntidadesParaListaDeDtoResponse(eletrodomesticos, EletrodomesticoDtoResponse.class);
    }

    @Override
    public EletrodomesticoDtoResponse cadastrar(EletrodomesticoDtoRequest eletrodomesticoDtoRequest) {
        final var eletrodomestico = eletrodomesticoRepository.save(mapper.converterDtoRequestParaEntidade(eletrodomesticoDtoRequest, Eletrodomestico.class));
        return mapper.converterEntidadeParaDtoResponse(eletrodomestico, EletrodomesticoDtoResponse.class);
    }

    @Override
    public EletrodomesticoDtoResponse atualizar(String id, EletrodomesticoDtoRequest eletrodomesticoDtoRequest) {
        final var eletrodomesticoAtual = buscarOuLancarException(id);
        final var novoEletrodomestico = mapper.converterDtoRequestParaEntidade(eletrodomesticoDtoRequest, Eletrodomestico.class);
        BeanUtils.copyProperties(novoEletrodomestico, eletrodomesticoAtual);
        eletrodomesticoRepository.save(eletrodomesticoAtual);
        return mapper.converterEntidadeParaDtoResponse(eletrodomesticoAtual, EletrodomesticoDtoResponse.class);
    }

    @Override
    public void deletar(String id) {
        final var eletrodomestico = buscarOuLancarException(id);
        eletrodomesticoRepository.delete(eletrodomestico);
    }
}

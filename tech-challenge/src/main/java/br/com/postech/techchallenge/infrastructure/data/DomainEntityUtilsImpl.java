package br.com.postech.techchallenge.infrastructure.data;

import br.com.postech.techchallenge.domain.data.DomainEntityUtils;
import br.com.postech.techchallenge.domain.model.DomainEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DomainEntityUtilsImpl implements DomainEntityUtils {

    @Override
    public void copiarPropriedades(DomainEntity origem, DomainEntity destino, String... ignorando) {
        var propriedadesIgnoradas = adicionarIdECodigoAsPropriedadesIgnoradas(ignorando);
        BeanUtils.copyProperties(origem, destino, propriedadesIgnoradas);
    }

    private String[] adicionarIdECodigoAsPropriedadesIgnoradas(String[] propriedadesIgnoradas) {
        List<String> arrayList = new ArrayList<>(Arrays.asList(propriedadesIgnoradas));
        arrayList.add("id");
        arrayList.add("codigo");
        return arrayList.toArray(propriedadesIgnoradas);
    }

}

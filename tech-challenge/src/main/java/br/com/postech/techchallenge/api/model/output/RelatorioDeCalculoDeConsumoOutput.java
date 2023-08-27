package br.com.postech.techchallenge.api.model.output;

import lombok.Data;

import java.text.NumberFormat;
import java.util.Locale;

@Data
public class RelatorioDeCalculoDeConsumoOutput {

    private String consumo;
    private String custoEstimado;

    public RelatorioDeCalculoDeConsumoOutput(Float consumo) {
        this.consumo = consumo + " kWh";
        this.custoEstimado = formatarValorMonetario(calcularCusto(consumo));
    }

    private Double calcularCusto(Float consumo) {
        // TODO - Consultar a tarifa média por estado usando o endereço de instalação
        return consumo * 0.721;
    }

    private String formatarValorMonetario(Double valor) {
        var brasil = new Locale("pt", "BR");
        var formatadorMonetario = NumberFormat.getCurrencyInstance(brasil);
        return formatadorMonetario.format(valor);
    }

}

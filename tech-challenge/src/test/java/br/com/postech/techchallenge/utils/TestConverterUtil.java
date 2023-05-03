package br.com.postech.techchallenge.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class TestConverterUtil {

  public static byte[] converterObjetoParaJson(Object object) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
    mapper.registerModule(new JavaTimeModule());
    return mapper.writeValueAsBytes(object);
  }
}

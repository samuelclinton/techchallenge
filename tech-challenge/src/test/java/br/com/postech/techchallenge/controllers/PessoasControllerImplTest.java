package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.entities.enums.SexoEnum;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import br.com.postech.techchallenge.utils.TestConverterUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoasControllerImplTest {

  public static final String ENDPOINT = "/v1/pessoas";

  public static final String UTF8 = "UTF-8";

  public static final String NOME = "Robert Martin";

  public static final String CPF = "45706093091";

  public static final String DATA_NASCIMENTO = "20/10/1970";

  public static final String SEXO = SexoEnum.MASCULINO.toString();

  public static final String PARENTESCO = "Irmão";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PessoaRepositoryJpa pessoaRepositoryJpa;

  private PessoaDtoRequest pessoaDtoRequest;

  @BeforeEach
  void criadorDeCenario() {

    pessoaDtoRequest = PessoaDtoRequest.builder()
        .nome(NOME)
        .cpf(CPF)
        .dataNascimento(DATA_NASCIMENTO)
        .sexo(SEXO)
        .parentesco(PARENTESCO)
      .build();
  }

  @AfterEach
  void destruidorDeCenario() {
    this.pessoaRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Post - Cadastrar 201")
  void deveRetornarCreate201ComRecursoNoCorpo_quandoCadastrar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  @DisplayName("Post - Cadastrar - checagem de persistência")
  void devePersistirCorretoNoDatabase_quandoCadastrar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());

    var pessoaPersistida = this.pessoaRepositoryJpa.findByCpf(CPF).get();

    Assertions.assertEquals(NOME, pessoaPersistida.getNome());
    Assertions.assertEquals(CPF, pessoaPersistida.getCpf());
    Assertions.assertEquals(DATA_NASCIMENTO, pessoaPersistida.getDataNascimento());
    Assertions.assertEquals(SEXO, pessoaPersistida.getSexo().toString());
  }
}



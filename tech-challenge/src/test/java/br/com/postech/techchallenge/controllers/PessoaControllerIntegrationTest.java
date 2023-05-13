package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.entities.enums.SexoEnum;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import br.com.postech.techchallenge.utils.TestConverterUtil;
import org.hamcrest.Matchers;
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
class PessoaControllerIntegrationTest {

  public static final String ENDPOINT = "/v1/pessoas";

  public static final String UTF8 = "UTF-8";

  public static final String NOME1 = "Martin Fowler";

  public static final String NOME2 = "Robert Martin";

  public static final String CPF1 = "45706093091";

  public static final String CPF2 = "12353577083";

  public static final String DATA_NASCIMENTO = "20/10/1970";

  public static final SexoEnum SEXO = SexoEnum.MASCULINO;

  public static final String PARENTESCO = "Irmão";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PessoaRepositoryJpa pessoaRepositoryJpa;

  private Pessoa pessoaSalva;

  private PessoaDtoRequest pessoaDtoRequest;

  @BeforeEach
  void criadorDeCenario() {

    pessoaSalva = Pessoa.builder()
        .nome(NOME1)
        .cpf(CPF1)
        .dataNascimento(DATA_NASCIMENTO)
        .sexo(SEXO)
        .parentesco(PARENTESCO)
      .build();
    pessoaSalva = this.pessoaRepositoryJpa.save(pessoaSalva);

    pessoaDtoRequest = PessoaDtoRequest.builder()
        .nome(NOME2)
        .cpf(CPF2)
        .dataNascimento(DATA_NASCIMENTO)
        .sexo(SEXO.toString())
        .parentesco(PARENTESCO)
      .build();
  }

  @AfterEach
  void destruidorDeCenario() {
    this.pessoaRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Post - Cadastrar - 201")
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
  @DisplayName("Post - Cadastrar - validar persistência")
  void devePersistirCorretoNoDatabase_quandoCadastrar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());

    var pessoaPersistida = this.pessoaRepositoryJpa.findByCpf(CPF2).get();

    Assertions.assertEquals(NOME2, pessoaPersistida.getNome());
    Assertions.assertEquals(CPF2, pessoaPersistida.getCpf());
    Assertions.assertEquals(DATA_NASCIMENTO, pessoaPersistida.getDataNascimento());
    Assertions.assertEquals(SEXO, pessoaPersistida.getSexo());
    Assertions.assertEquals(PARENTESCO, pessoaPersistida.getParentesco());
  }

  @Test
  @Order(3)
  @DisplayName("Post - Cadastrar - cpf único 409")
  void deveRetornarConflict409PorViolacaoDeRegraDeCpfUnico_quandoCadastrar() throws Exception {
    pessoaDtoRequest.setCpf(CPF1);

    mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }


  @Test
  @Order(5)
  @DisplayName("Put - Atualizar - 200")
  void deveRetornarOk200ComRecursoNoCorpo_quandoAtualizar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT.concat("/" + pessoaSalva.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpectAll(MockMvcResultMatchers.status().isOk(),
          MockMvcResultMatchers.jsonPath("$.nome", Matchers.equalToIgnoringCase(NOME2)),
          MockMvcResultMatchers.jsonPath("$.cpf", Matchers.equalTo(CPF2)),
          MockMvcResultMatchers.jsonPath("$.dataNascimento", Matchers.equalTo(DATA_NASCIMENTO)),
          MockMvcResultMatchers.jsonPath("$.sexo", Matchers.equalTo(SEXO.toString())),
          MockMvcResultMatchers.jsonPath("$.parentesco", Matchers.equalTo(PARENTESCO)))
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(6)
  @DisplayName("Put - Atualizar - validar persistência")
  void devePersistirCorretoNoDatabase_quandoAtualizar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT.concat("/" + pessoaSalva.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());

    var pessoaDoDatabase = this.pessoaRepositoryJpa.findByCpf(CPF2).get();

    Assertions.assertEquals(NOME2, pessoaDoDatabase.getNome());
    Assertions.assertEquals(CPF2, pessoaDoDatabase.getCpf());
    Assertions.assertEquals(DATA_NASCIMENTO, pessoaDoDatabase.getDataNascimento());
    Assertions.assertEquals(SEXO, pessoaDoDatabase.getSexo());
    Assertions.assertEquals(PARENTESCO, pessoaDoDatabase.getParentesco());
  }

  @Test
  @Order(7)
  @DisplayName("Put - Atualizar - cpf único 409")
  void deveRetornarConflict409PorViolacaoDeRegraDeCpfUnico_quandoAtualizar() throws Exception {

    var pessoaSalva2 = Pessoa.builder()
        .nome("Sam Newman")
        .cpf(CPF2)
        .dataNascimento("20/10/2005")
        .sexo(SEXO)
        .parentesco("tio")
      .build();
    this.pessoaRepositoryJpa.save(pessoaSalva2);

    pessoaDtoRequest.setCpf(CPF1);

    mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT.concat("/" + pessoaSalva2.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(TestConverterUtil.converterObjetoParaJson(pessoaDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpectAll(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }


  @Test
  @Order(10)
  @DisplayName("Delete - Deletar - 204")
  void deveRetornarNoContent204_quandoDeletar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT.concat("/" + pessoaSalva.getId())))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(11)
  @DisplayName("Delete - Deletar - validar persistência")
  void deveRemoverDadoPersistidoNoDatabase_quandoDeletar() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT.concat("/" + pessoaSalva.getId())))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
      .andDo(MockMvcResultHandlers.print());

    var existe = this.pessoaRepositoryJpa.findById(pessoaSalva.getId())
        .isPresent();

    Assertions.assertEquals(false, existe);
  }


  @Test
  @Order(15)
  @DisplayName("Pesquisar - Fluxo Principal I - dois objetos")
  void deveRetornarDoisObjetos_quandoPesquisarTodos() throws Exception {

    var pessoaSalva2 = Pessoa.builder()
      .nome("Jeff Sutherland")
      .cpf("30872708063")
      .dataNascimento("10/02/1975")
      .sexo(SEXO)
      .parentesco("sobrinho")
      .build();
    this.pessoaRepositoryJpa.save(pessoaSalva2);

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", Matchers.equalTo(2)))
      .andDo(MockMvcResultHandlers.print());
  }
}


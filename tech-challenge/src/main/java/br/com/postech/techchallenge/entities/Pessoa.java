package br.com.postech.techchallenge.entities;

import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.entities.enums.SexoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

@Entity
@Table(name = "pessoas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class Pessoa implements PoliticaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "nome", length = 150, nullable = false)
  private String nome;

  @Column(name = "data_nascimento")
  private String dataNascimento; // dd/MM/yyyy

  @Enumerated(EnumType.STRING)
  private SexoEnum sexo;

  @Column(name = "cpf", length = 14, nullable = false)
  private String cpf;

  @Column(name = "parentesco", length = 50, nullable = true)
  private String parentesco;

  @Column(name = "data_cadastro")
  private Instant dataCadastro;
}


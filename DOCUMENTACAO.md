# Documentação

Esta documentação tem como objetivo listar, descrever e exemplificar os endpoints de todas as APIs do projeto de forma a facilitar o consumo da API pelos clientes.
Para facilitar o teste e consumo da API em ambiente de desenvolvimento, foi fornecido um arquivo com as requisições e um arquivo com o ambiente no formato Postman no diretório [/recursos-extras](https://github.com/samuelclinton/techchallenge/tree/main/recursos-extras).

### **Índice**
🌎 [Endereços](#-api-de-endereços) \
📺 [Eletrodomésticos](#-api-de-eletrodomésticos) \
👤 [Pessoas](#-api-de-pessoas)

<br />

## 🌎 API de Endereços
A API de endereços consiste em um conjunto de endpoints para gerenciar os endereços de instalação dos sensores. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de endereços. <br /><br />

📌
### 🔵 **GET** /v1/enderecos
\
Lista todos os endereços contidos na base de dados. Aceita filtragem dos resultados por um ou mais dos seguintes campos: rua, numero, bairro, cidade e estado através de query params.

Status de retorno esperado:  **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />


    [
    	{
    		"codigo": "e10950b6-d0b1-4cb8-8d2e-b6feb86743f3",
    		"rua": "Rua 9",
    		"numero": "23A",
    		"bairro": "COHAB",
    		"cidade": "Guarulhos",
    		"estado": "SP",
			"responsavel": {
				"codigo": "bc9f920a-f8c3-4ff6-8f6f-6ed011e5edf1",
				"nome": "Maria da Paz"
        	}
    	},
    	{
    		"codigo": "799a9826-0d01-460a-b34f-85d2bf7249ec",
    		"rua": "Rua 12",
    		"numero": "28B",
    		"bairro": "Jardim Paulista",
    		"cidade": "Osasco",
    		"estado": "SP",
			"responsavel": {
				"codigo": "bc9f920a-f8c3-4ff6-8f6f-6ed011e5edf1",
				"nome": "Maria da Paz"
        	}
    	}
    ]
<br />

📌
### 🔵 **GET** /v1/enderecos/{codigo}
\
Busca um endereço através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />

    {
	    "codigo": "799a9826-0d01-460a-b34f-85d2bf7249ec",
		"rua": "Rua 12",
		"numero": "28B",
		"bairro": "Jardim Paulista",
		"cidade": "Osasco",
		"estado": "SP",
		"responsavel": {
			"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
			"nome": "Dominic Quigley"
    	},
    	"residentes": [
			{
				"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
				"nome": "Dominic Quigley"
			}
    	]
    }
<br />

📌
### 🔵 **GET** /v1/enderecos/{codigoEndereco}/eletrodomesticos
\
Busca os eletrodomésticos associados a um endereço através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />

    [
		{
			"codigo": "9189b5d7-ff8f-4315-8765-04bfc51ebbe9",
			"nome": "Lava-louças apartamento",
			"modelo": "VT-203",
			"potencia": 300.0,
			"voltagem": "V_110",
			"marca": "Brastemp"
		},
		{
			"codigo": "375459fd-b947-485f-8fcf-b12dab472d25",
			"nome": "Lava-louças apartamento",
			"modelo": "VT-203",
			"potencia": 300.0,
			"voltagem": "V_110",
			"marca": "Brastemp"
		}
	]
<br />

📌
### 🟢 **POST** /v1/enderecos/
\
Cria um endereço através dos dados recebidos via JSON no corpo da requisição 

Status de retorno esperado: **201 - CREATED**

#### *Exemplo de payload da requisição* <br /><br />

    {
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP",
		"codigoResponsavel": "a8cfa707-236f-4682-8e63-630cf2db5a4c"
    }
<br />

#### *Exemplo de payload da resposta* <br /><br />

    {
	    "codigo": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP",
		"responsavel": {
			"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
			"nome": "Dominic Quigley"
    	},
    	"residentes": [
			{
				"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
				"nome": "Dominic Quigley"
			}
    	]
    }
<br />

📌
### 🟠 **PUT** /v1/enderecos/{codigo}
\
Altera um endereço através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />

    {
	    "rua": "Rua teste 2",
	    "numero": "24B",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }
<br />

#### *Exemplo de payload da resposta* <br /><br />

    {
	    "codigo": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste 2",
	    "numero": "23B",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP",
		"responsavel": {
			"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
			"nome": "Dominic Quigley"
    	},
    	"residentes": [
			{
				"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
				"nome": "Dominic Quigley"
			}
    	]
    }
<br />

📌
### 🔴  **DELETE** /v1/enderecos/{codigo}
\
Exclui um endereço através do código recebido via parâmetro de URL. Os eletrodomésticos cadastrados no endereço também serão excluídos.

Status de retorno esperado: **204 - NO CONTENT**

<br />

📌
### 🟠 **PUT** /v1/enderecos/{codigo}/residentes
\
Adiciona um residente no endereço através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />

    {
		"codigoPessoa": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6"
	}
<br />

#### *Exemplo de payload da resposta* <br /><br />

    {
	    "codigo": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste 2",
	    "numero": "23B",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP",
		"responsavel": {
			"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
			"nome": "Dominic Quigley"
    	},
    	"residentes": [
			{
				"codigo": "1ef3f0da-08e1-4578-b6a0-06973a171e2a",
				"nome": "Dominic Quigley"
			},
			{
				"codigo": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6",
				"nome": "Maria Joaquina"
			}
    	]
    }
<br />

📌
### 🟠 **DELETE** /v1/enderecos/{codigoEndereco}/residentes/{codigoResidente}
\
Remove um residente do endereço através do código recebido via parâmetro de URL

Status de retorno esperado: **204 - NO CONTENT**

#### *Exemplo de payload da requisição* <br /><br />

    {
		"codigoPessoa": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6"
	}
<br />

<br />

## 📺 API de Eletrodomésticos

A API de eletrodomésticos consiste em um conjunto de endpoints para gerenciar os eletrodomésticos da aplicação. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de eletrodomésticos. <br /><br />

📌
### 🔵 **GET** /v1/eletrodomesticos
\
Lista todos os eletrodomésticos contidos na base de dados. Aceita filtragem dos resultados por um ou mais dos seguintes campos: nome, modelo, potenciaMax, potenciaMin, voltagem e marca através de query params.

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />

    [
		{
			"codigo": "9189b5d7-ff8f-4315-8765-04bfc51ebbe9",
			"nome": "Lava-louças apartamento",
			"modelo": "VT-203",
			"potencia": 300.0,
			"voltagem": "V_110",
			"marca": "Brastemp"
		},
		{
			"codigo": "375459fd-b947-485f-8fcf-b12dab472d25",
			"nome": "Lava-louças apartamento",
			"modelo": "VT-203",
			"potencia": 300.0,
			"voltagem": "V_110",
			"marca": "Brastemp"
		},
		{
			"codigo": "16194751-113f-4093-a803-b39ed4e0fa79",
			"nome": "Lava-louças apartamento",
			"modelo": "VT-203",
			"potencia": 300.0,
			"voltagem": "V_110",
			"marca": "Brastemp"
		}
	]
<br />

📌
### 🔵 **GET** /v1/eletrodomesticos/{codigo}
\
Busca um eletrodomestico através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />

    {
        "codigo": "0fc88683-f39a-44d2-99d8-3c3f8618449b",
        "nome": "Maquina de Lavar",
        "modelo": "Teste",
        "potencia": 1200.0,
        "voltagem": "V_110",
        "marca": "Eletrolux",
		"usuarios": [
			{
				"codigo": "bc9f920a-f8c3-4ff6-8f6f-6ed011e5edf1",
				"nome": "Maria da Paz"
			},
			{
				"codigo": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6",
				"nome": "Maria Joaquina"
			}
    	]
    }
<br />

📌
### 🔵 **GET** /v1/eletrodomesticos/{codigo}/calculo-de-consumo
\
Retorna um relatório de consumo do eletrodoméstico em quilowatt-hora e uma estimativa do custo de operação baseado na tarifa média nacional. O cálculo é baseado na quantidade de minutos em uso que é recebida via o query param "minutosEmUso" como um inteiro.

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />

    {
		"consumo": "7,20 kWh",
		"custoEstimado": "R$ 5,19"
	}
<br />

📌
### 🟢 **POST** /v1/eletrodomesticos
\
Cadastra um eletrodoméstico através dos dados recebidos via JSON no corpo da requisição.
\
O campo **potência** deve conter a potência do produto em watts.
\
O campo **voltagem** deve indicar a voltagem de trabalho do produto: **"V_110"** para 110 volts e **"V_220"** para 220 volts.

Status de retorno esperado: **201 - CREATED**

#### *Exemplo de payload da requisição* <br /><br />

    {
        "nome" : "Maquina de Lavar",
        "modelo" : "Teste",
        "potencia" : 1200.0,
        "voltagem": "V_110",
        "marca" : "Eletrolux",
		"codigoEndereco": "abc88683-f67a-44d2-99d8-3c3f86184dfg"
    }
<br />

#### *Exemplo de payload da resposta* <br /><br />

    {
        "codigo": "0fc88683-f39a-44d2-99d8-3c3f8618449b",
        "nome": "Maquina de Lavar",
        "modelo": "Teste",
        "potencia": 1200.0,
        "voltagem": "V_110",
        "marca": "Eletrolux",
		"usuarios": [
			{
				"codigo": "bc9f920a-f8c3-4ff6-8f6f-6ed011e5edf1",
				"nome": "Maria da Paz"
			},
			{
				"codigo": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6",
				"nome": "Maria Joaquina"
			}
    	]
    }
<br />

📌
### 🟠 **PUT** /v1/eletrodomesticos/{codigo}
\
Altera os dados do eletrodoméstico através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome": "Máquina de lavar",
		"modelo": "VT-2023",
		"potencia": 540.12,
		"voltagem": "V_220",
		"marca": "Electrolux"
	}
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
    "codigo": "16194751-113f-4093-a803-b39ed4e0fa79",
    "nome": "Máquina de lavar",
    "modelo": "VT-2023",
    "potencia": 540.12,
    "voltagem": "V_220",
    "marca": "Electrolux",
    "usuarios": [
			{
				"codigo": "bc9f920a-f8c3-4ff6-8f6f-6ed011e5edf1",
				"nome": "Maria da Paz"
			},
			{
				"codigo": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6",
				"nome": "Maria Joaquina"
			}
		]
	}
<br />

📌
### 🔴 **DELETE** /v1/eletrodomesticos/{codigo}
\
Exclui um eletrodoméstico através do código recebido via parâmetro de URL

Status de retorno esperado: **204 - NO CONTENT**

<br />

<br />

## 👤 API de Pessoas

A API de pessoas consiste em um conjunto de endpoints para gerenciar os usuários que representam os clientes da aplicação. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de pessoas. <br /><br />

📌
### 🔵 **GET** /v1/pessoas
\
Lista todos as pessoas contidas na base de dados. Aceita filtragem dos resultados por um ou mais dos seguintes campos: nome, dataNascimento, genero, cpf e tipoDeUsuario através de query params.

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />
	
	[
		{
			"codigo": "20acaf6c-4c84-4e5d-bf0e-111220f8cca6",
			"nome": "Maria Joaquina",
			"dataNascimento": "1994-08-27",
			"genero": "FEMININO",
			"cpf": "02347782290",
			"tipoDeUsuario": "DEPENDENTE",
			"dataCadastro": "2023-09-04T21:09:00.847184Z"
		},
		{
			"codigo": "bc9f920a-f8c3-4ff6-8f6f-6ed011e5edf1",
			"nome": "Jhonny Cash",
			"dataNascimento": "1994-08-27",
			"genero": "MASCULINO",
			"cpf": "02445221897",
			"tipoDeUsuario": "RESPONSAVEL",
			"dataCadastro": "2023-09-04T21:06:41.406671Z"
		},
		{
			"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
			"nome": "Maria da Paz",
			"dataNascimento": "1994-08-27",
			"genero": "FEMININO",
			"cpf": "52373016656",
			"tipoDeUsuario": "RESPONSAVEL",
			"dataCadastro": "2023-09-04T21:33:20.472141Z"
		}
	]
<br />

📌
### 🔵 **GET** /v1/pessoas/{codigo}
\
Busca uma pessoa através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />
	
	{
		"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
		"nome": "Maria da Paz",
		"dataNascimento": "1994-08-27",
		"genero": "FEMININO",
		"cpf": "52373016656",
		"tipoDeUsuario": "RESPONSAVEL",
		"dataCadastro": "2023-09-04T21:33:20.472141Z"
	}
<br />

📌
### 🔵 **GET** /v1/pessoas/{codigo}/enderecos
\
Lista todos os endereços de uma pessoa através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />
	
	[
		{
			"codigo": "81886e57-4b91-40be-83b7-ac77c325367f",
			"rua": "Rua maranguape",
			"numero": "23A",
			"bairro": "Jardim Paulista",
			"cidade": "Osasco",
			"estado": "SP",
			"responsavel": {
				"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
				"nome": "Maria da Paz"
			}
		},
		{
			"codigo": "043b9761-5383-461b-a50e-cf4f25f6f755",
			"rua": "Rua maranguape",
			"numero": "23A",
			"bairro": "Jardim Paulista",
			"cidade": "Osasco",
			"estado": "SP",
			"responsavel": {
				"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
				"nome": "Maria da Paz"
			}
		}
	]
<br />

📌
### 🔵 **GET** /v1/pessoas/{codigo}/familiares
\
Busca a arvore familiar da pessoa através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />
	
	{
		"codigo": "e01b2203-ca5c-408b-957a-a23c06d1f596",
		"avos": [],
		"pais": [
			{
				"codigo": "bc2f0d84-8c42-4b26-a9de-f421ae4e9611",
				"nome": "Juan Wintheiser",
				"genero": "MASCULINO"
			},
			{
				"codigo": "b3484d8a-a749-43b2-a034-2094fe85a68f",
				"nome": "Judy Erdman",
				"genero": "FEMININO"
			}
		],
		"filhos": [
			{
				"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
				"nome": "Maria da Paz",
				"genero": "FEMININO"
			}
		],
		"netos": []
	}
<br />

📌
### 🟢 **POST** /v1/pessoas
\
Cadastra uma pessoa através dos dados recebidos via JSON no corpo da requisição.

Status de retorno esperado: **201 - CREATED**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome" : "Doyle Hoppe",
		"dataNascimento" : "1989-01-29",
		"genero" : "MASCULINO",
		"cpf" : "268.687.630-19",
		"nivelHierarquicoFamiliar": "PAIS"
	}	
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
		"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
		"nome": "Doyle Hoppe",
		"dataNascimento": "1994-08-03",
		"genero": "FEMININO",
		"cpf": "52373016656",
		"tipoDeUsuario": "RESPONSAVEL",
		"dataCadastro": "2023-09-04T21:33:20.472140700Z"
	}
<br />

📌
### 🟠 **PUT** /v1/pessoas/{codigo}
\
Altera o cadastro da pessoa através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome": "Maria da Paz",
		"dataNascimento": "1994-08-27",
		"genero": "FEMININO"
	}
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
		"codigo": "5d1d4fc3-7726-442e-9805-3d82804f1c2c",
		"nome": "Maria da Paz",
		"dataNascimento": "1994-08-27",
		"genero": "FEMININO",
		"cpf": "52373016656",
		"tipoDeUsuario": "RESPONSAVEL",
		"dataCadastro": "2023-09-04T21:33:20.472141Z"
	}
<br />

📌
### 🔴 **DELETE** /v1/pessoas/{codigo}
\
Exclui uma pessoa através do codigo recebido via parâmetro de URL. Os familiares, endereços e eletrodomésticos relacionados a pessoa também serão excluídos.

Status de retorno esperado: **204 - NO CONTENT**
<br />

📌
### 🟢 **POST** /v1/pessoas/{codigo}/familiares
\
Cadastra um familiar relacionado a pessoa através dos dados recebidos via JSON no corpo da requisição.

Status de retorno esperado: **201 - CREATED**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome": "Bernard Christiansen",
		"dataNascimento": "2006-08-02",
		"genero": "MASCULINO",
		"cpf": "85655579871",
		"nivelHierarquicoFamiliar": "FILHOS"
	}
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
    "codigo": "e4edb59c-879c-4b71-9923-277da54fd789",
    "nome": "Bernard Christiansen",
    "dataNascimento": "2006-08-02",
    "genero": "MASCULINO",
    "cpf": "85655579871",
    "tipoDeUsuario": "DEPENDENTE",
    "dataCadastro": "2023-09-04T21:42:16.601390400Z"
}
<br />
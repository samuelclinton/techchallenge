# Documentação

Esta documentação tem como objetivo listar, descrever e exemplificar os endpoints de todas as APIs do projeto de forma a facilitar o consumo da API pelos clientes.

<br />

## **Índice**
🌎 [Endereços](#🌎-api-de-endereços) \
📺 [Eletrodomésticos](#📺-api-de-eletrodomésticos) \
👤 [Pessoas](#👤-api-de-pessoas)

<br />

---

<br />

## 🌎 API de Endereços
A API de endereços consiste em um conjunto de endpoints para gerenciar os endereços de instalação dos sensores. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de endereços. <br /><br />

📌
### 🔵 **GET** /v1/enderecos
\
Lista todos os endereços contidos na base de dados.

Status de retorno esperado:  **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />


    [
    	{
    		"codigo": "e10950b6-d0b1-4cb8-8d2e-b6feb86743f3",
    		"rua": "Rua 9",
    		"numero": "23A",
    		"bairro": "COHAB",
    		"cidade": "Guarulhos",
    		"estado": "SP"
    	},
    	{
    		"codigo": "799a9826-0d01-460a-b34f-85d2bf7249ec",
    		"rua": "Rua 12",
    		"numero": "28B",
    		"bairro": "Jardim Paulista",
    		"cidade": "Osasco",
    		"estado": "SP"
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
		"estado": "SP"
    }
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
	    "estado": "SP"
    }
<br />

#### *Exemplo de payload da resposta* <br /><br />

    {
	    "codigo": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
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
	    "estado": "SP"
    }
<br />

📌
### 🔴  **DELETE** /v1/enderecos/{codigo}
\
Exclui um endereço através do código recebido via parâmetro de URL

Status de retorno esperado: **204 - NO CONTENT**

<br />

---

<br />

## 📺 API de Eletrodomésticos

A API de eletrodomésticos consiste em um conjunto de endpoints para gerenciar os eletrodomésticos da aplicação. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de eletrodomésticos. <br /><br />

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
        "marca" : "Eletrolux"
    }
<br />

#### *Exemplo de payload da requisição* <br /><br />

    {
        "codigo": "0fc88683-f39a-44d2-99d8-3c3f8618449b",
        "nome": "Maquina de Lavar",
        "modelo": "Teste",
        "potencia": 1200.0,
        "voltagem": "V_110",
        "marca": "Eletrolux"
    }
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
        "marca": "Eletrolux"
    }
<br />

📌
### 🟠 **PUT** /v1/eletrodomesticos/{codigo}
\
Altera os dados do eletrodoméstico através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome" : "Maquina de Lavar",
		"modelo" : "Lava e Seca",
		"potencia" : 1200.0,
		"voltagem": "V_110",
		"marca" : "Eletrolux"
	}
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
		"codigo": "52b6a589-6cce-4de5-bbbf-160c46e71fbf",
		"nome": "Maquina de Lavar",
		"modelo": "Lava e Seca",
		"potencia": 1200.0,
		"voltagem": "V_110",
		"marca": "Eletrolux"
	}
<br />

📌
### 🔴 **DELETE** /v1/eletrodomesticos/{codigo}
\
Exclui um eletrodoméstico através do código recebido via parâmetro de URL

Status de retorno esperado: **204 - NO CONTENT**

<br />

---

<br />

## 👤 API de Pessoas

A API de pessoas consiste em um conjunto de endpoints para gerenciar os usuários que representam os clientes da aplicação. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de pessoas. <br /><br />


📌
### 🟢 **POST** /v1/pessoas
\
Cadastra uma pessoa através dos dados recebidos via JSON no corpo da requisição.
\
O campo **parente** é opcional, e deve ser utilizado quando cadastrando um usuário que deve ser vinculado a um outro cliente já previamente cadastrado. Ele recebe um objeto com o código da pessoa ao qual a nova pessoa deverá ser vinculada.

Status de retorno esperado: **201 - CREATED**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome" : "Eric Moraes",
		"dataNascimento" : "1989-01-29",
		"sexo" : "MASCULINO",
		"cpf" : "268.687.630-19",
		"parente" : {
			"codigo" : "cdd951a2-363c-49db-9e97-e5b5f7c7b176"
		}
	}	
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
		"codigo": "08081da1-0f72-4869-ba16-0c27e8f2e240",
		"nome": "Eric Moraes",
		"dataNascimento": "1989-01-29",
		"sexo": "MASCULINO",
		"cpf": "268.687.630-18",
		"parente": {
				"codigo": "cdd951a2-363c-49db-9e97-e5b5f7c7b176",
				"nome": "João Souza"
		},
		"dataCadastro": "2023-07-03T01:29:07.463381100Z"
	}
<br />

📌
### 🔵 **GET** /v1/pessoas/{codigo}
\
Busca uma pessoa através do código recebido via parâmetro de URL

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da resposta* <br /><br />
	
	{
		"codigo": "cdd951a2-363c-49db-9e97-e5b5f7c7b176",
		"nome": "João Souza",
		"dataNascimento": "1989-01-29",
		"sexo": "MASCULINO",
		"cpf": "108.578.510-63",
		"parente": null,
		"dataCadastro": "2023-07-03T01:26:52.257658Z"
	}
<br />

📌
### 🟠 **PUT** /v1/pessoas/{codigo}
\
Altera o cadastro da pessoa através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Status de retorno esperado: **200 - SUCCESS**

#### *Exemplo de payload da requisição* <br /><br />

	{
		"nome" : "Eric Silva",
		"dataNascimento" : "1989-01-29",
		"sexo" : "MASCULINO",
		"cpf" : "268.687.630-18",
		"parente" : {
			"codigo" : "cdd951a2-363c-49db-9e97-e5b5f7c7b176"
		}
	}
<br />

#### *Exemplo de payload da resposta* <br /><br />

	{
		"codigo": "08081da1-0f72-4869-ba16-0c27e8f2e240",
		"nome": "Eric Silva",
		"dataNascimento": "1989-01-29",
		"sexo": "MASCULINO",
		"cpf": "268.687.630-18",
		"parente": {
				"codigo": "cdd951a2-363c-49db-9e97-e5b5f7c7b176",
				"nome": "João Souza"
		},
		"dataCadastro": "2023-07-03T01:29:07.463381Z"
	}
<br />

📌
### 🔴 **DELETE** /v1/pessoas/{codigo}
\
Exclui uma pessoa através do codigo recebido via parâmetro de URL

Status de retorno esperado: **204 - NO CONTENT**
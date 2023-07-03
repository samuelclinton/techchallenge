# Documentação
Esta documentação tem como objetivo listar, descrever e exemplificar os endpoints de todas as APIs do projeto de forma a facilitar o consumo da API pelos clientes.

## API de Endereços
A API de endereços consiste em um conjunto de endpoints para gerenciar os endereços de instalação dos sensores. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de endereços.

### :pushpin: /v1/enderecos
Lista todos os endereços contidos na base de dados.

Verbo HTTP: 🔵 **GET**

Status de retorno esperado:  **200 - SUCCESS**
#### Exemplo de payload do retorno

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

---

### :pushpin: /v1/enderecos/{codigo}
Busca um endereço através do código recebido via parâmetro de URL

Verbo HTTP: 🔵 **GET**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da resposta

    {
	    "codigo": "799a9826-0d01-460a-b34f-85d2bf7249ec",
		"rua": "Rua 12",
		"numero": "28B",
		"bairro": "Jardim Paulista",
		"cidade": "Osasco",
		"estado": "SP"
    }

---

### :pushpin: /v1/enderecos/
Cria um endereço através dos dados recebidos via JSON no corpo da requisição 

Verbo HTTP:  🟢 **POST**

Status de retorno esperado: **201 - CREATED**

#### Exemplo de payload da requisição

    {
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }
#### Exemplo de payload da resposta

    {
	    "codigo": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }

---

### :pushpin: /v1/enderecos/{codigo}
Altera um endereço através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Verbo HTTP: 🟠 **PUT**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da requisição

    {
	    "rua": "Rua teste 2",
	    "numero": "24B",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }
#### Exemplo de payload da resposta

    {
	    "codigo": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste 2",
	    "numero": "23B",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }
  
---
 
### :pushpin: /v1/enderecos/{codigo}
Exclui um endereço através do código recebido via parâmetro de URL

Verbo HTTP: :red_circle:  **DELETE**

Status de retorno esperado: **204 - NO CONTENT**

---


---

## API de Eletrodomesticos

A API de eletrodomesticos consiste em um conjunto de endpoints para gerenciar os eletrodomesticos da aplicação. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de eletrodomesticos.

### :pushpin: /v1/eletrodomesticos

Cadastra um eletrodomestico através dos dados recebidos via JSON no corpo da requisição.

Verbo HTTP: 🔵 **POST**

Status de retorno esperado: **201 - CREATED**

#### Exemplo de payload da requisição

    {
        "nome" : "Maquina de Lavar",
        "modelo" : "Teste",
        "potencia" : 1200.0,
        "voltagem": "V_110",
        "marca" : "Eletrolux"
    }

#### Exemplo de payload do retorno

    {
        "codigo": "0fc88683-f39a-44d2-99d8-3c3f8618449b",
        "nome": "Maquina de Lavar",
        "modelo": "Teste",
        "potencia": 1200.0,
        "voltagem": "V_110",
        "marca": "Eletrolux"
    }
---

### :pushpin: /v1/eletrodomesticos/{codigo}

Busca um eletrodomestico através do codigo recebido via query params (parâmetro de URL)

Verbo HTTP: 🔵 **GET**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da resposta

    {
        "codigo": "0fc88683-f39a-44d2-99d8-3c3f8618449b",
        "nome": "Maquina de Lavar",
        "modelo": "Teste",
        "potencia": 1200.0,
        "voltagem": "V_110",
        "marca": "Eletrolux"
    }

### :pushpin: /v1/eletrodomesticos/{codigo}

Altera os dados do eletrodomestico através do codigo recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Verbo HTTP: 🟠 **PUT**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da requisição

	{
		"nome" : "Maquina de Lavar",
		"modelo" : "Lava e Seca",
		"potencia" : 1200.0,
		"voltagem": "V_110",
		"marca" : "Eletrolux"
	}

#### Exemplo de payload da resposta

	{
		"codigo": "52b6a589-6cce-4de5-bbbf-160c46e71fbf",
		"nome": "Maquina de Lavar",
		"modelo": "Lava e Seca",
		"potencia": 1200.0,
		"voltagem": "V_110",
		"marca": "Eletrolux"
	}

### :pushpin: /v1/eletrodomesticos/{codigo}

Exclui um eletrodomestico através do codigo recebido via parâmetro de URL

Verbo HTTP: :red_circle: **DELETE**

Status de retorno esperado: **204 - NO CONTENT**

---

## API de Pessoas

A API de pessoas consiste em um conjunto de endpoints para gerenciar os usuários que administrar a aplicação. Ela dispõe de métodos para criação, exclusão, edição, busca e listagem de endereços.

### :pushpin: /v1/pessoas

Cadastra uma pessoa através dos dados recebidos via JSON no corpo da requisição.

Verbo HTTP: 🔵 **POST**

Status de retorno esperado: **201 - CREATED**

#### Exemplo de payload da requisição

Parentesco é um campo opcional, pois ele realizar o vinculo ao usuário master através do código de cadastro.

	{
		"nome" : "Eric Moraes",
		"dataNascimento" : "1989-01-29",
		"sexo" : "MASCULINO",
		"cpf" : "268.687.630-18",
		"parente" : {
			"codigo" : "cdd951a2-363c-49db-9e97-e5b5f7c7b176"
		}
	}	

#### Exemplo de payload do retorno

	{
		"codigo": "08081da1-0f72-4869-ba16-0c27e8f2e240",
		"nome": "Eric Moraes",
		"dataNascimento": "1989-01-29",
		"sexo": "MASCULINO",
		"cpf": "268.687.630-18",
		"parente": {
				"codigo": "cdd951a2-363c-49db-9e97-e5b5f7c7b176",
				"nome": "Joao Souza"
		},
		"dataCadastro": "2023-07-03T01:29:07.463381100Z"
	}

---

### :pushpin: /v1/pessoas/{codigo}

Busca uma pessoa através do cpf recebido via query params (parâmetro de URL)

Verbo HTTP: 🔵 **GET**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da resposta
	
	{
		"codigo": "cdd951a2-363c-49db-9e97-e5b5f7c7b176",
		"nome": "Joao Souza",
		"dataNascimento": "1989-01-29",
		"sexo": "MASCULINO",
		"cpf": "108.578.510-63",
		"parente": null,
		"dataCadastro": "2023-07-03T01:26:52.257658Z"
	}

### :pushpin: /v1/pessoas/{codigo}

Altera o cadastro da pessoa através do código recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

Verbo HTTP: 🟠 **PUT**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da requisição

	{
		"nome" : "Eric Silva",
		"dataNascimento" : "1989-01-29",
		"sexo" : "MASCULINO",
		"cpf" : "268.687.630-18",
		"parente" : {
			"codigo" : "cdd951a2-363c-49db-9e97-e5b5f7c7b176"
		}
	}

#### Exemplo de payload da resposta

	{
		"codigo": "08081da1-0f72-4869-ba16-0c27e8f2e240",
		"nome": "Eric Silva",
		"dataNascimento": "1989-01-29",
		"sexo": "MASCULINO",
		"cpf": "268.687.630-18",
		"parente": {
				"codigo": "cdd951a2-363c-49db-9e97-e5b5f7c7b176",
				"nome": "Joao Souza"
		},
		"dataCadastro": "2023-07-03T01:29:07.463381Z"
	}

---

### :pushpin: /v1/pessoas/{codigo}

Exclui uma pessoa através do codigo recebido via parâmetro de URL

Verbo HTTP: :red_circle: **DELETE**

Status de retorno esperado: **204 - NO CONTENT**

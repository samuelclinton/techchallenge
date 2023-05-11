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
    		"id": "e10950b6-d0b1-4cb8-8d2e-b6feb86743f3",
    		"rua": "Rua teste",
    		"numero": "23A",
    		"bairro": "Bairro teste",
    		"cidade": "Cidade teste",
    		"estado": "SP"
    	},
    	{
    		"id": "799a9826-0d01-460a-b34f-85d2bf7249ec",
    		"rua": "Rua teste",
    		"numero": "23A",
    		"bairro": "Bairro teste",
    		"cidade": "Cidade teste",
    		"estado": "SP"
    	},
    	{
    		"id": "b4ed7149-4742-4d2f-bc5d-0f9a6a445106",
    		"rua": "Rua teste",
    		"numero": "23A",
    		"bairro": "Bairro teste",
    		"cidade": "Cidade teste",
    		"estado": "SP"
    	}
    ]

---

### :pushpin: /v1/enderecos/{enderecoId}
Busca um endereço através do UUID recebido via parâmetro de URL

Verbo HTTP: 🔵 **GET**

Status de retorno esperado: **200 - SUCCESS**

#### Exemplo de payload da resposta

    {
	    "id": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
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
	    "id": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste",
	    "numero": "23A",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }

---

### :pushpin: /v1/enderecos/{enderecoId}
Altera um endereço através do uuid recebido via parâmetro de URL e dos dados recebidos via JSON no corpo da requisição

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
	    "id": "a8cfa707-236f-4682-8e63-630cf2db5a4c",
	    "rua": "Rua teste 2",
	    "numero": "23B",
	    "bairro": "Bairro teste",
	    "cidade": "Cidade teste",
	    "estado": "SP"
    }
  
---
 
### :pushpin: /v1/enderecos/{enderecoId}
Exclui um endereço através do UUID recebido via parâmetro de URL

Verbo HTTP: :red_circle:  **DELETE**

Status de retorno esperado: **204 - NO CONTENT**

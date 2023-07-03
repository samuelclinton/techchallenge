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


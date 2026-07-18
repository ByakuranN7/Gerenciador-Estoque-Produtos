# Projeto JDBC - Gestão de Produtos

## Especificação do Projeto

Este projeto implementa um sistema simples de gerenciamento de produtos utilizando **JDBC** e **SQLite**, permitindo realizar operações de cadastro, consulta, atualização e exclusão de registros em um banco de dados.

---

# Classes e Métodos

## 1. ConexaoDB

Responsável por estabelecer conexões com o banco de dados.

### Métodos

- `conectar()`
  - Conecta ao banco de dados SQLite.

- `conectarGenerico(String url, String usuario, String senha)`
  - Conecta ao banco de dados utilizando a URL, usuário e senha fornecidos (para utilizar com outros BD).

---

## 2. CriadorTabela

Responsável por criar a tabela de produtos.

### Métodos

- `main()`
  - Conecta ao banco de dados e cria a tabela `produtos`.

---

## 3. Produto

Classe que representa um produto.

### Construtores

- `Produto(String nome, int quantidade, double preco, String status)`
  - Inicializa um produto com seus atributos.

- `Produto()`
  - Construtor padrão.

### Métodos

#### ID

- `getId()`
- `setId(int id)`

#### Nome

- `getNome()`
- `setNome(String nome)`

#### Quantidade

- `getQuantidade()`
- `setQuantidade(int quantidade)`

#### Preço

- `getPreco()`
- `setPreco(double preco)`

#### Status

- `getStatus()`
- `setStatus(String status)`

---

## 4. ProdutoDAO (Data Access Object)

Responsável pelas operações de acesso ao banco de dados.

### Criar

- `inserir(Produto produto)`
  - Insere um novo produto no banco.

### Atualizar

- `atualizar(Produto produto)`
  - Atualiza as informações de um produto.

### Excluir

- `excluir(int id)`
  - Exclui um produto pelo ID.

- `excluirTodos()`
  - Remove todos os produtos cadastrados.

### Consultar

- `consultarPorId(int id)`
  - Consulta um produto pelo ID.

- `listarTodos()`
  - Lista todos os produtos cadastrados.

---

## 5. Main

Classe utilizada para demonstrar o funcionamento do sistema.

### Métodos

- `main(String[] args)`
  - Método principal da aplicação.

- `excluirTodos()`
  - Remove todos os produtos.

- `mostrarProdutos(ProdutoDAO produtoDAO)`
  - Exibe todos os produtos cadastrados.

- `inserir(Produto produto)`
  - Insere um novo produto.

- `consultarPorId(int id)`
  - Consulta um produto pelo ID.

- `atualizar(Produto produto)`
  - Atualiza as informações de um produto.

- `excluir(int id)`
  - Exclui um produto pelo ID.
# Sistema de Mercado 🛒

Um sistema simples e eficiente desenvolvido em Java para gerenciamento de um mercado, permitindo o controle de produtos, estoque e vendas.

---

## 🚀 Funcionalidades

* **Gerenciamento de Produtos:** Cadastro, listagem, atualização e remoção de itens.
* **Ordenação dos produtos:** Ordenação dos produtos dentro das categorias por ordem alfabetica, por preço, entre outras ordenações.
* **Sistema de Relatórios:** Relatório sobre os mais vendidos, menos vendidos, menor estoque, mais caro, e muito mais.
* **Persistência de Dados:** Armazenamento local estruturado no arquivo `dados_mercado.txt`.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (versão 17 ou superior recomendada)
* **Banco de Dados:** Arquivo de texto plano (`dados_mercado.txt`)

---

## 📦 Como Executar o Projeto

### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/)
* [Git](https://git-scm.com/)
* Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/Sistema-de-Mercado.git](https://github.com/seu-usuario/Sistema-de-Mercado.git)
2. **Abra o projeto na sua IDE**
    * Importe a pasta raiz do projeto na sua IDE de preferência.
    * Certifique se de que a JDK instalada está configurada no projeto.
3. **Execute o sistema**
    * Localize a classe principal (provavelmente dentro de src/main/java/mercado/) e execute o método main.

---

## 📁 Estrutura de Pastas
```text
Sistema-de-Mercado/
├── src/
│   └── main/
│       └── java/
│           └── mercado/
│               ├── controller/            # Controlador do mercado
│               │   └── MercadoController.java
│               ├── estrutura/             # Estrutura de listas duplas encadeadas circulares e multilistas
│               │   ├── NoCategoria.java
│               │   └── NoProduto.java
│               ├── model/                 # Definição e modelo dos produtos
│               │   └── Produto.java
│               ├── service/               # Funções e regras de negócio do sistema
│               │   ├── CategoriaService.java
│               │   ├── OrdenacaoService.java
│               │   ├── ProdutoService.java
│               │   └── RelatorioService.java
│               ├── ui/                    # Front-end / Interface do usuário (abstraído)
│               └── Main.java              # Classe principal responsável por rodar o programa
├── dados_mercado.txt                      # Arquivo onde os dados são salvos
└── .gitignore                             # Arquivos ignorados pelo Git 
```

---

## ✒️ Autores
* 1-Alan Cristian de Jesus Dias;
* 2-Aldey Breno de Melo Morais;
* 3-João Pedro Costa Cruz;
* 4-Wagner Kauê Martins dos Santos.
# Escape Room - Java

Bem-vindo ao meu projeto da disciplina de Programação 3 (P3)! Este é um jogo de "Escape Room" em modo texto, desenvolvido em duas etapas (AB1 e AB2), que evoluiu de um script simples para um projeto (um pouco) maior utilizando conceitos de Programação Orientada a Objetos em Java.

## 📝 Descrição

O jogador acorda em uma **Câmara de Pedra** após o chão de ruínas antigas ceder. O objetivo inicial é encontrar uma saída.

A jornada não termina na primeira porta. O jogador deverá navegar por uma segunda área, o **Salão das Sombras**, resolvendo uma série de enigmas que envolvem luz, chaves e mecanismos escondidos para, finalmente, encontrar a saída das ruínas.

## 🚀 Como Jogar

O jogo é controlado por comandos de texto digitados diretamente no terminal.

### Compilação e Execução

Como o projeto utiliza pacotes (`jogo`, `jogo.objetos`, `jogo.excecoes`), a compilação e execução são feitas da seguinte forma:

1. Abra um terminal na pasta raiz do projeto (a pasta que contém a pasta `jogo`, por exemplo, `src/`).
2. Compile o projeto (o compilador Java encontrará e compilará todas as classes necessárias):
   ```sh
   javac jogo/Jogo.java
   ```
3. Execute o jogo (note o uso do nome completo do pacote para a classe `main`):
   ```sh
   java jogo.Jogo
   ```

### Comandos Disponíveis

* **`olhar`** : Mostra a descrição da sala e os objetos presentes.
* **`olhar [nome do objeto]`** : Descreve um objeto específico que está na sala ou no seu inventário.
* **`pegar [nome do objeto]`** : Adiciona um item ao seu inventário.
* **`usar [nome do objeto]`** : Interage com um objeto no cenário (ex: `usar tapete`).
* **`usar [item] em [alvo]`** : Usa um item do seu inventário em um objeto na sala ou em outro item no inventário (ex: `usar chave em porta`, `usar pederneira em tocha`).
* **`inventario`** : Lista todos os itens que você está carregando.

## 📂 Arquitetura e Conceitos (AB2)

A versão inicial (AB1) usava uma estrutura simples. Esta versão (AB2) foi profundamente refatorada para ser mais escalável e para demonstrar conceitos avançados de POO.

### Organização em Pacotes

O projeto agora está organizado em três pacotes principais para separar as responsabilidades:

* `jogo`: Contém as classes centrais que gerenciam o estado do jogo (`Jogo`, `Jogador`, `Sala`).
* `jogo.objetos`: Contém a superclasse `ObjetoInterativo` e todas as suas classes-filhas (ex: `ItemPegavel`, `Porta`, `Baú`, `Tapete`, `Arandela`, `Tocha`).
* `jogo.excecoes`: Contém as exceções customizadas para tratamento de erros (`ComandoInvalidoException`, `ObjetoNaoEncontradoException`, `InventarioCheioException`).

### Conceitos de POO Aplicados

* **Herança:** A classe `ObjetoInterativo` atua como uma superclasse abstrata. Todas as entidades do jogo (`ItemPegavel`, `ObjetoFixo`, `Porta`, `Tapete`, `Baú`, etc.) herdam dela. Isso permite que o jogo trate todos os objetos de forma uniforme.
* **Polimorfismo:** Este é o pilar da nova arquitetura. Em vez de a classe `Jogo` ter um `switch` gigante para decidir o que fazer, ela apenas chama métodos polimórficos como `interagir()` ou `usarCom()`. Cada classe-filha (`Porta`, `Tapete`, `Tocha`) implementa seu próprio comportamento (sobrescrevendo os métodos), e o Java decide em tempo de execução qual lógica específica executar.
* **Coleções (Collections):** Os `Arrays` de tamanho fixo da versão AB1 (usados para o inventário e para os objetos na sala) foram substituídos por `ArrayList` do Java Collections Framework. Isso permite um gerenciamento dinâmico de itens, sem limite fixo.
* **Tratamento de Exceções:** O fluxo de comandos do jogador é gerenciado por um bloco `try...catch`. Ações inválidas (ex: "pegar item_que_nao_existe", "usar comando_invalido") lançam exceções customizadas, o que limpa a lógica principal do jogo e separa o fluxo de erro do fluxo de sucesso.

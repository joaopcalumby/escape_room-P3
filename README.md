
# Escape Room em Java

Bem-vindo ao meu primeiro projeto em Java! Este é um jogo de "Escape Room" simples, baseado em texto, desenvolvido como parte dos meus estudos na disciplina de Programação 3 (P3).

## 📝 Descrição

O jogador acorda desorientado em uma câmara de pedra trancada. O objetivo é simples: interagir com o cenário, examinar objetos, coletar itens e resolver um pequeno enigma para encontrar a chave e escapar da sala.

## 🚀 Como Jogar

Para jogar, basta compilar e executar o arquivo `Jogo.java`. O jogo é controlado por comandos de texto digitados diretamente no terminal.

### Comandos Disponíveis

* **`olhar`** : Mostra a descrição da sala e os objetos presentes.
* **`olhar [nome do objeto]`** : Descreve um objeto específico que está na sala ou no seu inventário.
* **`pegar [nome do objeto]`** : Adiciona um item ao seu inventário.
* **`usar [nome do objeto]`** : Interage com um objeto no cenário.
* **`usar [item do inventário] em [alvo na sala]`** : Usa um item do seu inventário em um objeto na sala para resolver enigmas.
* **`inventario`** : Lista todos os itens que você está carregando.

## 📂 Estrutura do Projeto

O projeto é construído em Java puro e utiliza conceitos básicos de Programação Orientada a Objetos. Ele é dividido em quatro classes principais:

* **`Jogo.java`** : A classe principal que contém o método `main`. É responsável por inicializar o jogo, gerenciar o loop principal e processar os comandos inseridos pelo jogador.
* **`Sala.java`** : Representa o ambiente do jogo. Armazena a descrição da sala e todos os objetos interativos contidos nela. Também gerencia a lógica do enigma principal.
* **`Jogador.java`** : Representa o jogador. Guarda o nome e o inventário, permitindo adicionar e consultar itens coletados.
* **`ObjetoInterativo.java`** : Define os objetos com os quais o jogador pode interagir. Cada objeto tem um nome, uma descrição e uma propriedade que define se ele pode ser coletado (`pegavel`).

## 👨‍💻 Sobre Este Projeto

Este é o meu primeiro projeto desenvolvido em Java, com o objetivo de aplicar os conhecimentos adquiridos na matéria de Programação 3. Foi um ótimo exercício para praticar a criação de classes, objetos, métodos e a lógica de um programa interativo.

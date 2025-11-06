package Jogo;

import java.util.Scanner;

import Jogo.Exceptions.*;
import Jogo.Objetos.*;

public class Jogo {
    private Jogador jogador;
    private Sala salaAtual;
    private boolean jogoAtivo;
    
    private Scanner scanner; 

    public Jogo() {
        this.scanner = new Scanner(System.in); 
        
        System.out.print("\n=========================================================================================\n");
        System.out.println("Bem-vindo(a) à Fuga das Ruínas!");
        System.out.print("Como gostaria de ser chamado? > ");
        
        String nomeJogador = this.scanner.nextLine();
        
        this.jogador = new Jogador(nomeJogador,  5);
        this.salaAtual = criarFase1();
        this.jogoAtivo = true;
        
        System.out.print("\n=========================================================================================\n");
        System.out.println("\nOla, " + jogador.getNome() + "!\n---\nVocê estava explorando ruínas antigas quando o chao cedeu.");
        System.out.println("Você acorda desorientado e percebe que se encontra em uma " + salaAtual.getNome() + ".");
        System.out.println("Seu objetivo e simples: ESCAPE!");
    }
    
    private Sala criarFase1() {
        Sala sala = new Sala("Câmara de Pedra", 
            "Você está em uma sala fria com paredes de pedra, fracamente iluminada por uma\n" +
            "tocha bruxuleante na parede. A luz mal alcança os cantos.\n" +
            "No centro, há uma MESA de madeira com um BILHETE em cima dela.\n" +
            "Ao norte, uma grande PORTA de ferro.\n" +
            "No chao, um TAPETE empoeirado.");
            
        sala.adicionarObjeto(new Porta("porta", "\n---\nÉ uma porta de ferro maciço. Parece bem trancada."));
        sala.adicionarObjeto(new ObjetoFixo("mesa", "\n---\nUma mesa de madeira. Sobre ela, há um BILHETE."));
        sala.adicionarObjeto(new ItemPegavel("bilhete", "\n---\nNo bilhete amarelado está escrito: 'Onde o conforto repousa, o caminho se revela.'"));
        sala.adicionarObjeto(new Tapete("tapete", "\n---\nUm tapete velho. Parece esconder algo."));
        return sala;
    }
    
    private Sala criarFase2() {
        Sala sala = new Sala("Salao das Sombras",
            "A sala está muito escura. A única luz vem da porta atrás de você, que se fechou.\n" +
            "A medida que seus olhos se acostumam, você distingue três coisas:\n" +
            "Uma ARANDELA de ferro na parede ao lado.\n" +
            "Um BAU de madeira fechado no canto.\n" +
            "Uma grande PORTA_FINAL de pedra do outro lado do salão.");
            
        sala.adicionarObjeto(new Arandela("arandela", "\n---\nUma arandela de ferro... Parece ser bem antiga e está apagada."));
        sala.adicionarObjeto(new Bau("bau", "\n---\nUm baú de madeira reforçado com ferro. Está trancado."));
        sala.adicionarObjeto(new ObjetoFixo("porta_final", "\n---\nUma porta de pedra maciça, coberta de runas apagadas."));
        sala.adicionarObjeto(new ObjetoFixo("porta_de_ferro", "\n---\nA porta pela qual você veio. Ela se fechou e nao parece ter maçaneta deste lado."));
    
        return sala;
    }


    public void iniciar() {

        while (this.jogoAtivo) {
            this.salaAtual.mostrarSala();
            System.out.print("\nO que voce gostaria de fazer?\n- Pegar objeto\n- Usar objeto\n- Olhar objeto\n- Acessar inventario\n> ");
            
            String comando = this.scanner.nextLine().toLowerCase();

            try {
                processarComando(comando);
                if (this.salaAtual.getNome().equals("Câmara de Pedra") && this.salaAtual.isSaidaLiberada()) {
                    transicaoFase2();
                }
                else if (this.salaAtual.getNome().equals("Salao das Sombras") && this.salaAtual.isSaidaLiberada()) {
                    vitoria();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        this.scanner.close(); 
        System.out.println("\nFIM DE JOGO.\n");
    }
    
    private void transicaoFase2() {
        System.out.println("\n---\nA porta de ferro se abre com um rangido alto, revelando um corredor escuro...");
        System.out.println("Você entra...");
        this.salaAtual = criarFase2();
        System.out.println("Assim que você pisa no " + this.salaAtual.getNome() + ", a porta de ferro atrás de você se fecha com um ESTRONDO!");
    }
    
    private void vitoria() {
        System.out.println("\nParabens, " + jogador.getNome() + "! A porta de pedra se abre, revelando a luz do sol.\n" +
                           "Você escapou das ruínas!");
        this.jogoAtivo = false;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciar();
    }

    
    public void processarComando(String comando) 
        throws ComandoInvalidoException, ObjetoNaoEncontradoException, InventarioCheioException {
        
        String[] partesDoComando = comando.toLowerCase().split(" ");
        String acao = partesDoComando[0];

        switch (acao) {
            case "olhar":
                if (partesDoComando.length == 1) {
                    this.salaAtual.mostrarSala();
                } else {
                    String nomeDoObjeto = partesDoComando[1];
                    ObjetoInterativo objeto;
                    try {
                        objeto = this.salaAtual.buscarObjeto(nomeDoObjeto);
                    } catch (ObjetoNaoEncontradoException e) {
                        objeto = this.jogador.buscarItem(nomeDoObjeto);
                    }
                    System.out.println(objeto.getDescricao());
                }
                break;

            case "pegar":
                if (partesDoComando.length == 1) {
                    throw new ComandoInvalidoException("---\nPegar o que?");
                } 
                
                String nomeDoObjeto = partesDoComando[1];
                ObjetoInterativo objeto = this.salaAtual.buscarObjeto(nomeDoObjeto);

                if (objeto instanceof ItemPegavel) {
                    this.jogador.pegarItem(objeto);
                    this.salaAtual.removerObjeto(objeto);
                    System.out.println("\n---\nVoce pegou: " + objeto.getNome());
                    if (objeto.getNome().equalsIgnoreCase("bilhete")) {
                        this.salaAtual.setDescricao(
                            "Você está na câmara de pedra fracamente iluminada.\n" +
                            "No centro, há uma MESA de madeira, agora vazia.\n" +
                            "Ao norte, a PORTA de ferro.\n" +
                            "No chao, o TAPETE empoeirado."
                        );
                    }
                } else {
                    System.out.println("\n---\nVocê nao pode pegar '" + objeto.getNome() + "'.");
                }
                break;

            case "usar":
                if (partesDoComando.length == 2) {
                    String nomeDoAlvo = partesDoComando[1];
                    ObjetoInterativo alvo;
                    try {
                        alvo = this.salaAtual.buscarObjeto(nomeDoAlvo);
                    } catch (ObjetoNaoEncontradoException e) {
                        alvo = this.jogador.buscarItem(nomeDoAlvo);
                    }
                    
                    System.out.println(alvo.interagir(this.salaAtual));

                } else if (partesDoComando.length == 4 && partesDoComando[2].equals("em")) {
                    String nomeDoItem = partesDoComando[1];
                    String nomeDoAlvo = partesDoComando[3];

                    ObjetoInterativo item = this.jogador.buscarItem(nomeDoItem);
                    
                    ObjetoInterativo alvo;
                    try {
                        alvo = this.salaAtual.buscarObjeto(nomeDoAlvo);
                    } catch (ObjetoNaoEncontradoException e) {
                        alvo = this.jogador.buscarItem(nomeDoAlvo);
                    }

                    if (!(item instanceof ItemPegavel)) {
                        throw new ComandoInvalidoException("Algo deu errado. " + item.getNome() + " nao é um item.");
                    }

                    System.out.println(alvo.usarCom((ItemPegavel) item, this.salaAtual, this.jogador));

                } else {
                    throw new ComandoInvalidoException("---\nUse o formato: 'usar <objeto>' ou 'usar <item> em <alvo>'\n");
                }
                break;

            case "inventario":
                this.jogador.mostrarInventario();
                break;

            default:
                throw new ComandoInvalidoException("\nComando desconhecido. Tente novamente.\n");
        }
    }
}
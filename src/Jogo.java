import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n=========================================================================================\n");
        System.out.println("Bem-vindo(a) à Escape Room!");
        System.out.print("Como gostaria de ser chamado? > ");
            String nomeJogador = scanner.nextLine();
        Jogador jogador = new Jogador(nomeJogador,  5);
        System.out.print("\n=========================================================================================\n");

        Sala sala = new Sala("Camara de Pedra");

        System.out.println("\nOla, " + jogador.getNome() + "!\n---\nVocê acorda desorientado e percebe que se encontra em uma " + sala.getNome() + ".");
        System.out.println("Seu objetivo e simples: ESCAPE!");

        while (!sala.isSaidaLiberada()) {
            sala.mostrarSala();
            System.out.print("\nO que voce gostaria de fazer?\n- Pegar objeto\n- Usar objeto\n- Olhar objeto\n- Acessar inventario\n> ");
            String comando = scanner.nextLine().toLowerCase();

            processarComando(comando, jogador, sala);
        }

        System.out.println("\n---\nParabens, " + jogador.getNome() + "! Ao entrar na fechadura, a chave gira e você escuta um clique.\nAlguns segundos depois, a porta de ferro se abre.\nParabéns! Você escapou!");

        scanner.close();
    }

    
    public static void processarComando(String comando, Jogador jogador, Sala sala) {
        String[] partesDoComando = comando.toLowerCase().split(" ");
        String acao = partesDoComando[0];

        switch (acao) {
            case "olhar":
                if (partesDoComando.length == 1) {
                    sala.mostrarSala();
                } else {
                    String nomeDoObjeto = partesDoComando[1];
                    ObjetoInterativo objeto = sala.buscarObjeto(nomeDoObjeto);

                    if (objeto != null) {
                        System.out.println(objeto.getDescricao());
                    } else {
                        objeto = jogador.buscarItem(nomeDoObjeto);
                        if (objeto != null) {
                            System.out.println(objeto.getDescricao());
                        } else {
                            System.out.println("---\nIsso nao parece estar por aqui.");
                        }
                    }
                }
                break;

            case "pegar":
                if (partesDoComando.length == 1) {
                    System.out.println("---\nPegar o que?");
                } else {
                    String nomeDoObjeto = partesDoComando[1];
                    ObjetoInterativo objeto = sala.buscarObjeto(nomeDoObjeto);

                    if (objeto == null) {
                        System.out.println("---\nNao ha nenhum '" + nomeDoObjeto + "' aqui.");
                    } else {
                        if (objeto.isPegavel()) {
                            if (jogador.pegarItem(objeto)) {
                                sala.removerObjeto(objeto);
                                System.out.println("---\nVoce pegou: " + objeto.getNome());
                            } else {
                                System.out.println("---\nSeu inventario esta cheio!");
                            }
                        } else {
                            System.out.println("---\nVocê nao pode pegar '" + objeto.getNome() + "'.");
                        }
                    }
                }
                break;

            case "usar":
                if (partesDoComando.length == 2) {
                    String nomeDoAlvo = partesDoComando[1];

                    if (nomeDoAlvo.equalsIgnoreCase("bilhete")) {
                        ObjetoInterativo bilhete = jogador.buscarItem("bilhete");
                        if (bilhete != null) {
                            System.out.println("---\nVocê lê o bilhete: " + bilhete.getDescricao());
                        } else {
                            System.out.println("---\nVocê precisa pegar o bilhete para poder lê-lo.");
                        }
                    } else if (nomeDoAlvo.equalsIgnoreCase("tapete")) {
                        ObjetoInterativo tapete = sala.buscarObjeto("tapete");
                        if (tapete != null) {
                            sala.revelarChave();
                        } else {
                            System.out.println("---\nNão há um tapete aqui.");
                        }
                    } else {
                        System.out.println("---\nVocê nao pode usar '" + nomeDoAlvo + "' dessa forma.");
                    }

                } else if (partesDoComando.length == 4 && partesDoComando[2].equals("em")) {
                    String nomeDoItem = partesDoComando[1];
                    String nomeDoAlvo = partesDoComando[3];

                    ObjetoInterativo item = jogador.buscarItem(nomeDoItem);
                    ObjetoInterativo alvo = sala.buscarObjeto(nomeDoAlvo);

                    if (item == null) {
                        System.out.println("---\nVocê não tem '" + nomeDoItem + "' no seu inventario.");
                    } else if (alvo == null) {
                        System.out.println("---\nNao ha '" + nomeDoAlvo + "' na sala para usar isso.");
                    } else {
                        if (sala.resolverEnigma(item.getNome(), alvo.getNome())) {
                            System.out.println("---\nFuncionou!\n");
                        } else {
                            System.out.println("---\nIsso nao parece ter efeito.\n");
                        }
                    }
                } else {
                    System.out.println("---\nUse o formato: 'usar <objeto>' ou 'usar <item> em <alvo>'\n");
                }
                break;

            case "inventario":
                jogador.mostrarInventario();
                break;

            default:
                System.out.println("\nComando desconhecido. Tente novamente.\n");
                break;
        }
    }
}


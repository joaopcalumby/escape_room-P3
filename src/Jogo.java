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

            try {
                processarComando(comando, jogador, sala);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\n---\nParabens, " + jogador.getNome() + "! Ao entrar na fechadura, a chave gira e você escuta um clique.\nAlguns segundos depois, a porta de ferro se abre.\nParabéns! Você escapou!");

        scanner.close();
    }

    public static void processarComando(String comando, Jogador jogador, Sala sala) 
        throws ComandoInvalidoException, ObjetoNaoEncontradoException, InventarioCheioException {
        
        String[] partesDoComando = comando.toLowerCase().split(" ");
        String acao = partesDoComando[0];

        switch (acao) {
            case "olhar":
                if (partesDoComando.length == 1) {
                    sala.mostrarSala();
                } else {
                    String nomeDoObjeto = partesDoComando[1];
                    ObjetoInterativo objeto;
                    try {
                        objeto = sala.buscarObjeto(nomeDoObjeto);
                    } catch (ObjetoNaoEncontradoException e) {
                        objeto = jogador.buscarItem(nomeDoObjeto);
                    }
                    System.out.println(objeto.getDescricao());
                }
                break;

            case "pegar":
                if (partesDoComando.length == 1) {
                    throw new ComandoInvalidoException("---\nPegar o que?");
                } 
                
                String nomeDoObjeto = partesDoComando[1];
                ObjetoInterativo objeto = sala.buscarObjeto(nomeDoObjeto);

                if (objeto instanceof ItemPegavel) {
                    jogador.pegarItem(objeto);
                    sala.removerObjeto(objeto);
                    System.out.println("---\nVoce pegou: " + objeto.getNome());
                } else {
                    System.out.println("---\nVocê nao pode pegar '" + objeto.getNome() + "'.");
                }
                break;

            case "usar":
                if (partesDoComando.length == 2) {
                    String nomeDoAlvo = partesDoComando[1];
                    
                    ObjetoInterativo alvo;
                    try {
                        alvo = sala.buscarObjeto(nomeDoAlvo);
                    } catch (ObjetoNaoEncontradoException e) {
                        alvo = jogador.buscarItem(nomeDoAlvo);
                    }
                    
                    System.out.println(alvo.interagir(sala));

                } else if (partesDoComando.length == 4 && partesDoComando[2].equals("em")) {
                    String nomeDoItem = partesDoComando[1];
                    String nomeDoAlvo = partesDoComando[3];

                    ObjetoInterativo item = jogador.buscarItem(nomeDoItem);
                    ObjetoInterativo alvo = sala.buscarObjeto(nomeDoAlvo);

                    if (!(item instanceof ItemPegavel)) {
                        throw new ComandoInvalidoException("Algo deu errado.");
                    }

                    System.out.println(alvo.usarCom((ItemPegavel) item, sala));

                } else {
                    throw new ComandoInvalidoException("---\nUse o formato: 'usar <objeto>' ou 'usar <item> em <alvo>'\n");
                }
                break;

            case "inventario":
                jogador.mostrarInventario();
                break;

            default:
                throw new ComandoInvalidoException("\nComando desconhecido. Tente novamente.\n");
        }
    }
}
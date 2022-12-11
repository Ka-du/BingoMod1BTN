import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int TAMANHO = 5;
    public static final int LIMITE_MAXIMO = 61;
    private static Random random = new Random();

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("########      ##    ####     ##    ########    ########");
        System.out.println("##      ##    ##    ##  ##   ##    ##          ##    ##");
        System.out.println("########      ##    ##   ##  ##    ##  ####    ##    ##");
        System.out.println("##      ##    ##    ##    ## ##    ##    ##    ##    ##");
        System.out.println("########      ##    ##     ####    ########    ########");

        System.out.println("BEM VINDO AO BINGOMASTER");
        System.out.println("######################################################################");
        System.out.println("Insira o nome dos jogadores seguindo o exemplo (Player1-Player2-Player3) ");
        String nomeJogadores = sc.next();
        String jogadores[] = nomeJogadores.split("-");

        System.out.println("DESEJA JOGAR NO MODO ALEATORIO OU NO MODO MANUAL: ");
        System.out.println("Digite 1 para manual 2 para aleatorio:");
        int opcao = sc.nextInt();


        switch (opcao) {
            case 1:
                System.out.println("voce selecionou o modo manual");
                modomanual(jogadores, sc);
                break;
            case 2:
                System.out.println("voce selecionou o modo aleatorio");
                modoaleatorio(jogadores);
                break;
        }
    }


    public static void modomanual(String[] jogadores, Scanner sc) {

        String cartela[][] = new String[jogadores.length][TAMANHO];
        System.out.println("Digite a cartela dos jogadores seguindo o modelo: 1,2,3,4,5-6,7,8,9,1-2,3,4,5,6");
        String cartelaString = sc.next();
        String[] cartelaintermed = cartelaString.split("[-,]");


        for (int i = 0; i < cartelaintermed.length; i++) {


            System.out.print(cartelaintermed[i]);
            System.out.print(" ");

        }

        for (int i = 0, k = 0; i < jogadores.length; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                cartela[i][j] = cartelaintermed[k];
                k++;
            }
        }

        System.out.print("\n");
        for (int i = 0; i < jogadores.length; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(cartela[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }

        System.out.println("Deseja sortear o bingo de forma manual ou aleatoria?");
        System.out.println("1 para manual 2 para aleatorio");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                sorteioManualManual(cartela, jogadores);
                break;
            case 2:
                sorteioManualAleatorio(cartela, jogadores);
                break;
        }
    }

    public static void modoaleatorio(String [] jogadores) {
        System.out.println("Voce selecionou o modo aleatorio");
        int cartela [][] = new int[jogadores.length][TAMANHO];

        for(int i = 0; i< jogadores.length; i++){
            for (int j = 0; j<TAMANHO; j++){
                cartela[i][j] = random.nextInt(LIMITE_MAXIMO);
            }
        }

        for (int i = 0; i<jogadores.length; i++){
            for (int j = 0; j<TAMANHO; j++){
                System.out.println(cartela[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }

        System.out.println("Deseja sortear o bingo de forma manual ou aleatoria?");
        System.out.println("1 para manual 2 para aleatorio");
        int opcao = sc.nextInt();
        switch(opcao) {
            case 1:
                sorteioAleatorioManual(jogadores, cartela);
                break;
            case 2:
                sorteioLaeatorioAleatorio(jogadores,cartela);
                break;
        }
    }






    public static void sorteioManualManual (String cartela [][],String [] jogadores ) {

        int pontos [] = new int[jogadores.length];
        int rodada = 0;
        int marcacao[][] = new int[jogadores.length][TAMANHO];
        int BINGO = 0;
        String saida;
        String saidau = null;
        do {
            System.out.println("informe os numeros sorteados conforme o modelo: '1,2,3,4,5' ");
            String sort = sc.next();
            String[] sorteio = sort.split(",");
            for (int i = 0; i < sorteio.length; i++) {
                System.out.print(sorteio[i]);
                System.out.print(" ");
            }
            for (int i = 0, k = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    for (k = 0; k < sorteio.length; k++) {
                        if (Objects.equals(cartela[i][j], sorteio[k])) {
                            marcacao[i][j] = 1;
                            pontos[i] = pontos[i] + 1;
                        }
                    }
                }
            }

            System.out.println(" ");
            for (int i = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    System.out.print(marcacao[i][j]);
                    System.out.print(" ");

                }
                System.out.println(" ");
            }
            
            for(int i = 0; i< pontos.length; i++){
                if(pontos[i]==5){
                    System.out.print("O jogador: "+jogadores[i]);
                    System.out.print(" fez bingo\n");
                    BINGO = 1;
                }

            }
            
            if(BINGO!=1){
                System.out.println("\ndeseja sair ?");
                System.out.println("Digite C para continuar e X para sair");
                saida = sc.next();
                saidau = saida.toUpperCase();    
            }
            

            rodada++;

        } while (BINGO != 1 && !saidau.equals("X"));
        System.out.println("O numero de rodadas foi: "+rodada);
        System.out.println("a pontuacao dos jogadores foi: ");
        for (int i = 0; i< pontos.length; i++){
            System.out.print(jogadores[i]);
            System.out.print(": ");
            System.out.println(pontos[i]);
        }

    }

    public static void sorteioManualAleatorio(String cartela [][],String [] jogadores ){

        String [] sorteioString = new String [TAMANHO];
        int [] sorteio = new int[TAMANHO];
        int pontos [] = new int[jogadores.length];
        int rodada = 0;
        int marcacao[][] = new int[jogadores.length][TAMANHO];
        int BINGO = 0;
        String saida;
        String saidau = null;
        System.out.println("voce selecionou o modo de sorteio aleatorio.");
        do {

            for (int i = 0; i< sorteio.length; i++){
                sorteio[i] = random.nextInt(LIMITE_MAXIMO);
            }

            for (int i = 0; i < sorteio.length; i++) {
                System.out.print(sorteio[i]);
                System.out.print(" ");
            }
            for (int i = 0; i< sorteio.length; i++){
                sorteioString [i] = Integer.toString(sorteio[i]);
            }
            for (int i = 0, k = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    for (k = 0; k < sorteio.length; k++) {
                        if (Objects.equals(cartela[i][j], sorteioString[k])) {
                            marcacao[i][j] = 1;
                            pontos[i] = pontos[i] + 1;
                        }
                    }
                }
            }

            System.out.println(" ");
            for (int i = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    System.out.print(marcacao[i][j]);
                    System.out.print(" ");

                }
                System.out.println(" ");
            }

            for(int i = 0; i< pontos.length; i++){
                if(pontos[i]==5){
                    System.out.print("O jogador: "+jogadores[i]);
                    System.out.print(" fez bingo\n");
                    BINGO = 1;
                }
            }

            if(BINGO!=1){
                System.out.println("\ndeseja sair ?");
                System.out.println("Digite C para continuar e X para sair");
                saida = sc.next();
                saidau = saida.toUpperCase();
            }


            rodada++;

        } while (BINGO != 1 && !saidau.equals("X"));

        System.out.println("O numero de rodadas foi: "+rodada);
        System.out.println("a pontuacao dos jogadores foi: ");
        for (int i = 0; i< pontos.length; i++){
            System.out.print(jogadores[i]);
            System.out.print(": ");
            System.out.println(pontos[i]);
        }
    }

    public static void sorteioAleatorioManual(String [] jogadores, int [][] cartela){
        String [][] cartelaString = new String[jogadores.length][TAMANHO];
        int pontos [] = new int[jogadores.length];
        int rodada = 0;
        int marcacao[][] = new int[jogadores.length][TAMANHO];
        int BINGO = 0;
        String saida;
        String saidau = null;
        do {
            System.out.println("informe os numeros sorteados conforme o modelo: '1,2,3,4,5' ");
            String sort = sc.next();
            String[] sorteio = sort.split(",");
            for (int i = 0; i < sorteio.length; i++) {
                System.out.print(sorteio[i]);
                System.out.print(" ");
            }

            for(int i = 0; i< jogadores.length; i++){
                for (int j = 0; j<TAMANHO; j++) {
                    cartelaString[i][j] = Integer.toString(cartela[i][j]);
                }
            }

            for (int i = 0, k = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    for (k = 0; k < sorteio.length; k++) {
                        if (Objects.equals(cartelaString[i][j], sorteio[k])) {
                            marcacao[i][j] = 1;
                            pontos[i] = pontos[i] + 1;
                        }
                    }
                }
            }

            System.out.println(" ");
            for (int i = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    System.out.print(marcacao[i][j]);
                    System.out.print(" ");

                }
                System.out.println(" ");
            }

            for(int i = 0; i< pontos.length; i++){
                if(pontos[i]==5){
                    System.out.print("O jogador: "+jogadores[i]);
                    System.out.print(" fez bingo\n");
                    BINGO = 1;
                }

            }

            if(BINGO!=1){
                System.out.println("\ndeseja sair ?");
                System.out.println("Digite C para continuar e X para sair");
                saida = sc.next();
                saidau = saida.toUpperCase();
            }


            rodada++;

        } while (BINGO != 1 && !saidau.equals("X"));
        System.out.println("O numero de rodadas foi: "+rodada);
        System.out.println("a pontuacao dos jogadores foi: ");
        for (int i = 0; i< pontos.length; i++){
            System.out.print(jogadores[i]);
            System.out.print(": ");
            System.out.println(pontos[i]);
        }

    }


    public static void sorteioLaeatorioAleatorio(String[]jogadores, int[][]cartela){

        int [] sorteio = new int[TAMANHO];
        int pontos [] = new int[jogadores.length];
        int rodada = 0;
        int marcacao[][] = new int[jogadores.length][TAMANHO];
        int BINGO = 0;
        String saida;
        String saidau = null;
        System.out.println("voce selecionou o modo de sorteio aleatorio.");
        do {

            for (int i = 0; i< sorteio.length; i++){
                sorteio[i] = random.nextInt(LIMITE_MAXIMO);
            }

            for (int i = 0; i < sorteio.length; i++) {
                System.out.print(sorteio[i]);
                System.out.print(" ");
            }


            for (int i = 0, k = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    for (k = 0; k < sorteio.length; k++) {
                        if (cartela[i][j] == sorteio[k]) {
                            marcacao[i][j] = 1;
                            pontos[i] = pontos[i] + 1;
                        }
                    }
                }
            }

            System.out.println(" ");
            for (int i = 0; i < jogadores.length; i++) {
                for (int j = 0; j < TAMANHO; j++) {
                    System.out.print(marcacao[i][j]);
                    System.out.print(" ");

                }
                System.out.println(" ");
            }

            for(int i = 0; i< pontos.length; i++){
                if(pontos[i]==5){
                    System.out.print("O jogador: "+jogadores[i]);
                    System.out.print(" fez bingo\n");
                    BINGO = 1;
                }
            }

            if(BINGO!=1){
                System.out.println("\ndeseja sair ?");
                System.out.println("Digite C para continuar e X para sair");
                saida = sc.next();
                saidau = saida.toUpperCase();
            }


            rodada++;

        } while (BINGO != 1 && !saidau.equals("X"));

        System.out.println("O numero de rodadas foi: "+rodada);
        System.out.println("a pontuacao dos jogadores foi: ");
        for (int i = 0; i< pontos.length; i++){
            System.out.print(jogadores[i]);
            System.out.print(": ");
            System.out.println(pontos[i]);
        }
    }


}






//CRIAR UM ARRAY JA ATRIBUINDO VALORES ALEATORIOS DE 1 - 5;
// sorteados[1..60] limites
//

//MUDAR PARA NUMEROS ALEATORIOS;
//SORTEAR NUMERO .... ALEATORIO/ SCANNER;
//MARCAR O NUMERO DE ACORDO COM A POSICAO;
// CONDICAO DE SAIDA > MARCAO [1, 1, 1, 1, 1]
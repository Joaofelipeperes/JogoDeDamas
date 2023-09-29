import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;

public class Principal{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    
        Jogador jogador = new Jogador("Pretas"); 
        //Robo - Brancas

        int entrada;
        String origem, destino;
        int linhaorigem, colunaorigem, linhadestino, colunadestino;
        Tabuleiro tabuleiroPrincipal = new Tabuleiro();

        imprimeMenu("");
        entrada = sc.nextInt(); sc.nextLine();

        if (entrada == 1) {
            tabuleiroPrincipal.regras();
            tabuleiroPrincipal.montarTabuleiro();
        }
        else if (entrada == 2) {
            tabuleiroPrincipal = CarregaTabuleiro();
        }
        else if (entrada == 3) {
            sc.close();
            return;
        }

        do{
            tabuleiroPrincipal.imprimirTabuleiro(jogador.cor);

            origem = sc.nextLine().toUpperCase();

            if(origem.equals("S")) {
                System.out.println("Fim do Jogo!");
                break;
            }

            if(origem.equals("J") || origem.equals("I")) {
                System.out.println("Jogo salvado com sucesso!");
                SalvaDama(tabuleiroPrincipal);
                break;
            }

            destino = sc.nextLine().toUpperCase();

            linhaorigem = tradutor(origem.charAt(0));
            colunaorigem = tradutor(origem.charAt(1));
            
            linhadestino = tradutor(destino.charAt(0));
            colunadestino = tradutor(destino.charAt(1));

            if (tabuleiroPrincipal.isBlack(linhaorigem, colunaorigem)) {
                tabuleiroPrincipal.MechePessa(linhaorigem, colunaorigem, linhadestino, colunadestino); 
            }
            else {
                System.out.println("Selecione uma peça preta");
                continue;
            }

            Robo robo = new Robo();
            robo.JogaRobo(tabuleiroPrincipal);
            tabuleiroPrincipal.imprimirTabuleiro("Brancas");

         }while(true);

        sc.close();
    }

    public static void imprimeMenu(String mensagem) {
        System.out.println("+=============================================================================+");
        System.out.println("| Jogo de Damas -- Versão 0.1                                                 |");
        System.out.println("+=============================================================================+");
        System.out.println("|                                                                             |");
        System.out.println("|   Menu                                                                      |");
        System.out.println("|                                                                             |");
        System.out.println("|       [01] Iniciar um novo jogo                                             |");
        System.out.println("|       [02] Carregar um jogo [interrompido / finalizado]                     |");
        System.out.println("|       [03] Sair                                                             |");
        System.out.println("+=============================================================================+");
        System.out.printf("| Mensagem: %s                                                                  |\n", mensagem);
        System.out.println("+=============================================================================+");
    }

    public static int tradutor(int c) {
        if (c >= 65 && c <= 72) {
            c -= 65;
        }
        else if (c >= 97 && c <= 104) {
            c -= 97;
        }
        else if (c >= 49 && c <= 57) {
            c -= 49;
        }
        
        return c;
    }

    public static void SalvaDama(Tabuleiro tabuleiro){
        Gson gson= new Gson();
        String json= gson.toJson(tabuleiro);

        try(FileWriter writer= new FileWriter("SalvaDama")){
            writer.write(json);
            System.out.println("OBJETO SALVO COMO JSON !!!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Tabuleiro CarregaTabuleiro(){
        try(FileReader reader= new FileReader("SalvaDama")){
            Gson gson= new Gson();

            Tabuleiro tabu= gson.fromJson(reader, Tabuleiro.class );
            System.out.println("ARQUIVO CARREGADO COM SUCESSO !!!");
            return tabu;
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("ERRO AO CARREGAR O JOGO !!!");
        return null;
    }
}

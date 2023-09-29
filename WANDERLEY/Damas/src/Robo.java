import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Robo{
    Tabuleiro tabRobo;
    Arvore arvore= new Arvore();
    Jogada joga;
    List<Jogada> jogadasPo = new ArrayList<>();
    Robo(){
        this.tabRobo= new Tabuleiro();
    }
    public void JogaRobo(Tabuleiro tabuleiroPrincipal){
        this.tabRobo.tabuleiro= tabuleiroPrincipal.CopiaTab();
        JogadasPossiveis();
        JogadasInimigo(tabRobo);

        Jogada JogadaRobo=AchaJogada();
        tabuleiroPrincipal.MechePessa( JogadaRobo.linhaI, JogadaRobo.colI, JogadaRobo.linhaD, JogadaRobo.colD);
        ImprimeArvore();
    }
    public void JogadasPossiveis(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(tabRobo.tabuleiro[i][j].numero == 1){ //robo só vai funcionar nas brancas
                   if( tabRobo.ConfereMov(i, j, i+1, j-1) == true){
                    joga= new Jogada(i, j, i+1, j-1);
                    jogadasPo.add(joga);
                   }
                    if( tabRobo.ConfereMov(i, j, i+1, j+1) == true){
                        joga= new Jogada(i, j, i+1, j+1);
                        jogadasPo.add(joga);
                    }
                    if(tabRobo.PodeComerRoboDireita(i, j)== true){
                        joga= new Jogada(i, j, i+2, j+2);
                        joga.valorjogada++;
                        jogadasPo.add(joga);

                    }
                    if(tabRobo.PodeComerRoboEsquerda(i, j)== true){
                        joga= new Jogada(i, j, i+2, j-2);
                        joga.valorjogada++;
                        jogadasPo.add(joga);
                    }
                   }
                }
            }
        }
        public void PercorreJogadas(){
        for(Jogada jogada : jogadasPo){
            System.out.println(jogada);
        }
    }

    public void JogadasInimigo(Tabuleiro TABROBO){
        Tabuleiro tabteste;
        tabteste = new Tabuleiro();
        Jogada jogadainimigo;
        for(Jogada jogada : jogadasPo){

            tabteste.tabuleiro= TABROBO.CopiaTab();
            tabteste.TrocaLugar(jogada.linhaI, jogada.colI, jogada.linhaD, jogada.colD);
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(tabteste.tabuleiro[i][j].numero == 2){ //vai verificar se as pecas Pretas podem comer
                    if( tabteste.ConfereMov(i, j, i-1, j-1) == true){
                        jogadainimigo= new Jogada(i, j, i-1, j-1);
                        jogada.arvore.add(jogadainimigo);
                        PreveJogada3(jogadainimigo, tabteste);
                    }
                    if(tabteste.ConfereMov(i, j, i-1, j+1) == true){
                        jogadainimigo= new Jogada(i, j, i-1, j+1);
                        jogada.arvore.add(jogadainimigo);
                        PreveJogada3(jogadainimigo, tabteste);
                    }
                    if(tabteste.PodeComerInimigoDireita(i, j)== true){
                        jogadainimigo= new Jogada(i, j, i-2, j+2, 1);
                        jogada.arvore.add(jogadainimigo);
                        PreveJogada3(jogadainimigo, tabteste);
                    }
                    if(tabteste.PodeComerInimigoEsquerda(i, j)== true){
                        jogadainimigo= new Jogada(i, i, i-2, j-2, 1);
                        jogada.arvore.add(jogadainimigo);
                        PreveJogada3(jogadainimigo, tabteste);
                    }
                }
            }
        }
        }
    }
    public void PreveJogada3(Jogada jogadainimigo, Tabuleiro tabteste){
        Tabuleiro TABteste2 = new Tabuleiro();
        TABteste2.tabuleiro= tabteste.CopiaTab();
        TABteste2.TrocaLugar(jogadainimigo.linhaI, jogadainimigo.colI, jogadainimigo.linhaD, jogadainimigo.colD);
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(TABteste2.tabuleiro[i][j].numero == 1){ //Conferindo novamente o próximo movimento(Profundidade 3)
                   if( tabRobo.ConfereMov(i, j, i+1, j-1) == true){
                    joga= new Jogada(i, j, i+1, j-1);
                    jogadainimigo.arvore.add(joga);
                   }
                    if( TABteste2.ConfereMov(i, j, i+1, j+1) == true){
                        joga= new Jogada(i, j, i+1, j+1);
                        jogadainimigo.arvore.add(joga);
                    }
                    if(TABteste2.PodeComerRoboDireita(i, j)== true){
                        joga= new Jogada(i, j, i+2, j+2, 1);
                        jogadainimigo.arvore.add(joga);
                    }
                    if(TABteste2.PodeComerRoboEsquerda(i, j)== true){
                        joga= new Jogada(i, j, i+2, j-2, 1);
                        jogadainimigo.arvore.add(joga);
                    }
                   }
            }
        }
    }
    public Jogada AchaJogada(){
        Jogada melhorjogada= new Jogada(0, 0, 0, 0);
        Jogada jogadaAtual= new Jogada(0, 0, 0, 0, -7);
        melhorjogada.valorjogada=-3;
        List<Jogada> MelhoresJogadas = new ArrayList<>();
        List<Jogada> Jogadas1 = new ArrayList<>();
        List<Jogada> Jogadas2 = new ArrayList<>();
        Random random= new Random();

        for(Jogada jogada : jogadasPo){

            Jogadas1=jogada.arvore.RetornaLista();

            for(Jogada jogada1 : Jogadas1){
                Jogadas2 =jogada1.arvore.RetornaLista();
                for(Jogada jogada2 : Jogadas2){

            jogadaAtual.valorjogada= jogada.valorjogada - jogada1.valorjogada + jogada2.valorjogada;
            
            if(jogadaAtual.valorjogada > melhorjogada.valorjogada){
                jogadaAtual.linhaI= jogada.linhaI;
                jogadaAtual.linhaD= jogada.linhaD;
                jogadaAtual.colI= jogada.colI;
                jogadaAtual.colD= jogada.colD;
                MelhoresJogadas.clear();
                MelhoresJogadas.add(new Jogada(jogadaAtual.linhaI, jogadaAtual.colI, jogadaAtual.linhaD, jogadaAtual.colD, jogadaAtual.valorjogada));
                melhorjogada.valorjogada= jogadaAtual.valorjogada;
            }else{
                if(jogadaAtual.valorjogada == melhorjogada.valorjogada){
                jogadaAtual.linhaI= jogada.linhaI;
                jogadaAtual.linhaD= jogada.linhaD;
                jogadaAtual.colI= jogada.colI;
                jogadaAtual.colD= jogada.colD;
                    MelhoresJogadas.add(new Jogada(jogadaAtual.linhaI, jogadaAtual.colI, jogadaAtual.linhaD, jogadaAtual.colD, jogadaAtual.valorjogada));
                    melhorjogada.valorjogada= jogadaAtual.valorjogada;
                    
                }
            }
        }
    }
    }
        melhorjogada=MelhoresJogadas.get(random.nextInt(MelhoresJogadas.size()));
        System.out.println("Melhor Jogada Escolhida : \n" + melhorjogada);
        return melhorjogada;
    }
    
    public void ImprimeArvore(){
        for(Jogada jogada : jogadasPo){
            System.out.println("Primeira Jogada do Robo : \n");
            System.out.println(jogada);
            jogada.arvore.PercorrerA();
            System.out.println("==========================================");
        }
    }
    public void ImprimeJogadas(){
        for(Jogada jogada1 : jogadasPo){
            System.out.println(jogada1);
        }
    }
}

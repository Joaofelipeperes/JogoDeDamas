/*
 *-1 -> Borda
 * 0 -> Posicao invalida e sem peça
 * 1 -> Posicao valida e sem peça
 * 2 -> Posicao com peça
 */

public class Tabuleiro {
    Peca[][] tabuleiro = new Peca[8][8];
    int PontuacaoP=0;
    int PontuacaoB=0;
    Tabuleiro(){
        tabuleiro = new Peca[8][8];
    }
    Tabuleiro(Tabuleiro tab){
        this.tabuleiro= tab.tabuleiro;
    }

    //recebe a posição de destino e analisa se a posição é válida
    public boolean podeMover(int x, int y) {
        if (tabuleiro[x][y].numero == -1 || tabuleiro[x][y].numero == 1) {
            return false;
        }
        else return true;
    }

    public void regras() {
        System.out.println("|=============================================================================|");
        System.out.println("| Regras do Jogo                                                              |");
        System.out.println("| 1 - Peça não come para trás                                                 |");
        System.out.println("| 2 - Não é obrigatório realizar a captura                                    |");
        System.out.println("| 3 - Para mover a peça utilize a seguinte sintaxe: A2 (Linha-Coluna)         |");

        /*Comandos para salvar o jogo */
        System.out.println("|=============================================================================|\n\n");
    }
    
    public void deixaVazio(int linhaI, int colI){
        tabuleiro[linhaI][colI].Tipo= '-';
        tabuleiro[linhaI][colI].numero= 0;
        tabuleiro[linhaI][colI].valor= 0;
        tabuleiro[linhaI][colI].dama= false;
    }

    public void imprimirTabuleiro(String mensagem) {
        System.out.println("+=============================================================================+");
        System.out.println("| Jogo de Damas -- Versão 0.1                                                 |");
        System.out.println("+=============================================================================+");
        System.out.print("|         ");
        for (int i = 1; i < 9; i++) {
            if (i == 8) {
                System.out.print(i + "    |\n");
                break;
            }
            System.out.print(i + "        ");
        }

        for (int i = 0; i < 8; i++) {
            System.out.printf("|  %c  ", i+65);
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] != null) {
                    System.out.print("[   " + tabuleiro[i][j].getTipo() + "   ]");
                } else {
                    System.out.print("-"); // Imprime "-" caso a posição do tabuleiro seja nula
                }
            }
            System.out.println("|");
        } 

        System.out.println("|                                                                             |");
        System.out.println("| [A] Visualizar Árvore  [S] Sair                  [P] Ver peças capturadas   |");
        System.out.println("| [I] Interromper jogo   [J] Salvar jogo                                      |");
        System.out.println("+=============================================================================+");
        System.out.printf("| Mensagem: %s                                                            |\n", mensagem);
        System.out.println("+=============================================================================+");
    }

   public void TrocaLugar(int linhaI, int colI, int linhaD, int colD){
        tabuleiro[linhaD][colD].Tipo = tabuleiro[linhaI][colI].Tipo;
        tabuleiro[linhaD][colD].numero = tabuleiro[linhaI][colI].numero;
        tabuleiro[linhaD][colD].dama = tabuleiro[linhaI][colI].dama;
        tabuleiro[linhaD][colD].valor = tabuleiro[linhaI][colI].valor;

        deixaVazio(linhaI, colI);
    }

    public void montarTabuleiro(){
        for(int linha = 0; linha < 8; linha++){
            for(int coluna = 0; coluna < 8; coluna++){

                tabuleiro[linha][coluna]= new Peca();

                if(linha == 0 || linha == 2){
                    if(coluna == 0 || coluna == 2 || coluna == 4 || coluna ==6){
                        tabuleiro[linha][coluna].Tipo= 'B';
                        tabuleiro[linha][coluna].numero= 1;
                        tabuleiro[linha][coluna].valor= 1;
                    }
                } 
                else {
                    if (linha == 1) {
                        if(coluna ==1 || coluna ==3 || coluna == 5 || coluna ==7){
                            tabuleiro[linha][coluna].Tipo = 'B';
                            tabuleiro[linha][coluna].numero = 1;
                            tabuleiro[linha][coluna].valor = 1;
                        }
                    }
                }

            if(linha == 5 || linha == 7){
                if(coluna== 1 || coluna== 3 || coluna== 5 || coluna==7){
                    tabuleiro[linha][coluna].Tipo= 'P';
                    tabuleiro[linha][coluna].numero= 2;
                    tabuleiro[linha][coluna].valor= 1;
                }
            }
            else{
                if(linha== 6){
                    if(coluna==0 || coluna==2 || coluna== 4 || coluna==6){
                        tabuleiro[linha][coluna].Tipo= 'P';
                        tabuleiro[linha][coluna].numero= 2;
                        tabuleiro[linha][coluna].valor= 1;
                    }
                }
            }   
            }
        }
    }

    public boolean isBlack (int linhaI, int colI) { //Verifica se a peça selecionada é do Jogador
        if (tabuleiro[linhaI][colI].Tipo == 'P' || tabuleiro[linhaI][colI].Tipo == 'Y') {
            return true;
        }
        else return false;
    }

    public void MechePessa(int linhaI, int colI, int linhaD, int colD){
        if(tabuleiro[linhaI][colI].numero== 0){
            System.out.println("CASA VAZIA");
        }else{
        if(ConfereMov(linhaI, colI, linhaD, colD) == true){
            if(Math.abs(Math.ceil(linhaD-linhaI))==2){
                ComePeca(linhaI, colI, linhaD, colD);
                if(linhaD==0 && tabuleiro[linhaD][colD].numero== 2/*Preto */ || linhaD== 7 && tabuleiro[linhaD][colD].numero== 1/*Branco */){
                    tabuleiro[linhaD][colD].ViraDama();
                }
            }else{
                TrocaLugar(linhaI, colI, linhaD, colD);
                if(linhaD==0 && tabuleiro[linhaD][colD].numero== 2/*Preto */ || linhaD== 7 && tabuleiro[linhaD][colD].numero== 1/*Branco */){
                    tabuleiro[linhaD][colD].ViraDama();
                }
        }
        }else{
            if(DamaPercorre(linhaI, colI, linhaD, colD) == true){
            }else{
            System.out.println("MOVIMENTO INVALIDO DEU INVALIDO MECHEPESSA");
        }
    }
}
    }
    public void ComePeca(int linhaI, int colI, int linhaD, int colD){
        int meiolinha, meiocoluna;
        meiolinha=(linhaI+linhaD)/2;
        meiocoluna=(colD+colI)/2;

        if(tabuleiro[linhaI][colI].dama== false){
        if(tabuleiro[linhaI][colI].numero==1 && tabuleiro[linhaD][colD].numero== 0 && tabuleiro[meiolinha][meiocoluna].numero== 2){ //B
            TrocaLugar(linhaI, colI, linhaD, colD);
            deixaVazio(meiolinha, meiocoluna);
            PontuacaoB++;
        }else{
            if(tabuleiro[linhaI][colI].numero==2 && tabuleiro[linhaD][colD].numero== 0 && tabuleiro[meiolinha][meiocoluna].numero== 1){//P
                TrocaLugar(linhaI, colI, linhaD, colD);
                deixaVazio(meiolinha, meiocoluna);
                PontuacaoP++;
            }else{
                System.out.println("MOVIMENTO INVALIDO");
            }
        }
    }
}

    public boolean DamaPercorre(int linhaI, int colI, int linhaD, int colD){
        int i, j;
        int contadorpeca=0, contadorinimigo=0;
        int linhaInimigo=-1, colunainimigo=-1; //conferir dps, tem tudo pra dar errado;
        if(tabuleiro[linhaD][colD].valor == 0 && tabuleiro[linhaI][colI].dama == true){
        if(linhaI < linhaD && colI< colD){ //22 para 33
            for(i=linhaI, j=colI; i <=linhaD && j<=colD; i++, j++){
                if(tabuleiro[i][j].valor>=1){
                    contadorpeca++;
                    if(tabuleiro[i][j].numero == 1 && tabuleiro[linhaI][colI].numero== 2 || tabuleiro[i][j].numero == 2 && tabuleiro[linhaI][colI].numero== 1){
                         contadorinimigo++;
                         linhaInimigo= i;
                         colunainimigo= j;
                        }
                        System.out.println("MOVIMENTO INVALIDO ENTROU 22 33");
                }
            }
        }else{
            if(linhaI< linhaD && colI > colD){ //22 para 31
                for(i=linhaI, j=colI; i<=linhaD && j>=colD; i++, j--){
                    if(tabuleiro[i][j].valor>=1){
                        contadorpeca++;
                        if(tabuleiro[i][j].numero == 1 && tabuleiro[linhaI][colI].numero== 2 || tabuleiro[i][j].numero == 2 && tabuleiro[linhaI][colI].numero== 1){
                         contadorinimigo++;
                         linhaInimigo= i;
                         colunainimigo= j;
                        }
                    }
                    System.out.println("MOVIMENTO INVALIDO ENTROU 22 31");
                }
            }else{
                if(linhaI> linhaD && colI < colD){ //22 para 13
                for(i=linhaI, j=colI; i>=linhaD && j<=colD; i--, j++){
                    if(tabuleiro[i][j].valor>=1){
                        contadorpeca++;
                        if(tabuleiro[i][j].numero == 1 && tabuleiro[linhaI][colI].numero== 2 || tabuleiro[i][j].numero == 2 && tabuleiro[linhaI][colI].numero== 1){
                         contadorinimigo++;
                         linhaInimigo= i;
                         colunainimigo= j;
                        }
                    }
                    System.out.println("MOVIMENTO INVALIDO ENTROU 22 13");
                }
            }else{
                if(linhaI > linhaD && colI > colD){ //22 para 11
                for(i=linhaI, j=colI; i>=linhaD && j>=colD; i--, j--){
                    if(tabuleiro[i][j].valor>=1){
                        contadorpeca++;
                        if(tabuleiro[i][j].numero == 1 && tabuleiro[linhaI][colI].numero== 2 || tabuleiro[i][j].numero == 2 && tabuleiro[linhaI][colI].numero== 1){
                         contadorinimigo++;
                         linhaInimigo= i;
                         colunainimigo= j;
                        }
                    }
                    System.out.println("MOVIMENTO INVALIDO ENTROU 22 11");
                }
            }
            }
            }
        }
        if(contadorinimigo==1 && contadorpeca-1==0){
            TrocaLugar(linhaI, colI, linhaD, colD);
            deixaVazio(linhaInimigo, colunainimigo);
            System.out.println("MOVIMENTO INVALIDO ENTROU AQUI 1");
        }else{
            if(contadorinimigo==0 && contadorpeca-1==0){
                TrocaLugar(linhaI, colI, linhaD, colD);
                System.out.println("MOVIMENTO INVALIDO ENTROU AQUI 2");
            }else{
                if(contadorinimigo>1 || contadorpeca-1>1){
                    System.out.println("MOVIMENTO INVALIDO ENTROU AQUI 3");
                    //MOVIMENTO INVALIDO
                    return false;
                }
            }
        }
        System.out.println("VOLTOU VERDADEIRO");
        return true;
    }else{
        System.out.println("VOLTOU FALSO");
        return false; //posição onde quer movimentar ocupada ou não é Dama
    }
    }
    public boolean ConfereMov(int linhaI, int colI, int linhaD, int colD){
        if( linhaI<0 || linhaI>7 || linhaD<0 || linhaD>7|| colI<0 || colI>7 || colD<0 || colD>7){
            return false;
        }
        if(Math.abs(linhaD-linhaI)==2 && tabuleiro[linhaI][colI].dama== false){
            return true;
        }else{
        if(tabuleiro[linhaI][colI].numero == 1 && tabuleiro[linhaD][colD].numero ==0){ //B

            if(((linhaI +1== linhaD && colI +1== colD) || (linhaI +1== linhaD && colI -1== colD)) && tabuleiro[linhaI][colI].dama == false){ //move as B pra esqrd ou direita
                return true;
            }else{
                return false;
            }

        }else{if(tabuleiro[linhaI][colI].numero == 2 && tabuleiro[linhaD][colD].numero ==0){ //P
            if(((linhaI -1== linhaD && colI +1== colD) || (linhaI -1== linhaD && colI -1== colD)) && tabuleiro[linhaI][colI].dama == false){ //move as P pra esqrd ou direita
                return true;
            }else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
}
        public boolean PodeComerRoboDireita(int linhaI, int colI){
            if( linhaI+2>7 || colI+2>7){
            return false;
        }
        if(tabuleiro[linhaI][colI].numero==1 && tabuleiro[linhaI+2][colI+2].numero== 0 && tabuleiro[linhaI+1][colI+1].numero== 2){ //B
            return true;
        }
        return false;
        }
        public boolean PodeComerRoboEsquerda(int linhaI, int colI){
            if(colI-2<0 || linhaI+2>7){
            return false;
        }
        if(tabuleiro[linhaI][colI].numero==1 && tabuleiro[linhaI+2][colI-2].numero== 0 && tabuleiro[linhaI+1][colI-1].numero== 2){ //B
            return true;
        }
        return false;
        }
        
        public boolean PodeComerInimigoDireita(int linhaI, int colI){
            if( linhaI-2<0 || colI+2>7){
            return false;
        }
        if(tabuleiro[linhaI][colI].numero==2 && tabuleiro[linhaI-2][colI+2].numero== 0 && tabuleiro[linhaI-1][colI+1].numero== 1){ //p
            return true;
        }
        return false;
        }

        public boolean PodeComerInimigoEsquerda(int linhaI, int colI){
            if( linhaI-2<0 || colI-2<0){
            return false;
        }
        if(tabuleiro[linhaI][colI].numero==2 && tabuleiro[linhaI-2][colI-2].numero== 0 && tabuleiro[linhaI-1][colI-1].numero== 1){ //p
            return true;
        }
        return false;
        }
        public Peca[][] CopiaTab(){
            Peca[][] Copia = new Peca[8][8];
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    Copia[i][j]= new Peca();
                    Copia[i][j].Tipo= tabuleiro[i][j].Tipo;
                    Copia[i][j].valor= tabuleiro[i][j].valor;
                    Copia[i][j].numero= tabuleiro[i][j].numero;
                    Copia[i][j].dama= tabuleiro[i][j].dama;
                }
            }
            return Copia;
        }
}
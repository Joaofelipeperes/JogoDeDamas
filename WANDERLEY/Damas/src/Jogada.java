

public class Jogada {
    int valorjogada=0;
    int linhaI, colI, linhaD, colD;
    Arvore arvore= new Arvore();

    Jogada(int linhaI, int colI, int linhaD, int colD){
        this.linhaI=linhaI;
        this.linhaD=linhaD;
        this.colI=colI;
        this.colD= colD;
    }
    Jogada(int linhaI, int colI, int linhaD, int colD, int valorjogada){
        this.linhaI=linhaI;
        this.linhaD=linhaD;
        this.colI=colI;
        this.colD= colD;
        this.valorjogada= valorjogada;
    }
    public String toString(){
        return "Peca : linha " +(linhaI+1)+ " coluna " +(colI+1)+"\nDestino : linha " +(linhaD+1)+ " coluna "+(colD+1)+"\nValor Jogada : "+valorjogada+"\n";
    }
}

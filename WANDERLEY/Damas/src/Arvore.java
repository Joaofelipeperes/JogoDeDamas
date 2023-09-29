import java.util.ArrayList;
import java.util.List;

class No {
    Jogada jogada;
    No esquerda;
    No direita;

    public No(Jogada jogada){
        this.jogada= jogada;
        this.esquerda=null;
        this.direita= null; 
    }
}
class Arvore{
    No No;

    public Arvore(){
        this.No=null;
    }
    public void add(Jogada jogada){
        No= inserirNo(No, jogada);
    }
    public No inserirNo(No atual, Jogada jogada){
        if(atual == null){
            return new No(jogada);
        }
        if(jogada.valorjogada< atual.jogada.valorjogada){
                atual.esquerda= inserirNo(atual.esquerda, jogada);
        }else{
            if(jogada.valorjogada >= atual.jogada.valorjogada){
                atual.direita= inserirNo(atual.direita, jogada);
            }
        }
        return atual;
    }
    public void PercorreArvore(No no){
        if(no != null){
            
            System.out.println("Jogada do Inimigo em resposta da Jogada anterior");
            System.out.println(no.jogada.toString()); //imprimiu jogada do inimigo
            no.jogada.arvore.PercorrerA2();
            PercorreArvore(no.direita);
            PercorreArvore(no.esquerda);
        }
    }
    public void PercorreArvore2(No no){
        if(no != null){
            System.out.println("Jogada do Robo em resposta a Jogada do Inimigo");
            System.out.println(no.jogada.toString()); //imprimiu jogada final
            PercorreArvore2(no.direita);
            PercorreArvore2(no.esquerda);
        }
    }
    public void PercorrerA(){
        PercorreArvore(No);
    }
    public void PercorrerA2(){
        PercorreArvore2(No);
    }
    public List<Jogada> RetornaLista(){
        List<Jogada> JOGADAS= new ArrayList<>();
        PegaValores(No, JOGADAS);
        return JOGADAS;
        
    }
    public void PegaValores(No no, List<Jogada> JOGADAS){
        if(no!= null){
            PegaValores(no.direita, JOGADAS);
            JOGADAS.add(no.jogada);
            PegaValores(no.esquerda, JOGADAS);
        }
    }
}

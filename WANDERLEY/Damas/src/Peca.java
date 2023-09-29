public class Peca {
    int valor;
    char Tipo;
    Boolean dama;
    int numero;
    Peca(){
        valor=0;
        dama= false;
        Tipo= '-';
        numero= 0;
    }
    public int getNumero() {
        return numero;
    }
    public char getTipo() {
        return Tipo;
    }
    public void ViraDama(){
        if(numero==1){
            Tipo='X'; // Dama Branca
            dama=true;
        }else{
            if(numero==2){
                Tipo='Y'; //Dama Preta
                dama=true;
            }
        }
    }
}

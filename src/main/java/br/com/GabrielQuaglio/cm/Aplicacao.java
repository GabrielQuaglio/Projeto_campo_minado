package br.com.GabrielQuaglio.cm;

import br.com.GabrielQuaglio.cm.modelo.Campo;
import br.com.GabrielQuaglio.cm.modelo.Tabuleiro;

public class Aplicacao {
    public static void main(String[] args) {

        try {
            Tabuleiro t = new Tabuleiro(6, 6, 25);
            Campo c1 = new Campo(3, 3);
            t.abrir(3, 3);
            t.abrir(3, 4);
            t.abrir(4, 4);


            t.marcar(4, 4);
            t.marcar(2, 5);

            System.out.println(t);
        }catch (RuntimeException e){
            System.out.println("explodiu");
        }




    }
}

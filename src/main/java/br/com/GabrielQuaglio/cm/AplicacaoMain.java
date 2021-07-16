package br.com.GabrielQuaglio.cm;

import br.com.GabrielQuaglio.cm.modelo.Tabuleiro;
import br.com.GabrielQuaglio.cm.visao.TabuleiroTerminal;

public class AplicacaoMain {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);
        new TabuleiroTerminal(tabuleiro);




    }
}

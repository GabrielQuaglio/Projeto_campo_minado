package br.com.GabrielQuaglio.cm.visao;

import br.com.GabrielQuaglio.cm.modelo.Tabuleiro;

import javax.swing.*;

public class TelaPrincipal extends JFrame {//herdamos tudo diretamente
    //da classe JFrame, então não prescisamos instância-la

    public TelaPrincipal()  {
        Tabuleiro tabuleiro = new Tabuleiro(16,30,50);
      PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);

        add(painelTabuleiro);
        setTitle("Campo Minado");
        setSize(690,438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        new TelaPrincipal();




    }



}

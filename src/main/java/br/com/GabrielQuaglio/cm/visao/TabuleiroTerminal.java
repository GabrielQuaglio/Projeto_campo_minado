package br.com.GabrielQuaglio.cm.visao;

import br.com.GabrielQuaglio.cm.exceçao.SairException;
import br.com.GabrielQuaglio.cm.modelo.Tabuleiro;

import java.util.Scanner;

public class TabuleiroTerminal {

    private Tabuleiro tabuleiro;

    Scanner entrada = new Scanner(System.in);

    public TabuleiroTerminal(Tabuleiro tabuleiro){
    this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
   try {
       boolean continuar = true;

       while (continuar){
           cicloDoJogo();

           System.out.println("Outra partida? (S/n) ");
           String resposta = entrada.nextLine();

           if ("n".equalsIgnoreCase(resposta)){
               throw new SairException();
           }else{
               tabuleiro.reniciar();
           }
       }
   }catch (SairException e){
       System.out.println("Tchau");
   }finally {
       entrada.close(); // garantir que o Scanner feche
   }

    }

    private void cicloDoJogo() {
    }

}

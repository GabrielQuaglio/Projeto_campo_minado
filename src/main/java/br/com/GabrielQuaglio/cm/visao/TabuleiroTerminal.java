package br.com.GabrielQuaglio.cm.visao;

import br.com.GabrielQuaglio.cm.exceçao.ExplosaoException;
import br.com.GabrielQuaglio.cm.exceçao.SairException;
import br.com.GabrielQuaglio.cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
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
               continuar =false;
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
        try {
            while (!tabuleiro.objetivoAlcançado()){
                System.out.println(tabuleiro);


                String digitado =
                        capturarValorDigitado("Digite (X e Y): ");


              Iterator<Integer> xy = Arrays
                        .stream(digitado.split(","))
                        .map(lc -> Integer.parseInt(lc.trim())).iterator();
                // o split separa a string a cada virgula

                digitado =  capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar:  ");

                if (digitado.equalsIgnoreCase("1")) {
                    tabuleiro.abrir(xy.next(), xy.next());
                }else if(digitado.equalsIgnoreCase("2")){
                    tabuleiro.marcar(xy.next(),xy.next());
                }

            }

            System.out.println("Você ganhou");
        }catch (ExplosaoException e){
            System.out.println(tabuleiro);
            System.out.println("Você Perdeu");

        }
    }
    private String capturarValorDigitado( String texto)
    {
        System.out.print(texto);
        String digitado = entrada.nextLine();
        if ("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}

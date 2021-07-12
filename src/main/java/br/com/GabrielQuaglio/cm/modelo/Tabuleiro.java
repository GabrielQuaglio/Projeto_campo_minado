package br.com.GabrielQuaglio.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {


    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();//aqui foi preferido
    //organizar o tabuleiro como uma lista e nao como uma matriz


    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarOsVizinhos();
        sortearMinas();
    }



    private void gerarCampos() {
        for(int linha = 0; linha <linhas; linha++){
            for (int coluna = 0; coluna < colunas; coluna++){
                campos.add(new Campo(linha, coluna));
            }

        }

    }

    private void associarOsVizinhos() {
        for (Campo c1: campos){
            for (Campo c2: campos){
                c1.adicionarVizinho(c2);
            }
        }
    }


    private void sortearMinas() {
    long minasArmadas = 0;
    do {
        minasArmadas =  campos.stream().filter(c -> c.isMinado()).count();
      //count retorna long por isso o cast
        int aleatorio = (int) (Math.random() * campos.size());
        campos.get(aleatorio).isMinado();
    }while (minasArmadas< minas);
    }

    public boolean objetivoAlcançado(){
        return campos.stream().allMatch(c -> c.objetivoAlcançado());
    }
     public void reniciar(){
        campos.stream().forEach((c -> reniciar()));
        sortearMinas();
     }


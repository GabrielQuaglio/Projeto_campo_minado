package br.com.GabrielQuaglio.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;
    private boolean minado;
    private boolean aberto;
    private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<>();


    private int proximidade;

    Campo(int linha, int coluna){
        this.coluna = coluna;
        this.linha = linha;

    }

    boolean adicionarVizinho(Campo vizinho){
        return  true;
    }




}

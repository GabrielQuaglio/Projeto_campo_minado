package br.com.GabrielQuaglio.cm.modelo;

import br.com.GabrielQuaglio.cm.exceçao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;
    private boolean minado;
    private boolean aberto;
    private boolean marcado;


    private List<Campo> vizinhos = new ArrayList<>();


    public Campo(int linha, int coluna) {
        this.coluna = coluna;
        this.linha = linha;


    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else return false;


    }


    public void alternarMarcaçao() {
        if (!aberto) {
            marcado = !marcado;//assim podemos alternar se o campo esta marcado ou não

        }
    }

     public boolean abrir() {
        if (!aberto && !marcado) {
            aberto = true;
            if (minado) {
                throw new ExplosaoException();//usaremos uma exception personalizada, para quebrar o fluxo de código
            }
            if(vizinhançaSegura()){
                vizinhos.forEach(vizinho -> vizinho.abrir());//aqui fizemos uma chamada recursiva
            }
            return true;
        }else {
            return false;
        }
    }

    boolean vizinhançaSegura() {
        return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
    }

    public boolean isMarcado() { //getter de boolean
        return marcado;
    }

    public void minar(){
        minado = true;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isMinado() {
        return minado;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    boolean objetivoAlcançado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long miasNaVizinhança(){
        return  vizinhos.stream().filter(vizinhos -> vizinhos.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
        //reinicia os campos
    }

    @Override
    public String toString() {
        if (marcado){
            return "x";
        }else if(aberto && minado){
            return "*";
        }else if(aberto && miasNaVizinhança()> 0 ){
            return Long.toString(miasNaVizinhança());
        }else if(aberto){
            return "";
        }else {
            return "?";
        }



    }
}

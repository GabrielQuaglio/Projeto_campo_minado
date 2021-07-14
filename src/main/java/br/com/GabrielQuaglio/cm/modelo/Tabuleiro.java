package br.com.GabrielQuaglio.cm.modelo;

import br.com.GabrielQuaglio.cm.exceçao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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


    public void abrir(int linha,int coluna){
        try {


            campos.stream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());//o findFirst gera um optional, entao podemos ou nao termos
            //resutado por conta disso usamos o ifPresent
        }catch (ExplosaoException e){
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }

    public void marcar(int linha,int coluna){
        campos.stream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcaçao());//o findFirst gera um optional, entao podemos ou nao termos
        //resutado por conta disso usamos o ifPresent, que so faz a açao se o elemnto estiver presente

    }





    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                campos.add(new Campo(linha, coluna));
            }

        }

    }

    private void associarOsVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }


    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado =c -> c.isMinado();
        do {
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
            //count retorna long por isso o cast
        } while (minasArmadas < minas);
    }

    public boolean objetivoAlcançado() {
        return campos.stream().allMatch(c -> c.objetivoAlcançado());
    }

    public void reniciar() {
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i =0;
        sb.append(" ");
        sb.append(" ");
        for (int c = 0; c<colunas; c++){
            sb.append(" ");
            sb.append(c);
            sb.append(" ");


        }
        sb.append("\n");
        for( int l = 0; l< linhas; l++){
            sb.append(l);
            sb.append(" ");
            for(int c = 0; c < colunas; c++){
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
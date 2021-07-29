package br.com.GabrielQuaglio.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {


    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();//aqui foi preferido
    //organizar o tabuleiro como uma lista e nao como uma matriz
    private  final List<Consumer<Boolean>> observadores = new ArrayList<>();


    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarOsVizinhos();
        sortearMinas();
    }

    public void paraCada(Consumer<Campo> funçao){
        campos.forEach(funçao);
    }

    public void registrarObservadores(Consumer<Boolean> observador){
        observadores.add(observador);
    }

    private void notificarObservadores(boolean resultado){
        observadores.stream().forEach(obs -> obs.accept(resultado));
    }


    public void abrir(int linha,int coluna){
            campos.stream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());//o findFirst gera um optional, entao podemos ou nao termos
            //resutado por conta disso usamos o ifPresent
    }

    public void mostrarMinas(){
        campos.stream().filter(c -> c.isMinado()).
        forEach((c -> c.setAberto(true)));
    }

    public void marcar(int linha,int coluna){
        campos.stream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcaçao());//o findFirst gera um optional, entao podemos ou nao termos
        //resutado por conta disso usamos o ifPresent, que so faz a açao se o elemnto estiver presente

    }





    private void gerarCampos() {//cria o tabuleiro com os campos de acordo com o numero de linhas e colunas
        //passadas na instanciaçao do tabuleiro
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Campo campo = new Campo(linha,coluna);
                campo.registrarObservador(this);
                campos.add(campo);

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


    private void sortearMinas() {//sorteia o numero de minas passados na instanciaçao do tabulerio pelo
        //tabuleiro
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
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        if(evento == CampoEvento.EXPLODIR){
            mostrarMinas();
            notificarObservadores(false);

        }else if(objetivoAlcançado()){
            System.out.println("Ganhou ...");
            notificarObservadores(true);

        }
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
}
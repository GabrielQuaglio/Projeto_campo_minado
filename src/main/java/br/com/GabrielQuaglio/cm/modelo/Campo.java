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
    private List<CampoObservador> observadores = new ArrayList<>();


    public Campo(int linha, int coluna) {
        this.coluna = coluna;
        this.linha = linha;


    }

    public void registrarObservador(CampoObservador observador){
        observadores.add(observador);
    }

    private void notificarObservadores(CampoEvento evento){
        observadores.stream().forEach(obs -> obs.eventoOcorreu(this, evento) );
    }

    public boolean adicionarVizinho(Campo vizinho) {//metodo que adiciona vizinhos a um campo
        // a logica se resume em adicionar 2 tipos de vizinhos os das laterais e superiores e os diagonais
        // diagonais  o da diagonal sempre tera a subtraçao absoluta das posiçoes =2
        //. Exemplo: 1,1 - 2,2-> 1-2 = (-1) / 1-2 =(-1) / -1 + -1 = -2 que na forma absoluta fica =  a 2
        //laterais tem a mesma logica porem com o resultado absoluto da subtraçao =1
        //se as subtrçoes nao tiverem esses resultados, então não sao vizinhos
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
        //para marcar ou desmarcar um campo, que nao estja aberto
        if (!aberto) {
            marcado = !marcado;//assim podemos alternar se o campo esta marcado ou não

            if(marcado){
                notificarObservadores(CampoEvento.MARCAR);
            }else {
                notificarObservadores(CampoEvento.DESMARCAR);
            }
        }
    }

     public boolean abrir() { //para abrir um campo que nao esteja marcado e fechado
        if (!aberto && !marcado) {

            if (minado) {//se minado, joga a exceçao personalizada para quebrar o fluxo

                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }
            setAberto(true);
            notificarObservadores(CampoEvento.ABRIR);
            if(vizinhançaSegura()){//aqui usamos a recursividade, para conseguirmos expandir o abrimento
                //dos campos para os vizinhos dos vizinhos
                //aqui ele utiliza o "método vizinhançaSegura()", que verifica se a vizinhança esta segura
                //para poder abri-los e assim entrar nessa chamada recursiva até que a vizinhança nao seja mais segura
                vizinhos.forEach(vizinho -> vizinho.abrir());//aqui fizemos uma chamada recursiva
            }
            return true;
        }else {
            return false;
        }
    }


   //verifica se a vizinhança do campo passado é segura
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

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
        if(aberto){
            notificarObservadores(CampoEvento.ABRIR);
        }

    }

    boolean objetivoAlcançado(){//verifica se o objetivo foi alcançado
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }
    //conta o número de minas na vizinhança de um campo
    long minasNaVizinhança(){
        return  vizinhos.stream().filter(vizinhos -> vizinhos.minado).count();
    }

    void reiniciar(){//renicia o jogo reniciando os estados dos campos
        aberto = false;
        minado = false;
        marcado = false;
        //reinicia os campos
    }




    }


package modelo;

import br.com.GabrielQuaglio.cm.modelo.Campo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CampoTeste {

     Campo campo1 = new Campo(3,3);

    @Test
    void testeVizinhoEsquerda( ) {


        Campo vizinhoEsquerda = new Campo(3, 2);
        boolean testeEsquerda = campo1.adicionarVizinho(vizinhoEsquerda);
        assertTrue(testeEsquerda); //verifica se for true, se não false
    }
    @Test
    void testeVizinhoDireita( ) {
        Campo vizinhoDireita = new Campo(3, 4);
        boolean testeDireita = campo1.adicionarVizinho(vizinhoDireita);
        assertTrue(testeDireita); //verifica se for true, se não
    }
    @Test
    void testeVizinhoAcima( ) {
        Campo vizinhoAcima = new Campo(2, 3);
        boolean testeAcima = campo1.adicionarVizinho(vizinhoAcima);
        assertTrue(testeAcima); //verifica se for true, se não
    }
    @Test
    void testeVizinhoAbaixo( ) {
        Campo vizinhoAbaixo = new Campo(4, 3);
        boolean testeAbaixo = campo1.adicionarVizinho(vizinhoAbaixo);
        assertTrue(testeAbaixo); //verifica se for true, se não
    }
    @Test
    void testeVizinhoDiagonal( ) {
        Campo vizinhoDiagonal = new Campo(4, 4);
        boolean testeDiagonal = campo1.adicionarVizinho(vizinhoDiagonal);
        assertTrue(testeDiagonal); //verifica se for true, se não
    }
    }





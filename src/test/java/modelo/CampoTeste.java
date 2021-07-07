package modelo;

import br.com.GabrielQuaglio.cm.exceçao.ExplosaoException;
import br.com.GabrielQuaglio.cm.modelo.Campo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

     Campo campo33 = new Campo(3,3);

    @Test
    void testeVizinhoEsquerda( ) {
        Campo vizinhoEsquerda = new Campo(3, 2);
        boolean testeEsquerda = campo33.adicionarVizinho(vizinhoEsquerda);
        assertTrue(testeEsquerda); //verifica se for true, se não false
    }
    @Test
    void testeVizinhoDireita( ) {
        Campo vizinhoDireita = new Campo(3, 4);
        boolean testeDireita = campo33.adicionarVizinho(vizinhoDireita);
        assertTrue(testeDireita); //verifica se for true, se não da false
    }
    @Test
    void testeVizinhoAcima( ) {
        Campo vizinhoAcima = new Campo(2, 3);
        boolean testeAcima = campo33.adicionarVizinho(vizinhoAcima);
        assertTrue(testeAcima); //verifica se for true, se não da false
    }
    @Test
    void testeVizinhoAbaixo( ) {
        Campo vizinhoAbaixo = new Campo(4, 3);
        boolean testeAbaixo = campo33.adicionarVizinho(vizinhoAbaixo);
        assertTrue(testeAbaixo); //verifica se for true, se não da false
    }
    @Test
    void testeVizinhoDiagonal( ) {
        Campo vizinhoDiagonal = new Campo(4, 4);
        boolean testeDiagonal = campo33.adicionarVizinho(vizinhoDiagonal);
        assertTrue(testeDiagonal); //verifica se for true, se não da false
    }
    @Test
    void testevalorPadraoAtributoMarcado() {
        assertFalse(campo33.isMarcado());//verifica se o resultado é false(= true), se não (=false)
        //já esperamos que o valor de marcado seja false no começo
    }
    @Test
    void testeAlternarMarcaçao(){
        campo33.alternarMarcaçao();
        assertTrue(campo33.isMarcado());//como foi alterado a marcação
        //esperamos que de true

    }
     @Test
    void testeAlternarMarcaçaoDuasChamadas(){
        campo33.alternarMarcaçao();
        campo33.alternarMarcaçao();
        assertFalse(campo33.isMarcado());//esperamos que de false
        //pois ele era false, ai foi alternado 1 vez e virou true
        //entao ele foi alternado de novo e virou false
    }

    @Test
    void abrirCampoNaoMinadoNaoMarcado(){
        assertTrue(campo33.abrir());

    }
    @Test
    void abrirCampoMarcadoNaoMinado(){
        campo33.alternarMarcaçao();
        assertFalse(campo33.abrir());
    }

    @Test
    void abrirCampoMinadoMarcado(){
        campo33.minar();
        campo33.alternarMarcaçao();
        assertFalse(campo33.abrir());

    }

    @Test
    void abrirCampoMinadoNaoMarcado(){
        campo33.minar();
        assertThrows(ExplosaoException.class, ()-> {
            campo33.abrir();
        });//forma de testa a execuçao da exception para a explosao
        //no caso estamos dizendo que esperamos a "ExplosaoException"
        //para a execuçao do método "abrir"
    }
    @Test
    void testeAbrirComVizinhos(){

        Campo campo22 = new Campo(2,2);
        Campo campo11 = new Campo(1,1);

        campo22.adicionarVizinho(campo11);
        campo33.adicionarVizinho(campo22);

        assertTrue(campo33.abrir());
        assertTrue(campo33.isAberto());
        assertTrue(campo22.isAberto());
        assertTrue(campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinho2(){
        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,2);
        campo12.minar();

        Campo campo22 = new Campo(2,2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo33.adicionarVizinho(campo22);
        campo33.abrir();

        assertTrue(campo22.isAberto() && !campo11.isAberto());//o campo 11 não abre pois tem perigo
        //no campo 12


    }



    }





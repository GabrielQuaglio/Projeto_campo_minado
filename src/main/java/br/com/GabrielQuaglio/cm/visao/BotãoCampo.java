package br.com.GabrielQuaglio.cm.visao;

import br.com.GabrielQuaglio.cm.modelo.Campo;
import br.com.GabrielQuaglio.cm.modelo.CampoEvento;
import br.com.GabrielQuaglio.cm.modelo.CampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotãoCampo extends JButton implements CampoObservador, MouseListener {

    private Campo campo;
    private final Color BG_Padrao = new Color(184,184,184);
    private final Color BG_Marcado = new Color(8,179,247);
    private final Color BG_EXPLOSAO = new Color(189,66,68);
    private final Color TEXTO_VERDE = new Color(0,100,0);

    public BotãoCampo(Campo campo) {
        this.campo = campo;
        setBorder(BorderFactory.createBevelBorder(0));
        setBackground(BG_Padrao);
        addMouseListener(this);
        campo.registrarObservador(this);//registramos a classe botaoCampo
        //como observador
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento){
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;

            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_Padrao);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_EXPLOSAO);
        setText("X");
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_Marcado);
        setText("M");
    }

    private void aplicarEstiloAbrir() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if(campo.isMinado()){
            setBackground(BG_EXPLOSAO);
            return;
        }
        setBackground(BG_Padrao);

    switch (campo.minasNaVizinhança()){
        case 1:
            setForeground(TEXTO_VERDE);
            break;
        case 2:
            setForeground(Color.BLUE);
            break;
        case 3:
            setForeground(Color.yellow);
            break;
        case 4:
        case 5:
        case 6:
            setForeground(Color.RED);
            break;
        default:
            setForeground(Color.PINK);

    }
    String valor = !campo.vizinhançaSegura() ? campo.minasNaVizinhança() + "": "";

        setText(valor);
    }

    //interface dos eventos do mouse

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(mouseEvent.getButton()==1){
            campo.abrir();
        }else{
            System.out.println("outro Botão");
            campo.alternarMarcaçao();
        }
    }

//não usaremos essas mas tivemos que implementar, se não daria erro
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}
}

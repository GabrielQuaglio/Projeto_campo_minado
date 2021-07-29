package br.com.GabrielQuaglio.cm.visao;

import br.com.GabrielQuaglio.cm.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    //Panel agrupa outros componentes dentro dele, como varios botãoes


    public PainelTabuleiro(Tabuleiro tabuleiro) {

        setLayout(new GridLayout(tabuleiro.getLinhas()
                ,tabuleiro.getColunas()));
        //gridLayout é um layout que organiza os componentes em linhas e colunas

        tabuleiro.paraCada(c-> add(new BotãoCampo(c)));
        tabuleiro.registrarObservadores(e->{
            //TODO mostrar resultado para usuario
        });
        }
    }


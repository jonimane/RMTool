/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model;

import javafx.util.Callback;

/**
 *
 * @author jonimane
 */
public abstract class AbstractListController<T> extends AbstractController {
    public T ultimaSelecao;

    public T getUltimaSelecao() {
        return ultimaSelecao;
    }

    public void setUltimaSelecao(T ultimaSelecao) {
        this.ultimaSelecao = ultimaSelecao;
        aoSelecionar();
    }
    
    public abstract void aoSelecionar();
}

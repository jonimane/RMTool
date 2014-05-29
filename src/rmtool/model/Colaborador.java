/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import rmtool.model.bean.Usuario;

/**
 *
 * @author jonimane
 */
public class Colaborador {
    private Usuario usuario;
    private ReadOnlyBooleanWrapper colabora;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ReadOnlyBooleanWrapper colaboraProperty() {
        return colabora;
    }

    public void setColabora(ReadOnlyBooleanWrapper colabora) {
        this.colabora = colabora;
    }
    
    public String getNome()
    {
        return usuario.getNome();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model;

/**
 *
 * @author jonimane
 */
public abstract class AbstractFormController<T> extends AbstractController implements FormController<T> {
    public T editando;
    
    @Override
    public void editar(T o) {
        editando = o;
        carregarBean();
    }
    
    public abstract void carregarBean();
    public abstract void atualizarBean();
}

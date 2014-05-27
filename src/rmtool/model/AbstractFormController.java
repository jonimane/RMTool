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
    
    /**
     * Método responsável por atualizar o Formulário de acordo com o Bean
     */
    public abstract void carregarBean();
    
    /**
     * Método responsável por atualizar o Bean de acordo com o Formulário
     */
    public abstract void atualizarBean();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.view.components;

import javafx.scene.control.TreeItem;
import rmtool.model.NomeEditavel;

/**
 *
 * @author jonimane
 */
public class TreeItemImpl extends TreeItem<String> {
    private NomeEditavel objetoEditavel;
    
    public NomeEditavel getObjetoEditavel()
    {
        return objetoEditavel;
    }
    
    public TreeItemImpl setObjetoEditavel( NomeEditavel objeto )
    {
        objetoEditavel = objeto;
        setValue( objeto.getNomeEditavel() );
        
        return this;
    }
}

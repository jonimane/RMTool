/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.view.components;

import javafx.scene.control.TreeItem;

/**
 *
 * @author jonimane
 */
public class TreeItemImpl extends TreeItem<String> {
    private Object objeto;
    
    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
    public TreeItemImpl getParentTI()
    {
        if( getParent() instanceof TreeItemImpl )
        {
            return (TreeItemImpl) getParent();
        }
        
        return null;
    }
}

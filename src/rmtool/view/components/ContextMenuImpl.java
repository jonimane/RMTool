/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.view.components;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;

/**
 *
 * @author jonimane
 */
public class ContextMenuImpl extends ContextMenu {
    private Node pai;
    
    public ContextMenuImpl( Node pai ) {
        super();
        this.pai = pai ;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }
}

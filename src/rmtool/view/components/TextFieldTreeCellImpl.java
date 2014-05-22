/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmtool.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author jonimane
 */
public class TextFieldTreeCellImpl extends TreeCell<String> {
    private TextField textField;
    private ContextMenu addMenu = new ContextMenu();

    public TextFieldTreeCellImpl() {
        MenuItem addMenuItem = new MenuItem("...");
        addMenu.getItems().add(addMenuItem);
        
        addMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
    }
    
    @Override
    public void startEdit() {
        if( !( getTreeItem() instanceof TreeItemImpl ) )
        {
            return;
        }
        
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        
        setText(null);
        setGraphic(textField);
        textField.requestFocus();
        textField.selectAll();
    }
    
    @Override
    public void cancelEdit() {
        super.cancelEdit();

        textField.setText(getItem());
        setText(getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                
                if ( getTreeItem().getParent() != null ){
                    setContextMenu(addMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField( getString() );
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem();
    }
}

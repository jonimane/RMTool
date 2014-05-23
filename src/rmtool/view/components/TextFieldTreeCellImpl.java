/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmtool.view.components;

import rmtool.model.config.Menus;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import rmtool.model.NomeEditavel;

/**
 *
 * @author jonimane
 */
public class TextFieldTreeCellImpl extends TreeCell<String> {
    private TextField textField;
    private ContextMenuImpl menu;

    public TextFieldTreeCellImpl() {
        menu = new ContextMenuImpl(this);
        
        // Adicionar Item para gerar o evento OnShowing
        MenuItem addMenuItem = new MenuItem("Carregando...");
        menu.getItems().add(addMenuItem);
        
        menu.setOnShowing( menuGerarItens() );
    }
    
    public EventHandler<WindowEvent> menuGerarItens()
    {
        return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ContextMenuImpl menu = ( ContextMenuImpl ) event.getSource();
                menu.getItems().clear();
                
                TextFieldTreeCellImpl pai = (TextFieldTreeCellImpl) menu.getPai();
                TreeItemImpl ti = ( TreeItemImpl ) pai.getTreeItem();
                
                List<MenuItem> novosItens = new ArrayList<>();
                
                switch( ti.getObjeto().getClass().getSimpleName() )
                {
                    case "Projeto":
                        novosItens.addAll( Menus.Projeto.gerarItens( pai ) );
                    break;
                    
                    case "Requisito":
                        novosItens.addAll( Menus.Requisito.gerarItens( pai ) );
                    break;
                    
                    case "TipoRequisito":
                        novosItens.addAll( Menus.TipoRequisito.gerarItens( pai ) );
                    break;
                }
                
                if( novosItens.isEmpty() )
                {
                    MenuItem item = new MenuItem("...");
                    item.setDisable(true);
                    novosItens.add( item );
                }
                
                menu.getItems().addAll( novosItens );
            }
        };
    }
    
    @Override
    public void startEdit() {
        TreeItemImpl ti = (TreeItemImpl) getTreeItem();
        
        if( !( ti.getObjeto() instanceof NomeEditavel ) )
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
                    setContextMenu(menu);
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

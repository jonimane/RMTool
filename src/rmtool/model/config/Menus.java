/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.config;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import rmtool.model.TabManager;
import rmtool.model.TipoRequisito;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Requisito;
import rmtool.view.components.TextFieldTreeCellImpl;
import rmtool.view.components.TreeItemImpl;

/**
 *
 * @author jonimane
 */
public enum Menus {
    Projeto,
    TipoRequisito,
    Requisito;
    
    private TextFieldTreeCellImpl pai;
    
    public List<MenuItem> gerarItens( TextFieldTreeCellImpl pai )
    {
        setPai(pai);
        
        switch( this )
        {
            case Projeto:
                return gerarProjeto();

            case Requisito:
                return gerarRequisito();
                
            case TipoRequisito:
                return gerarTipoRequisito();
            
            default:
                return null;
        }
    }
    
    public List<MenuItem> gerarProjeto()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Editar", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = getProjeto();
            }
        }));

        lista.add( gerarMenuItem("Excluir", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = getProjeto();
            }
        }));

        lista.add( gerarMenuItem("Rastreabilidade", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        }));
        
        return lista;
    }
    
    public List<MenuItem> gerarRequisito()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Editar", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = getRequisito();
            }
        }));
        
        lista.add( gerarMenuItem("Excluir", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = getRequisito();
            }
        }));
        
        return lista;
    }
    
    public List<MenuItem> gerarTipoRequisito()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Criar Requisito", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TabManager.getInstance().criar( Telas.RequisitoForm );
            }
        }));
        
        return lista;
    }

    public TextFieldTreeCellImpl getPai() {
        return pai;
    }

    public void setPai(TextFieldTreeCellImpl pai) {
        this.pai = pai;
    }
    
    public Projeto getProjeto()
    {
        TreeItemImpl ti = ( TreeItemImpl ) pai.getTreeItem();
        
        return (Projeto) ti.getObjeto();
    }
    
    public Requisito getRequisito()
    {
        TreeItemImpl ti = ( TreeItemImpl ) pai.getTreeItem();
        
        return (Requisito) ti.getObjeto();
    }
    
    public TipoRequisito getTipoRequisito()
    {
        TreeItemImpl ti = ( TreeItemImpl ) pai.getTreeItem();
        
        return (TipoRequisito) ti.getObjeto();
    }
    
    public MenuItem gerarMenuItem( String titulo, EventHandler<ActionEvent> acao)
    {
        MenuItem mi = new MenuItem();
        mi.setText(titulo);
        mi.setOnAction( acao );
        
        return mi;
    }
}

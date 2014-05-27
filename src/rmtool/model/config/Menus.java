/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.config;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractFormController;
import rmtool.model.TabManager;
import rmtool.model.TipoRequisito;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Requisito;
import rmtool.model.dao.ProjetoDAO;
import rmtool.model.dao.RequisitoDAO;
import rmtool.view.components.TextFieldTreeCellImpl;
import rmtool.view.components.TreeItemImpl;

/**
 *
 * @author jonimane
 */
public enum Menus {
    Root,
    Projeto,
    TipoRequisito,
    Requisito;
    
    private TextFieldTreeCellImpl pai;
    
    public List<MenuItem> gerarItens( TextFieldTreeCellImpl pai )
    {
        setPai(pai);
        
        switch( this )
        {
            case Root:
                return gerarRoot();
                
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
    
    public List<MenuItem> gerarRoot()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Adicionar Projeto", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = new Projeto();
                chamarTelaEditar(Telas.ProjetoForm, p);
            }
        }));
        
        return lista;
    }

    /**
     * Método responsável por gerar os menus para um Projeto
     * 
     * @return List<MenuItem>
     */
    public List<MenuItem> gerarProjeto()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Adicionar Colaborador", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = getObjeto( getTI() );
                chamarTelaEditar(Telas.ProjetoForm, p);
            }
        }));

        lista.add( gerarMenuItem("Rastreabilidade", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        }));
        
        lista.add( gerarMenuSeparador() );
        
        lista.add( gerarMenuItem("Editar", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = getObjeto( getTI() );
                chamarTelaEditar(Telas.ProjetoForm, p);
            }
        }));

        lista.add( gerarMenuItem("Excluir", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Projeto p = getObjeto( getTI() );
                
                Dialog.buildConfirmation("Confirmar", "Deseja realmente excluir esse projeto?").addYesButton( new EventHandler<Event>() {
                    @Override
                    public void handle(Event t) {
                        ProjetoDAO projetoDAO = new ProjetoDAO();
                        projetoDAO.excluir(p);
                        TabManager.getInstance().getMain().tvListaAtualizar();
                    }
                }).addNoButton( new EventHandler<Event>() {
                    @Override
                    public void handle(Event t) {
                        
                    }
                }).build().show();
            }
        }));
        
        return lista;
    }
    
    /**
     * Método responsável por gerar os menus para um Requisito
     * 
     * @return List<MenuItem>
     */
    public List<MenuItem> gerarRequisito()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Editar", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = getObjeto( getTI() );
                chamarTelaEditar(Telas.RequisitoForm, r);
            }
        }));
        
        lista.add( gerarMenuItem("Excluir", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Requisito r = getObjeto( getTI() );
                
                Dialog.buildConfirmation("Confirmar", "Deseja realmente excluir esse requisito?").addYesButton( new EventHandler<Event>() {
                    @Override
                    public void handle(Event t) {
                        RequisitoDAO requisitoDAO = new RequisitoDAO();
                        requisitoDAO.excluir(r);
                        TabManager.getInstance().getMain().tvListaAtualizar();
                    }
                }).addNoButton( new EventHandler<Event>() {
                    @Override
                    public void handle(Event t) {
                        
                    }
                }).build().show();
            }
        }));
        
        return lista;
    }
    
    /**
     * Método responsável por gerar os menus para um TipoRequisito
     * 
     * @return List<MenuItem>
     */
    public List<MenuItem> gerarTipoRequisito()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Criar Requisito", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = new Requisito();
                r.setTipo( (TipoRequisito) getObjeto( getTI() ) );
                r.setProjeto( (Projeto) getObjeto( getTI().getParentTI() ) );
                chamarTelaEditar(Telas.RequisitoForm, r);
            }
        }));
        
        return lista;
    }
    
    public <T> void chamarTelaEditar( Telas tela, T editando )
    {
        TabManager tm = TabManager.getInstance();
        Tab t = tm.criar(tela);
        AbstractFormController controller = tm.get(t);
        controller.editar( editando );
    }

    /**
     * Método responsável para retornar qual a TreeCell em que está acontecendo o evento
     * 
     * @return TextFieldTreeCellImpl
     */
    public TextFieldTreeCellImpl getPai() {
        return pai;
    }

    public void setPai(TextFieldTreeCellImpl pai) {
        this.pai = pai;
    }
    
    public TreeItemImpl getTI()
    {
        return ( TreeItemImpl ) pai.getTreeItem();
    }
    
    public <T> T getObjeto( TreeItemImpl ti )
    {
        return (T) ti.getObjeto();
    }
    
    /**
     * Gera um Menu de acordo com os parametros passados e BINDA o EventHandler enviado nele
     * 
     * @param titulo
     * @param acao
     * @return MenuItem
     */
    public MenuItem gerarMenuItem( String titulo, EventHandler<ActionEvent> acao)
    {
        MenuItem mi = new MenuItem();
        mi.setText(titulo);
        mi.setOnAction( acao );
        
        return mi;
    }
    
    /**
     * Gera um separador de Menu
     * 
     * @return MenuItem
     */
    public MenuItem gerarMenuSeparador()
    {
        return new SeparatorMenuItem();
    }
}

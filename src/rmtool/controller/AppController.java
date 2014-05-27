/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import rmtool.model.Controller;
import rmtool.model.NomeEditavel;
import rmtool.model.SessionManager;
import rmtool.model.TabManager;
import rmtool.model.config.Telas;
import rmtool.model.TipoRequisito;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Requisito;
import rmtool.model.bean.Usuario;
import rmtool.model.config.Menus;
import rmtool.model.dao.ProjetoDAO;
import rmtool.model.dao.RequisitoDAO;
import rmtool.view.components.TextFieldTreeCellImpl;
import rmtool.view.components.TreeItemImpl;

/**
 * FXML Controller class
 *
 * @author jonimane
 */
public class AppController implements Initializable {

    @FXML
    public TabPane tpPrincipal;
    
    @FXML
    public TreeView tvLista;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater( new Runnable() {
            @Override
            public void run() {
                TabManager tm = TabManager.getInstance();
                tm.criar( Telas.Apresentacao );
            }
        });
        
        tvListaGerar();
    }
    
    public void tvListaGerar()
    {
        // Criando nó root
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        
        // Setando nó root na TreeView
        tvLista.setRoot(root);
        
        // Adicionando Itens ao nó root
        tvListaAtualizar( root );
        
        // Injetando TreeCell customizada
        tvLista.setCellFactory( tvListaSetCellFactory() );
        
        // Evento para verificar o suporte a editar item pelo TreeView
        tvLista.setOnEditCommit( tvListaSetOnEditCommit() );
        
        // Adicionar ContextMenu na TreeView
        tvLista.setContextMenu( tvListaGerarContextMenu() );
    }
    
    public void tvListaAtualizar()
    {
        tvListaAtualizar( tvLista.getRoot() );
    }
    
    public void tvListaAtualizar( TreeItem<String> root )
    {
        Usuario u = (Usuario) SessionManager.getInstance().get("usuario");
        List<Projeto> projetos = carregarProjetosUsuario(u);
        
        root.getChildren().clear();
        root.getChildren().addAll( gerarListaProjetos( projetos ) );
    }
    
    public List<Projeto> carregarProjetosUsuario( Usuario u )
    {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        
        return projetoDAO.procurar(u);
    }
    
    public List<TreeItemImpl> gerarListaProjetos( List<Projeto> projetos )
    {
        List<TreeItemImpl> lista = new ArrayList<>();
        TreeItemImpl temp = null;
        
        for (Projeto projeto : projetos) {
            temp = new TreeItemImpl();
            temp.setValue( projeto.getNome() );
            temp.setObjeto( projeto );
            temp.setExpanded( true );
            temp.getChildren().addAll( gerarListaRequisitos( projeto ) );
            lista.add( temp );
        }
        
        return lista;
    }
    
    public List<TreeItemImpl> gerarListaRequisitos( Projeto p )
    {
        RequisitoDAO requisitoDAO = new RequisitoDAO();
        List<Requisito> requisitos = requisitoDAO.procurar( p );
        
        return gerarListaTipoRequisito( requisitos );
    }
    
    public List<TreeItemImpl> gerarListaTipoRequisito( List<Requisito> requisitos )
    {
        List<TreeItemImpl> lista = new ArrayList<>();
        TreeItemImpl temp = null;
        
        for( TipoRequisito tipo : TipoRequisito.values() )
        {
            temp = new TreeItemImpl();
            temp.setValue( tipo.toString() );
            temp.setObjeto( tipo );
            temp.setExpanded( true );
            temp.getChildren().addAll( selecionarRequisitos(requisitos, tipo) );
            lista.add( temp );
        }
        
        return lista;
    }
    
    public List<TreeItemImpl> selecionarRequisitos( List<Requisito> requisitos, TipoRequisito tipo )
    {
        List<TreeItemImpl> selecao = new ArrayList<>();
        TreeItemImpl temp = null;
        
        for( Requisito requisito : requisitos )
        {
            if( TipoRequisito.parse( requisito.getTipo() ) == tipo )
            {
                temp = new TreeItemImpl();
                temp.setValue( requisito.getNome() );
                temp.setObjeto( requisito );
                temp.setExpanded( true );
                selecao.add( temp );
            }
        }
        
        return selecao;
    }
    
    public Callback<TreeView<String>,TreeCell<String>> tvListaSetCellFactory()
    {
        return new Callback<TreeView<String>,TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl();
            }
        };
    }
    
    public EventHandler<TreeView.EditEvent<String>> tvListaSetOnEditCommit()
    {
        return new EventHandler<TreeView.EditEvent<String>>() {
            @Override
            public void handle(TreeView.EditEvent<String> event) {
                TreeItem ti = event.getTreeItem();
                
                if( ti instanceof TreeItemImpl )
                {
                    Object o = ((TreeItemImpl) ti).getObjeto();
                    
                    if( o instanceof NomeEditavel )
                    {
                        ((NomeEditavel)o).setNomeEditavel( event.getNewValue() ).salvar();
                    }
                }
            }
        };
    }

    private ContextMenu tvListaGerarContextMenu() {
        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll( Menus.Root.gerarItens(null));
        
        return menu;
    }
}

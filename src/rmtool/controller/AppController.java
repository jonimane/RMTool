/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.tree.TreeCellEditor;
import rmtool.SessionManager;
import rmtool.TabManager;
import rmtool.Telas;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.ProjetoDAO;
import rmtool.view.components.TextFieldTreeCellImpl;

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
        
        Usuario u = (Usuario) SessionManager.getInstance().get("usuario");
        List<Projeto> projetos = carregarProjetosUsuario(u);
        atualizarLista(projetos);
    }
    
    public List<Projeto> carregarProjetosUsuario( Usuario u )
    {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        
        return projetoDAO.procurarPorUsuario(u);
    }
    
    public void atualizarLista( List<Projeto> projetos )
    {
        // Injetando TreeCell customizada
        tvLista.setCellFactory( new Callback<TreeView<String>,TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl();
            }
        });
        
        // Criando nó root
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        
        // Adicionando Itens ao nó root
        for (Projeto projeto : projetos) {
            TreeItem<String> ti = new TreeItem<>();
            ti.setValue( projeto.getNome() );
            
            root.getChildren().add(ti);
        }
        
        // Setando nó root na TreeView
        tvLista.setRoot(root);
        
        // Adicionado listener ao mudar seleção da TreeView
//        tvLista.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<TreeItem<String>>() {
//            @Override
//            public void changed(ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> anterior, TreeItem<String> atual) {
//                tvLista.edit( atual );
//            }
//        });
    }
    
    public void onEditarNomeProjeto( Event event)
    {
        
    }
    
    public void onDoubleClick( MouseEvent event )
    {
        if( event.getButton().equals( MouseButton.PRIMARY ) )
        {
            if(event.getClickCount() == 2)
            {
                
            }
        }
    }
}

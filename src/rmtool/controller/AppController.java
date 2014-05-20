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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import rmtool.SessionManager;
import rmtool.TabManager;
import rmtool.Telas;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.ProjetoDAO;

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
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        
        for (Projeto projeto : projetos) {
            TreeItem<String> ti = new TreeItem<>();
            ti.setValue( projeto.getNome() );
            
            root.getChildren().add(ti);
        }
        
        tvLista.setRoot(root);
    }
}
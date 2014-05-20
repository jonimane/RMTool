/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.RMTool;
import rmtool.TabManager;
import rmtool.Telas;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author jonimane
 */
public class EntrarController implements Initializable {
    @FXML
    public Button btnEntrar;
    @FXML
    public Button btnSair;
    @FXML
    public TextField txtUsuario;
    @FXML
    public TextField txtSenha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void actionEntrar( ActionEvent e )
    {
        Usuario u = new Usuario();
        u.setLogin( txtUsuario.getText() );
        u.setSenha( txtSenha.getText() );
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        u = usuarioDAO.validar( u );
        
        if( u == null )
        {
            Dialog.showError("RMTool", "Login ou Senha de usuário inválido!");
        }
        else
        {
            try {
                FXMLLoader fl = new FXMLLoader();
                Parent root = (Parent) fl.load( getClass().getResource( Telas.App.getFXML() ).openStream() );
                
                // Registrar AppController no TabManager
                TabManager.getInstance().setMain( (AppController) fl.getController());
                
                Scene scene = new Scene(root);
                RMTool.getInstance().mudarCena(scene);
            } catch (IOException ex) {
                Logger.getLogger(EntrarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void actionSair( ActionEvent e )
    {
        Dialog.buildConfirmation("Confirmar", "Deseja realmente sair?").addYesButton( new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                Platform.exit();
            }
        }).addNoButton( new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
            }
        }).build().show();
    }
}

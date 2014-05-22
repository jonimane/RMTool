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
import rmtool.model.SessionManager;
import rmtool.model.TabManager;
import rmtool.model.Telas;
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
    
    public SessionManager sm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sm = SessionManager.getInstance();
        
        setAtalhos( true );
        
        Platform.runLater( new Runnable() {
            @Override
            public void run() {
                txtUsuario.requestFocus();
            }
        });
    }
    
    public Usuario gerarUsuario()
    {
        Usuario u = new Usuario();
        u.setLogin( txtUsuario.getText() );
        u.setSenha( txtSenha.getText() );
        
        return u;
    }
    
    @FXML
    public void actionEntrar( ActionEvent e )
    {
        Usuario u = gerarUsuario();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        u = usuarioDAO.validar( u );
        
        if( u == null )
        {
            Dialog.showError("RMTool", "Login ou Senha de usuário inválido!");
        }
        else
        {
            setAtalhos( false );
            sm.put("usuario", u);
            carregarApp();
        }
    }
    
    public void carregarApp()
    {
        try {
            FXMLLoader fl = new FXMLLoader();
            Parent root = (Parent) fl.load( getClass().getResource( Telas.App.getFXML() ).openStream() );

            // Registrar AppController no TabManager
            TabManager.getInstance().setMain( (AppController) fl.getController() );

            RMTool rmTool = (RMTool) sm.get("RMTool");
            Scene scene = new Scene(root);
            rmTool.mudarCena(scene);
            rmTool.maximarTela();
        } catch (IOException ex) {
            Logger.getLogger(EntrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAtalhos( boolean status )
    {
        btnEntrar.setDefaultButton(status);
        btnSair.setCancelButton(status);
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

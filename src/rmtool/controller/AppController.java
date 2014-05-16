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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import rmtool.Telas;

/**
 * FXML Controller class
 *
 * @author jonimane
 */
public class AppController implements Initializable {

    @FXML
    public TabPane tpPrincipal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTab( Telas.Apresentacao );
    }
    
    public void carregarTab( Telas tela )
    {
        Tab t = carregarFXML( tela );
        addTab( t );
    }
    
    public void addTab( Tab t )
    {
        tpPrincipal.getTabs().add( t );
    }
    
    public Tab carregarFXML( Telas tela )
    {
        try {
            FXMLLoader l = new FXMLLoader();
            Parent root = (Parent) l.load( getClass().getResource( tela.getFXML() ).openStream() );
            
            Tab t = new Tab();
            t.setContent( root );
            t.setText( tela.toString() );
            t.setClosable( true );
            
            return t;
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
}

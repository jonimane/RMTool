/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import rmtool.TabManager;
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
        Platform.runLater( new Runnable() {
            @Override
            public void run() {
                TabManager tm = TabManager.getInstance();
                tm.criar( Telas.Apresentacao );
            }
        });
        
    }
}

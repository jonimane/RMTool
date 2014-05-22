/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import rmtool.model.Controller;
import rmtool.model.TabManager;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class UsuarioFormController implements Initializable, Controller {

   @FXML
   private TextField usuarioNome;
   @FXML 
   private TextField usuarioEmail;
   @FXML
   private TextField usuarioLogin;
   @FXML
   private TextField usuarioSenha;
   
   @FXML
   private Button btnCancelar;
   
   @FXML
   private void criarUsuario(ActionEvent event){
       Usuario usa = new Usuario();
       UsuarioDAO usaDao = new UsuarioDAO();
       
       usa.setNome("usuarioNome");
       usa.setEmail("usuarioEmail");
       usa.setLogin("usuarioLogin");
       usa.setSenha("usuarioSenha");
       
       usaDao.add(usa);
       
   }
   
   @FXML
   private void cancelar( ActionEvent event )
   {
       TabManager.getInstance().fechar( this );
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

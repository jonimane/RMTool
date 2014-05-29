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
import rmtool.model.SessionManager;
import rmtool.model.TabManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractFormController;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Thiago Rodrigo
 */
public  class UsuarioFormController extends AbstractFormController <Usuario> implements Initializable {
    
    
   @FXML
   public Label btnCadastrar ;
   @FXML
   public Label lblId;
   @FXML
   public TextField txtId;
   @FXML
   public TextField usuarioNome;
   @FXML 
   public TextField usuarioEmail;
   @FXML
   public TextField usuarioLogin;
   @FXML
   public TextField usuarioSenha;
   
   @FXML
   private Button btnCancelar;
   
   @FXML
   private Button btnCriarUsuario;
   
   @FXML
   private void criarUsuario(ActionEvent event){
       atualizarBean();
       try{
           Usuario usa = new Usuario();
           UsuarioDAO usaDao = new UsuarioDAO();
           
           if(editando.getNome().length()==0 || editando.getEmail().length()== 0 || editando.getLogin().length()== 0 || editando.getSenha().length()== 0)
       
      
            {
                Dialog.showError("RMTool", "Preencha todos os campos para continuar!!");
            }
           else 
           {
               if(editando.getId()== null)
               {
                 Usuario usuarioLogado = SessionManager.getInstance().get("usuario");
                 }
                 usaDao.salvar(editando);
                 TabManager.getInstance().getMain().tvListaAtualizar();
                Dialog.showInfo("RMTool", "Projeto criado com sucesso!!"); 
                fechar();
                 
               }
        }
        catch( Exception e )
        {
            Dialog.showInfo("RMTool", "Problema ao salvar Projeto!!");
        }
    }
   
   @FXML
   private void cancelar( ActionEvent event )
   {
       fechar();
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void carregarBean() {
     
        if( editando.getId() != null )
        {
            txtId.setText( editando.getId().toString() );
        }
        
        usuarioNome.setText(editando.getNome());
        usuarioEmail.setText(editando.getEmail());
        usuarioLogin.setText(editando.getLogin());
        usuarioSenha.setText(editando.getSenha());
    }
 

    @Override
    public void atualizarBean() {
        editando.setNome(usuarioNome.getText());
        editando.setEmail(usuarioEmail.getText());
        editando.setLogin(usuarioLogin.getText());
        editando.setSenha(usuarioSenha.getText());
       
    }
    
}

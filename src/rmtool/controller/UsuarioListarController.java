/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rmtool.model.AbstractController;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.ProjetoDAO;
import rmtool.model.dao.UsuarioDAO;


/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class UsuarioListarController extends AbstractController implements Initializable {

    
    @FXML
    private TextField buscarId;
    @FXML
    private TextField buscarNome;
   
    @FXML 
    private  TextArea buscarEmail;
    @FXML 
    private  TextField buscarLogin;
    @FXML 
    private  TextField buscarSenha;
    @FXML
    public Button btnBuscar;
    @FXML
    public Button btnAlterar;
    @FXML 
    public Button btnExluir;
    
    @FXML 
    public Button btnLimparCampos;


    /**
     * Initializes the controller class.
     */

    @FXML
    private void buscarUsuarioId(ActionEvent event)throws Exception
    {
        UsuarioDAO usaDao = new UsuarioDAO();
             
        List<Usuario> listaUsuarios =  usaDao.listar();
        
        if (listaUsuarios != null){ 
            for (Iterator<Usuario> i =  listaUsuarios.iterator(); i.hasNext(); )  
            {    
                Usuario u = i.next();  
                    if (u.getId().equals( buscarId.getText())){
                        buscarNome.setText(u.getNome());
                        buscarEmail.setText(u.getEmail());
                        buscarLogin.setText(u.getLogin());
                        buscarSenha.setText(u.getSenha());
                    }          
            } 
        }  
    }
    
    @FXML
    private void alterarUsuario(ActionEvent event) throws Exception {
        
        Usuario usa = new Usuario();
        usa.setNome(buscarNome.getText());
        usa.setEmail(buscarEmail.getText());
        usa.setLogin(buscarLogin.getText());
        usa.setSenha(buscarSenha.getText());
        
        UsuarioDAO usaDao = new UsuarioDAO();
        usaDao.editar(usa);
    }
    
    @FXML
    private void excluirUsuario (ActionEvent event)throws Exception{

         UsuarioDAO usaDao = new UsuarioDAO();
             
        List<Usuario> listaUsuarios =  usaDao.listar();
        
        if (listaUsuarios != null){ 
            for (Iterator<Usuario> i =  listaUsuarios.iterator(); i.hasNext(); )  
            {    
                Usuario u = i.next();  
                    if (u.getNome().equals( buscarNome.getText())){
                        usaDao.deletar(u);
                    }          
            } 
        }  
    }
    
    @FXML
    private void limparCampos(ActionEvent event)throws Exception{
        buscarNome.clear();
        buscarId.clear();
        buscarEmail.clear();
        buscarLogin.clear();
        buscarSenha.clear();
    }
    
    @FXML
    public void fecharTela()
    {
        fechar();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}

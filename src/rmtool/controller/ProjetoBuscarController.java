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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rmtool.model.Controller;
import rmtool.model.TabManager;
import rmtool.model.bean.Projeto;
import rmtool.model.dao.ProjetoDAO;


/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class ProjetoBuscarController implements Initializable, Controller {

    @FXML
    private TextField buscarNome;
    @FXML 
    private  TextArea buscarDescricao ;

    /**
     * Initializes the controller class.
     */

    @FXML
    private Projeto buscarProjetoNome(ActionEvent event)throws Exception
    {
        Projeto pro = new Projeto();
        ProjetoDAO proDao = new ProjetoDAO();
        
        String n = pro.getNome();
        
        proDao.buscarProjeto(n);
        
        return pro;
        
    }
    
    @FXML
    private void alterarProjeto(ActionEvent event) throws Exception {
        
        Projeto pro = new Projeto();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        
        pro.setNome("nome");
        pro.setDescricao("descricao");
        projetoDAO.alterar(pro);
    }
    
    @FXML
    public void fecharTela()
    {
        TabManager.getInstance().fechar(this);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}

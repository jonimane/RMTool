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
import rmtool.model.Controller;
import rmtool.model.bean.Projeto;
import rmtool.model.dao.ProjetoDAO;

/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class ProjetoAlterarController implements Initializable, Controller {

    @FXML
    private void AlterarProjeto(ActionEvent event) throws Exception {
        
        Projeto pro = new Projeto();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        
        pro.setNome("nome");
        pro.setDescricao("descricao");
        projetoDAO.alterar(pro);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

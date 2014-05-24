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
import rmtool.model.AbstractFormController;
import rmtool.model.bean.Projeto;
import rmtool.model.dao.ProjetoDAO;

/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class ProjetoAlterarController extends AbstractFormController<Projeto> implements Initializable {
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

    @Override
    public void carregarBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

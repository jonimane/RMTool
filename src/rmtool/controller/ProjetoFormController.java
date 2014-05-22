/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

/*import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
*/
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rmtool.model.Controller;
import rmtool.model.bean.Projeto;
import rmtool.model.dao.ProjetoDAO;
/**
/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class ProjetoFormController implements Initializable, Controller {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public TextField nomeProjeto;
    public TextArea descricaoProjeto;
    
    @FXML
    public void criarProjeto(ActionEvent event) {
        
        Projeto pro = new Projeto();
        ProjetoDAO proDao = new ProjetoDAO();
        
        pro.setNome("nomeProjeto");
        pro.setDescricao("descricaoProjeto");
        
        proDao.criar(pro);

    }
    
    //@FXML void AlterarProjeto
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

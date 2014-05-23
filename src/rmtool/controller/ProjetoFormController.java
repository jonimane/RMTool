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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.Controller;
import rmtool.model.TabManager;
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
    @FXML
    public TextArea descricaoProjeto;
    @FXML
    public Button cancelar;
    
    @FXML
    public void criarProjeto(ActionEvent event) {
        
        Projeto pro = new Projeto();
        pro.setNome(nomeProjeto.getText());
        pro.setDescricao(descricaoProjeto.getText());
        
        ProjetoDAO proDao = new ProjetoDAO();
        
       
        if( pro.getNome().length() == 0 || pro.getDescricao().length() == 0 ){
            Dialog.showError("RMTool", "Preencha todos os campos para continuar!!");
        }else{
            Dialog.showInfo("RMTool", "Projeto criado com sucesso!!"); 
            proDao.criar(pro);
            
            nomeProjeto.clear();
            descricaoProjeto.clear();
        }
        
    }
    @FXML
    public void fecharTela()
    {
        TabManager.getInstance().fechar(this);
    }
    //@FXML void AlterarProjeto
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

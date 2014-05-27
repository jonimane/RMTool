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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rmtool.model.AbstractController;
import rmtool.model.bean.Projeto;
import rmtool.model.dao.ProjetoDAO;


/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class ProjetoBuscarController extends AbstractController implements Initializable {

    @FXML
    private TextField buscarNome;
    @FXML 
    private  TextArea buscarDescricao;

    /**
     * Initializes the controller class.
     */

    @FXML
    private void buscarProjetoNome(ActionEvent event)throws Exception
    {
        ProjetoDAO proDao = new ProjetoDAO();
             
        List<Projeto> listaProjeto =  proDao.listar();
        
        if (listaProjeto != null){ 
            for (Iterator<Projeto> i =  listaProjeto.iterator(); i.hasNext(); )  
            {    
                Projeto p = i.next();  
                    if (p.getNome().equals( buscarNome.getText())){
                        buscarDescricao.setText(p.getDescricao());
                    }          
            } 
        }  
    }
    
    @FXML
    private void alterarProjeto(ActionEvent event) throws Exception {
        
        Projeto pro = new Projeto();
        pro.setNome(buscarNome.getText());
        pro.setDescricao(buscarDescricao.getText());
        
        ProjetoDAO proDao = new ProjetoDAO();
        proDao.alterar(pro);
    }
    
    @FXML
    private void excluirProjeto (ActionEvent event)throws Exception{

        ProjetoDAO proDao = new ProjetoDAO();
             
        List<Projeto> listaProjeto =  proDao.listar();
        
        if (listaProjeto != null){ 
            for (Iterator<Projeto> i =  listaProjeto.iterator(); i.hasNext(); )  
            {    
                Projeto p = i.next();  
                    if (p.getNome().equals( buscarNome.getText())){
                        proDao.excluir(p);
                    }          
            } 
        }  
    }
    
    @FXML
    private void limparCampos(ActionEvent event)throws Exception{
        buscarNome.clear();
        buscarDescricao.clear();
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

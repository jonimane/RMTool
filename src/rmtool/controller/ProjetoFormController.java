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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractFormController;
import rmtool.model.SessionManager;
import rmtool.model.TabManager;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.ProjetoDAO;
/**
/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class ProjetoFormController extends AbstractFormController<Projeto> implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    public Label lblId;
    @FXML
    public TextField txtId;
    @FXML
    public TextField nomeProjeto;
    @FXML
    public HTMLEditor descricaoProjeto;
    @FXML
    public Button btnCancelar;
    @FXML
    public Button btnSalvar;
    
    @FXML
    public void salvar( ActionEvent event )
    {
        atualizarBean();
        
        try {
            ProjetoDAO projetoDAO = new ProjetoDAO();
            
            if( editando.getNome().length() == 0 || editando.getDescricao().length() == 0 )
            {
                Dialog.showError("RMTool", "Preencha todos os campos para continuar!!");
            }
            else
            {
                if( editando.getId() == null )
                {
                    Usuario usuarioLogado = SessionManager.getInstance().get("usuario");
                    editando.getParticipantes().add( usuarioLogado );
                }
                
                projetoDAO.salvar( editando );
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
    public void cancelar( ActionEvent event )
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
        
        descricaoProjeto.setHtmlText( editando.getDescricao() );
        nomeProjeto.setText( editando.getNome() );
    }

    @Override
    public void atualizarBean() {
        editando.setDescricao( descricaoProjeto.getHtmlText() );
        editando.setNome( nomeProjeto.getText() );
    }
}

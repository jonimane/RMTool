/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.util.StringConverter;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractFormController;
import rmtool.model.Prioridade;
import rmtool.model.Situacao;
import rmtool.model.TabManager;
import rmtool.model.TipoRequisito;
import rmtool.model.bean.Requisito;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.RequisitoDAO;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author jonimane
 */
public class RequisitoFormController extends AbstractFormController<Requisito> implements Initializable {
    @FXML
    public GridPane gpPainel;
    @FXML
    public Label lblTitulo;
    @FXML
    public Label lblId;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtNome;
    @FXML
    public Button btnSalvar;
    @FXML
    public Button btnCancelar;
    @FXML
    public ComboBox<Prioridade> cbPrioridade;
    @FXML
    public ComboBox<Situacao> cbSituacao;
    @FXML
    public ComboBox<TipoRequisito> cbTipo;
    @FXML
    public ComboBox<Usuario> cbUsuario;
    @FXML
    public TextField txtCriacao;
    @FXML
    public HTMLEditor heDescricao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listar();
        
        lblTitulo.setText("Criar Requisito");
        lblId.setVisible(false);
        txtId.setVisible(false);
        
        cbUsuario.getItems().addAll( usuarios );
        cbUsuario.setConverter( new StringConverter<Usuario>() {
            @Override
            public String toString(Usuario object) {
                return object.getNome();
            }

            @Override
            public Usuario fromString(String string) {
                Usuario u = new Usuario();
                u.setNome(string);
                
                return u;
            }
        });
        
        cbTipo.getItems().addAll( TipoRequisito.values() );
        cbPrioridade.getItems().addAll( Prioridade.values() );
        cbSituacao.getItems().addAll( Situacao.values() );
        
        //setAtalhos(true);
    }
    
    public void setAtalhos( boolean status )
    {
        btnSalvar.setDefaultButton(status);
        btnCancelar.setCancelButton(status);
    }

    @Override
    public void carregarBean() {
        lblTitulo.setText("Alterar Requisito");
        lblId.setVisible(true);
        txtId.setVisible(true);
        
        txtId.setText( editando.getId().toString() );
        cbTipo.getSelectionModel().select( TipoRequisito.parse( editando.getTipo() ) );
        cbPrioridade.getSelectionModel().select( Prioridade.parse( editando.getPrioridade() ) );
        txtNome.setText( editando.getNome() );
        heDescricao.setHtmlText( editando.getDescricao() );
        cbSituacao.getSelectionModel().select( Situacao.parse( editando.getSituacao() ) );
        cbUsuario.getSelectionModel().select( editando.getUsuario() );
        txtCriacao.setText( editando.getCriacao().toString() );
    }
    
    @Override
    public void atualizarBean()
    {
        editando.setTipo( cbTipo.getSelectionModel().getSelectedItem() );
        editando.setPrioridade( cbPrioridade.getSelectionModel().getSelectedItem() );
        editando.setNome( txtNome.getText() );
        editando.setDescricao( heDescricao.getHtmlText() );
        editando.setSituacao( cbSituacao.getSelectionModel().getSelectedItem() );
        editando.setUsuario( cbUsuario.getSelectionModel().getSelectedItem() );
    }
    
    @FXML
    public void salvar( ActionEvent event )
    {
        setAtalhos(false);
        atualizarBean();
        
        try
        {
            RequisitoDAO requisitoDAO = new RequisitoDAO();
            requisitoDAO.alterar(editando);
            TabManager.getInstance().getMain().tvListaAtualizar();
            
            Dialog.showInfo("RMTool", "Requisito salvo com sucesso!", new EventHandler() {
                @Override
                public void handle(Event event) {
                    fechar();
                }
            });
        }
        catch( Exception e )
        {
            Dialog.showError("RMTool", "Problema ao salvar requisito!");
        }
    }
    
    @FXML
    public void cancelar( ActionEvent e )
    {
        setAtalhos(false);
        fechar();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractListController;
import rmtool.model.TabManager;
import rmtool.model.bean.Usuario;
import rmtool.model.config.Telas;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Haroldo&Faby
 */
public class UsuarioListarController extends AbstractListController<Usuario> implements Initializable {
    @FXML
    public TableView<Usuario> tvLista;
    @FXML
    public Button btnNovo;
    @FXML
    public Button btnEditar;
    @FXML
    public Button btnDeletar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEditar.setDisable( true );
        btnDeletar.setDisable( true );
        
        TableColumn<Usuario, Integer> tc = new TableColumn<>();
        tc.setText("ID");
        tc.setCellValueFactory( new PropertyValueFactory("id") );
        
        TableColumn<Usuario, String> tc2 = new TableColumn<>();
        tc2.setText("Nome");
        tc2.setCellValueFactory( new PropertyValueFactory("nome") );
        tc2.setPrefWidth(500);
        
        tvLista.getColumns().clear();
        tvLista.getColumns().addAll(tc, tc2);
        
        tvLista.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Usuario>() {
            @Override
            public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
                setUltimaSelecao( newValue );
            }
        });
        
        tvLista.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(getUltimaSelecao() != null && event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2) {
                        TabManager.getInstance().chamarTelaEditar(Telas.UsuarioForm, getUltimaSelecao() );
                    }
                }
            }
        });
        
        carregarLista();
    }
    
    @FXML
    public void novo( ActionEvent event )
    {
        Usuario u = new Usuario();
        TabManager.getInstance().chamarTelaEditar(Telas.UsuarioForm, u);
    }
    
    @FXML
    public void editar( ActionEvent event )
    {
        TabManager.getInstance().chamarTelaEditar(Telas.UsuarioForm, getUltimaSelecao() );
    }
    
    @FXML
    public void deletar( ActionEvent event )
    {
        Dialog.buildConfirmation("Confirmar", "Deseja realmente excluir esse requisito?").addYesButton( new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                try
                {
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.deletar( getUltimaSelecao() );
                    tvLista.getItems().remove( getUltimaSelecao() );
                    TabManager.getInstance().getMain().tvListaAtualizar();
                    Dialog.showInfo("RMTool", "Usuário deletado com sucesso!!"); 
                }
                catch( Exception e )
                {
                    Dialog.showError("RMTool", "Problema ao deletar Usuário!!"); 
                }
            }
        }).addNoButton( new EventHandler<Event>() {
            @Override
            public void handle(Event t) {

            }
        }).build().show();
    }

    @Override
    public void aoSelecionar() {
        btnEditar.setDisable( false );
        btnDeletar.setDisable( false );
    }

    private void carregarLista() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listar();
        
        tvLista.getItems().clear();
        tvLista.getItems().addAll( usuarios );
    }
}

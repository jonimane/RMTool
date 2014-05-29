/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractFormController;
import rmtool.model.Colaborador;
import rmtool.model.TabManager;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.ProjetoDAO;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author jonimane
 */
public class ProjetoColaboradorController extends AbstractFormController<Projeto> implements Initializable {
    @FXML
    public TableView tvColaboradores;
    @FXML
    public Button btnSalvar;
    @FXML
    public Button btnCancelar;
    
    public List<Colaborador> colaboradores;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn tc = new TableColumn<>();
        tc.setCellValueFactory( new PropertyValueFactory("colabora") );
        tc.setEditable(true);
        
        tc.setCellFactory( new Callback<TableColumn<Colaborador, Boolean>, TableCell<Colaborador, Boolean>>() {
            @Override
            public TableCell<Colaborador, Boolean> call(TableColumn<Colaborador, Boolean> param) {
                CheckBoxTableCell cbtc =  new CheckBoxTableCell<>();
                cbtc.setAlignment( Pos.CENTER );
                
                return cbtc;
            }
        });
        
        TableColumn tc2 = new TableColumn();
        tc2.setText("Nome");
        tc2.setCellValueFactory( new PropertyValueFactory("nome") );
        
        tvColaboradores.setEditable(true);
        tvColaboradores.getColumns().clear();
        tvColaboradores.getColumns().addAll(tc, tc2);
    }    
    
    @Override
    public void carregarBean() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listar();
        colaboradores = new ArrayList<>();
        
        for (Usuario usuario : usuarios) {
            Colaborador c = new Colaborador();
            c.setColabora( new ReadOnlyBooleanWrapper( editando.getParticipantes().contains( usuario ) ) );
            c.setUsuario(usuario);
            colaboradores.add(c);
        }
        
        tvColaboradores.getItems().clear();
        tvColaboradores.getItems().addAll( colaboradores );
    }
    
    @Override
    public void atualizarBean() {
        editando.getParticipantes().clear();
        
        for (Colaborador colaborador : colaboradores) {
            if( colaborador.colaboraProperty().getValue() )
            {
                editando.getParticipantes().add( colaborador.getUsuario() );
            }
        }
    }
    
    @FXML
    public void salvar( ActionEvent event )
    {
        atualizarBean();
        
        try {
            ProjetoDAO projetoDAO = new ProjetoDAO();
            projetoDAO.salvar( editando );
            TabManager.getInstance().getMain().tvListaAtualizar();
            Dialog.showInfo("RMTool", "Projeto salvo com sucesso!!"); 
            fechar();
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
}

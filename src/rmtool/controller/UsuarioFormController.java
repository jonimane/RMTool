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
import rmtool.model.TabManager;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import name.antonsmirnov.javafx.dialog.Dialog;
import rmtool.model.AbstractFormController;
import rmtool.model.bean.Usuario;
import rmtool.model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Thiago Rodrigo
 */
public class UsuarioFormController extends AbstractFormController<Usuario> implements Initializable {
    @FXML
    public Label lblId;
    @FXML
    public TextField txtId;
    @FXML
    public TextField usuarioNome;
    @FXML
    public TextField usuarioEmail;
    @FXML
    public TextField usuarioLogin;
    @FXML
    public TextField usuarioSenha;

    @FXML
    private void salvar(ActionEvent event) {
        atualizarBean();
        
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            if (editando.getNome().length() == 0 || editando.getEmail().length() == 0 || editando.getLogin().length() == 0 || editando.getSenha().length() == 0) {
                Dialog.showError("RMTool", "Preencha todos os campos para continuar!!");
            } else {
                usuarioDAO.salvar(editando);
                TabManager.getInstance().getMain().tvListaAtualizar();
                Dialog.showInfo("RMTool", "Usuário salvo com sucesso!!");
                fechar();
            }
        } catch (Exception e) {
            Dialog.showInfo("RMTool", "Problema ao salvar Usuário!!");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        fechar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblId.setVisible(false);
        txtId.setVisible(false);
        txtId.setDisable(true);
    }

    @Override
    public void carregarBean() {
        if (editando.getId() != null) {
            txtId.setText(editando.getId().toString());
            lblId.setVisible(true);
            txtId.setVisible(true);
        }

        usuarioNome.setText(editando.getNome());
        usuarioEmail.setText(editando.getEmail());
        usuarioLogin.setText(editando.getLogin());
        usuarioSenha.setText(editando.getSenha());
    }

    @Override
    public void atualizarBean()
    {
        editando.setNome(usuarioNome.getText());
        editando.setEmail(usuarioEmail.getText());
        editando.setLogin(usuarioLogin.getText());
        editando.setSenha(usuarioSenha.getText());

    }
}

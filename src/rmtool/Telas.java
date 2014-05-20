/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool;

/**
 *
 * @author jonimane
 */
public enum Telas {
    App("App"),
    Apresentacao("Apresentação"),
    Entrar("Entrar"),
    ProjetoBuscar("Projeto - Buscar"),
    ProjetoForm("Projeto - Form"),
    RequisitoForm("Requisito - Form"),
    RequisitoListar("Requisito - Listar"),
    UsuarioForm("Usuario - Form"),
    UsuarioListar("Usuario - Listar");
    
    private final String titulo;
    
    private Telas( String t )
    {
        titulo = t;
    }
    
    public String getFXML()
    {
        return "/rmtool/view/" + name() + ".fxml";
    }
    
    @Override
    public String toString()
    {
        return titulo;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model;

import rmtool.model.config.Telas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import rmtool.controller.AppController;

/**
 *
 * @author Haroldo&Faby
 */
public class TabManager {
    private static TabManager tm;
    private final Map<Controller, Tab> lista;
    private AppController main;
    
    private TabManager()
    {
        lista = new HashMap<>();
    }
    
    public static TabManager getInstance()
    {
        if( tm == null )
        {
            tm = new TabManager();
        }
        
        return tm;
    }
    
    public AppController getMain()
    {
        return main;
    }
    
    public void setMain(AppController c)
    {
        main = c;
    }
    
    public Tab criar(Telas tela, boolean requestFocus ){
        Tab tab = carregarFXML(tela);
        getMain().tpPrincipal.getTabs().add( tab );
        
        if( requestFocus )
        {
            tab.getTabPane().getSelectionModel().select( tab );
        }
        
        return tab;
    }
    
    public Tab criar( Telas tela )
    {
        return criar(tela, true);
    }
    
    public void fechar(Controller c)
    {
        Tab t = lista.get( c );
        t.getTabPane().getTabs().remove( t );
        lista.remove( c );
    }
    
    private Tab carregarFXML( Telas tela )
    {
        try {
           FXMLLoader l = new FXMLLoader();
           Parent root = (Parent) l.load( getClass().getResource( tela.getFXML() ).openStream() );
           
           Tab t = new Tab();
           t.setContent( root );
           t.setText( tela.toString() );
           t.setClosable( true );
           lista.put( (Controller) l.getController(), t );
            
           return t;
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            
           return null;
        }
    }

    public Map<Controller, Tab> getLista()
    {
        return lista;
    }
    
    public <T extends Controller> T get(Tab t)
    {
        for (Map.Entry<Controller, Tab> entry : lista.entrySet()) {
            if( entry.getValue() == t )
            {
                return (T) entry.getKey();
            }
        }
        
        return null;
    }
    
    public List<Controller> get(String tipo)
    {
        List<Controller> res = new ArrayList<>();
        
        for (Map.Entry<Controller, Tab> entry : lista.entrySet()) {
            if( entry.getKey().getClass().getSimpleName().equals( tipo ) )
            {
                res.add( entry.getKey() );
            }
        }
        
        return res;
    }
    
    public Tab get( Controller c )
    {
        for( Map.Entry<Controller, Tab> entry : lista.entrySet() ) {
            if( entry.getKey() == c )
            {
                return entry.getValue();
            }
        }
        
        return null;
    }
    
    public <T> void chamarTelaEditar( Telas tela, T editando )
    {
        Tab t = criar(tela);
        AbstractFormController controller = get(t);
        controller.editar( editando );
    }
}

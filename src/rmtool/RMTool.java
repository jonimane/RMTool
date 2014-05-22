/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool;

import rmtool.model.SessionManager;
import rmtool.model.Telas;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import rmtool.model.dao.HibernateUtilDAO;

/**
 *
 * @author jonimane
 */
public class RMTool extends Application {
    public Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        // Carregar SessionManager
        SessionManager sm = SessionManager.getInstance();
        sm.put("RMTool", this);
        
        // Carregar Hibernate
        HibernateUtilDAO.getSessionFactory();
        
        try {
            FXMLLoader fl = new FXMLLoader();
            Parent root = (Parent) fl.load( getClass().getResource( Telas.Entrar.getFXML() ).openStream() );
            
            Scene scene = new Scene(root);
            primaryStage.setScene( scene );
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(RMTool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void mudarCena( Scene novoPalco )
    {
        Stage s = getPrimaryStage();
        
        s.hide();
        s.setScene(novoPalco);
        s.show();
    }

    public void maximarTela() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
    }
}

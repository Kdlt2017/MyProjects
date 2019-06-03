package tn.kdlt.rankmeclient;

import java.security.NoSuchAlgorithmException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.kdlt.entities.Administrator;
import tn.kdlt.services.RankMeServiceRemote;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException, NoSuchAlgorithmException {
        
                
        String jndiName = "RankMe-ejb/RankMeService!tn.kdlt.services.RankMeServiceRemote";
        Context context = new InitialContext();
        RankMeServiceRemote proxy = (RankMeServiceRemote) context.lookup(jndiName);
        
        Administrator admin = new Administrator("kdlt","toussaint.kebou@gmail.com","Laurel Toussaint","KEBOU DJEUFO");
       admin.setPassword("Kdlt@2019".getBytes());
       
        proxy.createAdmin(admin);
        launch(args);

    }

}

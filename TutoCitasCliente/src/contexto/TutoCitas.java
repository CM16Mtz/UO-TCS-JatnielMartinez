package contexto;

import cliente.Cliente;
import interfaces.InterfazServidor;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JatnielMartínez
 */
public class TutoCitas extends Application {
  
  @Override
  public void start(Stage primaryStage) throws IOException {
    Cliente cliente = new Cliente();
    InterfazServidor servidor = cliente.getServidor();
    Contexto.getInstancia().setCliente(cliente);
    Contexto.getInstancia().setServidor(servidor);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IniciarSesion.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}

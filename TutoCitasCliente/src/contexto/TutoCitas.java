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
 * Clase principal del proyecto. Crea una instancia del cliente y de InterfazServidor,
 * y las establece en el contexto. Asimismo, carga la pantalla de iniciar sesión.
 * @author JatnielMartínez
 */
public class TutoCitas extends Application {
  
  /**
   * Carga la pantalla de inicio de sesión.
   * @param primaryStage Stage de la pantalla
   * @throws IOException En caso de que no se logre cargar la GUI
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    Cliente cliente = new Cliente();    //Se crea una instancia del cliente
    InterfazServidor servidor = cliente.getServidor();    //Toma el servidor del cliente
    //Se establecen las instancias en el contexto
    Contexto.getInstancia().setCliente(cliente);
    Contexto.getInstancia().setServidor(servidor);
    //Se carga la pantalla
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IniciarSesion.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();    //Se muestra la GUI
  }

  /**
   * Carga el método start().
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}

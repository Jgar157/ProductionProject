import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the driver class that runs the program and sets up the screen.
 * @author Jairo Garciga
 */
public class Main extends Application {

  /**
   * Launches the program.
   * @param args Command Line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * start creates the screen according to the saved sizes and also sets up the css.
   * @param primaryStage The main stage/screen of the program.
   * @throws Exception Thrown if bad size or css calls.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass()
        .getResource("sample.fxml"));

    int screenWidth = 766;
    int screenHeight = 574;

    Scene scene = new Scene(root, screenWidth, screenHeight);

    scene.getStylesheets().add(getClass()
        .getResource("style.css").toExternalForm());

    primaryStage.setTitle("Production");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable  {

  public Tab tab1;
  //Loading in the fxml

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void btnAddProductPressed(ActionEvent actionEvent) {
    System.out.println("Hello World!");
  }

  public void btnRecordProductionPressed(ActionEvent actionEvent) {
    System.out.println("PRESSED :)))))))))))))))))))))))))))))))))))");
  }
}
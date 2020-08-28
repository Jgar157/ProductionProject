import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable  {
  //Loading in the fxml
  @FXML
  Tab tab1;

  @FXML
  Tab tab2;

  @FXML
  Tab tab3;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Helps with loading the fxml
    FXMLLoader loader = new FXMLLoader();
    try {
      AnchorPane anch1 = loader.load(getClass().getResource("Tab1.fxml"));
      tab1.setContent(anch1);
    } catch (IOException exc) {
      System.out.println("unable to load tab1");
    }

    loader = new FXMLLoader();
    try {
      AnchorPane anch2 = loader.load(getClass().getResource("Tab2.fxml"));
      tab2.setContent(anch2);
    } catch (IOException exc) {
      System.out.println("unable to load tab2");
    }

    loader = new FXMLLoader();
    try {
      AnchorPane anch3 = loader.load(getClass().getResource("Tab3.fxml"));
      tab3.setContent(anch3);
    } catch (IOException exc) {
      System.out.println("unable to load tab3");
    }
  }
}
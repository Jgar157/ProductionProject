import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {

  /**
   * Upon the creation of the screen, the combobox is updated and it's becomes editable.
   */
  public void initialize() {

    //This loop populates the combobox with the numbers 1-10
    for (int i = 1; i <= 10; i++) {

      cmbbItemType.getItems().add(Integer.toString(i));
    }
    //Auto sets the combobox to the first choice
    cmbbItemType.getSelectionModel().selectFirst();
    cmbbItemType.setEditable(true);

  }

  //Skeletons for txt boxes and combo boxes
  @FXML
  private TextField txtfProductName;

  @FXML
  private TextField txtfManufacturer;

  @FXML
  private ComboBox<String> cmbbItemType;

  /**
   * Method called when the "Add Product Pressed" Button is pressed First, it creates the
   * connections to the database. Then it sets up variables for connections and statements A
   * preparedStatement is called to insert values from the text fields, then a statement is used to
   * output the PRODUCT Table to the console.
   */
  public void btnAddProductPressed() {

    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/HR;";
    /*
    I had to do some editing to the HR url because it would
    break whenever I attempted to add something to it.
    DB_CLOSE_DELAY=-1 Was used to fix this
     */

    //  Database credentials
    final String user = "";
    final String pass = "";
    Connection conn;
    PreparedStatement preparedstmt;
    Statement stmt;

    String tempType = cmbbItemType.getTypeSelector();
    String tempManufacturer = txtfManufacturer.getText();
    String tempProductName = txtfProductName.getText();

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

      //Sql statement
      String sql = "INSERT INTO PRODUCT(type, manufacturer, name)"
          + "Values(?, ?, ?)";

      //Puts the corresponding String into the respective ? mark
      preparedstmt = conn.prepareStatement(sql);
      preparedstmt.setString(1, tempType);
      preparedstmt.setString(2, tempManufacturer);
      preparedstmt.setString(3, tempProductName);
      preparedstmt.executeUpdate();

      /*
      Each time a new product is added, the entire list is output
      to the console.
      */
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
      while (rs.next()) {

        tempProductName = rs.getString(2); //Grabs col 1,2,3
        tempType = rs.getString(3);
        tempManufacturer = rs.getString(4);
        System.out.println(tempProductName + " " + tempType + " "
            + tempManufacturer);
      }

      //Closing statement then connection
      preparedstmt.close();
      conn.close();

      //Combined error catching instead of heaving each alone
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Button for the record production button.
   */
  public void btnRecordProductionPressed() {
    System.out.println("Record Production Pressed, pressed :)");
  }
}

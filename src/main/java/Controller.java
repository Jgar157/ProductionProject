import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

  /**
   * Upon the creation of the screen, the combobox is updated and it's becomes editable.
   */
  public void initialize() {
    setComboBoxes();
    setTableView();
    populateListChooseProduct();

    // Controller.testMultimedia(); // Tests whether the interfaces work
  }

  //Skeletons for txt boxes and combo boxes
  @FXML
  private TextField txtfProductName;

  @FXML
  private TextField txtfManufacturer;

  @FXML
  private ComboBox<String> cmbbChooseQuantity;

  @FXML
  private ComboBox<ItemType> cmbbItemType;

  @FXML
  private TableView<Product> tableviewExistingProducts;

  @FXML
  private TableColumn<?, ?> tableviewID;

  @FXML
  private TableColumn<?, ?> tableviewName;

  @FXML
  private TableColumn<?, ?> tableviewType;

  @FXML
  private TableColumn<?, ?> tableviewManufacturer;

  @FXML
  private Button btnRecordProduction;

  @FXML
  ListView<Product> listChooseProduct = new ListView<>();

  @FXML
  private TextArea productionLogTxt;

  ObservableList<Product> productLine = FXCollections.observableArrayList();

  public void setTableView() {

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
    Statement stmt;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

      while (rs.next()) {

        String tempProductName = rs.getString(2); //Grabs col 1,2,3
        String tempTypeString = rs.getString(3);
        String tempManufacturer = rs.getString(4);
        ItemType tempType;

        if (tempTypeString.equals(ItemType.AUDIO.code)) {
          tempType = ItemType.AUDIO;
        } else if (tempTypeString.equals(ItemType.AUDIO_MOBILE.code)) {
          tempType = ItemType.AUDIO_MOBILE;
        } else if (tempTypeString.equals(ItemType.VISUAL.code)) {
          tempType = ItemType.VISUAL;
        } else {
          tempType = ItemType.VISUAL_MOBILE;
        }

        Widget tempWidget = new Widget(tempProductName, tempManufacturer, tempType);
        productLine.add(tempWidget);

      }

      stmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    tableviewName.setCellValueFactory(new PropertyValueFactory("Name"));
    tableviewType.setCellValueFactory(new PropertyValueFactory("Type"));
    tableviewManufacturer.setCellValueFactory(new PropertyValueFactory("Manufacturer"));

    tableviewExistingProducts.setItems(productLine);
  }

  public void populateListChooseProduct() {
    listChooseProduct.setItems(productLine);
  }

  /**
   * The purpose of this method is set up the combo boxes
   * instead of flooding initialize with too much code.
   * First it does cmbbItemType then cmbbChooseQuantity.
   */
  public void setComboBoxes() {

    //Populating cmbbItemType
    for (ItemType item : ItemType.values()) {
      cmbbItemType.getItems().add(item);
    }
    cmbbItemType.getSelectionModel().selectFirst();


    //This loop populates the combobox in Product with the numbers 1-10
    for (int i = 1; i <= 10; i++) {

      cmbbChooseQuantity.getItems().add(Integer.toString(i));
    }
    //Auto sets the combobox to the first choice
    cmbbChooseQuantity.getSelectionModel().selectFirst();
    cmbbChooseQuantity.setEditable(true);
  }

  public void setProductionLogTxt() {
    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./res/HR;";
  }

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

    ItemType tempType = cmbbItemType.getValue();
    String tempTypeString;
    String tempManufacturer = txtfManufacturer.getText();
    String tempProductName = txtfProductName.getText();

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(db_url, user, pass);

      //Adds the new item to the tableview
      Widget tempWidget = new Widget(tempProductName, tempManufacturer, tempType);
      productLine.add(tempWidget);

      //Sql statement
      String sql = "INSERT INTO PRODUCT(type, manufacturer, name)"
          + "Values(?, ?, ?)";

      //Puts the corresponding String into the respective ? mark
      preparedstmt = conn.prepareStatement(sql);
      preparedstmt.setString(1, tempType.code);
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
        tempTypeString = rs.getString(3);
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

  @FXML
  void btnRecordProductionPressed() {
    try {
      String amount = cmbbChooseQuantity.getValue();
      Product tempProduct = listChooseProduct.getSelectionModel().getSelectedItem();
      productionLogTxt.setText(tempProduct + " Amt: " + amount + "\n" + productionLogTxt.getText());
    } catch (NullPointerException e) {
      System.out.println("No listview item chosen");
    }
  }



  public static void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
      }
    }
}

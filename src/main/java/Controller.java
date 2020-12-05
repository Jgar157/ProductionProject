import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The main class for the GUI, it holds every component and all
 * interactions the user can do.
 *
 * @author Jairo Garciga
 */
public class Controller {

  /* I had to do some editing to the HR url because it would
   * break whenever I attempted to add something to it.
   * DB_CLOSE_DELAY=-1 Was used to fix this
   */
  final String jdbc_driver = "org.h2.Driver";
  final String db_url = "jdbc:h2:./res/HR;DB_CLOSE_DELAY=-1";
  private final String user = "";
  public Tab tab1;
  private String pass = "";

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
  ListView<Product> listViewProducts = new ListView<>();

  @FXML
  private TextArea productionLogTxt;

  @FXML
  private TextField textName;

  @FXML
  private TextField textPass;

  @FXML
  private Label labelError;

  @FXML
  private Label labelEmployeeHelp;

  @FXML
  private Label labelProduceError;

  ArrayList<ProductionRecord> productionLog = new ArrayList<>();

  ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  ObservableList<Product> productLine;

  /**
   * Upon the creation of the screen, the combobox is updated and it's becomes editable.
   */
  public void initialize() {
    getPassword();
    setComboBoxes();
    loadProductList();
    populateListChooseProduct();
    loadProductionLog();
    showProduction();

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

  /**
   * Sets the listViewProducts to the productLine.
   */
  public void populateListChooseProduct() {
    listViewProducts.setItems(productLine);
  }

  /**
   * Method called when the "Add Product Pressed" Button is pressed.
   */
  public void btnAddProductPressed() {

    addToProductDB();
    loadProductList();
    populateListChooseProduct();

  }

  /**
   * Method called when "Record Production" pressed.
   */
  @FXML
  public void btnRecordProductionPressed() {


    getSelectedProduct();
    addToProductionRecordDB();
    loadProductionLog();
    showProduction();

  }

  /**
   * Creates a tempEmployee from text in the name and password text boxes
   * on the employees tab of the program.
   */
  @FXML
  public void enterEmployee() {
    Employee tempEmployee = new Employee(textName.getText(), textPass.getText());
    System.out.println(tempEmployee);
  }

  /**
   * Grabs the selected product from listView, then grabs the number from the
   * combobox for quantity, and creates x amount of that product to then put into
   * productionRun which keeps track of every product that has been produced.
   * TL;DR Adds to the record of products using listViewProducts and cmbbChooseQuantity
   */
  public void getSelectedProduct() {

    Product tempProduct;
    try {
      String amount = cmbbChooseQuantity.getValue();
      tempProduct = listViewProducts.getSelectionModel().getSelectedItem();

      if (Integer.parseInt(amount) <= 0) {
        throw new NumberFormatException();
      }

      for (int i = 0; i < Integer.parseInt(amount); i++) {
        ProductionRecord tempRecord = new ProductionRecord(tempProduct);
        this.productionRun.add(tempRecord);
      }

    } catch (NullPointerException e) {
      labelProduceError.setVisible(true);
      labelProduceError.setText("No product was chosen");

    } catch (NumberFormatException e) {

      labelProduceError.setVisible(true);
      labelProduceError.setText("Input a positive number for the quantity");

    }
  }

  /**
   * First, it creates the connections to the database.
   * Then it sets up variables for connections and statements A
   * preparedStatement is called to insert values from the text fields, then a statement is used to
   * output the PRODUCT Table to the console.
   */
  public void addToProductDB() {

    Connection conn;
    PreparedStatement preparedStatement;
    Statement stmt;

    ItemType tempType = cmbbItemType.getValue();
    String tempManufacturer = txtfManufacturer.getText();
    String tempProductName = txtfProductName.getText();

    if (tempManufacturer.length() >= 3) {
      try {

        // STEP 1: Register JDBC driver
        Class.forName(jdbc_driver);

        //STEP 2: Open a connection
        conn = DriverManager.getConnection(this.db_url, user, pass);

        //Sql statement
        String sql = "INSERT INTO PRODUCT(type, manufacturer, name)"
                + "Values(?, ?, ?)";

        //Puts the corresponding String into the respective ? mark
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, tempType.code);
        preparedStatement.setString(2, tempManufacturer);
        preparedStatement.setString(3, tempProductName);
        preparedStatement.executeUpdate();

        /*
        Each time a new product is added, the entire list is output
        to the console.
        */
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
        while (rs.next()) {

          tempProductName = rs.getString(2); //Grabs col 1,2,3

          tempManufacturer = rs.getString(3);
          System.out.println(tempProductName + " " + tempType + " "
                  + tempManufacturer);
        }

        //Closing statement then connection
        preparedStatement.close();
        conn.close();

        //Combined error catching instead of heaving each alone
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        databaseNotFound();
      }

    } else {
      this.setLabelErrorMessage("The manufacturer name must be at least 3 letters!");
    }
  }

  /**
   * First sets up connection to the Database.
   * SQL statement grabs everything in productionRun and sends it to the database.
   * It resets all the saved counts for the productionRun types since
   * productionRecord objects are created very often, it would be bad to
   * accidentally exponentially increase it.
   */
  public void addToProductionRecordDB() {

    Connection conn;
    PreparedStatement preparedStmt;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(this.db_url, user, pass);

      String sql = "INSERT INTO PRODUCTIONRECORD( product_id, serial_num, date_produced)"
              + "Values(?, ?, ?)";
      preparedStmt = conn.prepareStatement(sql);


      for (ProductionRecord tempRecord : this.productionRun) {

        Timestamp timeStamp = new Timestamp(tempRecord.getProdDate().getTime());

        //Puts the corresponding String into the respective ? mark
        preparedStmt.setInt(1, tempRecord.getProductID());
        preparedStmt.setString(2, tempRecord.getSerialNum());
        preparedStmt.setTimestamp(3, timeStamp);
        preparedStmt.executeUpdate();

      }

      // Clearing productionRun
      this.productionRun.clear();
      ProductionRecord.resetAllTypeCounts();
      preparedStmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      databaseNotFound();
    }

  }

  /**
   * Connects to database and creates products from PRODUCT table
   * Then adds these created products to the productLine.
   */
  public void loadProductList() {

    this.productLine = FXCollections.observableArrayList();

    Connection conn;
    Statement stmt;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      System.out.println(this.db_url);
      System.out.println(user);
      System.out.println(pass);
      conn = DriverManager.getConnection(this.db_url, user, pass);

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

      while (rs.next()) {

        int tempProductID = Integer.parseInt(rs.getString(1));
        String tempProductName = rs.getString(2); //Grabs col 1,2,3
        String tempTypeString = rs.getString(3);
        String tempManufacturer = rs.getString(4);
        ItemType tempType = ProductionRecord.findType(tempTypeString);

        Widget tempWidget = new Widget(tempProductName, tempManufacturer, tempType);
        tempWidget.setID(tempProductID);
        productLine.add(tempWidget);

      }

      stmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      databaseNotFound();
    }

    tableviewID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    tableviewName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    tableviewManufacturer.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    tableviewType.setCellValueFactory(new PropertyValueFactory<>("Type"));


    tableviewExistingProducts.setItems(productLine);
  }

  /**
   * Connects to database and creates productionRecord objects from ProductionRecord
   * In order to calculate the right type totals updateTypeTotals is called.
   * Finally adds the new productionRecord objects into productionLog.
   */
  public void loadProductionLog() {

    this.clearProductionLog();
    this.productionLogTxt.setText("");

    Connection conn;
    Statement stmt;
    PreparedStatement prep;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(jdbc_driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(this.db_url, user, pass);
      stmt = conn.createStatement();

      prep = conn.prepareStatement("SELECT NAME FROM PRODUCT WHERE ID=?");

      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");
      ResultSet temp;

      while (rs.next()) {

        ProductionRecord tempProductionRecord = new ProductionRecord((rs.getInt(2)));
        tempProductionRecord.setProductionNum(rs.getInt(1));
        tempProductionRecord.setSerialNum(rs.getString(3));
        tempProductionRecord.setProdDate(new Date(rs.getDate(4).getTime()));
        ProductionRecord.updateTypeTotals(tempProductionRecord.getSerialNum().substring(3, 5));
        // Necessary to update typeTotals to make sure future productionRecords
        // match with the proper serialNum

        prep.setInt(1, tempProductionRecord.getProductID());
        temp = prep.executeQuery();
        temp.next();
        tempProductionRecord.setProductName(temp.getString(1));

        productionLog.add(tempProductionRecord);

      }

      stmt.close();
      prep.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      databaseNotFound();
    }

  }

  /**
   * Loops over productRecord, and adds the string form of each object to
   * the productionLogTxt box in production log tab.
   */
  public void showProduction() {

    for (ProductionRecord productRecord : this.productionLog) {

      this.productionLogTxt.setText(this.productionLogTxt.getText() + productRecord.toString());

    }
    this.clearProductionLog();

  }

  /**
   * Simply clears the productionLog, used for readability.
   */
  public void clearProductionLog() {
    productionLog.clear();
  }

  /**
   * Changes the visibility of the employee help text.
   */
  @FXML
  void openEmployeeHelp() {
    // Sets the visibility to the opposite of the current visibility
    // If currently visible, sets it off. If currently not visible, makes visible.
    this.labelEmployeeHelp.setVisible(!this.labelEmployeeHelp.isVisible());
  }

  /**
   * Recursive method to reverse the password String, could theoretically
   * be used for other Strings too.
   *
   * @param pw The string representing the reversed password in properties
   * @return The String now reversed
   */
  public String reverseString(String pw) {
    if (pw.length() == 1) {
      return pw;
    }
    return pw.substring(pw.length() - 1) + reverseString(pw.substring(0, pw.length() - 1));
  }

  /**
   * Accesses res/properties to get the password String, then
   * it calls reverseString to reverse the password String.
   * Finally sets that String to the public object variable.
   */
  public void getPassword() {

    try {

      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties"));
      pass = reverseString(prop.getProperty("password"));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Error response for if the database is missing.
   * Product name text box will prompt user to restart the program.
   */
  public void databaseNotFound() {
    System.out.println("Database not found error, prompting user to restart app");
    this.setLabelErrorMessage("Database not found, please try restarting the program.");
  }

  /**
   * Changes the message in the error message below manufacturer text box.
   *
   * @param message The error message
   */
  public void setLabelErrorMessage(String message) {
    this.labelError.setText(message);
  }
}

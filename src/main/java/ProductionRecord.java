import java.util.Date;

/**
 * ProductionRecord keeps track of the types of Products that have been produced.
 * It keeps track of their time, the total amount of each type produced, the product name,
 * and it's ID.
 *
 * @author Jairo Garciga
 */
public class ProductionRecord {

  private int productionNumber = 0;
  private int productID;
  private String serialNumber;
  private String productName;
  private Date dateProduced;

  public static int totalAU = 0;
  public static int totalAM = 0;
  public static int totalVI = 0;
  public static int totalVM = 0;

  /**
   * This default constructor takes care of the quick instantiation of productionRecords
   * The type count has to be updated when this is called
   * since it does not update the itemType totals.
   *
   * @param productID The id of the product
   */
  public ProductionRecord(int productID) {

    this.setProductionNum(0); // The database will take care of auto-increment
    this.setProductID(productID);
    this.setSerialNum("0");
    this.setProdDate(new Date()); // Sets the date to the current date

  }

  /**
   * This constructor takes care of several things, it sets all the main variables,
   * updates the totals for the types, and finally sets the serial number.
   *
   * @param product The product which a production record will be made of
   */
  public ProductionRecord(Product product) {

    this.productName = product.getName();
    this.setProductID(product.getID());
    this.setProdDate(new Date());

    updateTypeTotals(product.getItemCode());
    int leadingZeros = 6 - (Integer.toString(productionNumber)).length();


    this.serialNumber = (product.getManufacturer()).substring(0, 3) + product.getItemCode()
            + String.format("%0" + leadingZeros + "d", getTypeTotal(product.getType()));

  }

  /**
   * This method is primarily used as output onto the productionLog.
   * It sets a string for the productionNum, productName, serialNum, and prodDate.
   *
   * @return The string format of a productionRecord object
   */
  public String toString() {

    return String.format("Prod. Num: %s Product Name: %s Serial Num: %s Date: %s \n",
            getProductionNum(), getProductName(), getSerialNum(), getProdDate());

  }

  /**
   * Grabs the productName of the object.
   *
   * @return The productName
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * Sets the productName.
   *
   * @param productName The new productName.
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * Gets the production number field.
   *
   * @return productionNumber
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * Gets the productID field and outputs.
   *
   * @return productID
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Gets the serialNumber field and outputs.
   *
   * @return serialNumber
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Gets the dateProduced field and outputs.
   *
   * @return dateProduced
   */
  public Date getProdDate() {
    return dateProduced;
  }

  /**
   * Sets productionNumber field to number.
   *
   * @param number The new product number
   */
  public void setProductionNum(int number) {
    productionNumber = number;
  }

  /**
   * Sets the productID field to number.
   *
   * @param number The new productID
   */
  public void setProductID(int number) {
    this.productID = number;
  }

  /**
   * Sets the serialNumber field to number.
   *
   * @param number The new serialNumber
   */
  public void setSerialNum(String number) {
    this.serialNumber = number;
  }

  /**
   * Sets the dateProduced to a new date.
   *
   * @param newDate The new date
   */
  public void setProdDate(Date newDate) {
    this.dateProduced = newDate;
  }

  /**
   * Takes the code parameter and updates the respective itemType total.
   *
   * @param code The ItemType to have it's total updated
   */
  public static void updateTypeTotals(String code) {

    if (code.equals(ItemType.AUDIO.code)) {
      totalAU++;
    } else if (code.equals(ItemType.AUDIO_MOBILE.code)) {
      totalAM++;
    } else if (code.equals(ItemType.VISUAL.code)) {
      totalVI++;
    } else {
      totalVM++;
    }

  }

  /**
   * Takes the ItemType parameter and returns the respective total.
   *
   * @param type The ItemType input
   * @return The respective ItemType amount
   */
  public static int getTypeTotal(ItemType type) {

    if (type == ItemType.AUDIO) {
      return totalAU;
    } else if (type == ItemType.AUDIO_MOBILE) {
      return totalAM;
    } else if (type == ItemType.VISUAL) {
      return totalVI;
    } else {
      return totalVM;
    }

  }

  /**
   * Takes the code parameter and returns the corresponding ItemType.
   *
   * @param code The String representation of an ItemType
   * @return The corresponding ItemType to the code
   */
  public static ItemType findType(String code) {

    if (code.equals(ItemType.AUDIO.code)) {
      return ItemType.AUDIO;
    } else if (code.equals(ItemType.AUDIO_MOBILE.code)) {
      return ItemType.AUDIO_MOBILE;
    } else if (code.equals(ItemType.VISUAL.code)) {
      return ItemType.VISUAL;
    } else {
      return ItemType.VISUAL_MOBILE;
    }
  }

  /**
   * Sets all of the totals for the types to 0.
   * This prevents issues where the typeTotals spiral out of control when continuously making
   * new productionRecord objects from the database.
   * Ex: Each time that ProductionRecord database is updated it remakes every ProductionRecord
   * Object in the productLog tab.
   */
  public static void resetAllTypeCounts() {
    totalAU = 0;
    totalVI = 0;
    totalAM = 0;
    totalVM = 0;
  }
}

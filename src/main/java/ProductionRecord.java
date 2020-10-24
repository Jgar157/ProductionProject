import java.util.Date;

public class ProductionRecord {

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  public ProductionRecord(int productID) {

    this.setProductionNum(0); // The database will take care of auto-increment
    this.setProductID(productID);
    this.setSerialNum("0");
    this.setProdDate(new Date()); // Sets the date to the current date

  }

  public ProductionRecord(Product product, int count) {

    int leadingZeros = 6 - (Integer.toString(count)).length();
    this.setProdDate(new Date());
    this.serialNumber = (product.getManufacturer()).substring(0,3) + product.getItemTypeCode() +
            String.format( "%0" + leadingZeros + "d", count);

  }

  public String toString() {

    return String.format("Prod. Num: %s Product ID: %s Serial Num: %s Date: %s",
        getProductionNum(), getProductID(), getSerialNum(), getProdDate());

  }

  /**
   * Gets the production number field
   * @return productionNumber
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * Gets the productID field and outputs
   * @return productID
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Gets the serialNumber field and outputs
   * @return serialNumber
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Gets the dateProduced field and outputs
   * @return dateProduced
   */
  public Date getProdDate() {
    return dateProduced;
  }

  /**
   * Sets productionNumber field to number
   * @param number The new product number
   */
  public void setProductionNum(int number) {
    this.productionNumber = number;
  }

  /**
   * Sets the productID field to number
   * @param number The new productID
   */
  public void setProductID(int number) {
    this.productID = number;
  }

  /**
   * Sets the serialNumber field to number
   * @param number The new serialNumber
   */
  public void setSerialNum(String number){
    this.serialNumber = number;
  }

  /**
   * Sets the dateProduced to a new date
   * @param newDate The new date
   */
  public void setProdDate(Date newDate) {
    this.dateProduced = newDate;
  }

}

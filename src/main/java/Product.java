/**
 * The main class for the GUI, it holds every component and all
 * interactions the user can do.
 * @author Jairo Garciga
 */
public abstract class Product implements Item{

  int id;
  ItemType type;
  String itemCode;
  String manufacturer;
  String name;

  /**
   * The Product Class creates product objects which are stored in
   * a database and used in the tableView. They are also used in the
   * list view on the listView.
   * @param name  The name of the product
   * @param manufacturer  The name of the manufacturer of the product (Must be 3 letters at least)
   * @param item  The ItemType of the object
   */
  public Product(String name, String manufacturer, ItemType item) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = item;
    this.itemCode = item.code;
  }

  /**
   * Takes the name, manufacturer, and type and outputs it as a string
   * @return The string output of a product object
   */
  public String toString() {
    return String.format("Name: %s\nManufacturer: %s\nType: %s",
        this.name, this.manufacturer, this.type);
  }

  /**
   * Sets the ID of the product object
   * @param id  The new id
   */
  public void setID(int id) { this.id = id; }

  /**
   * Grabs the object's ID
   * @return The object's ID
   */
  public int getID() { return this.id; }

  /**
   * Sets the name of the product object
   * @param name  The new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Grabs the object's name
   * @return  The object's name
   */
  public String getName() { return this.name; }

  /**
   * Sets the manufacturer of the product class
   * @param manufacturer  The new manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Grabs the name of the manufacturer of the product object
   * @return  The name of the manufacturer
   */
  public String getManufacturer() {
    return this.manufacturer;
  }

  /**
   * Sets the ItemType of a product object
   * @param type  The new ItemType
   */
  public void setType(ItemType type) {
    this.type = type;
  }

  /**
   * Grabs the current ItemType of
   * @return The current ItemType
   */
  public ItemType getType() { return this.type; }

  /**
   * Gets the itemCode of the product object
   * @return  The current itemCode
   */
  public String getItemCode() {
    return this.itemCode;
  }

}

/**
 * Widget extends Product so that Product can be used as Product is abstract.
 * Widget is the main type of product being produced in the controller.
 */
class Widget extends Product{

  public Widget(String name, String manufacturer, ItemType item) {
    super(name, manufacturer, item);
  }

}

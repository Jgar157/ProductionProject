import java.util.Date;

public abstract class Product implements Item{

  int id;
  ItemType type;
  String itemCode;
  String manufacturer;
  String name;

  public Product(String name, String manufacturer, ItemType item) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = item;
    this.itemCode = item.code;
  }

  public String toString() {
    return String.format("Name: %s\nManufacturer: %s\nType: %s",
        this.name, this.manufacturer, this.type);
  }

  public void setID(int id) { this.id = id; }

  public int getID() { return this.id; }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() { return this.name; };

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return this.manufacturer;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public ItemType getType() { return this.type; }

  public void setItemCode(ItemType type) {
    this.itemCode = type.code;
  }

  public String getItemCode() {
    return this.itemCode;
  }

}

class Widget extends Product{

  public Widget(String name, String manufacturer, ItemType item) {
    super(name, manufacturer, item);
  }

}

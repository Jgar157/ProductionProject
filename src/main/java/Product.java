import java.util.Date;

public abstract class Product implements Item{

  int id;
  String type;
  String itemCode;
  String manufacturer;
  String name;

  public Product(String name, String manufacturer, ItemType item) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = item.name();
    this.itemCode = item.code;
  }

  public String toString() {
    return String.format("Name: %s\nManufacturer: %s\nType: %s",
        this.name, this.manufacturer, this.type);
  }

  public int getId() { return this.id; }

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

  public String getItemTypeCode() {
    return this.itemCode;
  }

}

class Widget extends Product{
  public Widget(String name, String manufacturer, ItemType item) {
    super(name, manufacturer, item);
  }
}

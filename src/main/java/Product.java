
public abstract class Product implements Item{

  int id;
  String type;
  String manufacturer;
  String name;

  public Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString() {
    return String.format("Name: %s \nManufacturer: %s \nType: %s",
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

}

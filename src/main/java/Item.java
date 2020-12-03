/**
 * The item interface allows for polymorphism to be used with each implementation.
 * It represents an item which has an ID, name, and manufacturer.
 * Primarily used for the Products.
 * @author Jairo Garciga
 */
public interface Item {

  int getID();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();

}

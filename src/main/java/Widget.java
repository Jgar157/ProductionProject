/**
 * Widget extends Product so that Product can be used as Product is abstract.
 * Widget is the main type of product being produced in the controller.
 */
class Widget extends Product {

  public Widget(String name, String manufacturer, ItemType item) {
    super(name, manufacturer, item);
  }

}

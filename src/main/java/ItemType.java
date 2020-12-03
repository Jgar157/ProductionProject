/**
 * An enum for ItemTypes for products
 * Contains the String representation of it's code
 * @author Jairo Garciga
 */
public enum ItemType {

    AUDIO("AU"),
    VISUAL("VI"),
    AUDIO_MOBILE("AM"),
    VISUAL_MOBILE("VM");


    String code;
    /**
     * Creates an ItemType with a specific code
     */
    ItemType(String code) {
      this.code = code;
    }
}

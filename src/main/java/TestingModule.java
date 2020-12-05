/**
 * This is a testing class I used for making sure
 * a specific method actually reverses a string.
 * It's easier to test when it doesn't take 20 seconds to launch.
 *
 * @author Jairo Garciga
 */
public class TestingModule {

  /**
   * This main driver tests if the string "oriaj" can be reversed.
   *
   * @param args An unused set of arguments, mainly just placeholders.
   */
  public static void main(String[] args) {

    String name = "oriaj";
    name = reverseString(name);
    System.out.println(name);

  }

  /**
   * This method is used in the controller to reverse the database password.
   *
   * @param pw The string to be reversed
   * @return The new reversed string
   */
  public static String reverseString(String pw) {

    if (pw.length() == 1) {
      return pw;
    }
    return pw.substring(pw.length() - 1) + reverseString(pw.substring(0, pw.length() - 1));
  }
}

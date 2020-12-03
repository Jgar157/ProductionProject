import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to make a an employee object which is then used for tracking production for each employee.
 * @author Jairo Garciga
 */
public class Employee {

    public StringBuilder name;
    public String username;
    public String password;
    public String email;

    /**
     * Takes the input for the name and password of the employee
     * @param name  The name of the employee
     * @param password  The password of the employee
     */
    public Employee(String name, String password) {

        if (this.checkName(name)) {
            this.setUsername(name);
            this.setEmail(name);
        } else {
            this.username = "default";
            this.email = "user@oracleacademy.Test";
        }
        this.name = new StringBuilder(name);

        if (this.isValidPassword(password)) {
            this.password = password;
        } else {
            this.password = "pw";
        }
    }

    /**
     * Returns a string representing all the data associated with an employee object
     * @return  The String representing the data associated with an employee object.
     */
    public String toString() {
        return String.format("Employee Details\nName : %s\nUsername : %s\nEmail : %s\nInitial Password : %s\n",
                this.name, this.username, this.email, this.password);
    }

    /**
     * Creates a username using the first letter of their first name and the entire last name,
     * then sets it all to lower case.
     * @param name  The name of the employee
     */
    private void setUsername(String name) {
        this.username = (name.charAt(0) + name.substring(name.indexOf(" ") + 1)).toLowerCase();
    }

    /**
     * Checks if the name is a valid name, must contain a space between the first and last name.
     * Also, it fails if the final input is a space. This is because if the first name has a space
     * after it but no last name, the matcher does not know this. Instead we check if the last character
     * is a space because in that case it catches the missing last name or a bad space after the last name.
     * @param name  The name of the employee
     * @return  Boolean representing if the name is a valid name
     */
    private boolean checkName(String name) {
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher = pattern.matcher(name);
        if (name.charAt(name.length() - 1) != ' ')
            return matcher.find();
        else
            return false;
    }

    /**
     * Sets the email to the format firstName.lastName@oracleacademy.Test
     * @param name  The name of the employee
     */
    public void setEmail(String name) {
        name = name.toLowerCase();
        this.email = String.format("%s.%s@oracleacademy.Test",
                name.substring(0,name.indexOf(" ")), name.substring(name.indexOf(" ") + 1));
    }

    /**
     * Checks if the password has special characters, a lower case, and an upper case.
     * @param password  The desired user password
     * @return  Boolean representing if the user password is valid
     */
    private boolean isValidPassword(String password) {
        Matcher matcher;
        Pattern[] pattern = new Pattern[3];
        pattern[0] = Pattern.compile("[!@#$%^&*(){}?><]");
        pattern[1] = Pattern.compile("[a-z]+");
        pattern[2] = Pattern.compile("[A-Z]+");


        // I couldn't figure out how to combine the regex
        for (int i = 0; i < 3; i ++) {
            matcher = pattern[i].matcher(password);
            if (!matcher.find()) {
                // System.out.println("Broke on " + pattern[i]);
                // Previous line finds the reason why a specific password didn't work
                return false;
            }
        }
        return true;
    }
}

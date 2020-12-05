/**
 * Screen contains several variables that represent attributes
 * of a typical electronic screen such as resolution and refresh rate.
 *
 * @author Jairo Garciga
 */
public class Screen implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  /**
   * Constructor for the screen class.
   *
   * @param resolution   The resolution of the screen, Whole number by Whole number
   * @param refreshRate  The refresh rate of the screen in hertz
   * @param responseTime The response time of a screen
   */
  public Screen(String resolution, int refreshRate, int responseTime) {

    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;

  }

  /**
   * Gets the resolution of the screen.
   *
   * @return The resolution of the screen
   */
  public String getResolution() {
    return this.resolution;
  }

  /**
   * Gets the refresh rate of the screen.
   *
   * @return The refresh rate of the screen
   */
  public int getRefreshRate() {
    return this.refreshRate;
  }

  /**
   * Gets the response time of the screen.
   *
   * @return The response time
   */
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * Creates a string output for the object using each of the attributes.
   *
   * @return The string output for the screen
   */
  public String toString() {
    return String.format("\nResolution: %s\nRefresh rate: %s\nResponse time: %s",
            this.resolution, this.refreshRate, this.responseTime);
  }

}

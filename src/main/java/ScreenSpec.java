/**
 * Author: Jairo Garciga
 * Interface for attributes of a screen
 */

public interface ScreenSpec {

  // Interestingly no setters
  public String getResolution();
  public int getRefreshRate();
  public int getResponseTime();

}

/**
 * Interface for attributes of a screen
 * @author Jairo Garciga
 */

public interface ScreenSpec {

  // Interestingly no setters
  public String getResolution();
  public int getRefreshRate();
  public int getResponseTime();

}

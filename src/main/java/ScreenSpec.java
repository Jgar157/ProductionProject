/**
 * Interface for attributes of a screen.
 * @author Jairo Garciga
 */

public interface ScreenSpec {

  // Interestingly no setters
  String getResolution();

  int getRefreshRate();

  int getResponseTime();

}

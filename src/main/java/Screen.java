

public class Screen implements ScreenSpec{

  String resolution;
  int refreshrate;
  int responsetime;

  public Screen(String resolution, int refreshrate, int responsetime) {

    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;

  }

  public String getResolution() {
    return this.resolution;
  }

  public int getRefreshRate() {
    return this.refreshrate;
  }

  public int getResponseTime() {
    return responsetime;
  }

  public String toString() {
    return String.format("\nResolution: %s\nRefresh rate: %s\nResponse time: %s",
    this.resolution, this.refreshrate, this.responsetime);
  }

}

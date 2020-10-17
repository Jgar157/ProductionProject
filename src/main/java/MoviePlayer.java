

public class MoviePlayer extends Product implements MultimediaControl{

  public Screen screen;
  public MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {

    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;

  }

  public String toString() {

    return super.toString() + String.format(
        "\nScreen: %s\nMonitor Type: %s",
        this.screen, this.monitorType);
  }

  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }
}

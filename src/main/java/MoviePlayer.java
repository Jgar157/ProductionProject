/**
 * Extends the product class to create a movie player product.
 *
 * @author Jairo Garciga
 */
public class MoviePlayer extends Product implements MultimediaControl {

  public Screen screen;
  public MonitorType monitorType;

  /**
   * Constructor for the MoviePlayer class which calls Product constructor to set up
   * all the variables and then sets up it's object variables.
   *
   * @param name         The name of the movie player
   * @param manufacturer The manufacturer of the movie player
   * @param screen       The screen type of the movie player
   * @param monitorType  The monitor type of the movie player
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {

    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;

  }

  /**
   * Calls Product toString method and then appends the screen and monitor type.
   *
   * @return The string output of the moviePlayer object
   */
  public String toString() {

    return super.toString() + String.format(
            "\nScreen: %s\nMonitor Type: %s",
            this.screen, this.monitorType);
  }

  /**
   * Output that the movie is playing.
   */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * Output that the movie is stopping.
   */
  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * Output that the player is going to the previous movie.
   */
  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  /**
   * Output that the player is going to the next movie.
   */
  @Override
  public void next() {
    System.out.println("Next movie");
  }
}

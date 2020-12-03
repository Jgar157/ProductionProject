/**
 * A class that extends Product and creates an audio player.
 * @author Jairo Garciga
 */
public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudioFormats;
  String supportedPlaylistFormats;

  /**
   * Creates a specific Audio object, extends Product so it can implement it's methods and constructor
   * @param name  The name of the AudioPlayer
   * @param manufacturer  The name of the Manufacturer
   * @param supportedAudioFormats The audio format, MP3, etc.
   * @param supportedPlaylistFormats  The supported Playlist format
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {

    // Audio player defaults the type to AUDIO
    super(name, manufacturer, ItemType.AUDIO);

    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /***
   * First the method grabs the string from the parent class and concatenates
   * it's portion to it afterwards
   * @return  Returns a new string including supported audio and playlist formats
   */
  public String toString() {

    return super.toString() + String.format(
        "\nSupported Audio Formats: %s\nSupported Playlist Formats: %s",
        this.supportedAudioFormats, this.supportedPlaylistFormats);
  }

  /**
   * Outputs that the device is playing
   */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /**
   * Outputs that the device is stopping
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * Outputs that the device is going to the previous song
   */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * Outputs that the device is going to the next song
   */
  @Override
  public void next() {
    System.out.println("Next");
  }
}

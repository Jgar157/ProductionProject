
public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudioFormats;
  String supportedPlaylistFormats;
  String type = "AUDIO";

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

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }
}

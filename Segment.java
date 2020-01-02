public abstract class Segment {
  protected Controller controller;

  public Segment(Controller controller) {
    this.controller = controller;
  }

  public abstract void onRead(String read);
}
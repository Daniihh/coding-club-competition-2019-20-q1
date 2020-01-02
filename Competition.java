import java.util.Scanner;
import java.lang.reflect.Constructor;

public final class Competition implements Controller {
  private final class InputListener extends Thread {
    private InputListener() {
      this.start();
    }

    @Override
    public void run() {
      while (Competition.this.running) {
        if (Competition.this.segment == null)
          Competition.this.readMenuSelection();
        else
          Competition.this.readForSegment();
        Thread.interrupted();
      }
    }
  }

  private static final String TITLE_PREFIX = "-=-= ";
  private static final String TITLE_SUFFIX = " =-=-";
  private static final String TITLE_MAIN = "Main Menu";

  private static final String MAIN_INSTRUCTION = "Please type in a number to go to that segment.";
  private static final String MAIN_SELECT = "SELECTION> ";

  private Class<? extends Segment>[] segments;
  private Segment segment;
  private Scanner inputReader;
  private boolean running;

  @SafeVarArgs
  public Competition(Class<? extends Segment>... segments) {
    this.segments = segments;
    this.inputReader = new Scanner(System.in).useDelimiter("\\n");
    this.running = true;

    new InputListener();

    this.printMainMenu();
  }

  private void printMainMenu() {
    StringBuilder menu = new StringBuilder('\n');
    menu.append(Competition.TITLE_PREFIX);
    menu.append(Competition.TITLE_MAIN);
    menu.append(Competition.TITLE_SUFFIX).append('\n');
    menu.append(Competition.MAIN_INSTRUCTION);
    for (int i = 0; this.segments.length > i ; i++)
      menu.append('\n').append(i).append(": ").append(segments[i].getSimpleName());
    menu.append('\n').append(Competition.MAIN_SELECT);
    System.out.print(menu.toString());
  }

  private void readMenuSelection() {
    int read = this.inputReader.nextInt();
    if (read == -1)
      this.running = false;
    else {
      try {
        Class<? extends Segment> chosen = this.segments[read];
        Constructor<? extends Segment> constructor = chosen.getConstructor(Controller.class);
        System.out.println();
        this.segment = constructor.newInstance(this);
      } catch (Exception ex) {
        this.running = false;
        ex.printStackTrace();
      }
    }
  }

  private void readForSegment() {
    String line = this.inputReader.next();
    this.segment.onRead(line);
  }

  @Override
  public void finish() {
    this.segment = null;
    System.out.println();
    this.printMainMenu();
  }
}
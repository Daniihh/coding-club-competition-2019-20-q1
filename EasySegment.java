import java.util.Arrays;

public final class EasySegment extends Segment {
  public EasySegment(Controller controller) {
    super(controller);
    
    int baseVal = 100;
    double subtractVal = 1.5;
    boolean doTwice = true;
    
    System.out.println("Your integer value is: " + baseVal);
    System.out.println("Your double value is: " + subtractVal);
    System.out.println("Your boolean value is: " + doTwice);

    char baseChar = (char) baseVal;
    System.out.println("Your char value is: " + baseChar);

    char[] charArray = new char[5];
    charArray[0] = baseChar;
    if (doTwice)
      charArray[1] = (char) (baseVal - subtractVal - subtractVal);
    else
      charArray[1] = (char) (baseVal - subtractVal);
    charArray[2] = (char) 110;
    charArray[3] = (char) 105;
    charArray[4] = (char) 105;
    
    System.out.println("Your array value is (converted to a string): " + Arrays.toString(charArray));

    String str = new String(charArray);
    System.out.println("Your string value is: " + str);

    System.out.println("What's your first name?");
    System.out.print("NAME> ");
  }

  @Override
  public void onRead(String read) {
    System.out.println("Hello there " + read + "! Nice to meet you!");
    this.controller.finish();
  }
}
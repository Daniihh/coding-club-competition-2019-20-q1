public final class UserInputSegment extends Segment {
  private Boolean sameTime;
  private int action;

  public UserInputSegment(Controller controller) {
    super(controller);

    System.out.println("Would you like to have all actions applied at the same time (T) or seperately (F)?");
    System.out.print("CHOICE> ");
  }

  public void onRead(String read) {
    if (this.sameTime == null) {
      read = read.toUpperCase();
      this.sameTime = "T".equals(read) || "TRUE".equals(read);

      if (this.sameTime) this.inputSomething("a string to run all actions on");
      else this.inputSomething("a string to be turned into title case");
    } else if (this.sameTime) {
      if (read.toUpperCase().equals(read))
        read = read.toLowerCase();
      else
        read = read.substring(0, 1).toUpperCase() + read.substring(1);
      read = read.replace(" space ", " ");
      System.out.println("Output: " + read);
      this.controller.finish();
    } else if (this.action == 0) {
      read = read.substring(0, 1).toUpperCase() + read.substring(1);
      System.out.println("Output: " + read);

      this.action++;
      this.inputSomething("a string to be turned into lowercase if it's all upercase");
    } else if (this.action == 1) {
      if (read.toUpperCase().equals(read))
        read = read.toLowerCase();
      System.out.println("Output: " + read);
      
      this.action++;
      this.inputSomething("a string to replace the word space with spaces");
    } else if (this.action == 2) {
      read = read.replace(" space ", " ");
      System.out.println("Output: " + read);
      
      this.controller.finish();
    }
  }

  private void inputSomething(String to) {
    System.out.println("Please input " + to + '.');
    System.out.print("INPUT> ");
  }
}
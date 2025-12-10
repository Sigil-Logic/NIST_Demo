/**
 * JML-specified Hello World program.
 *
 * The intent is to demonstrate a minimal, side-effect constrained program
 * whose behavior can be checked with OpenJML runtime assertion checking (RAC).
 */
public final class HelloWorld {

  /**
   * The canonical message that should be emitted.
   *
   * @return "Hello, World\n"
   */
  //@ ensures \result.equals("Hello, World\n");
  public /*@ pure @*/ String message() {
    return "Hello, World\n";
  }

  /**
   * Writes the message to the provided PrintStream and does nothing else.
   *
   * @param out destination for program output
   */
  //@ requires out != null;
  //@ assignable out.*;
  //@ ensures true; // runtime harness checks the observable output
  public void run(java.io.PrintStream out) {
    out.print(message());
  }

  /**
   * Entry point that delegates to {@link #run(java.io.PrintStream)} using System.out.
   */
  public static void main(String[] args) {
    new HelloWorld().run(System.out);
  }
}

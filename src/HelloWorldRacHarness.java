import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * Runtime verification harness for HelloWorld when compiled with OpenJML RAC.
 *
 * It captures stdout, asserts the exact expected message, and repeats across
 * several iterations to mimic stochastic runs. RAC enforces the JML contracts
 * during execution; this harness checks the observable output.
 */
public final class HelloWorldRacHarness {
  private static final String EXPECTED = "Hello, World\n";

  public static void main(String[] args) {
    int iterations = iterationsFromEnv();
    for (int i = 0; i < iterations; i++) {
      runOnce();
    }
    System.out.println("RAC harness completed " + iterations + " iterations.");
  }

  private static void runOnce() {
    HelloWorld hello = new HelloWorld();
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream capture = new PrintStream(buffer);

    hello.run(capture);
    capture.flush();

    String observed = buffer.toString();
    if (!EXPECTED.equals(observed)) {
      throw new AssertionError(
          "Observed output mismatch. Expected \"" + EXPECTED + "\", got \"" + observed + "\"");
    }
  }

  private static int iterationsFromEnv() {
    String raw = System.getenv("HELLO_RAC_RUNS");
    if (raw == null || raw.isEmpty()) {
      return 5;
    }
    try {
      int value = Integer.parseInt(raw);
      if (value > 0) {
        return value;
      }
    } catch (NumberFormatException ignored) {
      // Fall through to randomized fallback.
    }
    // Provide a small randomized fallback to still get some coverage.
    return new Random().nextInt(5) + 1;
  }
}

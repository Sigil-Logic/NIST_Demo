# NIST_Demo

## Formally specified Hello World (JML + OpenJML)

Files:
- `src/HelloWorld.java` — JML-annotated implementation; `message()` is pure, `run(PrintStream)` is side-effect constrained (`assignable out.*`), and `main` delegates to `run(System.out)`.
- `src/HelloWorldRacHarness.java` — runtime verification harness that captures stdout and asserts the exact message. It respects `HELLO_RAC_RUNS` to control iteration count.

Running with OpenJML runtime assertion checking (RAC):
```sh
mkdir -p out
openjml -rac -d out src/HelloWorld.java src/HelloWorldRacHarness.java
java -cp out HelloWorldRacHarness
```
Set `HELLO_RAC_RUNS` (e.g., `HELLO_RAC_RUNS=20`) to increase stochastic runs. RAC enforces the JML contracts during execution; the harness checks the observable output. When both pass, you have runtime evidence that the program emits exactly `"Hello, World\n"` and touches nothing beyond the provided `PrintStream`.

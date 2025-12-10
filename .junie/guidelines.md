### NDIST_Demo — Project‑specific Development Guidelines

#### Build and Configuration
- JDK: The environment uses `javac 21` and `java 21`. Ensure your `JAVA_HOME` points to a JDK 21 installation and `javac -version` reports 21.x.
- Build tool: None configured (no Maven/Gradle). The project is currently a raw IntelliJ IDEA project with `.iml` and `.idea` metadata.
- Source layout: Minimal. Files currently in repo:
  - `src/Main.java` — note: not a standard Java class with `public static void main(String[] args)`. It appears to be a snippet for the IDE’s educational template, and references `IO.println`, which is not part of the standard library. Treat it as IDE‑run snippet rather than a `javac` target.
  - `README.md` — documents OpenJML usage for files that are not present in the repo. See “OpenJML note” below.

- Compiling ad‑hoc Java files:
  - You can compile standalone Java classes in the project root or under `src/` with: `javac <path/to/File.java>`
  - Run with: `java <ClassName>` (ensure you run from the directory containing the compiled `.class` file, or set an appropriate classpath with `-cp`).

- IntelliJ IDEA:
  - The `.iml` suggests a simple module without external dependencies. You can add a standard Java file with a proper entry point and run it via the gutter run icon.
  - If you convert this project to Gradle or Maven later, prefer JDK 21 toolchain and keep source/target compatibility at 21.

#### Testing: How to Configure and Run
Currently there is no test framework configured (no JUnit/Gradle/Maven). Until you introduce one, you can:

- Use self‑contained Java test drivers (small `main` programs) that assert conditions and set a non‑zero exit code on failure. This works with the existing lightweight setup and can be run from the command line or IDE.

Example (validated):
1) Create a file `TestExample.java` in the project root with an assertion‑like check and exit code handling:
```
public class TestExample {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 5; i++) sum += i;
        if (sum != 15) {
            System.out.println("[TEST FAILED] Expected 15, got " + sum);
            System.exit(1);
        }
        System.out.println("[TEST PASSED] sum=15");
    }
}
```
2) Compile and run:
```
javac TestExample.java
java TestExample
```
Observed output (on JDK 21):
```
[TEST PASSED] sum=15
```

Guidelines for adding new tests in this style:
- Put each test in its own `*.java` with a `main` method.
- Return non‑zero on failure via `System.exit(1)`; print a clear `[TEST PASSED]` or `[TEST FAILED]` line.
- If you need to share helpers, place them in package‑scoped classes and compile with `javac -cp . src/... helper/...` ensuring the classpath includes the output location.

Transitioning to a proper test framework:
- Recommended: Adopt Gradle with the `java` and `jvm-test-suite` plugins and add `testImplementation("org.junit.jupiter:junit-jupiter:5.11.x")`. Configure toolchain to 21. This will allow `./gradlew test` and IDE‑integrated test running.

#### Additional Development Information
- Code style:
  - Use standard modern Java style (4‑space indent, `lowerCamelCase` for methods/vars, `UpperCamelCase` for classes). Keep imports explicit; avoid wildcard imports.
  - Prefer `var` (Java 10+) for obvious local types; avoid where it harms readability.
  - Nullability: there is no annotation framework configured; be explicit in method contracts and parameter checks.

- `src/Main.java` specifics:
  - The file declares `void main()` without `public static` and uses `IO.println`, which suggests an IDE‑provided runtime or template, not standard Java. Do not rely on it for command‑line builds. If you need a runnable entry point, add a conventional class, e.g.:
```
public class App {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}
```
Compile and run with `javac src/App.java` and `java -cp src App` (or compile to an `out/` directory and run from there).

- OpenJML note:
  - `README.md` references `src/HelloWorld.java` and `src/HelloWorldRacHarness.java` and `openjml` commands; these files and the OpenJML toolchain are not included in this repo. If you intend to use OpenJML RAC:
    - Add those sources under `src/` and install OpenJML.
    - Example commands from the README (adjust paths):
      - `mkdir -p out`
      - `openjml -rac -d out src/HelloWorld.java src/HelloWorldRacHarness.java`
      - `java -cp out HelloWorldRacHarness`

- Housekeeping:
  - Keep ad‑hoc test drivers out of `src/` if they are not part of shipped code. Prefer a `tests/` folder or remove them after validation.
  - IDE metadata (`.idea/`, `*.iml`) is already present; ensure they reflect your local JDK and language level. Consider adding a `.gitignore` if not already present.

#### Verified Demo Status
- The demo test `TestExample.java` was compiled and executed successfully with output `[TEST PASSED] sum=15`. The file was only used to validate the workflow and has been removed to keep the repo clean.

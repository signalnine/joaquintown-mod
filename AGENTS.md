# Repository Guidelines

## Project Structure & Module Organization
- `src/main/java/com/joaquintown/mod` hosts shared logic (events, gameplay hooks) and houses mixins like `ExampleMixin`.
- `src/main/java/com/joaquintown/mod/world` stores persistent state helpers, e.g., the spawn village tracker.
- `src/main/resources` holds Fabric metadata (`fabric.mod.json`), access wideners, and future assets. Match resource namespaces to `joaquintown`.
- Gradle outputs land in `build/`; published JARs appear in `build/libs`. Clean generated artifacts with `./gradlew clean`.

## Build, Test, and Development Commands
- Ensure `JAVA_HOME` points to a Java 21 runtime (e.g., `export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home`) before invoking Gradle tasks.
- `./gradlew genSources` syncs mapped sources; run it after pulling Yarn or Loom upgrades before opening your IDE.
- `./gradlew build` compiles, runs unit tests, and produces the distributable JAR in `build/libs/`.
- `./gradlew runClient` launches a Fabric dev client with this mod; tweak JVM args in `gradle.properties` if needed.
- `./gradlew runServer` mirrors the dev server environment for integration checks.
- Loom is already configured with `splitEnvironmentSourceSets()`—add `src/client/java` back if you introduce client-only code.

## Coding Style & Naming Conventions
- Target Java 21; keep imports explicit and unused imports trimmed. Indent with tabs (same as current sources) and place braces on the same line.
- Name classes in PascalCase, methods in camelCase, constants in UPPER_SNAKE_CASE. Align package names with `com.joaquintown.mod` or a consistent alternative.
- Update `fabric.mod.json` and `joaquintown.accesswidener` together when you change the mod ID or namespace.

## Testing Guidelines
- Unit and integration tests belong in `src/test/java`; mirror package names from `src/main/java`.
- Use JUnit 5 (Gradle wires it in via Loom); name files `*Test.java` and keep tests deterministic.
- Run `./gradlew test` locally before opening a pull request; add focused fixtures when touching mixins or access wideners.

## Commit & Pull Request Guidelines
- There is no public Git history yet; adopt short, imperative commit subjects (e.g., `Add mixin for server load hook`) with optional detail in the body.
- Each PR should describe intent, list gameplay-impacting changes, and reference any tracked issues. Attach screenshots or logs when behavior changes are user-visible.
- Verify `./gradlew build` and relevant run tasks before requesting review, and call out skipped steps or known follow-ups in the PR description.

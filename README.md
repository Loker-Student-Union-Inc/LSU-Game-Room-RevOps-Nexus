# LSU-Game-Room-RevOps-Nexus

This application is used for activity tracking for the games room at Loker Student Union, managing shift information, updating, extracting shift totals, and handling profile and activity information.

## Local Configuration

Ensure that the following Gradle properties are set up correctly in `~/.gradle/gradle.properties`.

```properties
artifactoryUsername=<your-artifactory-username>
artifactoryPassword=<your-artifactory-password>
```

## Build App

To build the application, run the following command:

```bash
./gradlew clean build
```

## Running Tests

To run the test suite, use:

```bash
./gradlew clean test
```

## Running the Application Locally

The Spring's active profile is derived from the `-Denvironment` parameter and is set to `local` by default. To run the application locally with another profile like `dev`, use:

```bash
./gradlew bootRun -PjvmArgs="-Denvironment=dev -Dapp.config=src/main/resources -Dlog4j.configurationFile=log4j2-dev.xml"
```

- To print the logs to the console, use the above Log4j configuration property in the JVM arguments.
- The encrypted database password will be decrypted using the live config encryption infrastructure.

## Application's Common Endpoints

- **Application Heartbeat Endpoint**: `/lsu-game-room-revops-nexus/heartbeat`
- **Application Health Check Endpoint**: `/lsu-game-room-revops-nexus/health-check`

## CI/CD Pipeline

Use GitHub Actions to release a version of this library.

[GitHub Repository](https://github.com/Loker-Student-Union-Inc/LSU-Game-Room-RevOps-Nexus.git)

### Jenkins Integration (Optional)

If you plan to use Jenkins, ensure that your Jenkins pipeline is configured to use the same Gradle commands for building, testing, and deploying the application.

## License

Include any licensing information here if applicable.

```

### Customizing the README:

- Replace placeholders like `<your-artifactory-username>` and `<your-artifactory-password>` with actual instructions or variables.
- Ensure all commands and paths are accurate and relevant to your project setup.
- You may also want to add sections for **Prerequisites**, **Installation**, **Usage**, **Contributing**, **Versioning**, or **Authors** if needed.

This structure will help others understand how to set up, build, and run your application, as well as how to contribute or use it in different environments.
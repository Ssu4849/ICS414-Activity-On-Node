# Activity On Node

## Requirements
* [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Note
If you want to build and run the system using Maven, you may need to add the `JAVA_HOME` variable in the system environment
variables.

For Windows, instructions can be found [here](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html).

For UNIX, follow step 2 [here](https://docs.oracle.com/cd/E19182-01/821-0917/inst_set_jdk_korn_bash_t/index.html).

## Build Instructions

### Using Maven
1. Clone the repository using `git clone https://github.com/Ssu4849/ICS414-Activity-On-Node`
2. Move into the cloned folder using `cd ICS414-Activity-On-Node`
3. Install the dependencies using:
   * UNIX/macOS: `./mvnw install`
   * Windows: `mvnw install`
4. Run the application's GUI using:
   * UNIX/macOS: `./mvnw exec:java@gui`
   * Windows: `mvnw exec:java@gui`
5. Alternatively, you can run the application's CLI using:
   * UNIX/macOS: `./mvnw exec:java@cli`
   * Windows: `mvnw exec:java@cli`

### Using Eclipse
1. Clone the repository using `git clone https://github.com/Ssu4849/ICS414-Activity-On-Node`
2. Add the `ICS414-Activity-On-Node` folder to your workspace
3. Navigate to `src/main/java/edu/mango/activityonnode/MainMenu.java` on Eclipse
4. Run (ignore the warnings from the IDE saying that errors exist)

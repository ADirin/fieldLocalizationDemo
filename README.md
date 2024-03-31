Application Overview:
This application, named "LocalizationDemo," is a Java program designed to demonstrate how to localize messages in a Java application. It allows users to select a language (English, Japanese, or Farsi) and then enter employee information (name, age, salary), which is then inserted into a MySQL database. The application supports localization of messages based on the selected language.

Challenges to Get This Running:
•	Dependency Resolution: The project relies on external dependencies managed by Maven. One challenge encountered was the failure to resolve the MySQL Connector/J dependency during the build process. This issue arose due to Maven being unable to locate the required dependency in the Maven Central repository.
•	Missing ResourceBundle: Another challenge was the failure to locate the ResourceBundle file named "MessagesBundle" during runtime. This issue occurred because the application expected this file to be present for loading localized messages, but it was not found in the specified location.
•	Classpath and Environment Setup: Ensuring that the classpath is correctly configured and all necessary environment variables are set up properly was crucial for the application to run without errors. Any misconfiguration or missing setup could lead to runtime errors such as ClassNotFoundException or SQLException.

Solutions:
•	Dependency Resolution: To resolve the issue with dependency resolution, it's essential to verify the network connectivity and repository configuration. Additionally, manually installing the missing dependency to the local Maven repository can be a workaround if the dependency is not available in the remote repository.
•	ResourceBundle Location: Ensure that the ResourceBundle file is correctly named and placed in the expected location within the project's resources directory. Verify that the file naming and loading conventions are consistent with Java's ResourceBundle API.
•	Classpath and Environment Setup: Double-check the classpath configuration in the project's build configuration files (e.g., pom.xml for Maven projects) to include all required dependencies. Additionally, verify that the Java environment variables, such as JAVA_HOME and PATH, are correctly set up to point to the JDK installation directory

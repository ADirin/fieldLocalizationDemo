import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocalizationDemo {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/field_localization";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Test12";

    // ResourceBundle for localized strings
    private static ResourceBundle messages;

    public static void main(String[] args) {
        // Load messages for the default locale (English)
        messages = ResourceBundle.getBundle("messages", Locale.getDefault());

        // Prompt the user to select a language
        selectLanguage();

        // Connect to the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Insert employee data
            insertEmployee(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectLanguage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a language:");
        System.out.println("1. English");
        System.out.println("2. Japanese");
        System.out.println("3. Farsi");
        int choice = scanner.nextInt();

        // Set the default locale based on user's choice
        switch (choice) {
            case 1:
                Locale.setDefault(new Locale("en", "US"));
                break;
            case 2:
                Locale.setDefault(new Locale("ja", "JP"));
                break;
            case 3:
                Locale.setDefault(new Locale("fa", "IR")); // Farsi locale
                break;
            default:
                System.out.println("Invalid choice. Defaulting to English.");
                Locale.setDefault(Locale.ENGLISH);
        }

        // Load messages for the selected locale
        messages = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    private static void insertEmployee(Connection conn) throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println(messages.getString("prompt.enter.employee.name"));
            String name = scanner.nextLine();

            System.out.println(messages.getString("prompt.enter.employee.age"));
            int age = scanner.nextInt();

            System.out.println(messages.getString("prompt.enter.employee.salary"));
            double salary = scanner.nextDouble();

            // Prepare the SQL statement
            String sql = "INSERT INTO employees (name, age, salary) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setDouble(3, salary);

                // Execute the insert
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

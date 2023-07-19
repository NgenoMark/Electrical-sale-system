package electric.company.electricalsalescompany;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Electrical_sales_company";
    private static final String DB_USER = "localhost";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Execute a sample query
            String query = "SELECT * FROM tools";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from each row
                String toolName = resultSet.getString("tool_name");
                double power = resultSet.getDouble("power");
                double price = resultSet.getDouble("price");

                // Perform further processing or display the retrieved data
                System.out.println("Tool Name: " + toolName);
                System.out.println("Power: " + power);
                System.out.println("Price: " + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

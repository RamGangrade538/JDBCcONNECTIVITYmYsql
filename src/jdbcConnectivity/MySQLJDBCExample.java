package jdbcConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLJDBCExample {

    public static void main(String[] args) throws SQLException {
        // Update with your actual database, username, and password
        String jdbcUrl = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "myuser"; // Your MySQL username
        String password = "StrongP@ssw0rd!"; // Your MySQL password

        // Establish connection
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connected to MySQL database!");

        // Create the table
        Statement statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "designation VARCHAR(50), " +
                "salary DECIMAL(10, 2), " +
                "age INT)";
        statement.executeUpdate(createTableSQL);
        System.out.println("Table 'employees' created!");

        // Insert 10 entries
        String insertSQL = "INSERT INTO employees (name, designation, salary, age) VALUES " +
                "('John Doe', 'Software Engineer', 75000.00, 28), " +
                "('Jane Smith', 'Project Manager', 85000.00, 35), " +
                "('Robert Johnson', 'QA Engineer', 70000.00, 30), " +
                "('Emily Davis', 'UX Designer', 72000.00, 29), " +
                "('Michael Brown', 'DevOps Engineer', 80000.00, 32), " +
                "('Sarah Wilson', 'Business Analyst', 68000.00, 27), " +
                "('David Lee', 'Data Scientist', 90000.00, 31), " +
                "('Laura Miller', 'HR Manager', 76000.00, 34), " +
                "('James Taylor', 'Network Administrator', 73000.00, 29), " +
                "('Olivia Anderson', 'Content Writer', 67000.00, 26)";
        statement.executeUpdate(insertSQL);
        System.out.println("10 entries inserted into 'employees' table!");

        // Query and display the entries
        String selectSQL = "SELECT * FROM employees";
        ResultSet resultSet = statement.executeQuery(selectSQL);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String designation = resultSet.getString("designation");
            double salary = resultSet.getDouble("salary");
            int age = resultSet.getInt("age");

            System.out.printf("ID: %d, Name: %s, Designation: %s, Salary: %.2f, Age: %d%n",
                    id, name, designation, salary, age);
        }

        // Close resources
        resultSet.close();
        statement.close();
        connection.close();
    }
}

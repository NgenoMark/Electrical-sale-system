package electric.company.electricalsalescompany;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene mainScene;
    private TableView<Tool> toolTable;
    private List<Tool> tools;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Electrical Equipment Sales");

        createLoginScene();
        createMainScene();

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void createLoginScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (validateCredentials(username, password)) {
                primaryStage.setScene(mainScene);
            } else {
                System.out.println("Invalid username or password!");
            }
        });

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(loginButton);
        grid.add(buttonBox, 1, 2);

        loginScene = new Scene(grid);
    }

    private void createMainScene() {
        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(40));

        Button addButton = new Button("Add Tool");
        addButton.setOnAction(e -> {
            openAddToolWindow();
        });

        Button checkButton = new Button("Check Available Tools");
        checkButton.setOnAction(e -> {
            populateToolTable();
            primaryStage.setScene(new Scene(toolTable));
        });

        Button sellButton = new Button("Sell Product");
        sellButton.setOnAction(e -> {
            populateToolTable(); // Make sure to populate the 'tools' collection before opening the sell window
            openSellProductWindow();
        });

        vbox.getChildren().addAll(addButton, checkButton, sellButton);

        mainScene = new Scene(vbox);
    }


    private boolean validateCredentials(String username, String password) {
        // Replace the database connection details with your own
        String dbUrl = "jdbc:mysql://localhost:3306/Electrical_sales_company";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Return true if the result set is not empty
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an exception occurs or if the credentials are not found in the database
    }

    private void openAddToolWindow() {
        Stage addToolStage = new Stage();
        addToolStage.setTitle("Add Tool");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label powerLabel = new Label("Power Unit:");
        TextField powerField = new TextField();

        Label universalLabel = new Label("Universal Unit:");
        TextField universalField = new TextField();

        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String powerUnit = powerField.getText();
            String universalUnit = universalField.getText();
            double price = Double.parseDouble(priceField.getText());

            // Save the tool to the database (implement your logic here)
            saveTool(name, powerUnit, universalUnit, price);

            addToolStage.close();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(powerLabel, 0, 1);
        grid.add(powerField, 1, 1);
        grid.add(universalLabel, 0, 2);
        grid.add(universalField, 1, 2);
        grid.add(priceLabel, 0, 3);
        grid.add(priceField, 1, 3);
        grid.add(saveButton, 1, 4);

        Scene addToolScene = new Scene(grid);
        addToolStage.setScene(addToolScene);
        addToolStage.show();
    }

    private void saveTool(String name, String powerUnit, String universalUnit, double price) {
        // Replace the database connection details with your own
        String dbUrl = "jdbc:mysql://localhost:3306/Electrical_sales_company";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "INSERT INTO tools (name, powerunit, universalunit, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, powerUnit);
                statement.setString(3, universalUnit);
                statement.setDouble(4, price);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateToolTable() {
        tools = retrieveToolsFromDatabase();

        toolTable = new TableView<>();
        TableColumn<Tool, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Tool, String> powerUnitColumn = new TableColumn<>("Power Unit");
        TableColumn<Tool, String> universalUnitColumn = new TableColumn<>("Universal Unit");
        TableColumn<Tool, Double> priceColumn = new TableColumn<>("Price");

        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        powerUnitColumn.setCellValueFactory(data -> data.getValue().powerUnitProperty());
        universalUnitColumn.setCellValueFactory(data -> data.getValue().universalUnitProperty());
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject());

        toolTable.getColumns().addAll(nameColumn, powerUnitColumn, universalUnitColumn, priceColumn);
        toolTable.getItems().addAll(tools);
    }

    private List<Tool> retrieveToolsFromDatabase() {
        List<Tool> tools = new ArrayList<>();

        // Replace the database connection details with your own
        String dbUrl = "jdbc:mysql://localhost:3306/Electrical_sales_company";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM tools";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String powerUnit = resultSet.getString("powerunit");
                    String universalUnit = resultSet.getString("universalunit");
                    double price = resultSet.getDouble("price");

                    Tool tool = new Tool(name, powerUnit, universalUnit, price);
                    tools.add(tool);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tools;
    }


    private void openSellProductWindow() {
        Stage sellProductStage = new Stage();
        sellProductStage.setTitle("Sell Product");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberField = new TextField();

        Label idNumberLabel = new Label("ID Number:");
        TextField idNumberField = new TextField();

        Label toolLabel = new Label("Tool:");
        ComboBox<Tool> toolComboBox = new ComboBox<>();
        toolComboBox.getItems().addAll(tools);

        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();

        Button sellButton = new Button("Sell");
        sellButton.setOnAction(e -> {
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String idNumber = idNumberField.getText();
            Tool selectedTool = toolComboBox.getValue();
            double price = Double.parseDouble(priceField.getText());

            // Sell the product (implement your logic here)
            sellProduct(name, phoneNumber, idNumber, selectedTool, price);

            sellProductStage.close();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(phoneNumberLabel, 0, 1);
        grid.add(phoneNumberField, 1, 1);
        grid.add(idNumberLabel, 0, 2);
        grid.add(idNumberField, 1, 2);
        grid.add(toolLabel, 0, 3);
        grid.add(toolComboBox, 1, 3);
        grid.add(priceLabel, 0, 4);
        grid.add(priceField, 1, 4);
        grid.add(sellButton, 1, 5);

        Scene sellProductScene = new Scene(grid);
        sellProductStage.setScene(sellProductScene);
        sellProductStage.show();
    }

    private void sellProduct(String name, String phoneNumber, String idNumber, Tool tool, double price) {
        // Implement your logic to sell the product
        // You can use JDBC or an ORM framework like Hibernate for database operations
        // Here, you can print the details of the sold product as an example
        System.out.println("Product sold:");
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("ID Number: " + idNumber);
        System.out.println("Tool: " + tool.getName());
        System.out.println("Price: " + price);
        System.out.println();
    }

    public static class Tool {
        private final String name;
        private final String powerUnit;
        private final String universalUnit;
        private final double price;

        public Tool(String name, String powerUnit, String universalUnit, double price) {
            this.name = name;
            this.powerUnit = powerUnit;
            this.universalUnit = universalUnit;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getPowerUnit() {
            return powerUnit;
        }

        public String getUniversalUnit() {
            return universalUnit;
        }

        public double getPrice() {
            return price;
        }

        public StringProperty nameProperty() {
            return new SimpleStringProperty(name);
        }

        public StringProperty powerUnitProperty() {
            return new SimpleStringProperty(powerUnit);
        }

        public StringProperty universalUnitProperty() {
            return new SimpleStringProperty(universalUnit);
        }

        public DoubleProperty priceProperty() {
            return new SimpleDoubleProperty(price);
        }
    }
}

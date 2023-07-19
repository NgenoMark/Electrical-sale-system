package electric.company.electricalsalescompany;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Create the login form layout using a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Create form elements
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Validate the username and password
            if (validateCredentials(username, password)) {
                // Redirect to the main application or perform further actions
                System.out.println("Login successful!");
                // Add your logic for navigating to the main application here
            } else {
                // Display an error message for invalid credentials
                System.out.println("Invalid username or password!");
                // Add your logic for displaying an error message here
            }
        });

        // Add form elements to the grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(loginButton);
        grid.add(buttonBox, 1, 2);

        // Create the scene and set it in the stage
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    // Dummy validation method
    private boolean validateCredentials(String username, String password) {
        // Perform your authentication logic here
        // Replace this with your actual authentication mechanism
        return username.equals("admin") && password.equals("password");
    }
}

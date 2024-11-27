import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String USERNAME = "abdelrahman";
    private static final String PASSWORD = "123";

    private BorderPane mainLayout;
    private Scene mainScene;
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        // Show login page on application start
        stage.setTitle("Login");
        Scene loginScene = createLoginScene();
        stage.setScene(loginScene);
        stage.show();
    }

    private Scene createLoginScene() {
        Label titleLabel = new Label("Welcome to Store Management");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#2c3e50"));

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        usernameLabel.setTextFill(Color.web("#34495e"));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        passwordLabel.setTextFill(Color.web("#34495e"));

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        loginButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        Label messageLabel = new Label();
        messageLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        messageLabel.setTextFill(Color.RED);

        loginButton.setOnAction(event -> {
            String inputUsername = usernameField.getText();
            String inputPassword = passwordField.getText();

            if (inputUsername.equals(USERNAME) && inputPassword.equals(PASSWORD)) {
                // Successful login
                stage.setTitle("Store Management System");
                initializeMainPage();
                stage.setScene(mainScene);
            } else {
                // Show error message
                messageLabel.setText("Invalid username or password!");
            }
        });

        VBox loginForm = new VBox(10, titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, messageLabel);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(20));
        loginForm.setStyle("-fx-background-color: #ecf0f1; -fx-border-radius: 10; -fx-padding: 20;");

        StackPane root = new StackPane(loginForm);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #2c3e50, #34495e);");
        stage.setTitle("Store Management System");
        stage.getIcons().add(new Image("7656399.png"));

        return new Scene(root, 400, 300);
    }

    private void initializeMainPage() {
        // Create the main layout
        mainLayout = new BorderPane();

        // Create sidebar navigation
        VBox sidebar = createSidebar();

        // Set the default page (Home)
        mainLayout.setLeft(sidebar);
        showHomePage();

        // Create and set the main scene
        mainScene = new Scene(mainLayout, 1024, 768);
    }

    private VBox createSidebar() {
        Button homeButton = new Button("Home");
        Button productsButton = new Button("Products");
        Button ordersButton = new Button("Orders");
        Button customersButton = new Button("Customers");

        // Styling buttons
        for (Button button : new Button[]{homeButton, productsButton, ordersButton, customersButton}) {
            button.setMaxWidth(Double.MAX_VALUE);
            button.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;");
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;"));
            button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10;"));
        }

        // Button actions
        homeButton.setOnAction(e -> showHomePage());
        productsButton.setOnAction(e -> showProductsPage());
        ordersButton.setOnAction(e -> showOrdersPage());
        customersButton.setOnAction(e -> showCustomersPage());

        VBox sidebar = new VBox(10, homeButton, productsButton, ordersButton, customersButton);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle("-fx-background-color: #2c3e50;");
        sidebar.setPadding(new Insets(10));

        return sidebar;
    }

    private void showHomePage() {
        Label homeLabel = new Label("Welcome to the Home Page");
        homeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        homeLabel.setTextFill(Color.web("#34495e"));

        VBox homePage = new VBox(homeLabel);
        homePage.setAlignment(Pos.CENTER);

        mainLayout.setCenter(homePage);
    }

    private void showProductsPage() {
        TableView<Product> productTable = createProductTable();
        VBox productsPage = new VBox(10, createSearchBar("Search Products"), productTable);
//        productsPage.setAlignment(Pos.CENTER);
        productsPage.setPadding(new Insets(10));

        mainLayout.setCenter(productsPage);
    }

    private void showOrdersPage() {
        TableView<Order> orderTable = createOrderTable();
        VBox ordersPage = new VBox(10, createSearchBar("Search Orders"), orderTable);
//        ordersPage.setAlignment(Pos.CENTER);
        ordersPage.setPadding(new Insets(10));

        mainLayout.setCenter(ordersPage);
    }

    private void showCustomersPage() {
        TableView<Customer> customerTable = createCustomerTable();
        VBox customersPage = new VBox(10, createSearchBar("Search Customers"), customerTable);
//        customersPage.setAlignment(Pos.CENTER);
        customersPage.setPadding(new Insets(10));

        mainLayout.setCenter(customersPage);
    }

    private HBox createSearchBar(String placeholder) {
        TextField searchField = new TextField();
        searchField.setPromptText(placeholder);
        searchField.setMaxWidth(200);

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        searchButton.setOnAction(e -> {
            // Perform search based on the field's text
            System.out.println("Searching for: " + searchField.getText());
            // Add filter logic here (to be implemented)
        });

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setPadding(new Insets(10));
        return searchBox;
    }

    private TableView<Customer> createCustomerTable() {
        TableView<Customer> table = new TableView<>();
        ObservableList<Customer> customers = FXCollections.observableArrayList(
                new Customer("Inna Facey", "ifacey@twitpic.com", "ifacey0", 0, "enabled"),
                new Customer("Lauritz Tuny", "ltuny1@alibaba.com", "ltuny1", 0, "enabled"),
                new Customer("Micky Mee", "mmee2@qfc2.com", "mmee2", 4, "enabled")
        );

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name/Surname");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Customer, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Customer, Integer> ordersCol = new TableColumn<>("Orders");
        ordersCol.setCellValueFactory(new PropertyValueFactory<>("orders"));

        TableColumn<Customer, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(nameCol, emailCol, usernameCol, ordersCol, statusCol);
        table.setItems(customers);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    private TableView<Product> createProductTable() {
        TableView<Product> table = new TableView<>();
        ObservableList<Product> products = FXCollections.observableArrayList(
                new Product("Product 1", "Description of Product 1", 50, 9.99),
                new Product("Product 2", "Description of Product 2", 20, 14.99),
                new Product("Product 3", "Description of Product 3", 10, 19.99)
        );

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Product, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(nameCol, descCol, quantityCol, priceCol);
        table.setItems(products);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    private TableView<Order> createOrderTable() {
        TableView<Order> table = new TableView<>();
        ObservableList<Order> orders = FXCollections.observableArrayList(
                new Order("Order1", "Inna Facey", "Product 1", 10, "Completed"),
                new Order("Order2", "Lauritz Tuny", "Product 2", 5, "Pending"),
                new Order("Order3", "Micky Mee", "Product 3", 2, "Shipped")
        );

        TableColumn<Order, String> orderIdCol = new TableColumn<>("Order ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<Order, String> customerCol = new TableColumn<>("Customer");
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Order, String> productCol = new TableColumn<>("Product");
        productCol.setCellValueFactory(new PropertyValueFactory<>("product"));

        TableColumn<Order, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(orderIdCol, customerCol, productCol, quantityCol, statusCol);
        table.setItems(orders);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    public static class Customer {
        private final String name;
        private final String email;
        private final String username;
        private final int orders;
        private final String status;

        public Customer(String name, String email, String username, int orders, String status) {
            this.name = name;
            this.email = email;
            this.username = username;
            this.orders = orders;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public int getOrders() {
            return orders;
        }

        public String getStatus() {
            return status;
        }
    }

    public static class Product {
        private final String name;
        private final String description;
        private final int quantity;
        private final double price;

        public Product(String name, String description, int quantity, double price) {
            this.name = name;
            this.description = description;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class Order {
        private final String orderId;
        private final String customer;
        private final String product;
        private final int quantity;
        private final String status;

        public Order(String orderId, String customer, String product, int quantity, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.product = product;
            this.quantity = quantity;
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomer() {
            return customer;
        }

        public String getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getStatus() {
            return status;
        }
    }
}

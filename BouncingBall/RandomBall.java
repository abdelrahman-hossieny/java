package bouncingball;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBall extends Application {
    Pane root = new Pane();
    List<BouncingBall> balls = new ArrayList<>();
    Random random = new Random();

    public void start(Stage primaryStage) {
        Button btn = new Button("Add Ball");
        btn.setOnAction(e -> addNewBall());
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
    private void addNewBall(){
        double radius = 20;
        double x = radius + random.nextDouble() * (root.getWidth() - 2 * radius);
        double y = radius + random.nextDouble() * (root.getHeight() - 2 * radius);
        double dx = (random.nextDouble()*5);
        double dy = (random.nextDouble()*5);

        BouncingBall ball = new BouncingBall(x, y, dx, dy, radius, root);
        balls.add(ball);
        root.getChildren().add(ball.circle);
    }
    private void update() {
        for (BouncingBall ball : balls) {
            ball.move();
        }
    }
/*===========================================================================================*/
class BouncingBall{
        Circle circle;
        double dx;
        double dy;

        public BouncingBall(double x, double y, double dx, double dy, double radius, Pane pane) {
            this.circle = new Circle(x, y, radius, Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
            this.dx = dx;
            this.dy = dy;
        }


        public void move() {
            double x = circle.getCenterX();
            double y = circle.getCenterY();

            if (x + dx < circle.getRadius() || x + dx > root.getWidth() - circle.getRadius()) {
                dx = -dx;
            }
            if (y + dy < circle.getRadius() || y + dy > root.getHeight() - circle.getRadius()) {
                dy = -dy;
            }

            circle.setCenterX(x + dx);
            circle.setCenterY(y + dy);
        }
    }
}

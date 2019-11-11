package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.InternalWindow;

public class Main2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.getChildren().add(constructWindow());
        root.getChildren().add(constructWindow());
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public InternalWindow constructWindow() {
        // content
        ImageView imageView = new ImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Cheetah4.jpg/250px-Cheetah4.jpg");
        // title bar
        BorderPane titleBar = new BorderPane();
        titleBar.setStyle("-fx-background-color: green; -fx-padding: 3");
        Label label = new Label("header");
        titleBar.setLeft(label);
        Button closeButton = new Button("x");
        titleBar.setRight(closeButton);
        // title bat + content
        BorderPane windowPane = new BorderPane();
        windowPane.setStyle("-fx-border-width: 1; -fx-border-color: black");
        windowPane.setTop(titleBar);
        windowPane.setCenter(imageView);

        //apply layout to InternalWindow
        InternalWindow interalWindow = new InternalWindow();
        interalWindow.setRoot(windowPane);
        //drag only by title
//        interalWindow.makeDragable(titleBar);
//        interalWindow.makeDragable(label);
        interalWindow.makeResizable(20);
        interalWindow.makeFocusable();
        interalWindow.setCloseButton(closeButton);
        return interalWindow;
    }
}
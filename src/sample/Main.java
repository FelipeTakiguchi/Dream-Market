package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.control.Base;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws Exception {

        Pane root = loadMainPane();
        stage.setScene(new Scene(root, 1280, 720));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setResizable(false);
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Browser.BASE
                )
        );

        Base controller = loader.getController();

        Browser.setController(controller);
        Browser.loadWindows(Browser.LOGIN);

        return mainPane;
    }
}

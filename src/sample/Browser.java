package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import sample.control.Base;


import java.io.IOException;

public class Browser {

    public static final String BASE = "/sample/view/base.fxml";
    public static final String MAIN = "/sample/view/menu.fxml";
    public static final String LOGIN = "/sample/view/login.fxml";
    public static final String CADASTRAUSUARIO = "/sample/view/cadastro.fxml";

    private static Base controller;

    public static void setController(Base controller) {
            Browser.controller = controller;
    }
    public static void loadWindows(String fxml) {
        try { controller.setWindows((Node) FXMLLoader.load(Browser.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

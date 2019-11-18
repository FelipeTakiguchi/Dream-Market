package sample.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import sample.Browser;
import sample.InternalWindow;

import java.io.IOException;
import java.util.List;

public class Cadastro {

    @FXML
    AnchorPane anchoPane;

    @FXML
    private TextArea taAdm;

    @FXML
    private TextArea taNormal;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void explicaNormal(MouseEvent event) {
        taNormal.setVisible(Boolean.TRUE);
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        taNormal.setLayoutX(xOffset);
        taNormal.setLayoutY(yOffset);
    }

    public void desativaInfoNormal(MouseEvent mouseEvent) {
        taNormal.setVisible(Boolean.FALSE);
    }

    public void explicaAdministrador(MouseEvent event) {
        taAdm.setVisible(Boolean.TRUE);
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        taAdm.setLayoutX(xOffset);
        taAdm.setLayoutY(yOffset);
    }

    public void desativaInfoAdm(MouseEvent mouseEvent) {
        taAdm.setVisible(Boolean.FALSE);
    }

    public void criaNormal(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.getScene();
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Browser.BASE
                )
        );

        Base controller = loader.getController();

        Browser.setController(controller);
        Browser.loadWindows(Browser.CADASTRANORMAL);
        stage.setScene(new Scene(mainPane, 640, 360));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void criaAdministrador(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.getScene();
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Browser.BASE
                )
        );

        Base controller = loader.getController();

        Browser.setController(controller);
        Browser.loadWindows(Browser.CADASTRAADM);
        stage.setScene(new Scene(mainPane, 640, 360));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void handleJan1(Stage stage) throws IOException {

        System.out.println("Iniciando Janela Interna 1");

        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Browser.BASE
                )
        );

        Base controller = loader.getController();

        Browser.setController(controller);
        Browser.loadWindows(Browser.CADASTRAUSUARIO);

//        mainPane.getChildren().add();
        stage.setScene(new Scene(mainPane, 1280, 720));
        stage.show();
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.LOGIN);
    }
}

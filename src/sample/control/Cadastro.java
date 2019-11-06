package sample.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Cadastro {
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

    public void criaNormal(ActionEvent actionEvent) {
    }

    public void criaAdministrador(ActionEvent actionEvent) {
    }
}

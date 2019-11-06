package sample.control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import sample.Browser;

public class Login {
    @FXML
    void acaoFechar(){
        System.exit(0);
    }


    public void cadastrarUsuario(MouseEvent mouseEvent) {
        Browser.loadWindows(Browser.CADASTRAUSUARIO);
    }

}

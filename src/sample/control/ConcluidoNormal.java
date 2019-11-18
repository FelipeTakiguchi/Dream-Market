package sample.control;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.Browser;

public class ConcluidoNormal {

    @FXML
    private JFXButton btFechar;

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void logarConta(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }
}

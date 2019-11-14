package sample.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Browser;

public class ConcluidoNormal {

    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void logarConta(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }
}

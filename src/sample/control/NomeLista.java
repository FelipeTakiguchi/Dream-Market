package sample.control;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class NomeLista {
    @FXML
    private JFXTextField tfNome;

    public String retornaNome(){
        return tfNome.getText();
    }
}

package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Teste {


    @FXML
    private TextField tfTeste;


    public void acao(){
        System.out.println(tfTeste.getText());
    }

}

package sample.control;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Mensagem {
    protected void Aviso(Alert.AlertType a,String mensagem){
        Alert alert = new Alert(a,mensagem);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK){

        }
    }
}

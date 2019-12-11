package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.Controle;

import java.io.IOException;
import java.sql.SQLException;

public class ContratoNormal {
    @FXML
    private JFXCheckBox cbAcordo;
    @FXML
    private JFXButton btFechar;

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.CADASTRANORMAL);
    }

    public void proximo() throws Exception {
        if(cbAcordo.isSelected()){
            if(Controle.getUsuario() == null) {
                Controle.getInstance().addUsuario(Controle.getUsuario());
            }
            else{
                Controle.getInstance().mudaUsuarioNormal(Controle.getUsuario());
            }
        }else{
            throw new Exception("Aceite os termos de uso para prosseguir");
        }
    }
}

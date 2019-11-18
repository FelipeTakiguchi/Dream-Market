package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.Controle;

import java.sql.SQLException;

public class ContratoAdm {
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
        Browser.loadWindows(Browser.CADASTRAUSUARIO);
    }

    public void proximo(ActionEvent event) throws SQLException {
        if(cbAcordo.isSelected()){
            Controle.getInstance().addUsuarioAdm(Controle.getUsuarioAdm());
            Browser.loadWindows(Browser.CONCLUIDOADM);
        }
    }
}

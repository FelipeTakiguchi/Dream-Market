package sample.control;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Browser;
import sample.model.Controle;

import java.sql.SQLException;

public class ContratoAdm {
    @FXML
    private JFXCheckBox cbAcordo;

    @FXML
    void acaoFechar(){
        System.exit(0);
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

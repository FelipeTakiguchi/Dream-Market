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
import javafx.stage.Window;
import sample.Browser;
import sample.model.Controle;

import java.io.IOException;
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
        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Browser.CADASTRAADM));

        try{
            Pane conteudo = loader.load();

            dialog.getDialogPane().setContent(conteudo);

            dialog.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void proximo(ActionEvent event) throws SQLException {
        if(cbAcordo.isSelected()){
            Controle.getInstance().addUsuarioAdm(Controle.getUsuarioAdm());
            System.out.println(Controle.getUsuarioAdm());
            Dialog<ButtonType> dialog = new Dialog<>();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Browser.CONCLUIDOADM));

            try{
                Pane conteudo = loader.load();

                dialog.getDialogPane().setContent(conteudo);

                dialog.showAndWait();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

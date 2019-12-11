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
        Cadastro.setVoltar(1);
        System.out.println(Cadastro.getVoltar());
        acaoFechar();
    }

    public void proximo() throws Exception {
        if(cbAcordo.isSelected()){
            if(Controle.getUsuario() == null) {
                if(Controle.getInstance().verificaUsuarioNome(Controle.getUsuario().getNome(), Controle.getUsuario().getSenha()) == null && Controle.getInstance().verificaUsuarioEmail(Controle.getUsuario().getEmail(), Controle.getUsuario().getSenha()) == null) {
                    Controle.getInstance().addUsuario(Controle.getUsuario());
                }

                Controle.getInstance().addUsuarioAdm(Controle.getUsuarioAdm());
            }
            else{
                if(Controle.getInstance().verificaUsuarioNome(Controle.getUsuario().getNome(), Controle.getUsuario().getSenha()) == null && Controle.getInstance().verificaUsuarioEmail(Controle.getUsuario().getEmail(), Controle.getUsuario().getSenha()) == null) {
                    Controle.getInstance().mudaUsuarioNormal(Controle.getUsuario());
                }

                Controle.getInstance().mudaUsuario(Controle.getUsuarioAdm());
            }
        }else{
            throw new Exception("Aceite os termos de uso para prosseguir");
        }
    }
}

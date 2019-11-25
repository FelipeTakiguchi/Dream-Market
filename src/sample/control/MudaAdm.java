package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.Controle;
import sample.model.Usuario;
import sample.model.UsuarioAdm;

import java.io.IOException;
import java.sql.SQLException;

public class MudaAdm {
    @FXML
    private JFXButton btFechar;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfCpf;

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void proximo(ActionEvent event) throws SQLException {
        String telefone = tfTelefone.getText();
        String cpf = tfCpf.getText();

        Usuario usuario = Controle.getUsuario();
        UsuarioAdm usuarioAdm = new UsuarioAdm(usuario.getNome(), usuario.getEmail(), usuario.getSenha(),usuario.getCidade(), telefone, cpf);
        Controle.setUsuarioAdm(usuarioAdm);
        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Browser.EXIBECONDICOESADM));

        try{
            Pane conteudo = loader.load();

            dialog.getDialogPane().setContent(conteudo);

            dialog.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

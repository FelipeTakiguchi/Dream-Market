package sample.control;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import sample.Browser;
import sample.model.Controle;
import sample.modelDAO.UsuarioDAOImpl;

import java.sql.SQLException;

public class Login extends Mensagem{
    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXPasswordField pfSenha;


    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void cadastrarUsuario(MouseEvent mouseEvent) {
        Browser.loadWindows(Browser.CADASTRAUSUARIO);
    }

    public void logarUsuario(ActionEvent actionEvent) throws SQLException {
        if(tfEmail.getText().length() > 3 && pfSenha.getText().length() > 3){
            boolean verificacao = Controle.getInstance().verificaUsuario(tfEmail.getText(), pfSenha.getText());

            if(verificacao){
                Browser.loadWindows(Browser.MENU);
            }
            else{
                Aviso(Alert.AlertType.WARNING, "Email ou Senha Incorreto(s)!");
            }
        }
    }
}

package sample.control;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Browser;
import sample.model.Controle;
import sample.model.Usuario;

import java.sql.SQLException;
import java.util.Objects;

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

    public void logarUsuario() throws SQLException {
        if(tfEmail.getText().length() > 3 && pfSenha.getText().length() > 3){
            Usuario verificacao = Controle.getInstance().verificaUsuarioEmail(tfEmail.getText(), pfSenha.getText());
            Usuario verificacao2 = Controle.getInstance().verificaUsuarioNome(tfEmail.getText(), pfSenha.getText());

            if(verificacao != null || verificacao2 != null){

                //chama Task Logar

                //mostra uma barra de progresso
                Controle.setUsuario(Objects.requireNonNullElse(verificacao, verificacao2));

                if(verificacao != null){
                    Controle.setUsuario(verificacao);
                }
                else{
                    Controle.setUsuario(verificacao2);
                }

                Browser.loadWindows(Browser.MENU);
            }
            else{
                Aviso(Alert.AlertType.WARNING, "Email/Nome ou Senha Incorreto(s)!");
            }
        }
        else{
            Aviso(Alert.AlertType.WARNING, "Dados inválidos!");
        }
    }

    public void verificaTecla(KeyEvent keyEvent) throws SQLException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            logarUsuario();
        }
    }
}

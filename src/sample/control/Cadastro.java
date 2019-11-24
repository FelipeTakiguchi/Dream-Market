package sample.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.Browser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Cadastro {

    @FXML
    private TextArea taAdm;

    @FXML
    private TextArea taNormal;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void explicaNormal(MouseEvent event) {
        taNormal.setVisible(Boolean.TRUE);
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        taNormal.setLayoutX(xOffset);
        taNormal.setLayoutY(yOffset);
    }

    public void desativaInfoNormal(MouseEvent mouseEvent) {
        taNormal.setVisible(Boolean.FALSE);
    }

    public void explicaAdministrador(MouseEvent event) {
        taAdm.setVisible(Boolean.TRUE);
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        taAdm.setLayoutX(xOffset);
        taAdm.setLayoutY(yOffset);
    }

    public void desativaInfoAdm(MouseEvent mouseEvent) {
        taAdm.setVisible(Boolean.FALSE);
    }

    public void criaNormal(ActionEvent event) throws IOException {
            Dialog<ButtonType> dialog = new Dialog<>();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Browser.CADASTRANORMAL));
            try{
                Pane conteudo = loader.load();

                dialog.getDialogPane().setContent(conteudo);

//                dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//
//                Optional<ButtonType> res = dialog.showAndWait();
//
//                if(res.isPresent() && res.get() == ButtonType.OK){
//                    CadastraNormal controler = loader.getController();
//
//                    controler.acao();
//
//                }

                dialog.showAndWait();
                dialog.setResizable(false);
            }catch (IOException e){
                e.printStackTrace();
            }


    }

    public void criaAdministrador(ActionEvent event) throws IOException {
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
}

package sample.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.Browser;
import sample.model.Controle;

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

    @FXML
    void voltar(){
        Browser.loadWindows(Browser.LOGIN);
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
        loader.setLocation(getClass().getResource(Browser.CADASTRAADM));
        try{
            Pane conteudo = loader.load();

            dialog.getDialogPane().setContent(conteudo);

            dialog.showAndWait();
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
            ButtonType proximoButtonType = new ButtonType("Próximo", ButtonBar.ButtonData.NEXT_FORWARD);
            dialog.getDialogPane().getButtonTypes().add(proximoButtonType);
            boolean flag = false;
            while(!flag) {
                Optional<ButtonType> res = dialog.showAndWait();

                if (res.isPresent() && res.get() == proximoButtonType) {
                    CadastraAdm controle = loader.getController();
                    try {
                        controle.proximoPasso();

                        flag = true;

                        FXMLLoader loader2 = new FXMLLoader();
                        loader2.setLocation(getClass().getResource(Browser.EXIBECONDICOESADM));

                        Pane conteudo2 = loader2.load();

                        dialog.getDialogPane().getButtonTypes().clear();
                        dialog.getDialogPane().setContent(conteudo2);
                        ButtonType proximoButtonType2 = new ButtonType("Próximo", ButtonBar.ButtonData.NEXT_FORWARD);
                        dialog.getDialogPane().getButtonTypes().add(proximoButtonType2);
                        boolean flag2 = false;

                        while(!flag2) {
                            Optional<ButtonType> res2 = dialog.showAndWait();

                            if (res2.isPresent() && res2.get() == proximoButtonType) {
                                ContratoAdm controlador2 = loader.getController();
                               try {
                                   controlador2.proximo();
                                   flag2 = true;
                                   FXMLLoader loader3 = new FXMLLoader();
                                   loader3.setLocation(getClass().getResource(Browser.CONCLUIDOADM));

                                   Pane conteudo3 = loader3.load();

                                   dialog.getDialogPane().getButtonTypes().clear();
                                   dialog.getDialogPane().setContent(conteudo3);
                                   dialog.showAndWait();
                               }catch (Exception e){
                                   flag2 = false;
                               }
                            }
                        }
                    } catch (Exception e) {
                        flag = false;
                    }

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

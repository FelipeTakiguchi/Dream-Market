package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import sample.Browser;
import sample.model.Controle;

import java.io.IOException;
import java.sql.SQLException;

public class Menu {

    @FXML
    private JFXButton btUm;
    @FXML
    private JFXButton btDois;
    @FXML
    private JFXButton btTres;
    @FXML
    private JFXButton btQuatro;
    @FXML
    private JFXButton btCinco;
    @FXML
    private JFXButton btSeis;
    @FXML
    private TextArea taDescricao;
    @FXML
    private JFXToggleButton tgModo;
    @FXML
    private JFXDialog jfxDialog;

    private Image image1 = new Image("/sample/icons/pesquisa.jpeg");
    private Image image2 = new Image("/sample/icons/analise.jpeg");
    private Image image3 = new Image("/sample/icons/lista.jpeg");
    private Image image4 = new Image("/sample/icons/pdf.jpeg");
    private Image image5 = new Image("/sample/icons/administrador.jpeg");
    private Image image6 = new Image("/sample/icons/logout.jpeg");
    private Image bigImage1=new Image("/sample/icons/pesquisa2.jpeg");
    private Image bigImage2=new Image("/sample/icons/analise2.jpeg");
    private Image bigImage3=new Image("/sample/icons/lista2.jpeg");
    private Image bigImage4=new Image("/sample/icons/pdf2.jpeg");
    private Image bigImage5=new Image("/sample/icons/administrador2.jpeg");
    private Image bigImage6=new Image("/sample/icons/logout2.jpeg");

    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    private static int nivel = 1;

    public void initialize() throws SQLException {

        if(Controle.getInstance().isAdm(Controle.getUsuario().getNome(), Controle.getUsuario().getSenha())){
            tgModo.setVisible(true);
        }
        else{
            tgModo.setVisible(false);
        }

        if(nivel == 1){
            setPosUm();
        }
        else if(nivel == 2){
            setPosDois();
        }
        else if(nivel == 3){
            setPosTres();
        }
        else if(nivel == 4) {
            setPosQuatro();
        }
        else if(nivel == 5){
            setPosCinco();
        }
        else if(nivel == 6){
            setPosSeis();
        }
    }

    public void mudaOpcao(ScrollEvent scrollEvent) {
        if(scrollEvent.getDeltaY() < 0){
            nivel--;

            if(nivel == 0){
                nivel = 6;
            }
        }
        else
        {
            nivel++;

            if(nivel == 7){
                nivel = 1;
            }
        }

        alteraPosicao(nivel);
    }

    private void resetaSize(){
        btUm.setPrefSize(80, 80);
        btDois.setPrefSize(80, 80);
        btTres.setPrefSize(80, 80);
        btQuatro.setPrefSize(80, 80);
        btCinco.setPrefSize(80, 80);
        btSeis.setPrefSize(80, 80);
    }

    private void resetaImagem() {
        btUm.setGraphic(new ImageView(image1));
        btDois.setGraphic(new ImageView(image2));
        btTres.setGraphic(new ImageView(image3));
        btQuatro.setGraphic(new ImageView(image4));
        btCinco.setGraphic(new ImageView(image5));
        btSeis.setGraphic(new ImageView(image6));
    }

    private void setPosUm(){
        taDescricao.setText("\nPesquisa: Permite que sejam\n" +
                "encontrados com facilidade \n" +
                "todos os produtos desejados.\n" +
                "\n" +
                "Dica: Utilize Filtros!\n");
        btUm.setPrefSize(120, 120);
        btUm.setGraphic(new ImageView(bigImage1));
        btUm.setLayoutX(690);
        btUm.setLayoutY(130);
        btDois.setLayoutX(800);
        btDois.setLayoutY(340);
        btTres.setLayoutX(710);
        btTres.setLayoutY(505);
        btQuatro.setLayoutX(510);
        btQuatro.setLayoutY(505);
        btCinco.setLayoutX(390);
        btCinco.setLayoutY(340);
        btSeis.setLayoutX(510);
        btSeis.setLayoutY(150);
    }

    private void setPosDois() {
        taDescricao.setText("\n" +
                "\n" +
                "Análise de produtos:\n" +
                "Permite a comparação entre\n" +
                "dois produtos distintos,\n" +
                "exibindo os dados de cada um\n");
        btDois.setPrefSize(120, 120);
        btDois.setGraphic(new ImageView(bigImage2));
        btUm.setLayoutX(510);
        btUm.setLayoutY(150);
        btDois.setLayoutX(690);
        btDois.setLayoutY(130);
        btTres.setLayoutX(800);
        btTres.setLayoutY(340);
        btQuatro.setLayoutX(710);
        btQuatro.setLayoutY(505);
        btCinco.setLayoutX(510);
        btCinco.setLayoutY(505);
        btSeis.setLayoutX(390);
        btSeis.setLayoutY(340);
    }

    private void setPosTres() {
        taDescricao.setText("\n" +
                "\n" +
                "Listagem: Utilize esta opção\n" +
                "para criar suas próprias listas\n" +
                "que poderão auxiliar a encon-\n" +
                "trar seus produtos na hora da \n" +
                "compra!\n  ");
        btTres.setPrefSize(120, 120);
        btTres.setGraphic(new ImageView(bigImage3));
        btUm.setLayoutX(390);
        btUm.setLayoutY(340);
        btDois.setLayoutX(510);
        btDois.setLayoutY(150);
        btTres.setLayoutX(690);
        btTres.setLayoutY(130);
        btQuatro.setLayoutX(800);
        btQuatro.setLayoutY(340);
        btCinco.setLayoutX(710);
        btCinco.setLayoutY(505);
        btSeis.setLayoutX(510);
        btSeis.setLayoutY(505);
    }

    private void setPosQuatro(){
        taDescricao.setText("\n" +
                "\n" +
                "\n" +
                "Gerador de Arquivo:\n" +
                "Escolha uma lista, e gere seu\n" +
                "próprio arquivo!\n");
        btQuatro.setPrefSize(120, 120);
        btQuatro.setGraphic(new ImageView(bigImage4));
        btUm.setLayoutX(510);
        btUm.setLayoutY(505);
        btDois.setLayoutX(390);
        btDois.setLayoutY(340);
        btTres.setLayoutX(510);
        btTres.setLayoutY(150);
        btQuatro.setLayoutX(690);
        btQuatro.setLayoutY(130);
        btCinco.setLayoutX(800);
        btCinco.setLayoutY(340);
        btSeis.setLayoutX(710);
        btSeis.setLayoutY(505);
    }

    private void setPosCinco(){
        taDescricao.setText("\n" +
                "\n" +
                "Upgrade: Aprimore sua conta!\n" +
                "Torne-se um moderador e \n" +
                "ajude os outros usuários inse-\n" +
                "rindo novos registros!");
        btCinco.setPrefSize(120, 120);
        btCinco.setGraphic(new ImageView(bigImage5));
        btUm.setLayoutX(710);
        btUm.setLayoutY(505);
        btDois.setLayoutX(510);
        btDois.setLayoutY(505);
        btTres.setLayoutX(390);
        btTres.setLayoutY(340);
        btQuatro.setLayoutX(510);
        btQuatro.setLayoutY(150);
        btCinco.setLayoutX(690);
        btCinco.setLayoutY(130);
        btSeis.setLayoutX(800);
        btSeis.setLayoutY(340);
    }

    private void setPosSeis(){
        taDescricao.setText("\n" +
                "Log Out: Não deixe sua conta\n" +
                "logada sem necessidades!\n" +
                "Efetue o Log Out sempre \n" +
                "que desejar sair ou entrar\n" +
                "em outra conta\n" +
                "\n");
        btSeis.setPrefSize(120, 120);
        btSeis.setGraphic(new ImageView(bigImage6));
        btUm.setLayoutX(800);
        btUm.setLayoutY(340);
        btDois.setLayoutX(710);
        btDois.setLayoutY(505);
        btTres.setLayoutX(510);
        btTres.setLayoutY(505);
        btQuatro.setLayoutX(390);
        btQuatro.setLayoutY(340);
        btCinco.setLayoutX(510);
        btCinco.setLayoutY(150);
        btSeis.setLayoutX(690);
        btSeis.setLayoutY(130);
    }

    private void alteraPosicao(int nv){
        resetaSize();
        resetaImagem();

        switch (nv){
            case 1:
                setPosUm();
                break;
            case 2:
                setPosDois();
                break;
            case 3:
                setPosTres();
                break;
            case 4:
                setPosQuatro();
                break;
            case 5:
                setPosCinco();
                break;
            case 6:
                setPosSeis();
                break;
        }
    }

    public void opcaoPesquisa(ActionEvent event) {
        Browser.loadWindows(Browser.PESQUISA);
    }

    public void opcaoAnalise(ActionEvent event) {
        Browser.loadWindows(Browser.COMPARAITENS);
    }

    public void opcaoPdf(ActionEvent event) {
        Browser.loadWindows(Browser.GERARLISTA);
    }

    public void opcaoLista(ActionEvent event) {
        Browser.loadWindows(Browser.CRIALISTA);
    }

    public void opcaoLogoff(ActionEvent event) {
        Browser.loadWindows(Browser.LOGIN);
    }

    public void opcaoAdm() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Browser.MUDAADM));

        try{
            Pane conteudo = loader.load();

            dialog.getDialogPane().setContent(conteudo);

            dialog.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void alteraModo(ActionEvent event) throws SQLException{
        if(Controle.getInstance().isAdm(Controle.getUsuario().getNome(), Controle.getUsuario().getSenha())){
            Browser.loadWindows(Browser.MENUADM);
        }
    }
}

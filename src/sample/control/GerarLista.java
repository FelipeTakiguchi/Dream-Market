package sample.control;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GerarLista {
    @FXML
    private JFXButton btFechar;

    @FXML
    private ListView<Lista> ltvListas;

    @FXML
    private JFXButton btImprime;

    @FXML
    private Label lbFrom;

    @FXML
    private Label lbTo;

    private ObservableList<Lista> listagem = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {
        lbFrom.setText("From: Naoto");
        lbTo.setText("To: "+Controle.getUsuario().getNome());
        Task<Void> carrega = carregaDados();
        Thread backgroundThread = new Thread(carrega);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
        btImprime.setDisable(true);

        ltvListas.setItems(listagem);
    }

    private Document abreDocumento(String arq) throws IOException{
        PdfWriter writer = new PdfWriter(arq);
        PdfDocument pdf  = new PdfDocument(writer);
        Document document = new Document(pdf);

        return  document;
    }

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    private Task<Void> carregaDados(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<Lista> listas = Controle.getInstance().carregaListas();
                Platform.runLater(()->{
                    listagem.addAll(listas);
                });
                return null;
            }
        };
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

    public void gerarLista(ActionEvent event) {
        try{
            String arq = null;
            FileChooser fc = new FileChooser();
            File f = fc.showSaveDialog(null);


            if(f != null) {
                arq = f.getAbsolutePath();
            }

            Lista lista = ltvListas.getSelectionModel().getSelectedItem();

            Document document = abreDocumento(arq);

            Paragraph paragrafo = new Paragraph(lista.getNome());
            paragrafo.setTextAlignment(TextAlignment.CENTER);

            paragrafo.setBold();

            document.add(paragrafo);

            Table table = new Table(UnitValue.createPercentArray(new float[]{15, 10, 10, 15, 20, 25}))
                    .useAllAvailableWidth();

            String[] cabecalho = {"Produto", "Marca", "Preço", "Quantidade","Comércio", "Última verificação"};

            for(String s:cabecalho){
                Cell cell = new Cell();
                cell.add(new Paragraph(s));
                cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                cell.setBorder(new SolidBorder(ColorConstants.BLACK,2));
                table.addHeaderCell(cell);

            }

            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            table.setFont(font);
            table.setFontSize(12);

            for(ListaItem item:lista.getListaItems()){
                table.addCell(item.getItem().getProdutoNome());
                table.addCell(item.getItem().getProdutoMarca());
                table.addCell(""+item.getItem().getPreco());
                table.addCell(""+item.getQuantidade());
                table.addCell(item.getItem().getComercioNome());
                table.addCell(""+item.getItem().getDataAtualizacao());
            }

            document.add(table);
            document.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void abilitaBotao(MouseEvent mouseEvent) {
        btImprime.setDisable(false);
    }
}

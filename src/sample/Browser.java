package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import sample.control.Base;


import java.io.IOException;

public class Browser {

    public static final String BASE = "/sample/view/base.fxml";
    public static final String MENU = "/sample/view/menu.fxml";
    public static final String LOGIN = "/sample/view/login.fxml";
    public static final String CADASTRAUSUARIO = "/sample/view/cadastro.fxml";
    public static final String CADASTRANORMAL = "/sample/view/cadastraNormal.fxml";
    public static final String CADASTRAADM= "/sample/view/cadastraAdm.fxml";
    public static final String EXIBECONDICOESNORMAL= "/sample/view/contratoNormal.fxml";
    public static final String EXIBECONDICOESADM= "/sample/view/contratoAdm.fxml";
    public static final String CONCLUINORMAL= "/sample/view/concluidoNormal.fxml";
    public static final String CONCLUIDOADM= "/sample/view/concluidoAdm.fxml";
    public static final String CADASTRACIDADE= "/sample/view/adcionaCidade.fxml";
    public static final String PESQUISA= "/sample/view/pesquisaItens.fxml";
    public static final String COMPARAITENS= "/sample/view/comparaItens.fxml";
    public static final String CRIALISTA= "/sample/view/criaLista.fxml";
    public static final String GERARLISTA= "/sample/view/gerarLista.fxml";
    public static final String MUDAADM = "/sample/view/mudaAdm.fxml";
    public static final String MENUADM = "/sample/view/menuAdm.fxml";
    public static final String NOMEARQUIVO = "/sample/view/nomeLista.fxml";

    private static Base controller;

    public static void setController(Base controller) {
            Browser.controller = controller;
    }
    public static void loadWindows(String fxml) {
        try { controller.setWindows((Node) FXMLLoader.load(Browser.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

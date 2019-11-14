package sample.control;

import javafx.fxml.FXML;
import javafx.scene.input.ScrollEvent;

public class Menu {
    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void mudaOpcao(ScrollEvent scrollEvent) {
        System.out.println(scrollEvent.getDeltaY());
        if(scrollEvent.getDeltaY() < 0){
            System.out.println("Pra baixo");
        }
        else
        {
            System.out.println("Pra cima");
        }
    }
}

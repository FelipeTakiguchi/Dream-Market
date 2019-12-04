package sample;

import sample.modelDAO.*;

public class MainTestes {

    public static void main(String[] args) {
        try{

            ItemDAO itemDAO = new ItemDAOImpl();
            UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
            ItemDAO itemDao = new ItemDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            CidadeDAO cidadeDao = new CidadeDAOImpl();

            System.out.println(produtoDAO.listaAdm(366));


        }catch (Exception e){
            e.printStackTrace();
        }



    }
}

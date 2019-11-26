package sample;

import sample.modelDAO.*;

public class MainTestes {

    public static void main(String[] args) {
        try{

            ItemDAO itemDAO = new ItemDAOImpl();
            UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
            CidadeDAO cidadeDao = new CidadeDAOImpl();

            System.out.println(cidadeDao.lista());


        }catch (Exception e){
            e.printStackTrace();
        }



    }
}

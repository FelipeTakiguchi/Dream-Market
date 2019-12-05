package sample;

import sample.model.Cidade;
import sample.model.Controle;
import sample.model.UsuarioAdm;
import sample.modelDAO.*;

public class MainTestes {

    public static void main(String[] args) {
        try{

            ItemDAO itemDAO = new ItemDAOImpl();
            UsuarioAdmDAO usuarioAdmDAO = new UsuarioAdmDAOImpl();
            ItemDAO itemDao = new ItemDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            CidadeDAO cidadeDao = new CidadeDAOImpl();
            Cidade cidade = cidadeDao.buscaId(2);

            UsuarioAdm usuarioAdm = new UsuarioAdm("teste", "teste", "teste", cidade, "43423432", "324234332");
            Controle.setUsuarioAdm(usuarioAdm);
            Controle.getInstance().addUsuarioAdm(Controle.getUsuarioAdm());

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}

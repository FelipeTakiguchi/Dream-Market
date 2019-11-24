package sample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static int MAX_CONNECTIONS=5;
    private static FabricaConexao instance = new FabricaConexao();

    private static String ENDERECO_SERVIDOR = "";
    private static String NOME_BANCO = "";

    private static String USER="";
    private static String PASSWORD="";

    private static String STRING_CONEXAO ="jdbc:mysql://"+ENDERECO_SERVIDOR+"/"+NOME_BANCO; //porta 31 32;

    private static Connection[] connections;

    private FabricaConexao(){
        connections = new Connection[MAX_CONNECTIONS];
    }

    public static Connection getConnection() throws SQLException{
        for (int i=0; i<MAX_CONNECTIONS; i++){
            if (connections[i] == null || connections[i].isClosed()){
                connections[i] = DriverManager.getConnection(STRING_CONEXAO,USER,PASSWORD);

                return connections[i];
            }
        }

        throw new SQLException("Muitas conexoes abertas");
    }
}

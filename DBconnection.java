package LABBD.crudviagem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	protected static final String JDBC_URL = 
    "jdbc:sqlserver://DESKTOP-3D7FB7H;Database=biblioteca;encrypt=true;TrustServerCertificate=true;IntegratedSecurity=true";
	protected static final String JDBC_USER = "";
	protected static final String JDBC_PASS = "";
	protected Connection con;
	
	public DBconnection(){ 
        try { 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver carregado");
            con = DriverManager.getConnection(JDBC_URL);
            System.out.println("Conectado ao banco de dados com sucesso");
        } catch (ClassNotFoundException e) { 
            System.out.println("Classe do database não encontrada");
            e.printStackTrace();
        } catch (SQLException e) { 
            System.out.println("Erro de conexão ao banco de dados");
            e.printStackTrace();
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBancoDeDados {
    //Método que conecta com o DataBase
    public static Connection conector(){
        Connection conn = null;
        //A baixo ocorre a chamada do Driver do banco
        String driver = "com.mysql.cj.jdbc.Driver";
        //as outras informações necessárias para conexão com o database
        String url = "jdbc:mysql://localhost:3306/bancoiigd?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String senha = "Renancoletop217@";  
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, senha);
            return conn;
        } catch (Exception e) {
            //se der erro ao conectar ao banco exibirar o erro na variável "e"
            //System.out.println(e);
            return null;
        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogame;

import conexoes.ConexaoSQLite;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class CriarBancoSQLite {
    private final ConexaoSQLite conexaoSQLite;
    
    public CriarBancoSQLite(ConexaoSQLite conexaoSQLite){
        this.conexaoSQLite = conexaoSQLite;
    }
    
    public void criarTabelaTimes(){
        String query = "CREATE TABLE IF NOT EXISTS times"
                + "("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"nome TEXT NOT NULL,"
                + "corFundo TEXT NOT NULL,"
                + "corTexto TEXT NOT NULL"
                + ")";
       
        boolean conectou = false;
        
        try {
           conectou = this.conexaoSQLite.conectar();
           Statement stmt = this.conexaoSQLite.criarStatement();
           stmt.execute(query);
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (conectou) {
                this.conexaoSQLite.desconectar();
            }
        }
        
    }
    
}

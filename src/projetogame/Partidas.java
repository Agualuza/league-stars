/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogame;

import conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class Partidas {
    private int idTime1;
    private int idTime2;
    private int idVencedor;
    private int status;
    
    
    public void selectPartidas(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet,int n , int m) {
        String query = "SELECT * FROM times WHERE id = "+ n + " OR id ="+ m ;
       
        int i = 1;
        try {
          resultSet = statement.executeQuery(query);  
          System.out.println("\nPartida!");
            
          while(resultSet.next()){
           if (i==1){
           System.out.print(resultSet.getString("nome"));
           System.out.print(" x ");
           } else {
           System.out.print(resultSet.getString("nome"));
               System.out.println("");
           }
           i++;
          }
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
    
    }
    
    public void createPartidas(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet,int n , int m) {
        Partidas partida = new Partidas();
        partida.setIdTime1(n);
        partida.setIdTime2(m);
        partida.setIdVencedor(0);
        partida.setStatus(0);
        
        String query = "INSERT INTO partidas ("
                + "idTime1,"
                + "idTime2,"
                + "idVencedor,"
                + "status"
                + ")"
                + "VALUES(?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
            preparedStatement.setInt(1,partida.getIdTime1());
            preparedStatement.setInt(2,partida.getIdTime2());
            preparedStatement.setInt(3,partida.getIdVencedor());
            preparedStatement.setInt(4,partida.getStatus());
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
    }
    /**
     * @return the idTime1
     */
    public int getIdTime1() {
        return idTime1;
    }

    /**
     * @param idTime1 the idTime1 to set
     */
    public void setIdTime1(int idTime1) {
        this.idTime1 = idTime1;
    }

    /**
     * @return the idTime2
     */
    public int getIdTime2() {
        return idTime2;
    }

    /**
     * @param idTime2 the idTime2 to set
     */
    public void setIdTime2(int idTime2) {
        this.idTime2 = idTime2;
    }

    /**
     * @return the idVencedor
     */
    public int getIdVencedor() {
        return idVencedor;
    }

    /**
     * @param idVencedor the idVencedor to set
     */
    public void setIdVencedor(int idVencedor) {
        this.idVencedor = idVencedor;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
}

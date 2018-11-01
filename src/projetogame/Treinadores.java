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
public class Treinadores {
   private int id;
   private String nome;
   private int idTime;
   
   
   public void createTreinador(ConexaoSQLite conexaoSQLite,ResultSet resultSet, Treinadores treinador) {
       
        
        String query = "INSERT INTO treinadores ("
                + "nome,"
                + "idTime"
                + ")"
                + "VALUES(?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        
        try {
            preparedStatement.setString(1,treinador.getNome());
            preparedStatement.setInt(2,treinador.getIdTime());
            
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
    }
   
   public int selectTreinadorId(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int idTime){
        String query = "SELECT * FROM treinadores WHERE idTime = "+idTime+" ORDER BY id DESC LIMIT 1";
        int idTreinador = 0;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           idTreinador = resultSet.getInt("id"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
      
        
         return idTreinador;
        
    }
   
   public String selectTreinadorNome(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int idTreinador){
        String query = "SELECT * FROM treinadores WHERE id = "+idTreinador;
        String treinador = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           treinador = resultSet.getString("nome"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
      
        
         return treinador;
        
    } 
   
   public int selectTreinadorIdTime(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int idTreinador){
        String query = "SELECT * FROM treinadores WHERE id = "+idTreinador;
        int idTime = 0;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           idTime = resultSet.getInt("idTime"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
      
        
         return idTime;
        
    } 


       


    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the idTime
     */
    public int getIdTime() {
        return idTime;
    }

    /**
     * @param idTime the idTime to set
     */
    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

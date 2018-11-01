/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogame;

import conexoes.ConexaoSQLite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import views.EscolherTime;

/**
 *
 * @author user
 */
public class Times {
   private int id;
   private String nome;
   private String corFundo;
   private String corTexto;

   
   public String selectTimesNome(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int idTime){
        String query = "SELECT * FROM times WHERE id = "+idTime;
        String time = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           time = resultSet.getString("nome"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
        
         return time;
        
    }
   
    public String selectTimesCorFundo(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int idTime){
        String query = "SELECT * FROM times WHERE id = "+idTime;
        String time = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           time = resultSet.getString("corFundo"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
      
        
         return time;
        
    } 
    
     public String selectTimesCorTexto(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int idTime){
        String query = "SELECT * FROM times WHERE id = "+idTime;
        String time = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           time = resultSet.getString("corTexto"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }  
      
        
         return time;
        
    } 

   
   public void selectAdversario(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int n) {
        String query = "SELECT * FROM times WHERE id !="
                + n;
       
        
       
        try {
          resultSet = statement.executeQuery(query);  
          System.out.println("\nEscolha o Adversário!");
          

         
          while (resultSet.next()) {
                System.out.print(resultSet.getInt("id")+"-");
                System.out.println(resultSet.getString("nome"));
            }
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }  
        
   }
   
   public void selectTimes(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, EscolherTime janelaTime){
       String query = "SELECT * FROM times;";
       
        
        
        int i = 1;
       
        Map<String, Times> times = new HashMap<>();
        
        try {
          resultSet = statement.executeQuery(query);  
          System.out.println("Escolha seu time!");
          

         
          while (resultSet.next()) {
               System.out.print(resultSet.getInt("id")+"-");
                System.out.println(resultSet.getString("nome"));
                Times time = new Times();
                String string = "times"+i;
                time.setId(resultSet.getInt("id"));
                time.setNome(resultSet.getString("nome"));
                time.setCorFundo(resultSet.getString("corFundo"));
                time.setCorTexto(resultSet.getString("corTexto"));
                times.put(string,time);
                i++;
          }
          
            janelaTime.addLista(times);
         
            
            
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
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
     * @return the corFundo
     */
    public String getCorFundo() {
        return corFundo;
    }

    /**
     * @param corFundo the corFundo to set
     */
    public void setCorFundo(String corFundo) {
        this.corFundo = corFundo;
    }

    /**
     * @return the corTexto
     */
    public String getCorTexto() {
        return corTexto;
    }

    /**
     * @param corTexto the corTexto to set
     */
    public void setCorTexto(String corTexto) {
        this.corTexto = corTexto;
    }
}

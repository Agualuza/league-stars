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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import views.EscolherTime;
import java.util.List;

/**
 *
 * @author user
 */
public class Times {
   private int id;
   private String nome;
   private String corFundo;
   private String corTexto;
   private int pontuacao;
   private int flag;
   

   
    public List<Times> selectTimesPontuacao(int idTreinador){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
        String query = "SELECT * FROM pontuacao where idTreinador = "+idTreinador;
        List<Times> times = new ArrayList<>();
        
        try {
          resultSet = statement.executeQuery(query); 
          
          while(resultSet.next()){
           Times time = new Times();
           time.setId(resultSet.getInt("idTime"));
           time.setPontuacao(resultSet.getInt("pontos"));
           times.add(time);
          }
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } 
        return times;
    }
    
   
   public String selectTimesNome(int idTime){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement();  
       
       String query = "SELECT * FROM times WHERE id = "+idTime;
        String time = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           time = resultSet.getString("nome"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
         return time;
        
    }
   
    public String selectTimesCorFundo(int idTime){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
        String query = "SELECT * FROM times WHERE id = "+idTime;
        String time = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           time = resultSet.getString("corFundo"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
         return time;
        
    } 
    
     public String selectTimesCorTexto(int idTime){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
         
        String query = "SELECT * FROM times WHERE id = "+idTime;
        String time = null;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           time = resultSet.getString("corTexto"); 
           
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
         return time;
        
    } 

   
   public void selectAdversario(int n) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
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
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
   }
   
   public void selectTimes(EscolherTime janelaTime){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
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
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
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

    /**
     * @return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}

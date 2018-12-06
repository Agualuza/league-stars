/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogame;

import conexoes.ConexaoSQLite;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author user
 */
public class Partidas{
    private int id;
    private int idTime1;
    private int idTime2;
    private int idTreinador;
    private int idVencedor;
    private int status;
    private int jogo;
    private int rodada;
    private int flag;
    private int firstBlood;
    
    public void createFinais(int idTreinador, int idTime1, int idTime2, int rodada){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        
        String query = "INSERT INTO finais ("
                + "idTreinador,"
                + "idTime1,"
                + "idTime2,"
                + "rodada,"
                + "idVencedor"
                + ")"
                + "VALUES(?,?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
            preparedStatement.setInt(1,idTreinador);
            preparedStatement.setInt(2,idTime1);
            preparedStatement.setInt(3,idTime2);
            preparedStatement.setInt(4,rodada);
            preparedStatement.setInt(5,0);
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public Map<String,Partidas> selectProximasPartidas(int idTime1, int idTime2, int rodada){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
        String query = "SELECT * FROM partidas WHERE (idTime1 != "+ idTime1 + " AND idTime2 != "+ idTime1+") AND rodada = " + rodada;
        Map<String,Partidas> partidas = new HashMap<>();
        
        try {
          resultSet = statement.executeQuery(query); 
          int i = 1;
          while(resultSet.next()){
           Partidas partida = new Partidas();
           String string = "partida"+i;
           partida.setIdTime1(resultSet.getInt("idTime1"));
           partida.setIdTime2(resultSet.getInt("idTime2"));
           partidas.put(string, partida);
              System.out.println(i);
           i++;
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
        return partidas;
    }
    
    
    public void sortearPartidas(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet,int idTreinador){
        
        
        /**Map<String,Partidas> dicionario = new HashMap<>();
        int game = 1;
        int idTime = 1;
        
        while(game <= 28){
        
            for (int i = idTime+1; i <= 8; i++) {
                String string = "partida"+game;
                Partidas partida = new Partidas();
                partida.setIdTime1(idTime);
                partida.setIdTime2(i);
                dicionario.put(string,partida);
                game++;
                
            }
            
            idTime++;
            
        }
        
        int k = 1;
        String[] aux;
        
        while (k<28){
           int x = 1 + (int) (Math.random() * 28);
           
           aux[k] = 
        }**/
        
        
        
        
    }
    
    public void selectPartidas(int n , int m) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
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
    
    public Partidas selectPartidasByTime(int idTime,int jogo,int rodada) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
        String query = "SELECT * FROM partidas WHERE (idTime1 = " +idTime+ " OR idTime2 = "+idTime
                + ") AND rodada = "+rodada+ " AND jogo = "+jogo;
        Partidas partida = new Partidas();
        int i = 1;
        try {
          resultSet = statement.executeQuery(query);
  
          
          partida.setId(resultSet.getInt("id"));
          partida.setIdTime1(resultSet.getInt("idTime1"));
          partida.setIdTime2(resultSet.getInt("idTime2"));
          partida.setIdVencedor(resultSet.getInt("idVencedor"));
          partida.setRodada(resultSet.getInt("rodada"));
          partida.setJogo(resultSet.getInt("jogo"));
          partida.setStatus(resultSet.getInt("status"));
          
         
         
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
        return partida;
        
    }
    
    public Partidas selectPartidasFinaisByTime(int idTime,int idTreinador,int rodada) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
        String query = "SELECT * FROM finais WHERE (idTime1 = " +idTime+ " OR idTime2 = "+idTime
                + ") AND rodada = "+rodada+ " AND idTreinador = "+idTreinador;
        Partidas partida = new Partidas();
        try {
          resultSet = statement.executeQuery(query);
  
          
          partida.setId(resultSet.getInt("id"));
          partida.setIdTime1(resultSet.getInt("idTime1"));
          partida.setIdTime2(resultSet.getInt("idTime2"));
          partida.setIdVencedor(resultSet.getInt("idVencedor"));
          partida.setRodada(resultSet.getInt("rodada"));
          partida.setJogo(resultSet.getInt("jogo"));
          partida.setStatus(resultSet.getInt("status"));
          
         
         
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
        return partida;
        
    }
    
    private int selectPontos(int idTreinador,int idTime) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
        String query = "SELECT * FROM pontuacao WHERE idTreinador = "+idTreinador+" AND idTime = "+idTime; 
        
        try {
          resultSet = statement.executeQuery(query);
  
          
         int pontos = resultSet.getInt("pontos");
          
         return pontos;
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
             return 0;
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
    
    
    private void createPartidas(int n , int m) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        
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
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } 
    }
    
    public void createPontuacao(int idTreinador,int idTime) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        
        String query = "INSERT INTO pontuacao ("
                + "idTreinador,"
                + "idTime,"
                + "pontos"
                + ")"
                + "VALUES(?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
            preparedStatement.setInt(1,idTreinador);
            preparedStatement.setInt(2,idTime);
            preparedStatement.setInt(3,0);
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public void updatePontuacao(int idTreinador,int idTime , int pontos) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        
        String query = "UPDATE pontuacao SET pontos = ? WHERE idTreinador = ? AND idTime = ? ";
               
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
            int pontosAnteriores = this.selectPontos(idTreinador, idTime);
            preparedStatement.setInt(1,pontosAnteriores+pontos);
            preparedStatement.setInt(2,idTreinador);
            preparedStatement.setInt(3,idTime);
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
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

    /**
     * @return the jogo
     */
    public int getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(int jogo) {
        this.jogo = jogo;
    }

    /**
     * @return the rodada
     */
    public int getRodada() {
        return rodada;
    }

    /**
     * @param rodada the rodada to set
     */
    public void setRodada(int rodada) {
        this.rodada = rodada;
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

    /**
     * @return the idTreinador
     */
    public int getIdTreinador() {
        return idTreinador;
    }

    /**
     * @param idTreinador the idTreinador to set
     */
    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    /**
     * @return the firstBlood
     */
    public int getFirstBlood() {
        return firstBlood;
    }

    /**
     * @param firstBlood the firstBlood to set
     */
    public void setFirstBlood(int firstBlood) {
        this.firstBlood = firstBlood;
    }

  
}

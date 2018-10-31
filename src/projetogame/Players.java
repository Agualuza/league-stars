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

/**
 *
 * @author user
 */
public class Players extends Jogadores {
    private int idTime;
    private String nome;
    private int tipo;
    private int agressividade;
    private int forca;
    private int agilidade;
    private int inteligencia;
    
    @Override
    public float calculaLambda(Map<String,Players> players) {
        float player1Lambda = players.get("player1").getAgressividade() 
            + players.get("player1").getForca()
            + players.get("player1").getAgilidade()
            + players.get("player1").getInteligencia();
    
        float player2Lambda = players.get("player2").getAgressividade() 
            + players.get("player2").getForca()
            + players.get("player2").getAgilidade()
            + players.get("player2").getInteligencia();
     
        float player3Lambda = players.get("player3").getAgressividade() 
            + players.get("player3").getForca()
            + players.get("player3").getAgilidade()
            + players.get("player3").getInteligencia();
      
        float player4Lambda = players.get("player4").getAgressividade() 
            + players.get("player4").getForca()
            + players.get("player4").getAgilidade()
            + players.get("player4").getInteligencia();
        float player5Lambda = players.get("player5").getAgressividade() 
            + players.get("player5").getForca()
            + players.get("player5").getAgilidade()
            + players.get("player5").getInteligencia();
  
        
        float playersLambda = (player1Lambda+player2Lambda+player3Lambda+player4Lambda+player5Lambda)/25;
        
       
        
        return playersLambda;
        
    }
    
    @Override
    public Map<String,Players> selectPlayers(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int n){
        String query = "SELECT * FROM jogadores WHERE idTime ="+n;
        
        Map<String, Players> players = new HashMap<>();
        int i = 1;
        try {
           resultSet = statement.executeQuery(query);  
           System.out.println("Time Escolhido");
          

         
          while (resultSet.next()) {
                String tipo = null;
                if (resultSet.getInt("tipo") == 1) {
                    tipo = "Topo";
                } else if (resultSet.getInt("tipo") == 2) {
                    tipo = "Caçador";
                } else if (resultSet.getInt("tipo") == 3) {
                    tipo = "Meio";
                } else if (resultSet.getInt("tipo") == 4) {
                    tipo = "Atirador";
                } else if (resultSet.getInt("tipo") == 5){
                    tipo = "Suporte";
                } 
                System.out.print(i+"-");
                System.out.println(resultSet.getString("nome")+" "+tipo);
                
                String string = "player" + i;
                Players player = new Players();
                player.setIdTime( resultSet.getInt("idTime"));
                player.setNome( resultSet.getString("nome"));
                player.setTipo( resultSet.getInt("tipo"));
                player.setAgressividade( resultSet.getInt("agressividade"));
                player.setForca( resultSet.getInt("forca"));
                player.setAgilidade( resultSet.getInt("agilidade"));
                player.setInteligencia( resultSet.getInt("inteligencia"));
                
                players.put(string,player);
                
                i++;
                
               ;
            }
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
        
         return players;
        
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
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the agressividade
     */
    public int getAgressividade() {
        return agressividade;
    }

    /**
     * @param agressividade the agressividade to set
     */
    public void setAgressividade(int agressividade) {
        this.agressividade = agressividade;
    }

    /**
     * @return the forca
     */
    public int getForca() {
        return forca;
    }

    /**
     * @param forca the forca to set
     */
    public void setForca(int forca) {
        this.forca = forca;
    }

    /**
     * @return the agilidade
     */
    public int getAgilidade() {
        return agilidade;
    }

    /**
     * @param agilidade the agilidade to set
     */
    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    /**
     * @return the inteligencia
     */
    public int getInteligencia() {
        return inteligencia;
    }

    /**
     * @param inteligencia the inteligencia to set
     */
    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }
    
    
}

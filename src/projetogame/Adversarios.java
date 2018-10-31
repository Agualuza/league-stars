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
public class Adversarios extends Jogadores {
    private int idTime;
    private String nome;
    private int tipo;
    private int agressividade;
    private int forca;
    private int agilidade;
    private int inteligencia;
    
    @Override
    public float calculaLambda(Map<String,Players> players) {
        float adversario1Lambda = players.get("adversario1").getAgressividade() 
            + players.get("adversario1").getForca()
            + players.get("adversario1").getAgilidade()
            + players.get("adversario1").getInteligencia();
       
        float adversario2Lambda = players.get("adversario2").getAgressividade() 
            + players.get("adversario2").getForca()
            + players.get("adversario2").getAgilidade()
            + players.get("adversario2").getInteligencia();
        float adversario3Lambda = players.get("adversario3").getAgressividade() 
            + players.get("adversario3").getForca()
            + players.get("adversario3").getAgilidade()
            + players.get("adversario3").getInteligencia();
        float adversario4Lambda = players.get("adversario4").getAgressividade() 
            + players.get("adversario4").getForca()
            + players.get("adversario4").getAgilidade()
            + players.get("adversario4").getInteligencia();
        float adversario5Lambda = players.get("adversario5").getAgressividade() 
            + players.get("adversario5").getForca()
            + players.get("adversario5").getAgilidade()
            + players.get("adversario5").getInteligencia();
        
       float adversariosLambda = (adversario1Lambda+adversario2Lambda+adversario3Lambda+adversario4Lambda+adversario5Lambda)/25;
        
       
        
       return adversariosLambda;
        
    }
    
    @Override
    public Map<String,Players> selectPlayers(ConexaoSQLite conexaoSQLite, Statement statement, ResultSet resultSet, int n){
        String query = "SELECT * FROM jogadores WHERE idTime ="+n;
        
        Map<String, Players> players = new HashMap<>();
        int i = 1;
        try {
           resultSet = statement.executeQuery(query);  
           System.out.println("Time Adversário Escolhido");
          

         
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
                
                String string = "adversario" + i;
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
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
    public Map<String,Players> selectPlayers(int n){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        
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
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
          
        return players;
        
    }

}
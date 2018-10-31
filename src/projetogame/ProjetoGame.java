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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author user
 */
public class ProjetoGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        Scanner ler = new Scanner(System.in);
        
        ResultSet resultSet = null;
        Statement statement = null;
        
        conexaoSQLite.conectar(); 
        
   
        
        String query = "SELECT * FROM times;";
        statement = conexaoSQLite.criarStatement();
        
        int i = 1;
       
        
        try {
          resultSet = statement.executeQuery(query);  
          System.out.println("Escolha seu time!");
          

         
          while (resultSet.next()) {
               System.out.print(resultSet.getInt("id")+"-");
                System.out.println(resultSet.getString("nome"));
                
          }
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
        
        
        int n;
        
        n = ler.nextInt();
        
        String query2 = "SELECT * FROM jogadores WHERE idTime ="+n;
        
        Map<String, Player> players = new HashMap<>();
        i = 1;
        try {
          resultSet = statement.executeQuery(query2);  
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
                Player player = new Player();
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
        
        String query3 = "SELECT * FROM times WHERE id !="
                + n;
       
        
       
        try {
          resultSet = statement.executeQuery(query3);  
          System.out.println("\nEscolha o Adversário!");
          

         
          while (resultSet.next()) {
                System.out.print(resultSet.getInt("id")+"-");
                System.out.println(resultSet.getString("nome"));
            }
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } 
        
        int m = ler.nextInt();
        
        String query6 = "SELECT * FROM jogadores WHERE idTime ="+m;
        
        
        i = 1;
        try {
          resultSet = statement.executeQuery(query6);  
          System.out.println("\nTime Adversário Escolhido");
          

         
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
                Player player = new Player();
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
        
        
        
       
        /* Partidas partida = new Partidas();
        partida.setIdTime1(n);
        partida.setIdTime2(m);
        partida.setIdVencedor(0);
        partida.setStatus(0);
        
        String query4 = "INSERT INTO partidas ("
                + "idTime1,"
                + "idTime2,"
                + "idVencedor,"
                + "status"
                + ")"
                + "VALUES(?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query4);
        
        try {
            preparedStatement.setInt(1,partida.getIdTime1());
            preparedStatement.setInt(2,partida.getIdTime2());
            preparedStatement.setInt(3,partida.getIdVencedor());
            preparedStatement.setInt(4,partida.getStatus());
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } */ 
   
        String query5 = "SELECT * FROM times WHERE id = "+ n + " OR id ="+ m ;
       
        i = 1;
        try {
          resultSet = statement.executeQuery(query5);  
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
        
        float playersLambda = (player1Lambda+player2Lambda+player3Lambda+player4Lambda+player5Lambda)/25;
        float adversariosLambda = (adversario1Lambda+adversario2Lambda+adversario3Lambda+adversario4Lambda+adversario5Lambda)/25;
       
        Poisson poisson = new Poisson();
        
        float p = playersLambda/adversariosLambda;
        float a = adversariosLambda/playersLambda;
        
        float tempoPlayers = (float) (poisson.distribuicaoPoisson(p));
        float tempoAdversarios = (float) (poisson.distribuicaoPoisson(a));
        
        Timer timer = new Timer();
        
        long segPlayers = (long) (10000 * tempoPlayers );
        long segAdversarios = (long) (10000 * tempoAdversarios);
        segPlayers = (long)((Math.random()*5)*segPlayers);
        segAdversarios = (long)((Math.random()*5)*segAdversarios);
        
        
        TimerTask task = new TimerTask() {
            int j = 1;
            
            @Override
            public void run() {
                if (j == 1) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "player" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu a primeira torre");
                    j++;
                } else if (j == 2) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "player" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu a segunda torre");
                    j++;
                } else if (j == 3) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "player" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu o primeiro inibidor");
                    j++;
                } else if (j == 4) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "player" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu o segundo inibidor");
                    j++;
                } else if (j == 5) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "player" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu o nexus");
                    System.out.println("O jogo acabou - Você venceu");
                    timer.cancel();
                }
            }
        };
        
        timer.scheduleAtFixedRate(task, segPlayers, segPlayers);
        
        
        TimerTask task2 = new TimerTask() {
            int  k = 1;
            
            @Override
            public void run() {
                if (k == 1) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu a primeira torre");
                    k++;
                } else if (k == 2) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu a segunda torre");
                    k++;
                } else if (k == 3) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu o primeiro inibidor");
                    k++;
                } else if (k == 4) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu o segundo inibidor");
                    k++;
                } else if (k == 5) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = players.get(jogador).getNome();
                    System.out.println(pl+" destruiu o nexus");
                    System.out.println("O jogo acabou - Você perdeu");
                    timer.cancel();
                }  
            }
        };
        
        timer.scheduleAtFixedRate(task2, segAdversarios, segAdversarios);
        
        
        
                
    }
    
    
    
    
}

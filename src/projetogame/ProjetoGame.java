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
import javax.swing.JFrame;
import views.EscolherTime;
import views.Main;


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
        Main janelaMain = new Main();
        EscolherTime janelaTime = new EscolherTime();
            
        janelaTime.setLocationRelativeTo(null);
        janelaMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaTime.setVisible(true);
        //janelaTime.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement();
        
        Players player = new Players();
        Adversarios adversario = new Adversarios();
        Partidas partida = new Partidas();
        Times time = new Times();
        
        time.selectTimes(conexaoSQLite, statement, resultSet, janelaTime);
        int n = ler.nextInt();
        Map<String,Players> players = player.selectPlayers(conexaoSQLite, statement, resultSet, n);
        time.selectAdversario(conexaoSQLite, statement, resultSet, n);
        int m = ler.nextInt(); 
        Map<String,Players> adversarios = adversario.selectPlayers(conexaoSQLite, statement, resultSet, m);
        partida.selectPartidas(conexaoSQLite, statement, resultSet, n, m);
        float playersLambda = player.calculaLambda(players);
        float adversariosLambda = adversario.calculaLambda(adversarios);
        long segPlayers = player.calculaTempo(playersLambda, adversariosLambda);
        long segAdversarios = adversario.calculaTempo(adversariosLambda,playersLambda);
        
        
        
        Timer timer = new Timer();
        
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
                    String pl = adversarios.get(jogador).getNome();
                    System.out.println(pl+" destruiu a primeira torre");
                    k++;
                } else if (k == 2) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = adversarios.get(jogador).getNome();
                    System.out.println(pl+" destruiu a segunda torre");
                    k++;
                } else if (k == 3) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = adversarios.get(jogador).getNome();
                    System.out.println(pl+" destruiu o primeiro inibidor");
                    k++;
                } else if (k == 4) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = adversarios.get(jogador).getNome();
                    System.out.println(pl+" destruiu o segundo inibidor");
                    k++;
                } else if (k == 5) {
                    int x = 1 + (int) (Math.random() * 5);
                    String jogador = "adversario" + x;
                    String pl = adversarios.get(jogador).getNome();
                    System.out.println(pl+" destruiu o nexus");
                    System.out.println("O jogo acabou - Você perdeu");
                    timer.cancel();
                }  
            }
        };
        
        timer.scheduleAtFixedRate(task2, segAdversarios, segAdversarios);
        
        
                   
    }
    
    
    
    
}

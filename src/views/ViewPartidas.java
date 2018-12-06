/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import conexoes.ConexaoSQLite;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import projetogame.Adversarios;
import projetogame.Dados;
import projetogame.Partidas;
import projetogame.Players;
import projetogame.Times;
import projetogame.Treinadores;
      
/**
 *
 * @author iagoagualuza
 */
public class ViewPartidas extends javax.swing.JFrame {
    /**
     * Creates new form ViewPartidas
     */
    public ViewPartidas() {
        initComponents();
        String string = "#8895bd";
        Color cor = Color.decode(string);
        this.getContentPane().setBackground(cor);
    }
    
    public void recebeDados(int idTime,int idTreinador,int rodada) {
        Treinadores treinador = new Treinadores();
        Times time = new Times();
        Partidas partida = new Partidas();
        Adversarios adversario = new Adversarios();
        Players player = new Players();
        Principal janelaPrincipal = new Principal();
       
        partida = partida.selectPartidasByTime(idTime, 1, rodada);
        
        String corFundoTime1 = time.selectTimesCorFundo(partida.getIdTime1());
        String corTextoTime1 = time.selectTimesCorTexto(partida.getIdTime1());
        String corFundoTime2 = time.selectTimesCorFundo(partida.getIdTime2());
        String corTextoTime2 = time.selectTimesCorTexto(partida.getIdTime2());
        
        
        Color corFundo1 = Color.decode(corFundoTime1);
        Color corTexto1 = Color.decode(corTextoTime1);
        Color corFundo2 = Color.decode(corFundoTime2);
        Color corTexto2 = Color.decode(corTextoTime2);
        int id1 = partida.getIdTime1();
        int id2 = partida.getIdTime2();
        String time1 = time.selectTimesNome(partida.getIdTime1());
        String time2 = time.selectTimesNome(partida.getIdTime2());
        
        jLabel2.setText(time1);
        jLabel4.setText(time2);
        jLabel2.setOpaque(true);
        jLabel4.setOpaque(true);
        jLabel2.setForeground(corTexto1);
        jLabel4.setForeground(corTexto2);
        jLabel2.setBackground(corFundo1);
        jLabel4.setBackground(corFundo2);
        
        Map<String,Players> players = player.selectPlayers(idTime);
        time.selectAdversario(idTime); 
        int idAdversario = 0;
        
        if (idTime == partida.getIdTime1()) {
             idAdversario = partida.getIdTime2();
             player.setFlag(0);
        } else if (idTime == partida.getIdTime2()) {
             idAdversario = partida.getIdTime1();
             player.setFlag(1);
        }
        
        Map<String,Players> adversarios = adversario.selectPlayers(idAdversario);
        partida.selectPartidas(idTime, idAdversario);
        float playersLambda = player.calculaLambda(players);
        float adversariosLambda = adversario.calculaLambda(adversarios);
        long segPlayers = player.calculaTempo(playersLambda, adversariosLambda);
        long segAdversarios = adversario.calculaTempo(adversariosLambda,playersLambda);
        
        Map<String, Partidas> proxPartidas = partida.selectProximasPartidas(partida.getIdTime1(), partida.getIdTime2(), rodada);
        
        Map<String, Players> players2 = player.selectPlayers(proxPartidas.get("partida1").getIdTime1());
        Map<String, Players> adversarios2 = adversario.selectPlayers(proxPartidas.get("partida1").getIdTime2());
        float playersLambda2 = player.calculaLambda(players2);
        float adversariosLambda2 = adversario.calculaLambda(adversarios2);
        long segPlayers2 = player.calculaTempo(playersLambda, adversariosLambda2);
        long segAdversarios2 = adversario.calculaTempo(adversariosLambda,playersLambda2);
        
        Map<String, Players> players3 = player.selectPlayers(proxPartidas.get("partida2").getIdTime1());
        Map<String, Players> adversarios3 = adversario.selectPlayers(proxPartidas.get("partida2").getIdTime2());
        float playersLambda3 = player.calculaLambda(players3);
        float adversariosLambda3 = adversario.calculaLambda(adversarios3);
        long segPlayers3 = player.calculaTempo(playersLambda, adversariosLambda3);
        long segAdversarios3 = adversario.calculaTempo(adversariosLambda,playersLambda3);
        
        Map<String, Players> players4 = player.selectPlayers(proxPartidas.get("partida3").getIdTime1());
        Map<String, Players> adversarios4 = adversario.selectPlayers(proxPartidas.get("partida3").getIdTime2());
        float playersLambda4 = player.calculaLambda(players4);
        float adversariosLambda4 = adversario.calculaLambda(adversarios4);
        long segPlayers4 = player.calculaTempo(playersLambda, adversariosLambda4);
        long segAdversarios4 = adversario.calculaTempo(adversariosLambda,playersLambda4);
        
        String corFundoTime21 = time.selectTimesCorFundo(proxPartidas.get("partida1").getIdTime1());
        String corTextoTime21 = time.selectTimesCorTexto(proxPartidas.get("partida1").getIdTime1());
        String corFundoTime22 = time.selectTimesCorFundo(proxPartidas.get("partida1").getIdTime2());
        String corTextoTime22 = time.selectTimesCorTexto(proxPartidas.get("partida1").getIdTime2());
        String time21 = time.selectTimesNome(proxPartidas.get("partida1").getIdTime1());
        String time22 = time.selectTimesNome(proxPartidas.get("partida1").getIdTime2());
        
        int id21 = proxPartidas.get("partida1").getIdTime1();
        int id22 = proxPartidas.get("partida1").getIdTime2();
        
        Color corFundo21 = Color.decode(corFundoTime21);
        Color corTexto21 = Color.decode(corTextoTime21);
        Color corFundo22 = Color.decode(corFundoTime22);
        Color corTexto22 = Color.decode(corTextoTime22);
        
        String corFundoTime31 = time.selectTimesCorFundo(proxPartidas.get("partida2").getIdTime1());
        String corTextoTime31 = time.selectTimesCorTexto(proxPartidas.get("partida2").getIdTime1());
        String corFundoTime32 = time.selectTimesCorFundo(proxPartidas.get("partida2").getIdTime2());
        String corTextoTime32 = time.selectTimesCorTexto(proxPartidas.get("partida2").getIdTime2());
        String time31 = time.selectTimesNome(proxPartidas.get("partida2").getIdTime1());
        String time32 = time.selectTimesNome(proxPartidas.get("partida2").getIdTime2());
        
        int id31 = proxPartidas.get("partida2").getIdTime1();
        int id32 = proxPartidas.get("partida2").getIdTime2();
        
        Color corFundo31 = Color.decode(corFundoTime31);
        Color corTexto31 = Color.decode(corTextoTime31);
        Color corFundo32 = Color.decode(corFundoTime32);
        Color corTexto32 = Color.decode(corTextoTime32);
        
        String corFundoTime41 = time.selectTimesCorFundo(proxPartidas.get("partida3").getIdTime1());
        String corTextoTime41 = time.selectTimesCorTexto(proxPartidas.get("partida3").getIdTime1());
        String corFundoTime42 = time.selectTimesCorFundo(proxPartidas.get("partida3").getIdTime2());
        String corTextoTime42 = time.selectTimesCorTexto(proxPartidas.get("partida3").getIdTime2());
        String time41 = time.selectTimesNome(proxPartidas.get("partida3").getIdTime1());
        String time42 = time.selectTimesNome(proxPartidas.get("partida3").getIdTime2());
        
        int id41 = proxPartidas.get("partida3").getIdTime1();
        int id42 = proxPartidas.get("partida3").getIdTime2();
        
        Color corFundo41 = Color.decode(corFundoTime41);
        Color corTexto41 = Color.decode(corTextoTime41);
        Color corFundo42 = Color.decode(corFundoTime42);
        Color corTexto42 = Color.decode(corTextoTime42);
        
        
        jLabel8.setText(time21);
        jLabel10.setText(time22);
        jLabel8.setOpaque(true);
        jLabel10.setOpaque(true);
        jLabel8.setForeground(corTexto21);
        jLabel10.setForeground(corTexto22);
        jLabel8.setBackground(corFundo21);
        jLabel10.setBackground(corFundo22);
        
        jLabel13.setText(time31);
        jLabel15.setText(time32);
        jLabel13.setOpaque(true);
        jLabel15.setOpaque(true);
        jLabel13.setForeground(corTexto31);
        jLabel15.setForeground(corTexto32);
        jLabel13.setBackground(corFundo31);
        jLabel15.setBackground(corFundo32);
        
        jLabel18.setText(time41);
        jLabel20.setText(time42);
        jLabel18.setOpaque(true);
        jLabel20.setOpaque(true);
        jLabel18.setForeground(corTexto41);
        jLabel20.setForeground(corTexto42);
        jLabel18.setBackground(corFundo41);
        jLabel20.setBackground(corFundo42);
        
      
        
        Partidas partidaAux = new Partidas();
        Partidas partidaAux2 = new Partidas();
        Partidas partidaAux3 = new Partidas();
        Partidas partidaAux4 = new Partidas();
        Times t1 = new Times();
        Times t2 = new Times();
        Times t3 = new Times();
        Times t4 = new Times();
        Times t5 = new Times();
        Times t6 = new Times();
        Times t7 = new Times();
        Times t8 = new Times();
        partidaAux.setFlag(0);
        partidaAux.setFirstBlood(0);
        partidaAux2.setFirstBlood(0);
        partidaAux3.setFirstBlood(0);
        partidaAux4.setFirstBlood(0);
        t1.setFlag(4);
        t2.setFlag(4);
        t3.setFlag(4);
        t4.setFlag(4);
        t5.setFlag(4);
        t6.setFlag(4);
        t7.setFlag(4);
        t8.setFlag(4);
        
        
        
        
        List<String> listaEventos = new ArrayList<>();
        listaEventos.add(" conseguiu o first blood");
        listaEventos.add(" destruiu a primeira torre (BOT)");
        listaEventos.add(" destruiu a primeira torre (MID)");
        listaEventos.add(" destruiu a primeira torre (TOP)");
        
        List<String> listaEventos2 = new ArrayList<>();
        listaEventos2.add(" conseguiu o first blood");
        listaEventos2.add(" destruiu a primeira torre (BOT)");
        listaEventos2.add(" destruiu a primeira torre (MID)");
        listaEventos2.add(" destruiu a primeira torre (TOP)");
        
        List<String> listaEventos3 = new ArrayList<>();
        listaEventos3.add(" conseguiu o first blood");
        listaEventos3.add(" destruiu a primeira torre (BOT)");
        listaEventos3.add(" destruiu a primeira torre (MID)");
        listaEventos3.add(" destruiu a primeira torre (TOP)");
        
         List<String> listaEventos4 = new ArrayList<>();
        listaEventos4.add(" conseguiu o first blood");
        listaEventos4.add(" destruiu a primeira torre (BOT)");
        listaEventos4.add(" destruiu a primeira torre (MID)");
        listaEventos4.add(" destruiu a primeira torre (TOP)");
        
         List<String> listaEventos5 = new ArrayList<>();
        listaEventos5.add(" conseguiu o first blood");
        listaEventos5.add(" destruiu a primeira torre (BOT)");
        listaEventos5.add(" destruiu a primeira torre (MID)");
        listaEventos5.add(" destruiu a primeira torre (TOP)");
        
         List<String> listaEventos6 = new ArrayList<>();
        listaEventos6.add(" conseguiu o first blood");
        listaEventos6.add(" destruiu a primeira torre (BOT)");
        listaEventos6.add(" destruiu a primeira torre (MID)");
        listaEventos6.add(" destruiu a primeira torre (TOP)");
        
         List<String> listaEventos7 = new ArrayList<>();
        listaEventos7.add(" conseguiu o first blood");
        listaEventos7.add(" destruiu a primeira torre (BOT)");
        listaEventos7.add(" destruiu a primeira torre (MID)");
        listaEventos7.add(" destruiu a primeira torre (TOP)");
        
         List<String> listaEventos8 = new ArrayList<>();
        listaEventos8.add(" conseguiu o first blood");
        listaEventos8.add(" destruiu a primeira torre (BOT)");
        listaEventos8.add(" destruiu a primeira torre (MID)");
        listaEventos8.add(" destruiu a primeira torre (TOP)");
        
        
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {
       
            
            @Override
            public void run() {
                    
                int j = (int) (Math.random() * t1.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "player" + x;
                String pl = players.get(jogador).getNome();
                if (partidaAux.getFirstBlood() == 1 && listaEventos.get(j) == " conseguiu o first blood") {
                    
                } else {
                    if (player.getFlag() == 0) {
                        jLabel5.setText(pl+listaEventos.get(j));   
                    } else if (player.getFlag() == 1) {
                        jLabel6.setText(pl+listaEventos.get(j));
                    }
                }
                
                if (listaEventos.get(j) == " conseguiu o first blood")  {
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu o primeiro dragão");
                    partidaAux.setFirstBlood(1);
                    
                } else if (listaEventos.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos.get(j) == " destruiu o primeiro dragão") {
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu o segundo dragão");
                } else if (listaEventos.get(j) == " destruiu o segundo dragão") {
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu o arauto");
                } else if (listaEventos.get(j) == " destruiu o arauto"){
                    listaEventos.remove(j);
                    t1.setFlag(3);
                } else if (listaEventos.get(j).contains("inibidor")){
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu as torres do nexus");
                } else if (listaEventos.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu o nexus");
                } else if (listaEventos.get(j) == " destruiu o nexus"){
                   jLabel7.setText("acabou o jogo você venceu"); 
                    Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador, idTime, 3);
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                        setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    timer.cancel();
                }
            }
                    
                   
                  
        };
        timer.scheduleAtFixedRate(task, segPlayers, player.calculaTempo(playersLambda,adversariosLambda));
        
        TimerTask task2 = new TimerTask() {
            
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t2.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "adversario" + x;
                String pl = adversarios.get(jogador).getNome();
                if (partidaAux.getFirstBlood() == 1 && listaEventos2.get(j) == " conseguiu o first blood") {
                    
                } else {
                    if (player.getFlag() == 1) {
                        jLabel5.setText(pl+listaEventos2.get(j));   
                    } else if (player.getFlag() == 0) {
                        jLabel6.setText(pl+listaEventos2.get(j));
                    }
                }
                
                if (listaEventos2.get(j) == " conseguiu o first blood")  {
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu o primeiro dragão");
                    partidaAux.setFirstBlood(1);
                    
                } else if (listaEventos2.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos2.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos2.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos2.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos2.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos2.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos2.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos2.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos2.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos2.get(j) == " destruiu o primeiro dragão") {
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu o segundo dragão");
                } else if (listaEventos2.get(j) == " destruiu o segundo dragão") {
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu o arauto");
                } else if (listaEventos2.get(j) == " destruiu o arauto"){
                    listaEventos2.remove(j);
                    t2.setFlag(3);
                } else if (listaEventos2.get(j).contains("inibidor")){
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu as torres do nexus");
                } else if (listaEventos2.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos2.remove(j);
                    listaEventos2.add(" destruiu o nexus");
                } else if (listaEventos2.get(j) == " destruiu o nexus"){
                   jLabel7.setText("acabou o jogo você perdeu"); 
                    Partidas p = new Partidas();
                    if (idTime == id1 ) {
                        p.updatePontuacao(idTreinador, id2, 3);
                    } else {
                        p.updatePontuacao(idTreinador, id1, 3);
                    }
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    
                    timer.cancel();
                }
                    
                    
                
            }
        };
        timer.scheduleAtFixedRate(task2, segAdversarios, player.calculaTempo(adversariosLambda,playersLambda));
        
        Timer timer2 = new Timer();
        
        TimerTask task3 = new TimerTask() {
            
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t3.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "player" + x;
                String pl = players2.get(jogador).getNome();
                if (partidaAux2.getFirstBlood() == 1 && listaEventos3.get(j) == " conseguiu o first blood") {
                    
                } else {
                    jLabel11.setText(pl+listaEventos3.get(j));
                }
                
                if (listaEventos3.get(j) == " conseguiu o first blood")  {
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu o primeiro dragão");
                    partidaAux2.setFirstBlood(1);
                    
                } else if (listaEventos3.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos3.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos3.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos3.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos3.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos3.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos3.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos3.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos3.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos3.get(j) == " destruiu o primeiro dragão") {
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu o segundo dragão");
                } else if (listaEventos3.get(j) == " destruiu o segundo dragão") {
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu o arauto");
                } else if (listaEventos3.get(j) == " destruiu o arauto"){
                    listaEventos3.remove(j);
                    t3.setFlag(3);
                } else if (listaEventos3.get(j).contains("inibidor")){
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu as torres do nexus");
                } else if (listaEventos3.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos3.remove(j);
                    listaEventos3.add(" destruiu o nexus");
                } else if (listaEventos3.get(j) == " destruiu o nexus"){
                    Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador, id21, 3);
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    
                    timer2.cancel();
                } 
                    
                    
               
            }
        };
        timer2.scheduleAtFixedRate(task3, segPlayers2, player.calculaTempo(playersLambda2,adversariosLambda2));
        
        TimerTask task4 = new TimerTask() {
            
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t4.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "adversario" + x;
                String pl = adversarios2.get(jogador).getNome();
                if (partidaAux2.getFirstBlood() == 1 && listaEventos4.get(j) == " conseguiu o first blood") {
                    
                } else {
                    jLabel12.setText(pl+listaEventos4.get(j));
                }
                
                if (listaEventos.get(j) == " conseguiu o first blood")  {
                    listaEventos.remove(j);
                    listaEventos.add(" destruiu o primeiro dragão");
                    partidaAux2.setFirstBlood(1);
                    
                } else if (listaEventos4.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos4.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos4.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos4.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos4.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos4.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos4.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos4.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos4.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos4.get(j) == " destruiu o primeiro dragão") {
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu o segundo dragão");
                } else if (listaEventos4.get(j) == " destruiu o segundo dragão") {
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu o arauto");
                } else if (listaEventos4.get(j) == " destruiu o arauto"){
                    listaEventos4.remove(j);
                    t4.setFlag(3);
                } else if (listaEventos4.get(j).contains("inibidor")){
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu as torres do nexus");
                } else if (listaEventos4.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos4.remove(j);
                    listaEventos4.add(" destruiu o nexus");
                } else if (listaEventos4.get(j) == " destruiu o nexus"){
                     Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador,id22, 3);
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    timer2.cancel();
                
                }
                    
                   
            }
        };
        timer2.scheduleAtFixedRate(task4, segAdversarios2, player.calculaTempo(adversariosLambda2,playersLambda2));
        
        Timer timer3 = new Timer();
        
        TimerTask task5 = new TimerTask() {
            
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t5.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "player" + x;
                String pl = players3.get(jogador).getNome();
                if (partidaAux3.getFirstBlood() == 1 && listaEventos5.get(j) == " conseguiu o first blood") {
                    
                } else {
                    jLabel16.setText(pl+listaEventos5.get(j));
                }
                
                if (listaEventos5.get(j) == " conseguiu o first blood")  {
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu o primeiro dragão");
                    partidaAux3.setFirstBlood(1);
                    
                } else if (listaEventos5.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos5.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos5.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos5.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos5.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos5.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos5.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos5.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos5.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos5.get(j) == " destruiu o primeiro dragão") {
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu o segundo dragão");
                } else if (listaEventos5.get(j) == " destruiu o segundo dragão") {
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu o arauto");
                } else if (listaEventos5.get(j) == " destruiu o arauto"){
                    listaEventos5.remove(j);
                    t5.setFlag(3);
                } else if (listaEventos5.get(j).contains("inibidor")){
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu as torres do nexus");
                } else if (listaEventos5.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos5.remove(j);
                    listaEventos5.add(" destruiu o nexus");
                } else if (listaEventos5.get(j) == " destruiu o nexus"){
                   Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador, id31, 3);
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    timer3.cancel();
                }
                    
                    
               
            }
        };
        timer3.scheduleAtFixedRate(task5, segPlayers3, player.calculaTempo(playersLambda3, adversariosLambda3));
        
        TimerTask task6 = new TimerTask() {
            
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t6.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "adversario" + x;
                String pl = adversarios3.get(jogador).getNome();
                if (partidaAux3.getFirstBlood() == 1 && listaEventos6.get(j) == " conseguiu o first blood") {
                    
                } else {
                    jLabel17.setText(pl+listaEventos6.get(j));
                }
                
                if (listaEventos6.get(j) == " conseguiu o first blood")  {
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu o primeiro dragão");
                    partidaAux3.setFirstBlood(1);
                    
                } else if (listaEventos6.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos6.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos6.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos6.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos6.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos6.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos6.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos6.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos6.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos6.get(j) == " destruiu o primeiro dragão") {
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu o segundo dragão");
                } else if (listaEventos6.get(j) == " destruiu o segundo dragão") {
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu o arauto");
                } else if (listaEventos6.get(j) == " destruiu o arauto"){
                    listaEventos6.remove(j);
                    t6.setFlag(3);
                } else if (listaEventos6.get(j).contains("inibidor")){
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu as torres do nexus");
                } else if (listaEventos6.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos6.remove(j);
                    listaEventos6.add(" destruiu o nexus");
                } else if (listaEventos6.get(j) == " destruiu o nexus"){
                    Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador, id32, 3);
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    timer3.cancel();
                
                }   
                    
                     
            }
        };
        timer3.scheduleAtFixedRate(task6, segAdversarios3, player.calculaTempo(adversariosLambda3,playersLambda3));
        
        Timer timer4 = new Timer();
        
        TimerTask task7 = new TimerTask() {
           
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t7.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "player" + x;
                String pl = players4.get(jogador).getNome();
                if (partidaAux4.getFirstBlood() == 1 && listaEventos7.get(j) == " conseguiu o first blood") {
                    
                } else {
                    jLabel21.setText(pl+listaEventos7.get(j));
                }
                
                if (listaEventos7.get(j) == " conseguiu o first blood")  {
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu o primeiro dragão");
                    partidaAux4.setFirstBlood(1);
                    
                } else if (listaEventos7.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos7.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos7.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos7.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos7.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos7.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos7.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos7.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos7.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos7.get(j) == " destruiu o primeiro dragão") {
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu o segundo dragão");
                } else if (listaEventos7.get(j) == " destruiu o segundo dragão") {
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu o arauto");
                } else if (listaEventos7.get(j) == " destruiu o arauto"){
                    listaEventos7.remove(j);
                    t7.setFlag(3);
                } else if (listaEventos7.get(j).contains("inibidor")){
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu as torres do nexus");
                } else if (listaEventos7.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos7.remove(j);
                    listaEventos7.add(" destruiu o nexus");
                } else if (listaEventos7.get(j) == " destruiu o nexus"){
                    Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador, id41, 3);
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    timer4.cancel();
                }
                    
                    
                
            }
        };
        timer4.scheduleAtFixedRate(task7, segPlayers4, player.calculaTempo(playersLambda4, adversariosLambda4));
        
        TimerTask task8 = new TimerTask() {
            
            
            @Override
            public void run() {
                int j = (int) (Math.random() * t8.getFlag()); 
                int x = 1 + (int) (Math.random() * 5);
                String jogador = "adversario" + x;
                String pl = adversarios4.get(jogador).getNome();
                if (partidaAux4.getFirstBlood() == 1 && listaEventos8.get(j) == " conseguiu o first blood") {
                    
                } else {
                    jLabel22.setText(pl+listaEventos8.get(j));
                }
                
                if (listaEventos8.get(j) == " conseguiu o first blood")  {
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu o primeiro dragão");
                    partidaAux4.setFirstBlood(1);
                    
                } else if (listaEventos8.get(j) == " destruiu a primeira torre (BOT)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a segunda torre (BOT)");
                } else if (listaEventos8.get(j) == " destruiu a primeira torre (MID)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a segunda torre (MID)");
                } else if (listaEventos8.get(j) == " destruiu a primeira torre (TOP)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a segunda torre (TOP)");
                } else if (listaEventos8.get(j) == " destruiu a segunda torre (BOT)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a terceira torre (BOT)");
                } else if (listaEventos8.get(j) == " destruiu a segunda torre (MID)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a terceira torre (MID)");
                } else if (listaEventos8.get(j) == " destruiu a segunda torre (TOP)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a terceira torre (TOP)");
                } else if (listaEventos8.get(j) == " destruiu a terceira torre (BOT)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a o inibidor (BOT)");
                } else if (listaEventos8.get(j) == " destruiu a terceira torre (MID)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a o inibidor (MID)");
                } else if (listaEventos8.get(j) == " destruiu a terceira torre (TOP)"){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu a o inibidor (TOP)");
                } else if (listaEventos8.get(j) == " destruiu o primeiro dragão") {
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu o segundo dragão");
                } else if (listaEventos8.get(j) == " destruiu o segundo dragão") {
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu o arauto");
                } else if (listaEventos8.get(j) == " destruiu o arauto"){
                    listaEventos8.remove(j);
                    t8.setFlag(3);
                } else if (listaEventos8.get(j).contains("inibidor")){
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu as torres do nexus");
                } else if (listaEventos8.get(j) == " destruiu as torres do nexus" ) {
                    listaEventos8.remove(j);
                    listaEventos8.add(" destruiu o nexus");
                } else if (listaEventos8.get(j) == " destruiu o nexus"){
                    Partidas p = new Partidas();
                    p.updatePontuacao(idTreinador, id42 , 3);
                    
                    int aux = partidaAux.getFlag()+1;
                    partidaAux.setFlag(aux);
                    
                    if (partidaAux.getFlag() == 4) {
                         setVisible(false);
                        janelaPrincipal.setLocationRelativeTo(null);
                        janelaPrincipal.setVisible(true);
                        janelaPrincipal.recebeDados(idTreinador,rodada+1);
                    }
                    timer4.cancel();
                }    
                    
                    
                
            }
        };
        timer4.scheduleAtFixedRate(task8, segAdversarios4, player.calculaTempo(adversariosLambda4,playersLambda4));
        
        
    
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CBLoL");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("jLabel2");

        jLabel3.setText("X");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("jLabel8");

        jLabel9.setText("X");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("jLabel10");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("jLabel13");

        jLabel14.setText("X");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("jLabel14");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("jLabel18");

        jLabel19.setText("X");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("jLabel20");

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel22.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPartidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

}
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
import views.Inicial;


/**
 *
 * @author user
 */
public class ProjetoGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inicial janelaInicial = new Inicial();
            
        janelaInicial.setLocationRelativeTo(null);
        janelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaInicial.setVisible(true);
       
    }
}

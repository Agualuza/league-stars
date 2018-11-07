/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogame;

import conexoes.ConexaoSQLite;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author user
 */
public interface CalculosJogadores {
    public abstract float calculaLambda(Map<String,Players> players);
    
    public abstract Map<String,Players> selectPlayers(int n);

    public abstract long calculaTempo(float playersLambda ,float adversariosLambda);
}

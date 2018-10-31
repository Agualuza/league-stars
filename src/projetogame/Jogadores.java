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
import java.util.Timer;

/**
 *
 * @author user
 */
public abstract class Jogadores implements CalculosUsuarios {
    private int idTime;
    private String nome;
    private int tipo;
    private int agressividade;
    private int forca;
    private int agilidade;
    private int inteligencia;

    public long calculaTempo(float playersLambda,float adversariosLambda){
        Poisson poisson = new Poisson();
        
        float p = playersLambda/adversariosLambda;
        
        float tempoPlayers = (float) (poisson.distribuicaoPoisson(p));
       
        long segPlayers = (long) (10000 * tempoPlayers );
        
        segPlayers = (long)((Math.random()*5)*segPlayers);
        
        
        return segPlayers;
        
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

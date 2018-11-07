/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogame;

/**
 *
 * @author user
 */
public class GeradorAleatorio {
     public float distribuicaoPoisson(float lambda){
        int k = 1;
        float resultado = (float) (Math.exp(-lambda)*Math.pow(lambda, k))/this.fatorial(k);
        return resultado;
    }
    
    public int fatorial(int k){
        int resultado = 1;
        for (int i = k; i < 1 ; i--) {
            resultado = resultado * i;
        }
        return resultado;
    }
}

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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iagoagualuza
 */
public class Dados {
    private int id;
    private int idTreinador;
    private int idTime;
    private int rodada;
    private int p1;
    private int p2;
    private int p3;
    private int p4;
    private int p5;
    private int p6;
    private int p7;
    private int p8;
    
    public int selectDadosIdVencedor(int idTreinador, int rodada){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement();  
       
       String query = "SELECT * FROM finais WHERE idTreinador = "+idTreinador+" AND rodada = "+rodada;
        int id = 0;
        
        try {
           resultSet = statement.executeQuery(query);  
        
           id = resultSet.getInt("idVencedor"); 
           
         
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
     return id;   
    }
    
    public List<Dados> selectTodosDados(){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        List<Dados> lista = new ArrayList<>();
        
        String query = "SELECT * FROM salvos";
        
        try {
           resultSet = statement.executeQuery(query);  
           while(resultSet.next()){
               Dados dado = new Dados();
               dado.setId(resultSet.getInt("id"));
               dado.setIdTreinador(resultSet.getInt("idTreinador"));
               dado.setIdTime(resultSet.getInt("idTime"));
               dado.setRodada(resultSet.getInt("rodada"));
               dado.setP1(resultSet.getInt("p1"));
               dado.setP2(resultSet.getInt("p2"));
               dado.setP3(resultSet.getInt("p3"));
               dado.setP4(resultSet.getInt("p4"));
               dado.setP5(resultSet.getInt("p5"));
               dado.setP6(resultSet.getInt("p6"));
               dado.setP7(resultSet.getInt("p7"));
               dado.setP8(resultSet.getInt("p8"));
               lista.add(dado);
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
        
         return lista;
        
    }
    
     public void updateFinais(int idTreinador,int idVencedor,int rodada) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        
        String query = "UPDATE finais SET idVencedor = ? WHERE idTreinador = ? AND rodada = ?";
               
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
            preparedStatement.setInt(1,idVencedor);
            preparedStatement.setInt(2,idTreinador);
            preparedStatement.setInt(3,rodada);
            
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } 
    }
    
    private int selectDados(int idTreinador){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        int aux = 0;
        
        String query = "SELECT * FROM salvos WHERE idTreinador ="+idTreinador;
        
        try {
           resultSet = statement.executeQuery(query);  
           aux = resultSet.getInt("idTreinador");
         
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
        
         return aux;
        
    }
    
    public int selectDadosByIdTreinador(int idTreinador,int rodada){
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        ResultSet resultSet = null;
        Statement statement = null;
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement(); 
        int aux = 0;
        
        String query = "SELECT * FROM finais WHERE idTreinador ="+idTreinador+" AND rodada = "+rodada;
        
        try {
           resultSet = statement.executeQuery(query);  
           aux = resultSet.getInt("idTreinador");
         
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
        
         return aux;
        
    }


    
    public void salvarGame(int idTreinador,int idTime,int rodada,int p1,int p2,int p3,int p4, int p5, int p6,int p7,int p8) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        int aux = this.selectDados(idTreinador);
        
        if (aux == 0) {
            String query = "INSERT INTO salvos ("
                + "idTreinador,"
                + "idTime,"
                + "rodada,"
                + "p1,"
                + "p2,"
                + "p3,"
                + "p4,"
                + "p5,"
                + "p6,"
                + "p7,"
                + "p8"
                + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
            preparedStatement.setInt(1,idTreinador);
            preparedStatement.setInt(2,idTime);
            preparedStatement.setInt(3,rodada);
            preparedStatement.setInt(4,p1);
            preparedStatement.setInt(5,p2);
            preparedStatement.setInt(6,p3);
            preparedStatement.setInt(7,p4);
            preparedStatement.setInt(8,p5);
            preparedStatement.setInt(9,p6);
            preparedStatement.setInt(10,p7);
            preparedStatement.setInt(11,p8);
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        } else {
            String query = "UPDATE salvos SET idTime = ? , rodada = ? , p1 = ?,"
                    + "p2 = ? , p3 = ?, p4 = ?, p5 = ?, p6 = ?, p7 = ? , p8 = ? WHERE idTreinador = "+idTreinador;
                
                
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(query);
        
        try {
        
            preparedStatement.setInt(1,idTime);
            preparedStatement.setInt(2,rodada);
            preparedStatement.setInt(3,p1);
            preparedStatement.setInt(4,p2);
            preparedStatement.setInt(5,p3);
            preparedStatement.setInt(6,p4);
            preparedStatement.setInt(7,p5);
            preparedStatement.setInt(8,p6);
            preparedStatement.setInt(9,p7);
            preparedStatement.setInt(10,p8);
            preparedStatement.executeUpdate();
         
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        }
        
        
        
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idTreinador
     */
    public int getIdTreinador() {
        return idTreinador;
    }

    /**
     * @param idTreinador the idTreinador to set
     */
    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
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
     * @return the rodada
     */
    public int getRodada() {
        return rodada;
    }

    /**
     * @param rodada the rodada to set
     */
    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    /**
     * @return the p1
     */
    public int getP1() {
        return p1;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(int p1) {
        this.p1 = p1;
    }

    /**
     * @return the p2
     */
    public int getP2() {
        return p2;
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(int p2) {
        this.p2 = p2;
    }

    /**
     * @return the p3
     */
    public int getP3() {
        return p3;
    }

    /**
     * @param p3 the p3 to set
     */
    public void setP3(int p3) {
        this.p3 = p3;
    }

    /**
     * @return the p4
     */
    public int getP4() {
        return p4;
    }

    /**
     * @param p4 the p4 to set
     */
    public void setP4(int p4) {
        this.p4 = p4;
    }

    /**
     * @return the p5
     */
    public int getP5() {
        return p5;
    }

    /**
     * @param p5 the p5 to set
     */
    public void setP5(int p5) {
        this.p5 = p5;
    }

    /**
     * @return the p6
     */
    public int getP6() {
        return p6;
    }

    /**
     * @param p6 the p6 to set
     */
    public void setP6(int p6) {
        this.p6 = p6;
    }

    /**
     * @return the p7
     */
    public int getP7() {
        return p7;
    }

    /**
     * @param p7 the p7 to set
     */
    public void setP7(int p7) {
        this.p7 = p7;
    }

    /**
     * @return the p8
     */
    public int getP8() {
        return p8;
    }

    /**
     * @param p8 the p8 to set
     */
    public void setP8(int p8) {
        this.p8 = p8;
    }
}

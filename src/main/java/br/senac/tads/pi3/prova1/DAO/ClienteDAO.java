/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.prova1.DAO;

import ConexaoBanco.ConexaoBanco;
import br.senac.tads.pi3.prova1.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcelo.smoraes2
 */
public class ClienteDAO {

    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static boolean retorno = false;
    private static Connection con = ConexaoBanco.getConnection();

    

    public static boolean create(Cliente cliente) throws SQLException {
        try {
            con = ConexaoBanco.getConnection();
            String query = "INSERT INTO clientebd (NOME, DATA_NASCIMENTO) VALUES(?,?)";

            ps = con.prepareStatement(query);
            ps.setString(1, cliente.getNomeCompleto());
            ps.setLocalDate(2, new java.sql.LocalDate(cliente.getDataNascimento().getTime()));

            int updatedlines = ps.executeUpdate();

            retorno = updatedlines > 0;
            con.close();
            return retorno;

        } catch (SQLException ex) {
            printSQLException(ex);
            con.close();
        }
        con.close();
        return false;
    }
    
    public static ArrayList<Cliente> read() {
        ArrayList<Cliente> Clientes = new ArrayList<>();
        try {
            con = ConexaoBanco.getConnection();
            String conexao = "SELECT * FROM clientebd";

            ps = con.prepareStatement(conexao);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("ID"));
                    cliente.setNomeCompleto(rs.getString("NOME"));
                    cliente.setDataNascimento((LocalDate) rs.getObject("DATA_NASCIMENTO"));
                    Clientes.add(cliente);
                }
            }
            return Clientes;
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return null;
    }

    public static List<Cliente> findByName(String nome) throws SQLException {
        try {
            con = ConexaoBanco.getConnection();
            String query = "SELECT * FROM clientebd WHERE NOME LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + nome + "%");

            rs = ps.executeQuery();

            List<Cliente> clientList = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    Cliente client = new Cliente();
                    client.setId(rs.getInt("ID"));
                    client.setNomeCompleto(rs.getString("NOME"));
                    client.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                    
                    clientList.add(client);
                }
            }
            con.close();
            return clientList;
        } catch (SQLException ex) {
            printSQLException(ex);
            con.close();
        }
        con.close();
        return null;
    }

    public static Cliente findBydId(int id) throws SQLException {
        try {
            con = ConexaoBanco.getConnection();
            String query = "SELECT * FROM clientebd WHERE ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            Cliente client = null;
            while (rs.next()) {
                client = new Cliente();
                client.setId(rs.getInt("ID"));
                client.setNomeCompleto(rs.getString("NOME"));
                client.setDataNascimento(rs.getLocalDate("DATA_NASCIMENTO"));
                
            }
            con.close();
            return client;

        } catch (SQLException ex) {
            printSQLException(ex);
            con.close();
        }
        con.close();
        return null;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteController;

import br.senac.tads.pi3.prova1.Cliente;
import br.senac.tads.pi3.prova1.DAO.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelopc
 */
public class ClienteController {

    public static boolean create(String nomeCompleto, Date dataNascimento) throws SQLException {
        Cliente cliente = new Cliente(nomeCompleto, dataNascimento);
        return ClienteDAO.create(cliente);

    }

    public static ArrayList<Cliente> read()  {
        return ClienteDAO.read();
    }
    
    public static Cliente findById(int id) throws Exception {
        return ClienteDAO.findBydId(id);
        
    }

    public static List<Cliente> findByName(String nomeCompleto) throws SQLException {
        List<Cliente> clientList = ClienteDAO.findByName(nomeCompleto);
        if (clientList == null) {
            clientList = new ArrayList<>();
            return clientList;
        }
        
        return clientList;

    }
}

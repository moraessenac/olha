/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteSERVLET;

import br.senac.tads.pi3.prova1.Cliente;
import br.senac.tads.pi3.prova1.DAO.ClienteDAO;
import ClienteController.ClienteController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcelo.smoraes2
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/client/*"})
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action;
        if (request.getPathInfo() == null) {
            action = request.getServletPath();
        } else {
            action = request.getPathInfo();
        }
// Give url paths by servletPath or getPathInfo depending on situation
        try {
            switch (action) {
                case "/client":
                    read(request, response);
                    break;

                case "/new":
                    form(request, response);
                    break;

                case "/create":
                    create(request, response);
                    break;

                //               case "/delete":
                //                 delete(request, response);
                //               break;
                //         case "/edit":
                //           formEdit(request, response);
                //          break;
                //              case "/update":
                //                update(request, response);
                //              break;
                //            case "/searchClient":
                //              searchClient(request, response);
                //            break;
                default:
                    read(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);

    }

    private void form(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = "./Client/ClientCreate.jsp";
        request.setAttribute("path", path);
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/IndexJSP.jsp");
        dispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {

        String nomeCompleto = request.getParameter("nomeCompleto");
        String dataNascimentoString = request.getParameter("dataNascimento");

        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formato.parse(dataNascimentoString);

        if (nomeCompleto == null || dataNascimento == null) {
            request.setAttribute("errorCreateClient", "Não é possível finalizar essa ação com campos vazios!");
            response.sendRedirect(request.getContextPath() + "/client/new");
        } else {
            if (ClienteController.create(nomeCompleto, dataNascimento)) {

            } else {
                response.sendRedirect(request.getContextPath() + "/client/new");
            }
        }
    }

    public void read(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            String path = "./Client/ClientList.jsp";
            request.setAttribute("path", path);
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher(
                            "/WEB-INF/IndexJSP.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import general.LucruBd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

public class Login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = "";
        String password = "";
        
        try {
            username = request.getParameter("username");
            password = request.getParameter("password");
            System.out.println("-----Login-----");
            System.out.println("username: " + username);
            System.out.println("password: " + password);

            boolean exista = login(username, password);

            if (exista) {
                JSONObject obj = new JSONObject();
                
                //obj.put("cheie", "valoare");
                obj.put("id", "1");
                obj.put("username", username);
                obj.put("password", password);
                
                System.out.println("login reusit");
                
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(obj.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                System.out.println("login esuat");
            }
        } catch (Exception e) {

        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Login</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    public boolean login(String userName, String parola) {
        LucruBd lucruBd = new LucruBd();
        lucruBd.getConnection();

        String query = "select * from users where username='" + userName+"'";
        System.out.println("query: "+query);

        try {
            Statement stmt = lucruBd.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("NU exista userul");
                rs.close();
                stmt.close();
                return false;
            } else {
                System.out.println("parola introdusa: "+parola);
                System.out.println("parola bd: "+rs.getString(3));
                if (parola.equals(rs.getString(3))) {
                    System.out.println("Utilizatorul exista si are parola introdusa");
                    rs.close();
                    stmt.close();
                    return true;
                } else {
                    rs.close();
                    stmt.close();
                    return false;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}


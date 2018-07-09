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
import javax.servlet.http.HttpSession;
import org.json.*;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
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
                
                //Salvam sesiunea
                HttpSession session = request.getSession(true);
                session.setAttribute("user", obj);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(obj.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                System.out.println("login esuat");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }

    public boolean login(String userName, String parola) {
        LucruBd lucruBd = new LucruBd();

        try {
            PreparedStatement ps = null;
            String query = "select * from users where username = ? and password = ?";
            ps = lucruBd.getConnection().prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, parola);
            ResultSet rs = ps.executeQuery();
            Boolean utilizatorExista = rs.next();
            //inchidem conexiunile
            rs.close();
            ps.close();

            return utilizatorExista;
        } catch (Exception sqle) {
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

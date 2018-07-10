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
                salveazaSesiunea(request, username, password);

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
            boolean utilizatorExista = rs.next();
            //inchidem conexiunile
            rs.close();
            ps.close();

            return utilizatorExista;
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }

        return true;
    }

    public void salveazaSesiunea(HttpServletRequest request, String username, String password) {
        //Salvam sesiunea
        HttpSession session = request.getSession(true);
        int id = 0;

        try {
            LucruBd lucruBd = new LucruBd();
            PreparedStatement ps = null;
            String query = "select users.id from users where username = ? and password = ?";
            ps = lucruBd.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("id", id);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
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

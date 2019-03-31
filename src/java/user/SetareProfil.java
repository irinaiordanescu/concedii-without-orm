/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import general.LucruBd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class SetareProfil extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

        JSONObject utilizatori = new JSONObject();
        String id = (String) request.getSession().getAttribute("id");
        if (id == null) {
            return;
        }
        try {
            String query = "SELECT users.username, tip_angajat.prioritate,tip_angajat.tip_angajat,departament.denumire, users.este_admin FROM users JOIN tip_angajat ON users.id_tip_angajat=tip_angajat.id JOIN departament ON users.id_departament=departament.id WHERE users.id = ?";

            PreparedStatement pst = LucruBd.getConnection().prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                JSONObject utilizator = new JSONObject();

                String username = rs.getString(1);
                utilizator.put("username", username);

                String prioritate = rs.getString(2);
                utilizator.put("prioritate", prioritate);

                String tip_angajat = rs.getString(3);
                utilizator.put("tip_angajat", tip_angajat);

                String departament = rs.getString(4);
                utilizator.put("departament", departament);

                String este_admin = rs.getString(5);
                utilizator.put("este_admin", este_admin);

                utilizator.put("id", id);

                utilizatori.put("utilizator", utilizator);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(utilizatori.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        String id = (String) request.getSession().getAttribute("id");
        if (id == null) {
            return;
        }

        String username = null;
        String password = null;

        try {
            username = request.getParameter("username");
            password = request.getParameter("password");
     
            LucruBd dataBase = new LucruBd();
            dataBase.getConnection();
            String query = "";
            if (password == null || password.length() == 0) {
                query = "UPDATE users SET username = ? WHERE id = ?";
                PreparedStatement pst = LucruBd.conn.prepareStatement(query);

                pst.setString(1, username);
                pst.setString(2, id);
                pst.executeUpdate();
            } else {
                query = "UPDATE users SET username = ?, password = ? WHERE id = ?";
                PreparedStatement pst = LucruBd.conn.prepareStatement(query);

                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, id);
                pst.executeUpdate();
            }

            request.getSession(true).setAttribute("username", username);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

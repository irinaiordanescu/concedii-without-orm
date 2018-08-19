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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Leahu Cristian
 */
public class Utilizatori extends HttpServlet {

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

        String esteAdmin = (String) request.getSession().getAttribute("este_admin");

        if (esteAdmin == null || esteAdmin.equals("0")) {
            response.setStatus(404);
            return;
        }

        JSONObject utilizatori = new JSONObject();

        try {
            String query = "SELECT users.username, users.prioritate,tip_angajat.tip_angajat,departament.denumire, users.este_admin, users.id FROM users JOIN tip_angajat ON users.id_tip_angajat=tip_angajat.id JOIN departament ON users.id_departament=departament.id";

            Statement pst = LucruBd.getConnection().createStatement();
            ResultSet rs = pst.executeQuery(query);

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

                String id = rs.getString(6);
                utilizator.put("id", id);

                utilizatori.append("utilizatori", utilizator);
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

        String esteAdmin = (String) request.getSession().getAttribute("este_admin");

        if (esteAdmin == null || esteAdmin.equals("0")) {
            response.setStatus(404);
            return;
        }
        
        String type = request.getParameter("type");
        
        if (type != null){
            if (type.equals("DELETE")){
                doDelete(request, response);
                return;
            }
            
        }

        String username = null;
        String parola = null;
        String prioritate = null;
        String departament = null;
        String tipangajat = null;
        String admin = null;

        try {
            username = request.getParameter("username");
            parola = request.getParameter("password");
            prioritate = request.getParameter("prioritate");
            departament = request.getParameter("departament");
            tipangajat = request.getParameter("tipAngajat");
            admin = request.getParameter("esteAdmin").equals("true") ? "1" : "0";

            LucruBd dataBase = new LucruBd();
            dataBase.getConnection();
            String query = "insert into users(username, password, prioritate, id_departament, id_tip_angajat, este_admin)values(?,?,?,?,?,?)";
            PreparedStatement pst = LucruBd.conn.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, parola);
            pst.setString(3, prioritate);
            pst.setString(4, departament);
            pst.setString(5, tipangajat);
            pst.setString(6, admin);
            pst.executeUpdate();
            System.out.println("query: " + query);
            System.out.println("s-a realizat cu succes");
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String esteAdmin = (String) request.getSession().getAttribute("este_admin");

        if (esteAdmin == null || esteAdmin.equals("0")) {
            response.setStatus(404);
            return;
        }

        try {
            String id = request.getParameter("id");

            LucruBd dataBase = new LucruBd();
            dataBase.getConnection();
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement pst = LucruBd.conn.prepareStatement(query);

            pst.setString(1, id);
            pst.executeUpdate();
            System.out.println("query: " + query);
            System.out.println("s-a realizat cu succes");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

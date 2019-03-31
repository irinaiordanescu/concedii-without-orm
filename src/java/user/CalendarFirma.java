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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class CalendarFirma extends HttpServlet {
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
    
    //afiseaza toate concediile tuturor angajatilor crae sunt in acelasi departament in funtie de gradul lor
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String prioritate = null;
        JSONObject json = new JSONObject();

        try {
            prioritate = (String) request.getSession().getAttribute("prioritate");
            
            if(prioritate == null){
                return;
            }
            
            String id_departament = (String) request.getSession().getAttribute("id_departament");
            //selectez toti angajatii care sunt in acelasi departament si afisez concediile doar daca sunt sef adjunct si sef
            String query = "SELECT users.username, prima_zi_concediu, ultima_zi_concediu FROM formular_concediu,users WHERE formular_concediu.user_id = users.id AND users.id IN (SELECT users.id FROM users,tip_angajat WHERE users.id_tip_angajat =tip_angajat.id AND  users.id_departament = ? AND tip_angajat.prioritate <= ?)";
            PreparedStatement pst = LucruBd.getConnection().prepareStatement(query);
            pst.setString(1, id_departament);
            pst.setString(2, prioritate);
            ResultSet rs = pst.executeQuery();

            //adaug concediile in lista de concedii
            while (rs.next()) {
                List<String> concedii = new ArrayList<String>();
                JSONObject newJson = new JSONObject();
                concedii.add(rs.getString(2));
                concedii.add(rs.getString(3));
                newJson.put(rs.getString(1), concedii.toArray());
                json.append("concedii", newJson.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
        response.setStatus(HttpServletResponse.SC_OK);
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

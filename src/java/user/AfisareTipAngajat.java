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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class AfisareTipAngajat extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        processRequest(request, response);
        String tipAngajati = null;
        JSONObject json = new JSONObject();
        List<String> usernames = new ArrayList<String>();
        
        try {
            tipAngajati = request.getParameter("tipAngajat");
            String query = "";
            
            if (tipAngajati.equals("toti angajatii")) {
                tipAngajati = "%";
            }

            query = "select users.username from users join tip_angajat on users.id_tip_angajat = tip_angajat.id where tip_angajat.tip_angajat LIKE ?";
            PreparedStatement pst = LucruBd.getConnection().prepareStatement(query);
            pst.setString(1, tipAngajati);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString(1));
            }
            
            json.put("usernames", usernames.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{success: false}");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }  
}
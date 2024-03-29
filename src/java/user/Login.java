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
import java.util.logging.Level;
import java.util.logging.Logger;
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

        String username = "";
        String password = "";

        try {
            username = request.getParameter("username");
            password = request.getParameter("password");
            System.out.println("-----Login-----");
            System.out.println("username: " + username);
            System.out.println("password: " + password);

            //cu functia login verific ca in BD daca exista username si password
            //daca exista returneaza true
            boolean exista = login(username, password); 

            if (exista) {
                //daca exista user si parola introduse apeleaza fct getUserData
                JSONObject user = getUserData(username, password);
                //deschid o sesiune 
                HttpSession session = request.getSession(true);
                //pt fiecare atribut returnat de fct getUserData, il salvez in sesiune
                for (String key : user.keySet()) {
                    session.setAttribute(key, user.get(key));
                }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(user.toString());
                response.setStatus(HttpServletResponse.SC_OK); //daca OK-ul este trimis se apeleaza codul din succes din paginaLogin.js, daca nu se apeleaza codul din error
                System.out.println("login reusit");
            } else {
                System.out.println("login esuat");
            }
        } catch (Exception e) {
            
        }
    }

    public boolean login(String userName, String parola) {
        //creez o noua conexiune cu BD
        LucruBd lucruBd = new LucruBd();
        lucruBd.getConnection();

        String query = "select * from users where username='" + userName + "'";
        System.out.println("query: " + query);

        try {
            //creez un query pt a putea apela comanda de la linia 66
            Statement stmt = lucruBd.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                //daca nu exista user, se inchide conexiunea
                System.out.println("NU exista userul");
                rs.close();
                stmt.close();
                return false;
            } else {
                System.out.println("parola introdusa: " + parola);
                System.out.println("parola bd: " + rs.getString(3)); //rs.getString(3) = val parolei din BD
                //verifica daca coincid parolele
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

    //fct care returneaza un obiect cu toate val din BD
    public JSONObject getUserData(String username, String password) {
        LucruBd lucruBd = new LucruBd();
        JSONObject user = new JSONObject();
        try {
            String query = "select users.id,tip_angajat.prioritate,id_departament,id_tip_angajat,este_admin from users JOIN tip_angajat ON users.id_tip_angajat=tip_angajat.id WHERE users.username = ? and users.password = ?";
            PreparedStatement pst = LucruBd.getConnection().prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user.put("id", rs.getString(1));
                user.put("prioritate", rs.getString(2));
                user.put("id_departament", rs.getString(3));
                user.put("id_tip_angajat", rs.getString(4));
                user.put("este_admin", rs.getString(5));
                user.put("username", username);
                user.put("password", password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
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

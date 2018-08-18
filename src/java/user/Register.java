/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Register extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = null;
        String password = null;
        String checkpassword = null;
        String prioritate = null;
        String departament = null;
        String tipangajat = null;
        
        try {
            username = request.getParameter("username");
            password = request.getParameter("password");
            checkpassword = request.getParameter("checkpassword");
            prioritate = request.getParameter("prioritate");
            departament = request.getParameter("departament");
            tipangajat = request.getParameter("tipangajat");
            
            System.out.println("-----Reg-----");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println("checkpassword: " + checkpassword);
            System.out.println("prioritate: " + prioritate);
            System.out.println("departament: " + departament);
            System.out.println("tipangajat: " + tipangajat);
            
            boolean exista = reg(username, password, checkpassword, prioritate, departament, tipangajat);

            if(exista) {
                JSONObject obj1 = new JSONObject();

                obj1.put("username", username);
                obj1.put("password", password);
                obj1.put("checkpassword", checkpassword);
                obj1.put("prioritate", prioritate);
                obj1.put("departament", departament);
                obj1.put("tipangajat", tipangajat);
                
                System.out.println("reg reusit");
                
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(obj1.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                System.out.println("reg esuat");
            }
        } catch (Exception e) {
            
        }
    }

    public boolean reg(String userName, String parola, String checkpassword, String prioritate, String departament, String tipangajat) {
        try {
            if(userName.equals("") || parola.equals("")){ 
                return false;
            }
            
            LucruBd dataBase = new LucruBd();
            dataBase.getConnection();
            
            String query = "select * from users where username='" + userName+"'";
            System.out.println("query: "+query);

            Statement stmt = dataBase.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("exista userul");
                rs.close();
                stmt.close();
                return false;
            } 
            else if(parola.equals(checkpassword)){
                query = "select prioritate from tip_angajat where id = '" + tipangajat +"'";
                ResultSet rs1 = stmt.executeQuery(query);
                rs1.next();
                String x = rs1.getString("prioritate");
                
                query = "insert into users(username, password, prioritate, id_departament, id_tip_angajat)values(?,?,?,?,?)";
                PreparedStatement pst = LucruBd.conn.prepareStatement(query);

                pst.setString(1, userName);
                pst.setString(2, parola);
                pst.setString(3, x);
                pst.setString(4, departament);
                pst.setString(5, tipangajat);
                pst.executeUpdate();
                System.out.println("query: " + query);
                System.out.println("s-a realizat cu succes");
            } else {
                System.out.println("parolele nu coincid");
                return false;
            }
        } catch (Exception e) {
            System.out.println("nu s-a realizat" + e);
            return false;
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

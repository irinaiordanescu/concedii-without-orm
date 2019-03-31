/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.sql.Connection;
import java.sql.DriverManager;

//returneaza conexiunea cu baza de date
public class LucruBd {
    public static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            if (conn == null) { // Pe principiul Singleton, daca e null, o initiez doar o singura data
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/firma", "root", "mysqlpass");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

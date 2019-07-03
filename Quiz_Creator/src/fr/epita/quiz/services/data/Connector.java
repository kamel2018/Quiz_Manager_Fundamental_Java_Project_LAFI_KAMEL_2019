/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.epita.quiz.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import oracle.jdbc.OracleDriver;

/**
 * @author lafik
 *
 */

public class Connector {
     public static java.sql.Connection connect() {
        java.sql.Connection c=null;
       try {
//            Class.forName("com.mysql.jdbc.Driver");
//            c=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","dbpass");
             DriverManager.registerDriver(new OracleDriver());
           
            c=DriverManager.getConnection("jdbc:oracle:thin:LAFI_KAMEL/123456@localhost:1522:XE");
           
       }
       catch(Exception e){
           System.out.println("cant connect");
       }
       return c;
    }
}

package fr.uvsq._2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {
    
    public static void initBDD() {
        Connection conect = null;
        try {
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            String personelTABLE = "CREATE TABLE Personnel("
                    + "ID int,"
                    + "Nom varchar(30),"
                    + "Prenom varchar(30),"
                    + "Fonctions varchar(100),"
                    + "DateNaissance date,"
                    + "PRIMARY KEY (ID)"
                    + ")";
            String telephoneTABLE = "CREATE TABLE Telephone("
                    + "IDpersonnel int,"
                    + "telephone varchar(30),"
                    + "PRIMARY KEY (telephone),"
                    + "FOREIGN KEY (IDpersonnel) REFERENCES Personnel(ID)"
                    + ")";
            Statement stmt = conect.createStatement();
            try {
                stmt.execute(personelTABLE);
            } catch (SQLException e) { }
            try {
                stmt.execute(telephoneTABLE);
            } catch (SQLException e) { }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }
    
    public static void delBDD() {
        Connection conect = null;
        try {
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            try {
                Statement stmt = conect.createStatement();
                stmt.execute("DROP TABLE Telephone");
                stmt.execute("DROP TABLE Personnel");
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conect != null) {
                try {
                    System.out.println("close");
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }
}

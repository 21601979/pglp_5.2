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
            String groupeTABLE = "CREATE TABLE Groupe("
                    + "ID int,"
                    + "PRIMARY KEY (ID)"
                    +")";
            String contientGroupeTABLE = "CREATE TABLE ContientGroupe("
                    + "IDgroupe int,"
                    + "IDgroupecontenu int,"
                    + "PRIMARY KEY (IDgroupe,IDgroupecontenu),"
                    + "FOREIGN KEY (IDgroupe) REFERENCES Groupe(ID),"
                    + "FOREIGN KEY (IDgroupecontenu) REFERENCES Groupe(ID)"
                    + ")";
            String contientPersonnelTABLE = "CREATE TABLE ContientPersonnel("
                    + "IDgroupe int,"
                    + "IDpersonnel int,"
                    + "PRIMARY KEY (IDpersonnel,IDgroupe),"
                    + "FOREIGN KEY (IDpersonnel) REFERENCES Personnel(ID),"
                    + "FOREIGN KEY (IDgroupe) REFERENCES Groupe(ID)"
                    + ")";
            Statement stmt = conect.createStatement();
            try {
                stmt.execute(personelTABLE);
            } catch (SQLException e) { }
            try {
                stmt.execute(telephoneTABLE);
            } catch (SQLException e) { }
            try {
                stmt.execute(groupeTABLE);
            } catch (SQLException e) {e.printStackTrace(); }
            try {
                stmt.execute(contientGroupeTABLE);
            } catch (SQLException e) {e.printStackTrace(); }
            try {
                stmt.execute(contientPersonnelTABLE);
            } catch (SQLException e) {e.printStackTrace(); }
 
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
                stmt.execute("DROP TABLE ContientGroupe");
                stmt.execute("DROP TABLE ContientPersonnel");
                stmt.execute("DROP TABLE Groupe");
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

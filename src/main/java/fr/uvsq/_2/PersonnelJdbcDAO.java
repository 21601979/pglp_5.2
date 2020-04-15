package fr.uvsq._2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.uvsq._2.Personnel.builder;

public class PersonnelJdbcDAO extends DAO<Personnel> {

    @Override
    public void create(Personnel obj) throws ExisteDejaException {
        Connection conect = null;
            try {
                String addPersonnel = "INSERT INTO Personnel (ID,Nom,Prenom,Fonctions,DateNaissance) VALUES(?,?,?,?,?)";
                conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(addPersonnel);
                prep.setInt(1, obj.getID());
                prep.setString(2, obj.getNom());
                prep.setString(3, obj.getPrenom());
                prep.setString(4, obj.getFonctions());
                prep.setDate(5, new Date(0, 0, 0));
                int result = prep.executeUpdate();
                assert result == 1;
                
            } catch (SQLException e) {
                // TODO Auto-generated catch block
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

    @Override
    public Personnel find(String iD) {
        Connection conect = null;
        try {
            String findPersonnel = "SELECT * FROM Personnel WHERE ID = ?";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(findPersonnel,ResultSet.TYPE_SCROLL_SENSITIVE);
            prep.setInt(1, Integer.parseInt(iD));
            ResultSet result = prep.executeQuery();
            if(result.first()) {
                builder b = new builder(result.getString("Nom"),result.getString("Prenom"),LocalDate.of(result.getDate("DateNaissance").getYear(), result.getDate("DateNaissance").getMonth(), result.getDate("DateNaissance").getDay()),result.getInt("Id"));
                System.out.print(b.build().toString());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(conect != null) {
                try {
                    System.out.println("close");
                    conect.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }

    @Override
    public void delete(Personnel obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Personnel update(Personnel obj) {
        // TODO Auto-generated method stub
        return null;
    }

}

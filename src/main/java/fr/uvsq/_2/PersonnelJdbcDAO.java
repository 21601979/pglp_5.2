package fr.uvsq._2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                Date d = Date.valueOf(obj.getDate());
                prep.setDate(5, d);
                int result = prep.executeUpdate();
                assert result == 1;
                for(int i = 0; i<obj.getTelephone().size();i++) {
                    this.addNum(obj.getTelephone().get(i),obj.getID());
                }
                
            } catch (Exception e) {
                for(int i = 0; i<obj.getTelephone().size();i++) {
                    this.dellNum(obj.getTelephone().get(i));
                }
                delete(obj);
                throw new ExisteDejaException();
            } finally {
                if(conect != null) {
                    try {
                        conect.close();
                    } catch (SQLException e) { }
                }
            }
    }
    
    private void addNum(final String num, final int iD) throws ExisteDejaException
    {
        Connection conect = null;
        try {
            String addPersonnel = "INSERT INTO Telephone (IDpersonnel,telephone) VALUES(?,?)";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(addPersonnel);
            prep.setInt(1, iD);
            prep.setString(2, num);
            int result = prep.executeUpdate();
            assert result == 1;
            
        } catch (SQLException e) {
            throw new ExisteDejaException();
        } finally {
            if(conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }
    
    private void dellNum(final String num)
    {
        Connection conect = null;
        try {
            String addPersonnel = "DELETE FROM Telephone WHERE telephone = ?";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(addPersonnel);
            prep.setString(1, num);
            int result = prep.executeUpdate();
            assert result == 1;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(conect != null) {
                try {
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
            String getTelephone = "SELECT telephone FROM Telephone WHERE IDpersonnel = ?";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(findPersonnel);
            PreparedStatement preptel = conect.prepareStatement(getTelephone);
            prep.setInt(1, Integer.parseInt(iD));
            preptel.setInt(1, Integer.parseInt(iD));
            ResultSet result = prep.executeQuery();
            ResultSet resulttel = preptel.executeQuery();
            if(result.next()) {
                builder b = new builder(result.getString("Nom"),result.getString("Prenom"),result.getDate("DateNaissance").toLocalDate(),result.getInt("Id"));
                while(resulttel.next()) {
                    b.settelephone(resulttel.getString("telephone"));
                }
                return b.build();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }

    @Override
    public void delete(Personnel obj) {
        Connection conect = null;
        try {
            String findPersonnel = "SELECT * FROM Personnel WHERE ID = ?";
            String dellPersonnel = "DELETE FROM Personnel WHERE ID = ?";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(findPersonnel);
            for(int i = 0; i<obj.getTelephone().size();i++) {
                this.dellNum(obj.getTelephone().get(i));
            }
            PreparedStatement prepdell = conect.prepareStatement(dellPersonnel);
            prep.setInt(1, obj.getID());
            prepdell.setInt(1, obj.getID());
            ResultSet result = prep.executeQuery();
            if(result.next()) {
                prepdell.executeUpdate();
            }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
            if(conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }

    @Override
    public Personnel update(Personnel obj) {
        Personnel test = find(obj.getID()+"");
        if(test != null){
            delete(obj);
            try {
                create(obj);
            } catch (ExisteDejaException e) {
            }
        }
        return null;
    }

}

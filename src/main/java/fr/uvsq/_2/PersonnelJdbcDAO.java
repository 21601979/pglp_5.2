package fr.uvsq._2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.uvsq._2.Personnel.builder;

/**
 * DAO qui gère les personnel dans la base de données.
 * @author Tanguy
 */
public class PersonnelJdbcDAO extends DAO<Personnel> {
    /**
     * methode create pour un personel dans la base de données.
     */
    @Override
    public void create(final Personnel obj) throws ExisteDejaException {
        Connection conect = null;
            try {
                String addPersonnel = "INSERT INTO Personnel "
                        + "(ID,Nom,Prenom,Fonctions,DateNaissance)"
                        + " VALUES(?,?,?,?,?)";
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(addPersonnel);
                prep.setInt(1, obj.getID());
                prep.setString(2, obj.getNom());
                final int quatre = 4;
                final int trois = 3;
                prep.setString(trois, obj.getPrenom());
                prep.setString(quatre, obj.getFonctions());
                Date d = Date.valueOf(obj.getDate());
                final int cinq = 5;
                prep.setDate(cinq, d);
                int result = prep.executeUpdate();
                assert result == 1;
                for (int i = 0; i < obj.getTelephone().size(); i++) {
                    this.addNum(obj.getTelephone().get(i), obj.getID());
                }

            } catch (Exception e) {
                for (int i = 0; i < obj.getTelephone().size(); i++) {
                    this.dellNum(obj.getTelephone().get(i));
                }
                delete(obj);
                throw new ExisteDejaException();
            } finally {
                if (conect != null) {
                    try {
                        conect.close();
                    } catch (SQLException e) { }
                }
            }
    }

    /**
     * methode qui ajoute un numéro dans la base de données.
     * @param num numéro a ajouter
     * @param iD id du personnel a qui appartient le numéro
     * @throws ExisteDejaException est renvoyé si le numéro existe déja
     */
    private void addNum(final String num, final int iD)
            throws ExisteDejaException {
        Connection conect = null;
        try {
            String addPersonnel = "INSERT INTO Telephone "
                    + "(IDpersonnel,telephone) VALUES(?,?)";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(addPersonnel);
            prep.setInt(1, iD);
            prep.setString(2, num);
            int result = prep.executeUpdate();
            assert result == 1;

        } catch (SQLException e) {
            throw new ExisteDejaException();
        } finally {
            if (conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }

    /**
     * methode qui suprime un numéro dans la base de données.
     * @param num numéro a supprimer
     */
    private void dellNum(final String num) {
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
            if (conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }

    /**
     * methode find pour un personnel dans la base de données.
     */
    @Override
    public Personnel find(final String iD) {
        Connection conect = null;
        try {
            String findPersonnel = "SELECT * "
                    + "FROM Personnel WHERE ID = ?";
            String getTelephone = "SELECT telephone "
                    + "FROM Telephone WHERE IDpersonnel = ?";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(findPersonnel);
            PreparedStatement preptel = conect.prepareStatement(getTelephone);
            prep.setInt(1, Integer.parseInt(iD));
            preptel.setInt(1, Integer.parseInt(iD));
            ResultSet result = prep.executeQuery();
            ResultSet resulttel = preptel.executeQuery();
            if (result.next()) {
                builder b = new builder(result.getString("Nom"),
                        result.getString("Prenom"),
                        result.getDate("DateNaissance").toLocalDate(),
                        result.getInt("Id"));
                while (resulttel.next()) {
                    b.settelephone(resulttel.getString("telephone"));
                }
                return b.build();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }

    /**
     * methode delete pour un personnel dans la base de données.
     */
    @Override
    public void delete(final Personnel obj) {
        Connection conect = null;
        try {
            String findPersonnel = "SELECT * FROM Personnel WHERE ID = ?";
            String dellPersonnel = "DELETE FROM Personnel WHERE ID = ?";
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(findPersonnel);
            for (int i = 0; i < obj.getTelephone().size(); i++) {
                this.dellNum(obj.getTelephone().get(i));
            }
            PreparedStatement prepdell = conect.prepareStatement(dellPersonnel);
            prep.setInt(1, obj.getID());
            prepdell.setInt(1, obj.getID());
            ResultSet result = prep.executeQuery();
            if (result.next()) {
                prepdell.executeUpdate();
            }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
            if (conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }
    /**
     * methode update pour un personnel dans la base de données.
     */
    @Override
    public Personnel update(final Personnel obj) {
        Personnel test = find(obj.getID() + "");
        if (test != null) {
            delete(obj);
            try {
                create(obj);
            } catch (ExisteDejaException e) {
            }
        }
        return null;
    }

}

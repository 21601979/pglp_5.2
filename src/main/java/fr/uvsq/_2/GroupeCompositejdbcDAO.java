package fr.uvsq._2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import fr.uvsq._2.Personnel.builder;

public class GroupeCompositejdbcDAO extends DAO<GroupeComposite>{

    @Override
    public void create(GroupeComposite obj) throws ExisteDejaException {
        Connection conect = null;
        try {
            String addGroupe = "INSERT INTO Groupe (ID) VALUES(?)";
            String addGroupecompo = "INSERT INTO ContientGroupe (IDgroupe,IDgroupecontenu) VALUES(?,?)" ;
            String addPersonelcompo = "INSERT INTO ContientPersonnel (IDgroupe,IDpersonnel) VALUES(?,?)" ;
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(addGroupe);
            PreparedStatement prepgroupe = conect.prepareStatement(addGroupecompo);
            PreparedStatement prepcompo = conect.prepareStatement(addPersonelcompo);
            prep.setInt(1, obj.getID());
            prepgroupe.setInt(1, obj.getID());
            prepcompo.setInt(1, obj.getID());
            int result = prep.executeUpdate();
            Iterator<Groupe> it = obj.iterator();
            Object g;
            DAO<GroupeComposite> GC =((DAOFactoryJDBC) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAOtype.JDBC)).getGroupeCompositeDAO();
            DAO<Personnel> P =((DAOFactoryJDBC) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAOtype.JDBC)).getPersonnelDAO();
            while (it.hasNext()) {
                g = it.next();
                if(GroupeComposite.class == g.getClass()) {
                   GC.create((GroupeComposite) g);
                   prepgroupe.setInt(2, ((GroupeComposite) g).getID());
                   prepgroupe.executeUpdate();
                }
                else {
                    P.create((Personnel) g);
                    prepcompo.setInt(2, ((Personnel) g).getID());
                    prepcompo.executeUpdate();
                }
               }
            assert result == 1;
           
        } catch (Exception e) {
            //delete(obj);
            e.printStackTrace();
            throw new ExisteDejaException();
        } finally {
            if(conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }

    @Override
    public GroupeComposite find(String iD) {
        Connection conect = null;
        try {
            DAO<Personnel> P =((DAOFactoryJDBC) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAOtype.JDBC)).getPersonnelDAO();
            String findGroupe = "SELECT * FROM Groupe WHERE ID = ?";
            String findcompoGroupe = "SELECT * FROM ContientGroupe WHERE IDgroupe = ?";
            String findPersonnel = "SELECT * FROM ContientPersonnel WHERE IDgroupe = ?";

            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            PreparedStatement prep = conect.prepareStatement(findGroupe);
            PreparedStatement prepGroupe = conect.prepareStatement(findcompoGroupe);
            PreparedStatement prepPersonnel = conect.prepareStatement(findPersonnel);

            prep.setInt(1, Integer.parseInt(iD));
            prepGroupe.setInt(1, Integer.parseInt(iD));
            prepPersonnel.setInt(1, Integer.parseInt(iD));

            ResultSet result = prep.executeQuery();
            if(result.next()) {
                ResultSet resultGroupe = prepGroupe.executeQuery();
                ResultSet resultPersonnel = prepPersonnel.executeQuery();
                GroupeComposite ret = new GroupeComposite(result.getInt("ID"));
                while(resultGroupe.next()) {
                    ret.add(find(""+resultGroupe.getInt("IDgroupecontenu")));
                }
                while(resultPersonnel.next()) {
                    ret.add(P.find(""+resultPersonnel.getInt("IDpersonnel")));
                }
                return ret;
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
    public void delete(GroupeComposite obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GroupeComposite update(GroupeComposite obj) {
        // TODO Auto-generated method stub
        return null;
    }

}

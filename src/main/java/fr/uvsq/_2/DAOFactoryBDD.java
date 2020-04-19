package fr.uvsq._2;

public class DAOFactoryBDD extends AbstractDAOFactory {
    /**
     * methode qui crée un personnel DAO.
     * @return dao de personnel
     */
    public static DAO<Personnel> getPersonnelDAO() {
        return new PersonnelJdbcDAO();
    }
    /**
     * methode qui crée un GoupeComposite DAO.
     * @return dao DE GroupeComposite
     */
    public static DAO<GroupeComposite> getGroupeCompositeDAO() {
        return null;
        //a modif quand groupecomposite
    }
}

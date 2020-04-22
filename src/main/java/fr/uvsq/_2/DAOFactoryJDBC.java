package fr.uvsq._2;

public class DAOFactoryJDBC extends AbstractDAOFactory {
    /**
     * methode qui crée un personnel DAO.
     * @return dao de personnel
     */
    public DAO<Personnel> getPersonnelDAO() {
        return new PersonnelJdbcDAO();
    }
    /**
     * methode qui crée un GoupeComposite DAO.
     * @return dao DE GroupeComposite
     */
    public DAO<GroupeComposite> getGroupeCompositeDAO() {
        return new GroupeCompositejdbcDAO();
    }
}

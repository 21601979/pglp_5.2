package fr.uvsq._2;
/**
 * factory de DAO.
 * @author Tanguy
 */
public class DAOFactoryserial extends AbstractDAOFactory{
    /**
     * methode qui crée un personnel DAO.
     * @return dao de personnel
     */
    public static DAO<Personnel> getPersonnelDAO() {
        return new PersonnelDAO();
    }
    /**
     * methode qui crée un GoupeComposite DAO.
     * @return dao DE GroupeComposite
     */
    public static DAO<GroupeComposite> getGroupeCompositeDAO() {
        return new GoupeCompositeDAO();
    }
}

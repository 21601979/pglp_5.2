package fr.uvsq._2;

/**
 * classe abstraite qui renvoi une DAO factory.
 */
public abstract class AbstractDAOFactory {
    /**
     * enuméération qui définie les DAO existant.
     */
    public enum DAOtype { SERIAL, JDBC }

    /**
     * methode qui renvoie une DAO factory.
     * @return dao factory ou NULL si le type n'existe pas
     * @param type type du DAO
     */
    public static Object getFactory(final DAOtype type) {
        if (type == DAOtype.SERIAL) {
            return new DAOFactoryserial();
        }
        if (type == DAOtype.JDBC) {
            return new DAOFactoryJDBC();
        }
        return null;
    }

}

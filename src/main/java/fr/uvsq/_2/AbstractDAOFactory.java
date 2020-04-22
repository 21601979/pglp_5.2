package fr.uvsq._2;

public abstract class AbstractDAOFactory {
    public enum DAOtype {SERIAL,JDBC}
    
    //public abstract DAO getGroupeCompositeDAO();
    //public abstract DAO getPersonnelDAO();
    
    public static Object getFactory(DAOtype type) {
        if(type == DAOtype.SERIAL) return new DAOFactoryserial();
        if(type == DAOtype.JDBC) return new DAOFactoryJDBC();
        return null;
    }

}

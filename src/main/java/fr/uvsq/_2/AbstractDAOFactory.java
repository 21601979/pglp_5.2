package fr.uvsq._2;

public class AbstractDAOFactory {
    public enum DAOtype {SERIAL,BDD}
    
    public static AbstractDAOFactory getFactory(DAOtype type) {
        if(type == DAOtype.SERIAL) return new DAOFactoryserial();
        if(type == DAOtype.BDD) return new DAOFactoryBDD();
        return null;
    }

}

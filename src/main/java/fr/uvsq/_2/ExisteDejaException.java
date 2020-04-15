package fr.uvsq._2;
/**
 * exception qui indique que l'id de l'objet existe déja.
 * @author Tanguy
 */
public class ExisteDejaException extends Exception {

    /**
     * id de l'exeption.
     */
    private static final long serialVersionUID = 3931273041226247298L;

    /**
     * exception qui renvoi que l'objet existe deja.
     */
    public ExisteDejaException() {
        super("l'objet existe deja");
    }
}

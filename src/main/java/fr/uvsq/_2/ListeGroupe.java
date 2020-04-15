package fr.uvsq._2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * class qui encapsule une liste.
 */
public class ListeGroupe implements Iterable<Groupe>, Serializable {
    /**
     * num de serialisation.
     */
    private static final long serialVersionUID = -5443695659401435201L;
    /**
     * liste qui contient des groupe ou des personnel.
     */
    private ArrayList<Groupe> l = new ArrayList<Groupe>();

    /**
     * constructeur de l'objet ListeGroupe.
     */
    ListeGroupe() { }

    /**
     * methode qui ajoute un objet Groupe a la liste .
     * @param g groupe
     */
    public void add(final Groupe g) {
        l.add(g);
        }
    /**
     * methode qui renvoi la longueur de la liste.
     * @return int
     */
    public int size() {
        return l.size();
        }
    /**
     * methode qui renvoie un éllement i de la liste .
     * @param i indice
     * @return un groupe d'indice i.
     */
    public Groupe get(final int i) {
        return l.get(i);
        }
    /**
     * methode qui renvoi un itérateur sur la liste.
     * @return iterateur
     */
    public Iterator<Groupe> iterator() {
            return new GroupeIterator(l);
        }
    /**
     * methode qui revoi le string représentant une liste de groupe.
     * @return string
     */
    public String toString() {
        return l.toString();
    }
}

package fr.uvsq._2;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Tanguy
 *class groupe composite.
 */
public class GroupeComposite implements Groupe, Serializable {
    /**
     * num de serialisation.
     */
    private static final long serialVersionUID = -364604849233971037L;
    /**
     * id d'un groupe composite.
     */
    private int iD;
    /**
     * liste de groupe.
     */
    private ListeGroupe l = new ListeGroupe();
    /**
     * constructeur de groupeComposite.
     * @param set id
     */
    public GroupeComposite(final int set) {
        this.iD = set;
    }
    /**
     * methode qui ajoute un groupe composite.
     * @param compo : groupe a ajouter
     */
    public void add(final Groupe compo) {
        getL().add(compo);
        }
    /**
     * methode qui renvoi l'iterateur de groupe composite.
     * @return iterateur
     */
    public Iterator<Groupe> iterator() {
        return getL().iterator();
        }
     /**
      * methode qui affiche un éllement i d'un groupe.
      * @param i artéfacte
      */
    public void affiche(final int i) {
        Iterator<Groupe> it = this.iterator();
        int i2 = i;
        while (it.hasNext()) {
            i2++;
            ((GroupeComposite) it.next()).affiche(i2);
            }
        }
    /**
     * @return liste l
     */
    public ListeGroupe getL() {
        return l;
    }
    /**
     * methode qui test si deux groupes composites sont égaux.
     * @param c groupe composite
     * @return si égalité : true, sinon false
     */
    public boolean equals(final GroupeComposite c) {
        if (this.toString().equals(c.toString())) {
            return true;
        }
        return false;
    }
    /**
     * retour de la chaine de char qui represente un groupe composite.
     * @return string corepondant a un groupe composite
     */
    public String toString() {
        String temp = "";
        Iterator<Groupe> it = this.iterator();
        while (it.hasNext()) {
            temp = temp + it.next().toString();
            }
        return temp;
    }
    /**
     * methode qui permet de récupérer l'id d'un groupe composite.
     * @return id d'un groupe composite
     */
    public int getID() {
        return iD;
    }

}

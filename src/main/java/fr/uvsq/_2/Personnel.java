package fr.uvsq._2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Tanguy
 *class personnel.
 */
final class Personnel  implements Groupe, Serializable {
    /**
     * num de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * nom du personnel.
     */
    private String nom;
    /**
     * prenom du personnel.
     */
    private String prenom;
    /**
     * fonction du personnel.
     */
    private String fonctions;
    /**
     * date de naissance du personnel.
     */
    private LocalDate dateNaissance;
    /**
     * liste des numéros de telephone d'un personnel.
     */
    private ArrayList<String> telephone;

    /**
     * id d'un personel.
     */
    private int iD;

    /**
     * @author Tanguy
     *builder qui permet de construire un objet personnel.
     */
    public static class builder {
        /**
         * nom d'un personnel.
         */
        private final String nom;
        /**
         * prenom d'un personnel.
         */
        private final String prenom;
        /**
         * fonction d'un personnel.
         */
        private String fonctions;
        /**
         * date de naissance d'un personnel.
         */
        private LocalDate dateNaissance;
        /**
         * liste des numéros de telephone d'un personnel.
         */
        private ArrayList<String> telephone;
        /**
         * id d'un personel.
         */
        private int iD;
        /**
         * construction du builder.
         * @param no nom
         * @param pre prenom
         * @param date date de naissance
         * @param iDent id d'un personnel
         */
        builder(final String no, final String pre,
                final LocalDate date, final int iDent) {
            this.nom = no;
            this.prenom = pre;
            this.setDateNaissance(date);
            this.fonctions = "";
            this.telephone = new ArrayList<String>();
            this.iD = iDent;
            }
        /**
         * methode qui ajoute une date de naissance .
         * @param date date de naissance
         */
        private void setDateNaissance(final LocalDate date) {
            this.dateNaissance  = date;
        }

        /**
         * methode qui ajoute une fonction.
         * @param fonction fonction du personnel
         */
        public void setfonction(final String fonction) {
            this.fonctions = fonction;
            }
        /**
         * methode qui ajoute un numéro de telephone.
         * @param tel nume de telephone
         */
        public void settelephone(final String tel) {
            this.telephone.add(tel);
            }
        /**
         * methode qui construit un objet personnel .
         * @return retourne un objet personnel.
         */
        public Personnel build() {
            return new Personnel(this);
            }

        /**
         * @return descritpion de l'objet.
         */
        public String toString() {
            return telephone.toString() + " " + fonctions
                    + " " + dateNaissance.toString() + " "
                    + prenom + " " + nom;
        }
        }
    /**
    * methode qui affiche dans le terminal le nom/prenom .
    * @param i profondeur
    */
    public void affiche(final int i) {
        int j;
        for (j = 0; j < i; j++) {
            System.out.println("-");
            }
        System.out.println(nom  + " " + prenom + "\n");
        }

    /**
     * constructeur de l'objet personnel.
     * @param b builder
     */
    private Personnel(final builder b) {
        this.nom = b.nom;
        this.prenom = b.prenom;
        this.fonctions = b.fonctions;
        this.dateNaissance = b.dateNaissance;
        this.telephone = b.telephone;
        this.iD = b.iD;
        }

    /**
     * @return String
     */
    public String toString() {
        return telephone.toString() + " " + fonctions
                + " " + dateNaissance.toString() + " " + prenom
                + " " + nom;
    }
    /**
     * renvoi l'identifinant d'un personnel.
     * @return ID
     */
    public int getID() {
        return iD;
    }
}

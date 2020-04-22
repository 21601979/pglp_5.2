package fr.uvsq._2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * class groupeCompositeDAO.
 * @author Tanguy
 */
public class GoupeCompositeDAO extends DAO<GroupeComposite> {
    /**
     * methode de sérailisation d'un groupe Composite.
     */
    public void serialize(final GroupeComposite obj, final String file) {
        ObjectOutputStream out = null;
        try {
          final FileOutputStream fichier = new FileOutputStream(file);
          out = new ObjectOutputStream(fichier);
          out.writeObject(obj);
          out.flush();
        } catch (java.io.IOException e) {
          e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * methode de deserialisation d'un groupe composite.
     */
    public GroupeComposite deserialize(final String file) {
        ObjectInputStream in = null;
        GroupeComposite ret = null;
        try {
            final FileInputStream fichier = new FileInputStream(file);
            in = new ObjectInputStream(fichier);
            ret = (GroupeComposite) in.readObject();
            fichier.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        return ret;
    }
    /**
     * methode d'aout de la sérialisation d'un objet.
     */
    @Override
    public void create(final GroupeComposite obj) throws ExisteDejaException {
        int i;
        File repertoire = new File("GroupeComposite");
        String[] liste = repertoire.list();

        for (i = 0; i < liste.length; i++) {
            if (Integer.parseInt(liste[i]) == obj.getID()) {
                throw new ExisteDejaException();
            }
        }
        this.serialize(obj, "GroupeComposite\\" + (obj.getID()));
    }
    /**
     * methode de recherche d'un objet serilisé.
     */
    @Override
    public GroupeComposite find(final String iD) {
        int i;
        File repertoire = new File("GroupeComposite");
        String[] liste = repertoire.list();
        if (liste.length == 0) {
            return null;
        } else {
            for (i = 0; i < liste.length; i++) {
                if (liste[i].equals(iD)) {
                    return this.deserialize("GroupeComposite\\" + liste[i]);
                }
            }
        }
        return null;
    }
    /**
     * methode de supression d'un objet sérialisée.
     */
    @Override
    public void delete(final GroupeComposite obj) {
        if (this.find(obj.getID() + "") != null) {
            File del = new File("GroupeComposite\\" + obj.getID());
            del.delete();
        }
    }
    /**
     * methode d'update d'un objet sérialisé.
     */
    @Override
    public GroupeComposite update(final GroupeComposite obj) {
        GroupeComposite up = this.find(obj.getID() + "");
        if (up != null) {
            this.delete(up);
            try {
                this.create(obj);
            } catch (ExisteDejaException e) {
                return null;
            }
            return obj;
        }
        return null;
    }

}

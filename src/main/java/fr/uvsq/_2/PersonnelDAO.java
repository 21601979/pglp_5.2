package fr.uvsq._2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * class personnel DAO.
 * @author Tanguy
 */
public class PersonnelDAO extends DAO<Personnel> {
    /**
     * methode de sérialisation d'un objet personnel.
     */
    public void serialize(final Personnel obj, final String file) {
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
     * methode de desérialisation d'un objet personnel.
     */
    public Personnel deserialize(final String file) {
        ObjectInputStream in = null;
        Personnel ret = null;
        try {
            final FileInputStream fichier = new FileInputStream(file);
            in = new ObjectInputStream(fichier);
            ret = (Personnel) in.readObject();
            fichier.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        return ret;
    }
    /**
     * methode de sauvegarde d'un objet personnel.
     */
    @Override
    public void create(final Personnel obj) throws ExisteDejaException {
        int i;
        File repertoire = new File("Personnel");
        String[] liste = repertoire.list();
        for (i = 0; i < liste.length; i++) {
            if (Integer.parseInt(liste[i]) == obj.getID()) {
                throw new ExisteDejaException();
            }
        }
        this.serialize(obj, "Personnel\\" + (obj.getID()));
    }
    /**
     * methode de rechercher d'un objet personnel sauvegardé.
     */
    @Override
    public Personnel find(final String iD) {
        int i;
        File repertoire = new File("Personnel");
        String[] liste = repertoire.list();
        if (liste.length == 0) {
            return null;
        } else {
            for (i = 0; i < liste.length; i++) {
                if (liste[i].equals(iD)) {
                    return this.deserialize("Personnel\\" + liste[i]);
                }
            }
        }
        return null;
    }
    /**
     * methode de supression d'un objet sauvegardé.
     */
    @Override
    public void delete(final Personnel obj) {
        if (this.find(obj.getID() + "") != null) {
            File del = new File("Personnel\\" + obj.getID());
            del.delete();
        }
    }
    /**
     * methode d'update d'un objet sauvegardé.
     */
    @Override
    public Personnel update(final Personnel obj) {
        Personnel up = this.find(obj.getID() + "");
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

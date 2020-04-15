package fr.uvsq._2;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import org.junit.Test;

import fr.uvsq._2.Personnel.builder;

public class serialTest {
    @Test
    public void serialPersonelTest() {
        builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
        Personnel p = b.build();
        Personnel p2 = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
          final FileOutputStream fichier = new FileOutputStream("test");
          out = new ObjectOutputStream(fichier);
          out.writeObject(p);
          out.flush();
        } catch (java.io.IOException e) {
          e.printStackTrace(); 
        } 

        try {
            final FileInputStream fichier = new FileInputStream("test");
            in = new ObjectInputStream(fichier);
            p2 = (Personnel) in.readObject();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        assertEquals(p.toString(),p2.toString());
        }

    @Test
    public void serialListeGroupeTest() {
        builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
        Personnel p = b.build();
        ListeGroupe g = new ListeGroupe();
        ListeGroupe g2 = new ListeGroupe();
        g.add(p);
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
          final FileOutputStream fichier = new FileOutputStream("test");
          out = new ObjectOutputStream(fichier);
          out.writeObject(g);
          out.flush();
        } catch (java.io.IOException e) {
          e.printStackTrace();
        } 

        try {
            final FileInputStream fichier = new FileInputStream("test");
            in = new ObjectInputStream(fichier);
            g2 = (ListeGroupe) in.readObject();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        assertEquals(g.toString(),g2.toString());
        }
    
    @Test
    public void serialGroupeCompositeTest() {
        GroupeComposite g = new GroupeComposite(1);  
        GroupeComposite g2 = new GroupeComposite(2);

        builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
        Personnel p = b.build();
        builder b2 = new builder("ma","rio",LocalDate.of(1, 1, 1),1);
        Personnel p2 = b2.build();
        g.add(p);
        g.add(g2);
        g2.add(p2);
        
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
          final FileOutputStream fichier = new FileOutputStream("test");
          out = new ObjectOutputStream(fichier);
          out.writeObject(g);
          out.flush();
        } catch (java.io.IOException e) {
          e.printStackTrace();
        } 

        try {
            final FileInputStream fichier = new FileInputStream("test");
            in = new ObjectInputStream(fichier);
            g2 = (GroupeComposite) in.readObject();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        assertEquals(g.toString(),g2.toString());
        }
}

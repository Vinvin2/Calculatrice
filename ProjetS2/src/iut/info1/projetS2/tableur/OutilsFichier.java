/*
 * OutilsFichier.java               13 mai 2015
 * IUT RODEZ INFO1 2014-2015
 */

package iut.info1.projetS2.tableur;

import iut.info1.projetS2.tableur.action.Commandes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Cette classe contient des m�thodes pour g�rer les fichiers du tableur.
 * Ces m�thodes permettent de :
 *     - enregistrer dans un fichier un tableau � 2 dimensions contenant des 
 *       cha�nes de caract�res.
 *     - lire le contenu d'un fichier suppos� contenir le tableau du tableur
 *       et renvoyer celui-ci sous la forme d'un tableau � 2 dimensions 
 *       contenant des cha�nes de caract�res.
 * @author INFO1
 * @version 1.0
 */
public class OutilsFichier {

    /** Tableur associ�e � la fenetre */
    private Tableur fenetre;
    
    /**
     * Nom du fichier dans lequel est enregistr� le tableau � 2 dimensions
     * contenant les paires "arr�t/ligne".
     */
    public static File nomFichier;
      

    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_PAIRE
     * le tableau contenant les paires "ligne/arr�t". Il s'agit d'un tableau
     * � 2 dimensions contenant des valeurs de type String.
     * @param tableCalcule 
     * @param tableEnDur 
     * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effecu�e
     */
    public static boolean enregistrerTableur(Object[][] tableCalcule,
                                             String[][] tableEnDur) {
        boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi
        
        // cr�ation et ouverture du fichier NOM_FICHIER_PAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                    new FileOutputStream(nomFichier))) {
                       
            // On �crit l'objet argument dans le fichier
            fichier.writeObject(tableCalcule);  
            fichier.writeObject((Object[][])tableEnDur);
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'acc�s au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des paires "arret/ligne".
     * Les paires sont suppos�es �tre pr�sentes dans le fichier de nom 
     * NOM_FICHIER_TABLEUR, sous la forme d'un tableau � 2 dimensions contenant
     * des valeurs de type Object.
     * @return un tableau � 2 dimensions contenant des cha�nes de caract�res
     *         ou bien la valeur null si un probl�me emp�che l'acc�s au fichier
     */
    public Object[][] restaurerPaireLignTableur()  {
        
        // objet tampon dans lequel est plac� l'objet lu dans le fichier  
        Object[][] tampon = null; 
        String[][] tampon2 = null;
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream fichier = new ObjectInputStream(
                    new FileInputStream(nomFichier))) {           
            
            // lecture de l'objet contenu dans le fichier
            tampon = (Object[][]) fichier.readObject();
            ModeleDeTable.setDonnees(tampon);
            tampon2 = (String[][]) fichier.readObject();
            this.fenetre.getActions().setEntrees(tampon2);
            
            
        } catch (ClassNotFoundException erreur) {
            
            // la donn�e pr�sente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donn�e lue dans le fichier n'est pas un objet � 2 dimensions    
        } catch (IOException erreur) {
            
            // probl�me d'acc�s au fichier
        }
        return tampon;
    }


    /**
     * Constructeur par d�faut rendu inutilisable
     */
    private OutilsFichier() {
    }
    
    /**
     * Construit une instance de OutilsFichier associ�e � un tableur
     * @param fenetre fenetre associ�e � cette classe OutilsFichier
     */
    public OutilsFichier(Tableur fenetre) {
        this();
        this.fenetre = fenetre;
    }
 }

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
 * Cette classe contient des méthodes pour gérer les fichiers du tableur.
 * Ces méthodes permettent de :
 *     - enregistrer dans un fichier un tableau à 2 dimensions contenant des 
 *       chaînes de caractères.
 *     - lire le contenu d'un fichier supposé contenir le tableau du tableur
 *       et renvoyer celui-ci sous la forme d'un tableau à 2 dimensions 
 *       contenant des chaînes de caractères.
 * @author INFO1
 * @version 1.0
 */
public class OutilsFichier {

    /** Tableur associée à la fenetre */
    private Tableur fenetre;
    
    /**
     * Nom du fichier dans lequel est enregistré le tableau à 2 dimensions
     * contenant les paires "arrêt/ligne".
     */
    public static File nomFichier;
      

    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_PAIRE
     * le tableau contenant les paires "ligne/arrêt". Il s'agit d'un tableau
     * à 2 dimensions contenant des valeurs de type String.
     * @param tableCalcule 
     * @param tableEnDur 
     * @return un booléen égal à vrai ssi la sauvegarde a bien été effecuée
     */
    public static boolean enregistrerTableur(Object[][] tableCalcule,
                                             String[][] tableEnDur) {
        boolean reussi = true;      // vrai ssi l'enregistrement a réussi
        
        // création et ouverture du fichier NOM_FICHIER_PAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                    new FileOutputStream(nomFichier))) {
                       
            // On écrit l'objet argument dans le fichier
            fichier.writeObject(tableCalcule);  
            fichier.writeObject((Object[][])tableEnDur);
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'accès au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des paires "arret/ligne".
     * Les paires sont supposées être présentes dans le fichier de nom 
     * NOM_FICHIER_TABLEUR, sous la forme d'un tableau à 2 dimensions contenant
     * des valeurs de type Object.
     * @return un tableau à 2 dimensions contenant des chaînes de caractères
     *         ou bien la valeur null si un problème empêche l'accès au fichier
     */
    public Object[][] restaurerPaireLignTableur()  {
        
        // objet tampon dans lequel est placé l'objet lu dans le fichier  
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
            
            // la donnée présente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donnée lue dans le fichier n'est pas un objet à 2 dimensions    
        } catch (IOException erreur) {
            
            // problème d'accès au fichier
        }
        return tampon;
    }


    /**
     * Constructeur par défaut rendu inutilisable
     */
    private OutilsFichier() {
    }
    
    /**
     * Construit une instance de OutilsFichier associée à un tableur
     * @param fenetre fenetre associée à cette classe OutilsFichier
     */
    public OutilsFichier(Tableur fenetre) {
        this();
        this.fenetre = fenetre;
    }
 }

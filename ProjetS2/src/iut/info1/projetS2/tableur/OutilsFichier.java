/*
 * OutilsFichier.java               13 mai 2015
 * IUT RODEZ INFO1 2014-2015
 */

package iut.info1.projetS2.tableur;

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
        
    /**
     * Nom du fichier dans lequel est enregistré le tableau à 2 dimensions
     * contenant les paires "arrêt/ligne".
     */
    public final static String NOM_FICHIER = "fichier_tableur.bin";
      
    
    /**
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_PAIRE
     * le tableau contenant les paires "ligne/arrêt". Il s'agit d'un tableau
     * à 2 dimensions contenant des valeurs de type String.
     * @param table  tableau à 2 dimensions contenant des String
     * @return un booléen égal à vrai ssi la sauvegarde a bien été effecuée
     */
    public static boolean enregistrerPaireLigneArret(Object[][] table) {
        boolean reussi = true;      // vrai ssi l'enregistrement a réussi
        
        // création et ouverture du fichier NOM_FICHIER_PAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                    new FileOutputStream(NOM_FICHIER))) {
                       
            // On écrit l'objet argument dans le fichier
            fichier.writeObject(table);  
        }  catch (IOException erreur) {
            
            // une erreur s'est produite lors de l'accès au fichier
            reussi = false;
        }
        return reussi;        
    }
    
    
    /**
     * Restauration des paires "arret/ligne".
     * Les paires sont supposées être présentes dans le fichier de nom 
     * NOM_FICHIER_PAIRE, sous la forme d'un tableau à 2 dimensions contenant
     * des valeurs de type String
     * @return un tableau à 2 dimensions contenant des chaînes de caractères
     *         ou bien la valeur null si un problème empêche l'accès au fichier
     */
    public static Object[][] restaurerPaireLigneArret()  {
        
        // objet tampon dans lequel est placé l'objet lu dans le fichier  
        Object[][] tampon = null;  
        
        // ouverture du fichier et lecture de l'objet qu'il contient
        try(ObjectInputStream fichier = new ObjectInputStream(
                    new FileInputStream(NOM_FICHIER))) {           
            
            // lecture de l'objet contenu dans le fichier
            tampon = (Object[][]) fichier.readObject();
            
        } catch (ClassNotFoundException erreur) {
            
            // la donnée présente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
                
            // la donnée lue dans le fichier n'est pas un objet à 2 dimensions    
        } catch (IOException erreur) {
            
            // problème d'accès au fichier
        }
        return tampon;
    }
 }

/*
 * FiltreSimple.java				21 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.io.File; 
import javax.swing.filechooser.*;

/**
 * Classe permettant de créer des filtres
 * @author Mickaël
 * @version 0.1
 */
public class FiltreSimple extends FileFilter{
    
    /** Description acceptée par le filtre */
    private String description;
    
    /** Extension acceptée par le filtre */
    private String extension;
    
    /**
     * Constructeur à partir de la description et de l'extension acceptée
     * @param description description de notre fichier
     * @param extension extension de notre fichier
     */
    public FiltreSimple(String description, String extension){
        if(description == null || extension ==null){
            throw new NullPointerException("La description (ou extension) ne peut être null."); 
        }
        this.description = description;
        this.extension = extension;
    }

    /**
     * Permet de savoir quels fichiers sont affichable.
     * De plus affiche automatiquement les dossiers.
     */
    public boolean accept(File file){
        if(file.isDirectory()) { 
            return true; 
        } 
        String nomFichier = file.getName().toLowerCase(); 
        
        return nomFichier.endsWith(extension);
    }
    /**
     * @return la description
     */
    public String getDescription(){
        return description;
    }
    
}
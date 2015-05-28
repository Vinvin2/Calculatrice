/*
 * Commandes.java                                       30 avr. 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

//import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iut.info1.projetS2.tableur.*;
import iut.info1.projetS2.utilitaires.Utilitaires;

/**
 * Méthode contenant l'ensemble des commandes interprétables par le tableur
 * --> Important : <--
 * La classe commande est instaciable et sera FORCEMENT liée à un tableur (lors
 * de l'instanciation de ce dernier)
 * @author jo
 * @version 0.1
 */
public class Commandes {
    /** fenêtre à laquelle cette classe sera liée */
    private Tableur fenetre;

    /** tableau contenant les entrées */
    private String[][] entrees = new String[20][26];

    /** pattern d'une lettre de cellule */
    private static final String REG_LETTRE = "([A-Z])";

    /** pattern d'une lettre de cellule */
    private static final String REG_LETTRE2 = "((\\0044)?([A-Z]))";
    // note: \\0044 est l'octal value de '$'

    /** pattern d'un nombre de cellule */
    private static final String REG_NBRE = "(0*(1?\\d|20))";

    /** pattern d'un nombre de cellule */
    private static final String REG_NBRE2 = "((\\0044)?0*((1?\\d|20)))";

    /** Pattern d'une case type nombreLettre */
    private static final String REG_CASE1 = "("+REG_NBRE+REG_LETTRE+")";

    /** Pattern d'une case type lettreNombre */
    private static final String REG_CASE2 = "("+REG_LETTRE+REG_NBRE+")";

    /** Pattern des cases  */
    private static final String REG_CASE = "("+REG_CASE1+"|"+REG_CASE2+")";

    /** Pattern d'une case qui réutilisé dans les commandes/calculs */
    private static final String REG_MODIF1 = "("+REG_NBRE2+REG_LETTRE2+")";

    /** Pattern d'une case qui réutilisé dans les commandes/calculs */
    private static final String REG_MODIF2 = "("+REG_LETTRE2+REG_NBRE2+")";

    /** Pattern d'une case qui réutilisé dans les commandes/calculs */
    private static final String REG_MODIF =
            "(" + REG_MODIF1 + "|" + REG_MODIF2 + ")";

    /** pattern d'une plage */
    private static final String REG_PLAGE = REG_CASE + "\\0056{2}" + REG_CASE;
    // note: \\0056 est l'octal value de '.'

    /** REGEX de type 'simple affichage' type: nombreLettre aAfficher */
    private static final String REG_AFFICH1 = REG_CASE1 + "\\s+((.)*)";

    /** REGEX de type 'simple affichage' type: lettreNombre aAfficher */
    private static final String REG_AFFICH2 = REG_CASE2 + "\\s+((.)*)";

    /** REGEX pour définir s'il y a calcul, type: nombreLettre =aAfficher */
    private static final String REG_NEEDCALC = REG_CASE1 + "\\s*=\\s*((.)*)";

    /** REGEX pour définir s'il y a calcul, type: lettreNombre =aAfficher */
    private static final String REG_NEEDCALC2 = REG_CASE2 + "\\s*=\\s*((.)*)";

    /** Identifie une demande de copie */
    private static final String REG_COPIER = "((COPIER)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande de copie de valeur */
    private static final String REG_COPVAL = "((COPVAL)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande d'effaçage */
    private static final String REG_RAZ = "((RAZ)\\s+((.)+)\\s*)";

    /** Détermine si une commande a été entrée */
    private static final String REG_NEEDCOMMANDE = 
            REG_COPIER + "|" + REG_COPVAL + "|" + REG_RAZ;

    /**
     * Appelé sur l'évènement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tapé dans sa 'ligne de commande'
     */
    public void actionValider() {
        // recupération du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        // test via pattern si une commande est appelée
        Pattern testSiCom = Pattern.compile(REG_NEEDCOMMANDE);
        Matcher matchSiCom = testSiCom.matcher(aRenvoyer);
        // test via pattern si besoin de calcul ou pas
        Pattern testSiCalc = Pattern.compile(REG_NEEDCALC);
        Pattern testSiCalc2 = Pattern.compile(REG_NEEDCALC2);
        Matcher matchSiCalc = testSiCalc.matcher(aRenvoyer);
        Matcher matchSiCalc2 = testSiCalc2.matcher(aRenvoyer);
        // test si un simpleaffichage peut être réalisé
        Pattern testSiAffich = Pattern.compile(REG_AFFICH1);
        Pattern testSiAffich2 = Pattern.compile(REG_AFFICH2);
        Matcher matchSiAffich = testSiAffich.matcher(aRenvoyer);
        Matcher matchSiAffich2 = testSiAffich2.matcher(aRenvoyer);


        // on oriente selon l'entrée
        if (matchSiCom.matches()) {
            //            System.out.println(matchSiCom.group());
            //            for (int i = 0; i < matchSiCom.groupCount(); i++) {
            //                System.out.println(i + " " + matchSiCom.group(i));
            //            }
            /*
             * COPIER -> group(2), param1 -> group(3), param2 -> group(4)
             * COPVAL -> group(6), param1 -> group(7), param2 -> group(8)
             * RAZ -> group(10), param -> group(11)
             */
            if (matchSiCom.group(2) != null // cas du COPIER
                    && matchSiCom.group(2).equals("COPIER")) {
                this.copier(matchSiCom.group(3), matchSiCom.group(4));
            } else if (matchSiCom.group(6)!= null // cas du COPVAL
                    && matchSiCom.group(6).equals("COPVAL")) {
                this.copval(matchSiCom.group(7), matchSiCom.group(8));  
            } else if (matchSiCom.group(10)!= null // cas du RAZ
                    && matchSiCom.group(10).equals("RAZ")) {
                this.raz(matchSiCom.group(11));                
            }
        } else if (matchSiCalc.matches() || matchSiCalc2.matches()) {
            this.affichageCalcule();
        } else if (matchSiAffich.matches() || matchSiAffich2.matches()) {
            this.affichageSimple();
        } else {
            this.fenetre.getLabel().setText("Erreur de syntaxe");
        }
    }

    /**
     * récupère la colonne et la ligne d'une case à partir d'une chaine
     * @param aRecuperer case contenant les données
     * @return tab[0] contient la ligne
     *         tab[1] contient la colonne
     */
    private static int[] recupCase(String aRecuperer) {
        int[] coord = new int[2]; // coordonnées de la cellule aRecuperer
        Pattern testCase = Pattern.compile(REG_MODIF);
        Matcher siCase = testCase.matcher(aRecuperer);

        if (siCase.matches()) {
            //          for (int i = 0; i < siCase.groupCount(); i++) {
            //              System.out.println(i + " " + siCase.group(i));
            //          }
            if (siCase.group(2) == null) { // cas lettre/chiffre
                coord[0] = Integer.parseInt(siCase.group(16)) - 1;
                coord[1] = siCase.group(13).charAt(0) - 65;
            } else { // cas chiffre/lettre
                coord[0] = Integer.parseInt(siCase.group(5)) - 1;
                coord[1] = siCase.group(9).charAt(0) - 65;
            }
            return coord;
        } // else
        return null;
    }

    /**
     * adapte une chaine contenant des références vers d'autres cases
     * @param aAdapter chaine à adapter
     * @param nbDecLig nombre de décalage à effectuer sur la ligne
     * @param nbDecCol nombre de décalage à effectuer sur la colonne
     * @return une chaine représentant le calcul adapté
     */
    private static String adapteCalc(String aAdapter, int nbDecLig,
            int nbDecCol) {
        // ArrayList contenant les cases à remplacer trouvées
        ArrayList<String> lol = new ArrayList<String>();
        // chaine de caractères avec les cases changées par leur contenu
        String aRenvoyer = aAdapter;
        String tmp; // stockage temporaire d'une occurence (ou non) de case
        char tmpChar; // stock temporairement un caractère
        Matcher testeur;
        // scanner de récupération d'occurences de cases
        Scanner test = new Scanner(aAdapter);
        // analyse toute la chaine à la recherche d'occurences de cases
        while (test.hasNext()) {
            tmp = test.findInLine(REG_MODIF);
            test.skip("\\s*");
            if (tmp != null) { // une occurence a été trouvée
                lol.add(tmp);
            } else { // aucune occurence présente dans cette chaine
                break;
            }
            System.out.println(lol.toString());
        }
        // remplace s'il y en a les occurence de cases par leur valeur
        for (int i = 0; i < lol.size(); i++) {
            //System.out.println(lol.get(i));
            //System.out.println(aRenvoyer);
            if (lol.get(i).matches(REG_MODIF2)) { // lettre/chiffre
                testeur = Pattern.compile(REG_MODIF2).matcher(lol.get(i));
                testeur.matches();
                for (int j = 0; j < testeur.groupCount(); j++) {
                    System.out.println(i + " = " + testeur.group(i));
                }
                tmpChar = colonneLocked(lol.get(i)) ? lol.get(i).charAt(0)
                        : (char)(lol.get(i).charAt(0) + nbDecCol);
                System.out.println("char " + tmpChar);
                tmp = ligneLocked(lol.get(i)) ? lol.get(i).substring(1)
                        : lol.get(i).substring(1) + nbDecLig;
                System.out.println("nbre " + tmp);
                tmp = String.valueOf(tmpChar).concat(tmp);
                aRenvoyer = aRenvoyer.replaceFirst(lol.get(i), tmp);
            }
            if (lol.get(i).matches(REG_MODIF1)) { //chiffre/lettre
                testeur = Pattern.compile(REG_MODIF1).matcher(lol.get(i));   
            }

        }
    test.close();
    System.out.println("aRenvoyer " + aRenvoyer);
    return aRenvoyer;
}

/**
 * copie une commande d'un case/plage vers une autre case/plage en
 * l'adaptant à la case destinatation
 * @param aCopier case source de la copie
 * @param ouCopier case destination de la copie
 */
private void copier(String aCopier, String ouCopier) {
    int[] coordInitInit;  // début coordonnées de l'emplacement à copier
    //int[] coordInitFinal; // fin coordonnées de l'emplacement à copier
    int[] coordFinalInit; // début coordonnées de l'emplacement ou copier
    //int[] coordFinalFinal;// fin coordonnées del'emplacement ou copier
    String tmp; // chaine temporaire
    //Scanner donnees; // analyseur de plages

    // TODO tester c'est un calc car si c'est un affichage ya rien à faire

    if (aCopier.matches(REG_CASE) && ouCopier.matches(REG_CASE)) {
        coordInitInit = recupCase(aCopier);
        coordFinalInit = recupCase(ouCopier);
        // les données sont correctement récupérées
        tmp = adapteCalc(entrees[coordInitInit[0]][coordInitInit[1]],
                coordFinalInit[0] - coordInitInit[0],
                coordFinalInit[1] - coordInitInit[1]);
        affichage(tmp);
        this.fenetre.getLabel().setText("Transfert effectué");
        // this.fenetre.getConsole().setText("");
    }
}

/**
 * copie la valeur d'une case/plage vers une case/plage
 * la copie prends en compte les commandes entrées
 * @param aCopier coordonnées de la valeur à copier
 * @param ouCopier coordonnées de l'emplacement ou copier
 */
private void copval(String aCopier, String ouCopier) {
    int[] coordInitInit;  // début coordonnées de l'emplacement à copier
    int[] coordInitFinal; // fin coordonnées de l'emplacement à copier
    int[] coordFinalInit; // début coordonnées de l'emplacement ou copier
    int[] coordFinalFinal;// fin coordonnées del'emplacement ou copier
    Scanner donnees; // analyseur de plages

    if (aCopier.matches(REG_CASE) && ouCopier.matches(REG_CASE)) {
        coordInitInit = recupCase(aCopier);
        coordFinalInit = recupCase(ouCopier);
        // les données sont correctement récupérées
        this.fenetre.getModele().setValueAt(
                this.entrees[coordInitInit[0]][coordInitInit[1]],
                coordFinalInit[0], coordFinalInit[1]);
        this.entrees[coordInitInit[0]][coordInitInit[1]]
                = this.entrees[coordFinalInit[0]][coordFinalInit[1]];
        this.fenetre.getLabel().setText("Copie effectuée");
        // this.fenetre.getConsole().setText("");
    } else if (aCopier.matches(REG_CASE) && ouCopier.matches(REG_PLAGE)) {
        coordInitInit = recupCase(aCopier);
        // donnees analyse la plage ouCopier
        donnees = new Scanner(ouCopier).useDelimiter("\\0056{2}");
        // la syntaxe est déjà vérifiée, la récupération marchera
        coordFinalInit = recupCase(donnees.next());
        coordFinalFinal = recupCase(donnees.next());
        // copie i*j fois la case coordInitInit
        for (int i = coordFinalInit[0]; i <= coordFinalFinal[0]; i++) {
            for (int j = coordFinalInit[1]; j <= coordFinalFinal[1]; j++) {
                this.fenetre.getModele().setValueAt(
                        this.fenetre.getModele().getValueAt(
                                coordInitInit[0], coordInitInit[1]), i, j);
                this.entrees[i][j] = 
                        this.entrees[coordInitInit[0]][coordInitInit[1]];
            }
        }
        this.fenetre.getLabel().setText("Copie effectuée");
        // this.fenetre.getConsole().setText("");
    } else if (aCopier.matches(REG_PLAGE) && ouCopier.matches(REG_PLAGE)) {

        int xPattern; //nombre de colonnes du pattern à copier
        int yPattern; //nombre de lignes du pattern à copier
        boolean copieOk; // true si la copie est possible false sinon
        // donnees analyse la plage ouCopier
        donnees = new Scanner(aCopier);
        donnees.useDelimiter("\\0056{2}");
        coordInitInit = recupCase(donnees.next());
        coordInitFinal = recupCase(donnees.next());
        donnees.close();
        // donnees analyse la plage ouCopier
        donnees = new Scanner(ouCopier);
        donnees.useDelimiter("\\0056{2}");
        coordFinalInit = recupCase(donnees.next());
        coordFinalFinal = recupCase(donnees.next());
        donnees.close();
        /*
         * testsi la copie est possible
         * pour une copie valide il faut :
         * finalfinal[0] - finalinit[0]
         * doit etre divisible par 
         * initfinal[0] - initfinal[0]
         *  
         * finalfinal[1] - finalinit[1]
         * doit etre divisible par
         * initfinal[1] - initfinal[1] 
         */
        try { // gère le cas de la division par 0
            copieOk = ((coordFinalFinal[0]-coordFinalInit[0]+1)
                    /(coordInitFinal[0]-coordInitInit[0]+1))
                    %((coordFinalFinal[0]-coordFinalInit[0]+1)
                            /(coordInitFinal[0]-coordInitInit[0]+1))==0
                            && ((coordFinalFinal[1]-coordFinalInit[1]+1)
                                    /(coordInitFinal[1]-coordInitInit[1]+1))
                                    %((coordFinalFinal[1]
                                            -coordFinalInit[1]+1)
                                            /(coordInitFinal[1]
                                                    -coordInitInit[1]
                                                            +1))==0;
        } catch (ArithmeticException e) {
            // division par 0 => intervalle mauvais
            copieOk = false;
        }

        if (copieOk) {
            xPattern = coordInitFinal[0] - coordInitInit[0] + 1;
            yPattern = coordInitFinal[1] - coordInitInit[1] + 1;
            // copie
            for (int i = coordFinalInit[0]; i<=coordFinalFinal[0];i++) {
                for (int j = coordFinalInit[1]; j<=coordFinalFinal[1];j++) {
                    this.fenetre.getModele().setValueAt(
                            this.fenetre.getModele().getValueAt(
                                    coordInitInit[0] + i % xPattern, 
                                    coordInitInit[1] + j % yPattern),
                                    i, j);
                    // copie également les commandes enregistrées
                    this.entrees[i][j] =
                            this.entrees[coordInitInit[0] + i % xPattern]
                                    [coordInitInit[1] + j % yPattern];
                }
            }
            this.fenetre.getLabel().setText("Copie effectuée");
            // this.fenetre.getConsole().setText("");
        } else { // copie impossible
            xPattern = 0;
            yPattern = 0;
            this.fenetre.getLabel().setText("Copie impossible pour "
                    + "les plages entrées");
        }
    } else { // erreur de syntaxe
        this.fenetre.getLabel().setText("Erreur de syntaxe");
    }
}


/**
 * Efface la cellule ou la plage spécifiée ainsi que la commande
 * enregistrée correspondante
 * @param aEffacer cellule ou plage du tableur
 */
private void raz(String aEffacer) {
    int[] coordInit;  // coordonnées de la case à effacer initiales
    int[] coordFinal; // coordonnées finales de la surface à supprimer
    int i; // indice de colonne
    int j; // indice de ligne
    Pattern testPlage = Pattern.compile(REG_PLAGE);
    Matcher siPlage = testPlage.matcher(aEffacer);
    if (siPlage.matches()) {
        //            for (int k = 0; k < siPlage.groupCount(); k++) {
        //               System.out.println(k + " " + siPlage.group(k)); 
        //            }
        /*
         * première case -> group(1)
         * seconde case -> group(10)
         */
        coordInit = recupCase(siPlage.group(1));
        coordFinal = recupCase(siPlage.group(14));
        // les coordonnées ont été récupérées
        if (coordInit != null && coordFinal != null) {
            for (i = coordInit[0]; i <= coordFinal[0]; i++) {
                for (j = coordInit[1]; j <= coordFinal[1]; j++) {
                    this.fenetre.getModele().setValueAt("", i, j);
                    this.entrees[i][j] = "";
                }
                this.fenetre.getLabel().setText("Case effacées");
            }
            // si les coordonnées initiales > coordonnées finales
            if (coordFinal[0] < coordInit[0]
                    || coordFinal[1] < coordInit[1]) {
                this.fenetre.getLabel().setText("Intervalle Inexistant");
            }
            // this.fenetre.getConsole().setText("");
        }
    } else { // l'user n'a pas entré de plage
        coordInit = recupCase(aEffacer);
        if (coordInit != null) {
            this.fenetre.getModele().setValueAt(
                    "", coordInit[0], coordInit[1]);
            this.entrees[coordInit[0]][coordInit[1]] = "";
            this.fenetre.getLabel().setText("Case effacée"); 
            // this.fenetre.getConsole().setText("");           
        } else { // erreur de syntaxe
            this.fenetre.getLabel().setText("Erreur de syntaxe"); 
        }
    }
}

/**
 * affiche le texte paramêtre suivant la case de destination
 * ex: A1 bonjour   // A1 contient bonjour
 * ex: A1 2 + 3 - 1 // A1 contient 4
 * @param aAfficher String à afficher dans le tableur
 */
private void affichage(String aAfficher) {
    int col = -1; // colonne de la case à modifier
    int lig = -1; // ligne de la case à modifier

    String aAfficherTraite = ""; // texte à afficher après traitement
    // test sur les deux pattern autorisés
    Pattern ok = Pattern.compile(REG_AFFICH1);
    Pattern ok2 = Pattern.compile(REG_AFFICH2);
    Pattern ok3 = Pattern.compile(REG_NEEDCALC);
    Pattern ok4 = Pattern.compile(REG_NEEDCALC2);
    Matcher lol = ok.matcher(aAfficher);
    Matcher lel = ok2.matcher(aAfficher);
    Matcher lil = ok3.matcher(aAfficher);
    Matcher lul = ok4.matcher(aAfficher);

    // orientation selon l'ordre nombre/lettre - lettre/nombre - calculs
    if (lul.matches()) { // lettre/nombre
        //            for (int i = 0; i < lul.groupCount(); i++) {
        //                System.out.println(i + " lul " + lul.group(i));
        //            }
        /*
         * on enleve pour simplifier l'utilisation du tableur (1~20)
         * au lieu de (0~19)
         */             
        lig = Integer.parseInt(lul.group(3)) - 1;
        // on recupère la lettre et on la transforme en int utilisable
        col = lul.group(2).charAt(0) - 65;
        aAfficherTraite = String.valueOf(Utilitaires.calculEvolue(
                this.prepareCalc(lul.group(5))));
        this.entrees[lig][col] = lul.group(0);
        if (!aAfficherTraite.equals("NaN")) {
            this.fenetre.getLabel().setText("Calcul effectué");
        } else {
            this.fenetre.getLabel().setText("Calcul non effectué");  
        }
    } else if (lil.matches()) { // nombre/lettre
        //            for (int i = 0; i < lil.groupCount(); i++) {
        //                System.out.println(i + " lil " + lil.group(i));
        //            }
        /* 
         * on enleve pour simplifier l'utilisation du tableur (1~20)
         * au lieu de (0~19)
         */
        lig = Integer.parseInt(lil.group(3)) - 1;
        // on recupère la lettre et on la transforme en int utilisable
        col = lil.group(4).charAt(0) - 65;
        aAfficherTraite = String.valueOf(Utilitaires.calculEvolue(
                this.prepareCalc(lil.group(5))));
        this.entrees[lig][col] = lil.group(0);
        if (!aAfficherTraite.equals("NaN")) {
            this.fenetre.getLabel().setText("Calcul effectué");
        } else {
            this.fenetre.getLabel().setText("Calcul non effectué");
        }
    } else if (lel.matches()) { // lettre/nombre
        //            for (int i = 0; i < lel.groupCount(); i++) {
        //                System.out.println(i + " lel " + lel.group(i));
        //            }
        /* 
         * on enleve pour simplifier l'utilisation du tableur (1~20)
         * au lieu de (0~19)
         */
        lig = Integer.parseInt(lel.group(3)) - 1;
        // on recupère la lettre et on la transforme en int utilisable
        col = lel.group(2).charAt(0) - 65;  
        aAfficherTraite = lel.group(5);
        this.entrees[lig][col] = lel.group(0);
    } else if (lol.matches()) {
        //            for (int i = 0; i < lol.groupCount(); i++) {
        //                System.out.println(i + " lol " + lol.group(i));
        //            }
        /* 
         * on enleve pour simplifier l'utilisation du tableur (1~20)
         * au lieu de (0~19)
         */
        lig = Integer.parseInt(lol.group(3)) - 1;
        // on recupère la lettre et on la transforme en int utilisable
        col = lol.group(4).charAt(0) - 65;
        aAfficherTraite = lol.group(5);
        this.entrees[lig][col] = lol.group(0);
    }
    // else => lig = -1 et col = -1 => prochaine condition false

    if (lig > -1 & col > -1) { // La syntaxe est ok et tout est récupéré
        this.fenetre.getModele().setValueAt(aAfficherTraite, lig, col);
        if (!aAfficherTraite.equals("NaN")) {
            // this.fenetre.getConsole().setText("");
        }
    } else { // syntaxiquement faux
        this.fenetre.getLabel().setText("Erreur de syntaxe type: "
                + "A1 texte ou 1A texte");
    }
}


/**
 * permet de chercher la position de la parenthèse fermante correspondant
 * à la parenthèse ouvrante située en position 0, prends en compte
 * les parenthèses ouvrantes situées après et cherche la bonne occurence
 * de ')'correspondant mathématiquement à la première occurence de '('
 * ex: "(bonjour), je suis pacifique" retourne 8
 *     "((bonjour), je suis) pacifique" retourne 19
 *     "((bonjour), (je) suis) pacifique" retourne 21
 * @param aChercher l'indexe de l'occurence
 * @return l'occurence fermant la première parenthèse ouvrante
 *         -1 si le premier caractère n'est pas une parenthèse ou si
 *         aucune occurence ne correspond à la parenthèse ouvrante
 */
public static int fermetureParentheseA(String aChercher) {
    // nombre de '(' rencontrés après la première occurence de '('
    int parentOuvranteSupp = 0;
    int i; // variable parcourant aChercher

    if (aChercher.charAt(0) != '(') {
        return -1; // erreur de syntaxe sur aChercher
    }
    // else
    // parcourt la aChercher en scrutant les occurences de '(' et ')'
    for (i=1; i < aChercher.length(); i++) {
        if (aChercher.charAt(i) == '(') {
            parentOuvranteSupp++; // ajout d'une parenthèse à fermer
        } else if (aChercher.charAt(i) == ')') {
            if (parentOuvranteSupp == 0) {
                return i; // la première parenthèse a été fermée
            } else {
                parentOuvranteSupp--; // une parenthèse a été fermée
            }
        }
    }
    return -1; // aucune occurence fermante de la première occurence de '('
}


/**
 * prepare un calcul évolué en remplaçant les occurences de case par 
 * leurs valeurs, erreurs de syntaxe non vérifiées
 * @param aPreparer chaine à modifier
 * @return une chaine prête à être exploiter pour un calcul évolué
 */
private String prepareCalc(String aPreparer) {
    // ArrayList contenant les cases à remplacer trouvées
    ArrayList<String> lol = new ArrayList<String>();
    // chaine de caractères avec les cases changées par leur contenu
    String aRenvoyer = aPreparer;
    String tmp; // stockage temporaire d'une occurence (ou non) de case
    // scanner de récupération d'occurences de cases
    Scanner test = new Scanner(aPreparer);
    // analyse toute la chaine à la recherche d'occurences de cases
    while (test.hasNext()) {
        tmp = test.findInLine(REG_MODIF);
        test.skip("\\s*");
        if (tmp != null) { // une occurence a été trouvée
            lol.add(tmp);
        } else { // aucune occurence présente dans cette chaine
            break;
        }
        //System.out.println("aPreparer = " + aPreparer);
        //System.out.println("lol = " + lol.toString());
    }
    // remplace s'il y en a les occurence de cases par leur valeur
    for (int i = 0; i < lol.size() && i > -1; i++) {
        //System.out.println(lol.get(i));
        //System.out.println(aRenvoyer);
        aRenvoyer = aRenvoyer.replaceFirst(
                REG_MODIF, this.getValueAt(lol.get(i)));
    }
    test.close();
    return aRenvoyer;
}

/**
 * permet de récupérer le contenu d'une case
 * @param aObtenir coordonnées sous forme de String d'une case
 * @return une String représentant le contenu de la case si tout est ok
 *         sinon retourne null
 */
private String getValueAt(String aObtenir) {
    // determine si la String représente une coordonnée de case
    Matcher ok = Pattern.compile(REG_MODIF).matcher(aObtenir);
    int[] tab; // récupère sous forme de int les coordonnées
    if (ok.matches()) { // aObtenir correspond à une case
        tab = recupCase(aObtenir);
        return String.valueOf(
                this.fenetre.getModele().getValueAt(tab[0], tab[1]));
    }
    // else
    return null; // aObtenir n'est pas une coordonnée de case
}

/**
 * vérifie si la ligne est verrouillée via un '$'
 * @param caseAtester case ou la recherche s'effectue
 * @return true si la case est verrouillée false sinon
 */
private static boolean ligneLocked(String caseAtester) {
    Pattern testCase = Pattern.compile(REG_MODIF);
    Matcher siCase = testCase.matcher(caseAtester);
    if (siCase.matches()) {
        //          for (int i = 0; i < siCase.groupCount(); i++) {
        //              System.out.println(i + " " + siCase.group(i));
        //          }
        if (siCase.group(2) == null) { // cas lettre/chiffre
            return siCase.group(15) != null;
        } else { // cas chiffre/lettre
            return siCase.group(4) != null;
        }
    } // else
    return false; // erreur de syntaxe
}

/**
 * vérifie si la colonne est verrouillée via un '$'
 * @param caseAtester case ou la recherche s'eefectue
 * @return true si la case est verrouillée false sinon
 */
private static boolean colonneLocked(String caseAtester) {
    Pattern testCase = Pattern.compile(REG_MODIF);
    Matcher siCase = testCase.matcher(caseAtester);
    if (siCase.matches()) {
        //          for (int i = 0; i < siCase.groupCount(); i++) {
        //              System.out.println(i + " " + siCase.group(i));
        //          }
        if (siCase.group(2) == null) { // cas lettre/chiffre
            return siCase.group(12) != null;
        } else { // cas chiffre/lettre
            return siCase.group(8) != null;
        }
    } // else
    return false; // erreur de syntaxe
}

/**
 * Permet un simple affichage de ce que l'utilisateur a entré dans la
 * 'ligne de commande' de type nombreLettre aAfficher
 *                          ou lettreNombre aAfficher
 */
private void affichageSimple() {
    String aAfficher = this.fenetre.getConsole().getText();
    if (aAfficher == null) {
        aAfficher = "";
    }
    this.affichage(aAfficher); // affichage du texte voulu
    this.fenetre.getLabel().setText("Texte affiché");
}


/**
 * Permet d'afficher dans une case du tableur le résultat d'un calcul
 * entré dans la 'ligne de commande'
 */
private void affichageCalcule() {
    String aAfficher = this.fenetre.getConsole().getText();
    if (aAfficher == null) {
        aAfficher = "";
    }
    this.affichage(aAfficher); // affichage du texte (après calcul)
}

/**
 * Redéfinition du contructeur par défaut crée par java, il est rendu
 * unitilisable pour éviter qu'une instance de Commandes se retrouve sans
 * fenêtre.
 */
private Commandes() {
}

/**
 * Unique constructeur disponible pour la classe Commandes permet une
 * navigabilité vers le tableur
 * @param fenetre fenêtre à laquelle cette classe sera liée
 */
public Commandes(Tableur fenetre) {
    this();
    this.fenetre = fenetre;
    this.fenetre.getLabel().setText(" ");
    // initialisation de entrees
    for (int i = 0; i < entrees.length; i++) {
        for (int j = 0; j < entrees[i].length; j++) {
            entrees[i][j] = "";
        }
    }
}

//public String 


/**
 * @return the entrees
 */
public String[][] getEntrees() {
    return entrees;
}

/**
 * @param entrees the entrees to set
 */
public void setEntrees(String[][] entrees) {
    this.entrees = entrees;
}
}
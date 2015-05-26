/*
 * Commandes.java                                       30 avr. 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iut.info1.projetS2.tableur.*;
import iut.info1.projetS2.utilitaires.Utilitaires;

/**
 * M�thode contenant l'ensemble des commandes interpr�tables par le tableur
 * --> Important : <--
 * La classe commande est instaciable et sera FORCEMENT li�e � un tableur (lors
 * de l'instanciation de ce dernier)
 * @author jo
 * @version 0.1
 */
public class Commandes {
    /** fen�tre � laquelle cette classe sera li�e */
    private Tableur fenetre;

    /** tableau contenant les entr�es */
    private String[][] entrees = new String[20][26];

    /** pattern d'une lettre de cellule */
    private static final String REG_LETTRE = "([A-Z])";

    /** pattern d'une lettre de cellule */
    private static final String REG_LETTRE2 = "(((\\0044)?)([A-Z]))";
    // note: \\0044 est l'octal value de '$'

    /** pattern d'un nombre de cellule */
    private static final String REG_NBRE = "([0]*((1?\\d)||(20)))";

    /** pattern d'un nombre de cellule */
    private static final String REG_NBRE2 = "((\\0044)?)([0]*((1?\\d)||(20)))";

    /** Pattern d'une case type nombreLettre */
    private static final String REG_CASE1 = "("+REG_NBRE+REG_LETTRE+")";

    /** Pattern d'une case type lettreNombre */
    private static final String REG_CASE2 = "("+REG_LETTRE+REG_NBRE+")";

    /** Pattern des cases  */
    private static final String REG_CASE = "("+REG_CASE1+"||"+REG_CASE2+")";

    /** Pattern d'une case qui r�utilis� dans les commandes/calculs */
    private static final String REG_MODIF1 = "("+REG_NBRE2+REG_LETTRE2+")";

    /** Pattern d'une case qui r�utilis� dans les commandes/calculs */
    private static final String REG_MODIF2 = "("+REG_LETTRE2+REG_NBRE2+")";

    /** Pattern d'une case qui r�utilis� dans les commandes/calculs */
    private static final String REG_MODIF
    = "(" + REG_MODIF1 + "||" + REG_MODIF2 + ")";

    /** pattern d'une plage */
    private static final String REG_PLAGE = REG_CASE + "\\0056{2}" + REG_CASE;
    // note: \\0056 est l'octal value de '.'

    /** REGEX de type 'simple affichage' type: nombreLettre aAfficher */
    private static final String REG_affich1 = REG_CASE1 + "\\s+((.)*)";

    /** REGEX de type 'simple affichage' type: lettreNombre aAfficher */
    private static final String REG_affich2 = REG_CASE2 + "\\s+((.)*)";

    /** REGEX pour d�finir s'il y a calcul, type: nombreLettre =aAfficher */
    private static final String REG_needCalc = REG_CASE1 + "\\s*=\\s*((.)*)";

    /** REGEX pour d�finir s'il y a calcul, type: lettreNombre =aAfficher */
    private static final String REG_needCalc2 = REG_CASE2 + "\\s*=\\s*((.)*)";

    /** Identifie une demande de copie */
    private static final String REG_COPIER = "((COPIER)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande de copie de valeur */
    private static final String REG_COPVAL = "((COPVAL)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande d'effa�age */
    private static final String REG_RAZ = "((RAZ)\\s+((.)+)\\s*)";

    /** D�termine si une commande a �t� entr�e */
    private static final String REG_needCommande
    = REG_COPIER + "||" + REG_COPVAL + "||" + REG_RAZ;

    /**
     * Appel� sur l'�v�nement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tap� dans sa 'ligne de commande'
     */
    public void actionValider() {
        // recup�ration du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        // test via pattern si une commande est appel�e
        Pattern testSiCom = Pattern.compile(REG_needCommande);
        Matcher matchSiCom = testSiCom.matcher(aRenvoyer);
        // test via pattern si besoin de calcul ou pas
        Pattern testSiCalc = Pattern.compile(REG_needCalc);
        Pattern testSiCalc2 = Pattern.compile(REG_needCalc2);
        Matcher matchSiCalc = testSiCalc.matcher(aRenvoyer);
        Matcher matchSiCalc2 = testSiCalc2.matcher(aRenvoyer);
        // test si un simpleaffichage peut �tre r�alis�
        Pattern testSiAffich = Pattern.compile(REG_affich1);
        Pattern testSiAffich2 = Pattern.compile(REG_affich2);
        Matcher matchSiAffich = testSiAffich.matcher(aRenvoyer);
        Matcher matchSiAffich2 = testSiAffich2.matcher(aRenvoyer);


        // on oriente selon l'entr�e
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
     * r�cup�re la colonne et la ligne d'une case � partir d'une chaine
     * @param aRecuperer case contenant les donn�es
     * @return tab[0] contient la ligne
     *         tab[1] contient la colonne
     */
    private static int[] recupCase(String aRecuperer) {
        int[] coord = new int[2]; // coordonn�es de la cellule aRecuperer
        Pattern testCase = Pattern.compile(REG_MODIF);
        Matcher siCase = testCase.matcher(aRecuperer);

        if (siCase.matches()) {
            //          for (int i = 0; i < siCase.groupCount(); i++) {
            //              System.out.println(i + " " + siCase.group(i));
            //          }
            /*
             * cas chiffre / lettre
             * chiffre -> group(5)
             * lettre -> group(9)
             * 
             * cas lettre / chiffre
             * lettre -> group(14)
             * chiffre -> group(20)
             */
            if (siCase.group(2) == null) { // cas lettre/chiffre
                coord[0] = Integer.parseInt(siCase.group(20)) - 1;
                coord[1] = siCase.group(17).charAt(0) - 65;
            } else { // cas chiffre/lettre
                coord[0] = Integer.parseInt(siCase.group(5)) - 1;
                coord[1] = siCase.group(12).charAt(0) - 65;
            }
            return coord;
        } // else
        return null;
    }


    /**
     * copie la valeur d'une case/plage vers une case/plage
     * la copie prends en compte les commandes entr�es
     * @param aCopier coordonn�es de la valeur � copier
     * @param ouCopier coordonn�es de l'emplacement ou copier
     */
    private void copval(String aCopier, String ouCopier) {
        int[] coordInitInit;  // d�but coordonn�es de l'emplacement � copier
        int[] coordInitFinal; // fin coordonn�es de l'emplacement � copier
        int[] coordFinalInit; // d�but coordonn�es de l'emplacement ou copier
        int[] coordFinalFinal;// fin coordonn�es del'emplacement ou copier
        Scanner donnees; // analyseur de plages


        if (aCopier.matches(REG_CASE) && ouCopier.matches(REG_CASE)) {
            coordInitInit = recupCase(aCopier);
            coordFinalInit = recupCase(ouCopier);
            // les donn�es sont correctement r�cup�r�es
            this.fenetre.getModele().setValueAt(
                    this.fenetre.getModele().getValueAt(
                            coordInitInit[0], coordInitInit[1]),
                            coordFinalInit[0], coordFinalInit[1]);
            this.entrees[coordInitInit[0]][coordInitInit[1]]
                    = this.entrees[coordFinalInit[0]][coordFinalInit[1]];
            this.fenetre.getLabel().setText("Copie effectu�e");
            this.fenetre.getConsole().setText("");
        } else if (aCopier.matches(REG_CASE) && ouCopier.matches(REG_PLAGE)) {
            coordInitInit = recupCase(aCopier);
            // donnees analyse la plage ouCopier
            donnees = new Scanner(ouCopier).useDelimiter("\\0056{2}");
            // la syntaxe est d�j� v�rifi�e, la r�cup�ration marchera
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
            this.fenetre.getLabel().setText("Copie effectu�e");
            this.fenetre.getConsole().setText("");
        } else if (aCopier.matches(REG_PLAGE) && ouCopier.matches(REG_PLAGE)) {

            int xPattern; //nombre de colonnes du pattern � copier
            int yPattern; //nombre de lignes du pattern � copier
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
            try { // g�re le cas de la division par 0
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
                        // copie �galement les commandes enregistr�es
                        this.entrees[i][j] =
                                this.entrees[coordInitInit[0] + i % xPattern]
                                        [coordInitInit[1] + j % yPattern];
                    }
                }
                this.fenetre.getLabel().setText("Copie effectu�e");
                this.fenetre.getConsole().setText("");
            } else { // copie impossible
                xPattern = 0;
                yPattern = 0;
                this.fenetre.getLabel().setText("Copie impossible pour "
                        + "les plages entr�es");
            }
        } else { // erreur de syntaxe
            this.fenetre.getLabel().setText("Erreur de syntaxe");
        }
    }


    /**
     * Efface la cellule ou la plage sp�cifi�e ainsi que la commande
     * enregistr�e correspondante
     * @param aEffacer cellule ou plage du tableur
     */
    private void raz(String aEffacer) {
        int[] coordInit;  // coordonn�es de la case � effacer initiales
        int[] coordFinal; // coordonn�es finales de la surface � supprimer
        int i; // indice de colonne
        int j; // indice de ligne
        Pattern testPlage = Pattern.compile(REG_PLAGE);
        Matcher siPlage = testPlage.matcher(aEffacer);
        if (siPlage.matches()) {
            //            for (int i = 0; i < siPlage.groupCount(); i++) {
            //               System.out.println(i + " " + siPlage.group(i)); 
            //            }
            /*
             * premi�re case -> group(1)
             * seconde case -> group(10)
             */
            coordInit = recupCase(siPlage.group(1));
            coordFinal = recupCase(siPlage.group(10));
            // les coordonn�es ont �t� r�cup�r�es
            if (coordInit != null && coordFinal != null) {
                for (i = coordInit[0]; i <= coordFinal[0]; i++) {
                    for (j = coordInit[1]; j <= coordFinal[1]; j++) {
                        this.fenetre.getModele().setValueAt("", i, j);
                        this.entrees[i][j] = "";
                    }
                    this.fenetre.getLabel().setText("Case effac�es");
                }
                // si les coordonn�es initiales > coordonn�es finales
                if (coordFinal[0] < coordInit[0]
                        || coordFinal[1] < coordInit[1]) {
                    this.fenetre.getLabel().setText("Intervalle Inexistant");
                }
                this.fenetre.getConsole().setText("");
            }
        } else { // l'user n'a pas entr� de plage
            coordInit = recupCase(aEffacer);
            if (coordInit != null) {
                this.fenetre.getModele().setValueAt(
                        "", coordInit[0], coordInit[1]);
                this.entrees[coordInit[0]][coordInit[1]] = "";
                this.fenetre.getLabel().setText("Case effac�e"); 
                this.fenetre.getConsole().setText("");           
            } else { // erreur de syntaxe
                this.fenetre.getLabel().setText("Erreur de syntaxe"); 
            }
        }
    }

    /**
     * TODO commenter le r�le de la m�thode
     * @param group
     * @param group2
     */
    private void copier(String group, String group2) {
        // TODO Auto-generated method stub
    }


    /**
     * Affichage de texte param�tre, la position est r�cup�r�e automatiquement
     * depuis la ligne de commande
     * @param aAfficher String � afficher dans le tableur
     */
    private void affichage(String aAfficher) {
        int col = -1; // colonne de la case � modifier
        int lig = -1; // ligne de la case � modifier

        String aAfficherTraite = ""; // texte � afficher apr�s traitement
        // test sur les deux pattern autoris�s
        Pattern ok = Pattern.compile(REG_affich1);
        Pattern ok2 = Pattern.compile(REG_affich2);
        Pattern ok3 = Pattern.compile(REG_needCalc);
        Pattern ok4 = Pattern.compile(REG_needCalc2);
        Matcher lol = ok.matcher(aAfficher);
        Matcher lel = ok2.matcher(aAfficher);
        Matcher lil = ok3.matcher(aAfficher);
        Matcher lul = ok4.matcher(aAfficher);

        // orientation selon l'ordre nombre/lettre - lettre/nombre - calculs
        if (lul.matches()) { // nombre/lettre
            //            for (int i = 0; i < lul.groupCount(); i++) {
            //                System.out.println(i + " lul " + lul.group(i));
            //            }
            /*
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~19)
             */             
            lig = Integer.parseInt(lul.group(3)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lul.group(2).charAt(0) - 65;
            aAfficherTraite = String.valueOf(Utilitaires.calculEvolue(
                    this.prepareCalc(lul.group(7))));
            this.entrees[lig][col] = "=" + lul.group(7);
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getLabel().setText("Calcul effectu�");
            } else {
                this.fenetre.getLabel().setText("Calcul non effectu�");  
            }
        } else if (lil.matches()) { // lettre/nombre
            //            for (int i = 0; i < lil.groupCount(); i++) {
            //                System.out.println(i + " lil " + lil.group(i));
            //            }
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~19)
             */
            lig = Integer.parseInt(lil.group(2)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lil.group(6).charAt(0) - 65;
            aAfficherTraite = String.valueOf(Utilitaires.calculEvolue(
                    this.prepareCalc(lil.group(7))));
            this.entrees[lig][col] = "=" + lil.group(7);
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getLabel().setText("Calcul effectu�");
            } else {
                this.fenetre.getLabel().setText("Calcul non effectu�");
            }
        } else if (lel.matches()) {
            //            for (int i = 0; i < lel.groupCount(); i++) {
            //                System.out.println(i + " lel " + lel.group(i));
            //            }
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~19)
             */
            lig = Integer.parseInt(lel.group(3)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lel.group(2).charAt(0) - 65;  
            aAfficherTraite = lel.group(7);
            this.entrees[lig][col] = lel.group(7);
        } else if (lol.matches()) {
            //            for (int i = 0; i < lol.groupCount(); i++) {
            //                System.out.println(i + " lol " + lol.group(i));
            //            }
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~19)
             */
            lig = Integer.parseInt(lol.group(2)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lol.group(6).charAt(0) - 65;
            aAfficherTraite = lol.group(7);
            this.entrees[lig][col] = lol.group(7);
        }
        // else => lig = -1 et col = -1 => prochaine condition false

        if (lig > -1 & col > -1) { // La syntaxe est ok et tout est r�cup�r�
            this.fenetre.getModele().setValueAt(aAfficherTraite, lig, col);
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getConsole().setText("");
            }
            this.entrees[lig][col] = aAfficherTraite;
        } else { // syntaxiquement faux
            this.fenetre.getLabel().setText("Erreur de syntaxe type: "
                    + "A1 texte ou 1A texte");
        }
    }


    /**
     * prepare un calcul �volu� en rempla�ant les occurences de case par 
     * leurs valeurs, erreurs de syntaxe non v�rifi�es
     * @param aPreparer chaine � modifier
     * @return une chaine pr�te � �tre exploiter pour un calcul �volu�
     */
    public String prepareCalc(String aPreparer) {
        String aRenvoyer = ""; // chaine transform�e � renvoyer
        String tmp; // chaine temporaire
        Scanner calcul = new Scanner(aPreparer); // Scanner d'analyse du calcul
        calcul.useDelimiter(Utilitaires.REG_OPERATEUR);

        while (calcul.hasNext()) { // remplace toutes les occurences de cases
            calcul.skip("\\s*");
            tmp = calcul.nextLine();
            System.out.println("TMP = " + tmp);
            calcul = new Scanner(tmp);
            calcul.useDelimiter(Utilitaires.REG_OPERATEUR + "[(]");
            if (tmp.length() > 0 && tmp.charAt(0) == '(') {
                int deb = tmp.indexOf("(") + 1;
                int fin = tmp.lastIndexOf(")");
                tmp = "(" + prepareCalc(tmp.substring(deb, fin)) + ")";
                calcul.findInLine("[(].*[)]");
            } else {
                int deb = tmp.indexOf("(") + 1;
                int fin = tmp.lastIndexOf(")");
                if (deb > 1 && fin > 1 && deb > fin) {
                    calcul.useDelimiter("[)]" + Utilitaires.REG_OPERATEUR);
                    tmp = prepareCalc(calcul.next());
                    tmp = tmp.concat(")");
                    calcul.useDelimiter("[^*/+-]");
                    tmp = tmp.concat(calcul.next()).concat("(");
                    calcul.useDelimiter("[(]");
                    tmp = tmp.concat(prepareCalc(calcul.next()));
                } else {
                    tmp = this.remplaceCases(calcul.next());
                }
            }
            aRenvoyer = aRenvoyer.concat(tmp);
            try {
                calcul.useDelimiter("[^*/+-]+");
                aRenvoyer = aRenvoyer.concat(calcul.next());
                calcul.useDelimiter(Utilitaires.REG_OPERATEUR + "[(]");
            } catch (NoSuchElementException e) {
                System.out.println("FIN = " + aRenvoyer);
                calcul.close();
                return aRenvoyer;
            }
        }
        System.out.println("FIN = " + aRenvoyer);
        calcul.close();
        return aRenvoyer;
    }

    /**
     * Remplace chaque occurences de coordonn�es de cases par leur valeur
     * correspondante
     * @param aRemplacer chaine qu'il faut modifier
     * @return une chaine de caract�res utilisable pour les calculs
     */
    private String remplaceCases(String aRemplacer) {
        String chaineModifiee;      // chaine apr�s modification
        String operandeTmp;         // derni�re op�rande r�cup�r�e � analyser
        int[] coordonnees; // coordonn�es d'une op�rande identifi�e comme case
        String aRemplacerVerif;
        if (aRemplacer.length() > 0 && aRemplacer.charAt(0) == '-') {
            aRemplacerVerif = aRemplacer.substring(1);
            chaineModifiee = "-";
        } else {
            aRemplacerVerif = aRemplacer;
            chaineModifiee = "";
        }

        /*
         *  pr�paration d'un Scanner r�cup�rant les op�randes
         *  note: si une op�rande n'est pas valide elle ne sera pas g�r�e ici
         *  elle sera tout simplement ignor�e, la v�rification s'effectuera
         *  au moment du calcul
         */
        Scanner recupOperande = new Scanner(aRemplacerVerif);
        recupOperande.useDelimiter("\\s*[+*/-]\\s*");
        /*
         * pr�paration d'un Scanner r�cup�rant les op�rateur
         * note: si une op�rande est manquante aucune erreur ne sera renvoy�e
         * l'erreur sera d�tect�e au moment du calcul
         */
        Scanner recupOperateur = new Scanner(aRemplacerVerif);
        recupOperateur.useDelimiter("[^+/*-]+");

        try {
            // inititialisation de chaineModifiee
            operandeTmp = recupOperande.next();
            if (operandeTmp.matches(REG_MODIF)) { // si case d�tect�e
                coordonnees = recupCase(operandeTmp);
                operandeTmp = String.valueOf(this.fenetre.getModele()
                        .getValueAt(coordonnees[0], coordonnees[1]));
                if (Double.parseDouble(operandeTmp) < 0) {
                    operandeTmp = String.valueOf(
                            Double.parseDouble(operandeTmp) * (-1.0));
                    chaineModifiee = ""; // reset du '-'
                }
            }
            chaineModifiee = chaineModifiee.concat(operandeTmp);
        } catch (NoSuchElementException e) { // chaine vide
        } catch (NullPointerException e) {   // chaine vide
        } catch (NumberFormatException e) {  // contient pas un double
        }
        while (recupOperande.hasNext()) {
            // ajout du prochain op�rateur
            try {
                operandeTmp = recupOperateur.next();
                chaineModifiee = chaineModifiee.concat(operandeTmp);
            } catch (NoSuchElementException e) { // rien n'est g�r� ici
            }
            // ajout de la prochaine op�rande
            try {
                operandeTmp = recupOperande.next();
                if (operandeTmp.matches(REG_MODIF)) { // si case d�tect�e
                    coordonnees = recupCase(operandeTmp);
                    // on r�cup�re sa valeur et on la place dans la chaine
                    operandeTmp = String.valueOf(this.fenetre.getModele()
                            .getValueAt(coordonnees[0], coordonnees[1]));
                }
                chaineModifiee = chaineModifiee.concat(operandeTmp);
            } catch (NoSuchElementException e) { // rien n'est g�r� ici
            }
            //          System.out.println(chaineModifiee);
        }
        recupOperande.close();
        recupOperateur.close();
        return chaineModifiee;
    }

    /**
     * Permet un simple affichage de ce que l'utilisateur a entr� dans la
     * 'ligne de commande' de type nombreLettre aAfficher
     *                          ou lettreNombre aAfficher
     */
    private void affichageSimple() {
        String aAfficher = this.fenetre.getConsole().getText();
        if (aAfficher == null) {
            aAfficher = "";
        }
        this.affichage(aAfficher); // affichage du texte voulu
        this.fenetre.getLabel().setText("Texte affich�");
    }


    /**
     * Permet d'afficher dans une case du tableur le r�sultat d'un calcul
     * entr� dans la 'ligne de commande'
     */
    private void affichageCalcule() {
        String aAfficher = this.fenetre.getConsole().getText();
        if (aAfficher == null) {
            aAfficher = "";
        }
        this.affichage(aAfficher); // affichage du texte (apr�s calcul)
    }


    /**
     * Red�finition du contructeur par d�faut cr�e par java, il est rendu
     * unitilisable pour �viter qu'une instance de Commandes se retrouve sans
     * fen�tre.
     */
    private Commandes() {
    }

    /**
     * Unique constructeur disponible pour la classe Commandes permet une
     * navigabilit� vers le tableur
     * @param fenetre fen�tre � laquelle cette classe sera li�e
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
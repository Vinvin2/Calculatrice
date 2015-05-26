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

    /** Pattern d'une case qui réutilisé dans les commandes/calculs */
    private static final String REG_MODIF1 = "("+REG_NBRE2+REG_LETTRE2+")";

    /** Pattern d'une case qui réutilisé dans les commandes/calculs */
    private static final String REG_MODIF2 = "("+REG_LETTRE2+REG_NBRE2+")";

    /** Pattern d'une case qui réutilisé dans les commandes/calculs */
    private static final String REG_MODIF
    = "(" + REG_MODIF1 + "||" + REG_MODIF2 + ")";

    /** pattern d'une plage */
    private static final String REG_PLAGE = REG_CASE + "\\0056{2}" + REG_CASE;
    // note: \\0056 est l'octal value de '.'

    /** REGEX de type 'simple affichage' type: nombreLettre aAfficher */
    private static final String REG_affich1 = REG_CASE1 + "\\s+((.)*)";

    /** REGEX de type 'simple affichage' type: lettreNombre aAfficher */
    private static final String REG_affich2 = REG_CASE2 + "\\s+((.)*)";

    /** REGEX pour définir s'il y a calcul, type: nombreLettre =aAfficher */
    private static final String REG_needCalc = REG_CASE1 + "\\s*=\\s*((.)*)";

    /** REGEX pour définir s'il y a calcul, type: lettreNombre =aAfficher */
    private static final String REG_needCalc2 = REG_CASE2 + "\\s*=\\s*((.)*)";

    /** Identifie une demande de copie */
    private static final String REG_COPIER = "((COPIER)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande de copie de valeur */
    private static final String REG_COPVAL = "((COPVAL)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande d'effaçage */
    private static final String REG_RAZ = "((RAZ)\\s+((.)+)\\s*)";

    /** Détermine si une commande a été entrée */
    private static final String REG_needCommande
    = REG_COPIER + "||" + REG_COPVAL + "||" + REG_RAZ;

    /**
     * Appelé sur l'évènement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tapé dans sa 'ligne de commande'
     */
    public void actionValider() {
        // recupération du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        // test via pattern si une commande est appelée
        Pattern testSiCom = Pattern.compile(REG_needCommande);
        Matcher matchSiCom = testSiCom.matcher(aRenvoyer);
        // test via pattern si besoin de calcul ou pas
        Pattern testSiCalc = Pattern.compile(REG_needCalc);
        Pattern testSiCalc2 = Pattern.compile(REG_needCalc2);
        Matcher matchSiCalc = testSiCalc.matcher(aRenvoyer);
        Matcher matchSiCalc2 = testSiCalc2.matcher(aRenvoyer);
        // test si un simpleaffichage peut être réalisé
        Pattern testSiAffich = Pattern.compile(REG_affich1);
        Pattern testSiAffich2 = Pattern.compile(REG_affich2);
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
                    this.fenetre.getModele().getValueAt(
                            coordInitInit[0], coordInitInit[1]),
                            coordFinalInit[0], coordFinalInit[1]);
            this.entrees[coordInitInit[0]][coordInitInit[1]]
                    = this.entrees[coordFinalInit[0]][coordFinalInit[1]];
            this.fenetre.getLabel().setText("Copie effectuée");
            this.fenetre.getConsole().setText("");
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
            this.fenetre.getConsole().setText("");
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
                this.fenetre.getConsole().setText("");
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
            //            for (int i = 0; i < siPlage.groupCount(); i++) {
            //               System.out.println(i + " " + siPlage.group(i)); 
            //            }
            /*
             * première case -> group(1)
             * seconde case -> group(10)
             */
            coordInit = recupCase(siPlage.group(1));
            coordFinal = recupCase(siPlage.group(10));
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
                this.fenetre.getConsole().setText("");
            }
        } else { // l'user n'a pas entré de plage
            coordInit = recupCase(aEffacer);
            if (coordInit != null) {
                this.fenetre.getModele().setValueAt(
                        "", coordInit[0], coordInit[1]);
                this.entrees[coordInit[0]][coordInit[1]] = "";
                this.fenetre.getLabel().setText("Case effacée"); 
                this.fenetre.getConsole().setText("");           
            } else { // erreur de syntaxe
                this.fenetre.getLabel().setText("Erreur de syntaxe"); 
            }
        }
    }

    /**
     * TODO commenter le rôle de la méthode
     * @param group
     * @param group2
     */
    private void copier(String group, String group2) {
        // TODO Auto-generated method stub
    }


    /**
     * Affichage de texte paramêtre, la position est récupérée automatiquement
     * depuis la ligne de commande
     * @param aAfficher String à afficher dans le tableur
     */
    private void affichage(String aAfficher) {
        int col = -1; // colonne de la case à modifier
        int lig = -1; // ligne de la case à modifier

        String aAfficherTraite = ""; // texte à afficher après traitement
        // test sur les deux pattern autorisés
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
            // on recupère la lettre et on la transforme en int utilisable
            col = lul.group(2).charAt(0) - 65;
            aAfficherTraite = String.valueOf(Utilitaires.calculEvolue(
                    this.prepareCalc(lul.group(7))));
            this.entrees[lig][col] = "=" + lul.group(7);
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getLabel().setText("Calcul effectué");
            } else {
                this.fenetre.getLabel().setText("Calcul non effectué");  
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
            // on recupère la lettre et on la transforme en int utilisable
            col = lil.group(6).charAt(0) - 65;
            aAfficherTraite = String.valueOf(Utilitaires.calculEvolue(
                    this.prepareCalc(lil.group(7))));
            this.entrees[lig][col] = "=" + lil.group(7);
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getLabel().setText("Calcul effectué");
            } else {
                this.fenetre.getLabel().setText("Calcul non effectué");
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
            // on recupère la lettre et on la transforme en int utilisable
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
            // on recupère la lettre et on la transforme en int utilisable
            col = lol.group(6).charAt(0) - 65;
            aAfficherTraite = lol.group(7);
            this.entrees[lig][col] = lol.group(7);
        }
        // else => lig = -1 et col = -1 => prochaine condition false

        if (lig > -1 & col > -1) { // La syntaxe est ok et tout est récupéré
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
     * prepare un calcul évolué en remplaçant les occurences de case par 
     * leurs valeurs, erreurs de syntaxe non vérifiées
     * @param aPreparer chaine à modifier
     * @return une chaine prête à être exploiter pour un calcul évolué
     */
    public String prepareCalc(String aPreparer) {
        String aRenvoyer = ""; // chaine transformée à renvoyer
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
     * Remplace chaque occurences de coordonnées de cases par leur valeur
     * correspondante
     * @param aRemplacer chaine qu'il faut modifier
     * @return une chaine de caractères utilisable pour les calculs
     */
    private String remplaceCases(String aRemplacer) {
        String chaineModifiee;      // chaine après modification
        String operandeTmp;         // dernière opérande récupérée à analyser
        int[] coordonnees; // coordonnées d'une opérande identifiée comme case
        String aRemplacerVerif;
        if (aRemplacer.length() > 0 && aRemplacer.charAt(0) == '-') {
            aRemplacerVerif = aRemplacer.substring(1);
            chaineModifiee = "-";
        } else {
            aRemplacerVerif = aRemplacer;
            chaineModifiee = "";
        }

        /*
         *  préparation d'un Scanner récupérant les opérandes
         *  note: si une opérande n'est pas valide elle ne sera pas gérée ici
         *  elle sera tout simplement ignorée, la vérification s'effectuera
         *  au moment du calcul
         */
        Scanner recupOperande = new Scanner(aRemplacerVerif);
        recupOperande.useDelimiter("\\s*[+*/-]\\s*");
        /*
         * préparation d'un Scanner récupérant les opérateur
         * note: si une opérande est manquante aucune erreur ne sera renvoyée
         * l'erreur sera détectée au moment du calcul
         */
        Scanner recupOperateur = new Scanner(aRemplacerVerif);
        recupOperateur.useDelimiter("[^+/*-]+");

        try {
            // inititialisation de chaineModifiee
            operandeTmp = recupOperande.next();
            if (operandeTmp.matches(REG_MODIF)) { // si case détectée
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
            // ajout du prochain opérateur
            try {
                operandeTmp = recupOperateur.next();
                chaineModifiee = chaineModifiee.concat(operandeTmp);
            } catch (NoSuchElementException e) { // rien n'est géré ici
            }
            // ajout de la prochaine opérande
            try {
                operandeTmp = recupOperande.next();
                if (operandeTmp.matches(REG_MODIF)) { // si case détectée
                    coordonnees = recupCase(operandeTmp);
                    // on récupère sa valeur et on la place dans la chaine
                    operandeTmp = String.valueOf(this.fenetre.getModele()
                            .getValueAt(coordonnees[0], coordonnees[1]));
                }
                chaineModifiee = chaineModifiee.concat(operandeTmp);
            } catch (NoSuchElementException e) { // rien n'est géré ici
            }
            //          System.out.println(chaineModifiee);
        }
        recupOperande.close();
        recupOperateur.close();
        return chaineModifiee;
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
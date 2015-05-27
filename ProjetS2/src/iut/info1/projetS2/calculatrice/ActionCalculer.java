/*
 * ActionCalculer.java					3 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import iut.info1.projetS2.utilitaires.CommandesMemoire;
import iut.info1.projetS2.utilitaires.Utilitaires;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * On calcule le résultat de la commande entrée, on affiche cette commande
 * et le résultat sur l'écran
 * @author Sébastien
 * @version 0.1
 */
public class ActionCalculer implements ActionListener {
    
    /** Champ de texte pour les exécutions de commandes */
    private ExecuteurCommandes executeurAssocie;

    /** Ecran où les commandes et leur résultat seront affichées */
    private Ecran ecran;

    /** Vérifie si on est en mode mémoire ou non */
    private static boolean modeMem;
    

    /**
     * Permettra de récupérer le texte présent dans l'ecran et l'executeur
     * de commande, et ainsi de le modifier
     * @param executeur
     * @param ecran 
     */
    public ActionCalculer(ExecuteurCommandes executeur, Ecran ecran) {

        this.executeurAssocie = executeur;
        this.ecran = ecran;

    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed
     * (java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // on récupère le texte du textfield exécuteur de commandes
        String commande = executeurAssocie.getText();
        
        // fonction qui pourra être utilisée pour la commande
        int fonctionok;
        // on insère la commande à l'écran
        ecran.insert(" " + commande + "\n", ecran.getText().length());
        


        if (!modeMem) {
            // on cherche une fonction non liée au mode mémoire et correspondant
            // à la commande
            fonctionok = fonctionAUtiliserNonMem(commande);

            // on vérifie si la commande ne dépasse pas les 75 caracteres
            if (commande.length() > 75) {
                fonctionok = 0;
            }          
            if (fonctionok == 1){       // commande MEM
                
                // on informe l'utilisateur qu'il passe en mode mémoire
                ecran.insert(" Mode mémoire actif.\n", 
                        ecran.getText().length());
                
            } else if (fonctionok == 2) {       // commande affectation
                
                double resultat = CommandesMemoire.affectation(commande);
                
                // resultat sous forme de chaîne
                String strresult = Double.toString(resultat);
                // on insère le résultat à l'écran
                // si le résultat se termine par .0, on l'enlève à l'affichage
                if (strresult == "NaN") {
                    ecran.insert(" Erreur de syntaxe d'affectation"
                            + ", entrez par exemple \"5 + 5 = A\"\n", 
                            ecran.getText().length());
                } else if (strresult.endsWith(".0")) {

                    strresult = strresult.substring(0, strresult.length() - 2); 

                    ecran.insert(" = " + strresult + "\n", 
                            ecran.getText().length());
                } else {
                    ecran.insert(" = " + resultat + "\n", 
                            ecran.getText().length());
                }

            } else if (fonctionok == 3) {       // commande calcul
                
                double resultat = Utilitaires.calculEvolue(commande);
                
                // resultat sous forme de chaîne
                String strresult = Double.toString(resultat);
                // on insère le résultat à l'écran
                // si le résultat se termine par .0, on l'enlève à l'affichage
                if (strresult == "NaN") {
                    ecran.insert(" Erreur de syntaxe de calcul"
                            + ", entrez par exemple \"5.12 * 12.08\"\n", 
                            ecran.getText().length());
                } else if (strresult.endsWith(".0")) {

                    strresult = strresult.substring(0, strresult.length() - 2); 

                    ecran.insert(" = " + strresult + "\n", 
                            ecran.getText().length());
                } else {
                    ecran.insert(" = " + resultat + "\n", 
                            ecran.getText().length());
                }
                
            } else if (fonctionok == 4) {       // commande calcul
                
                double resultat = CommandesMemoire.calculVariable(commande);
                
                // resultat sous forme de chaîne
                String strresult = Double.toString(resultat);
                // on insère le résultat à l'écran
                // si le résultat se termine par .0, on l'enlève à l'affichage
                if (strresult == "NaN") {
                    ecran.insert(" Erreur de calcul, une variable n'a pas été "
                            + "initialisée, ou\n une erreur de syntaxe a été "
                            + "commise. Entrez un calcul du type \"A+12\"\n", 
                            ecran.getText().length());
                } else if (strresult.endsWith(".0")) {

                    strresult = strresult.substring(0, strresult.length() - 2); 

                    ecran.insert(" = " + strresult + "\n", 
                            ecran.getText().length());
                } else {
                    ecran.insert(" = " + resultat + "\n", 
                            ecran.getText().length());
                }
                
            } else if (fonctionok == 0) {       // commande trop longue
                ecran.insert(" Erreur, la commande entrée ne doit pas dépasser"
                        + " les 80 caractères\n", ecran.getText().length());                
            } else {                            // commande inexistante
                ecran.insert(" Erreur, vous n'avez pas entré une commande "
                        + "correcte,\nappuyez sur Aide pour consulter les"
                        + " commandes disponibles\n", ecran.getText().length());
            }
            
        } else {
            
            // on cherche une fonction liée au mode mémoire et correspondant
            // à la commande
            fonctionok = fonctionAUtiliserMem(commande);
            
            // on vérifie si la commande ne dépasse pas les 80 caracteres
            if (commande.length() > 80) {
                fonctionok = 0;
            }  
            
            if (fonctionok == 1) {      // commande RAZ
                ecran.insert(CommandesMemoire.raz(commande), 
                        ecran.getText().length());
            } else if (fonctionok == 2) {       // commande VOIR
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(CommandesMemoire.voir(commande), 
                        ecran.getText().length());
            } else if (fonctionok == 3) {       // commande QUIT
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(" Mode mémoire inactif.\n", 
                        ecran.getText().length());
            } else if (fonctionok == 4) {
                CommandesMemoire.aide(commande);// commande AIDE
            } else if (fonctionok == 5) { // commande INCR
                ecran.insert(CommandesMemoire.incr(commande), 
                        ecran.getText().length());
            } else if (fonctionok == 6) { // commande CAR
                ecran.insert(CommandesMemoire.car(commande),
                        ecran.getText().length());
            } else if (fonctionok == 7) {// commande SQRT
                ecran.insert(CommandesMemoire.sqrt(commande),
                        ecran.getText().length());
            } else if (fonctionok == 8) {       // commande SOM
                // on stocke le résultat de la somme
                double resultat = CommandesMemoire.som(commande);
                
                // resultat sous forme de chaîne
                String strresult = Double.toString(resultat);
                // on insère le résultat à l'écran
                // si le résultat se termine par .0, on l'enlève à l'affichage
                if (strresult == "NaN") {
                    ecran.insert(" Erreur de syntaxe de calcul"
                            + ", entrez par exemple \"SOM A..C\"\n"
                            + " (il se peut que des variables n'aient pas été"
                            + " initialisées)\n", 
                            ecran.getText().length());
                } else if (strresult.endsWith(".0")) {

                    strresult = strresult.substring(0, strresult.length() - 2); 

                    ecran.insert(" = " + strresult + "\n", 
                            ecran.getText().length());
                } else {
                    ecran.insert(" = " + resultat + "\n", 
                            ecran.getText().length());
                }
                
            } else if (fonctionok == 9) {       // commande PROD
                // on stocke le résultat du produit
                double resultat = CommandesMemoire.prod(commande);
                
                // resultat sous forme de chaîne
                String strresult = Double.toString(resultat);
                // on insère le résultat à l'écran
                // si le résultat se termine par .0, on l'enlève à l'affichage
                if (strresult == "NaN") {
                    ecran.insert(" Erreur de syntaxe de calcul"
                            + ", entrez par exemple \"PROD A..C\"\n"
                            + " (il se peut que des variables n'aient pas été"
                            + " initialisées)\n", 
                            ecran.getText().length());
                } else if (strresult.endsWith(".0")) {

                    strresult = strresult.substring(0, strresult.length() - 2); 

                    ecran.insert(" = " + strresult + "\n", 
                            ecran.getText().length());
                } else {
                    ecran.insert(" = " + resultat + "\n", 
                            ecran.getText().length());
                }
                
            } else if (fonctionok == 10) {      // commande MOY
                // on stocke le résultat de la moyenne
                double resultat = CommandesMemoire.moy(commande);
                
                // resultat sous forme de chaîne
                String strresult = Double.toString(resultat);
                // on insère le résultat à l'écran
                // si le résultat se termine par .0, on l'enlève à l'affichage
                if (strresult == "NaN") {
                    ecran.insert(" Erreur de syntaxe de calcul"
                            + ", entrez par exemple \"MOY A..C\"\n"
                            + " (il se peut que des variables n'aient pas été"
                            + " initialisées)\n", 
                            ecran.getText().length());
                } else if (strresult.endsWith(".0")) {

                    strresult = strresult.substring(0, strresult.length() - 2); 

                    ecran.insert(" = " + strresult + "\n", 
                            ecran.getText().length());
                } else {
                    ecran.insert(" = " + resultat + "\n", 
                            ecran.getText().length());
                }
                
            } else if (fonctionok == 11) {      // commande INIT
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(CommandesMemoire.init(commande), 
                        ecran.getText().length());
            } else if (fonctionok == 12) {      // commande ADD
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(CommandesMemoire.add(commande), 
                        ecran.getText().length());
            } else if (fonctionok == 13) {      // commande MUL
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(CommandesMemoire.mul(commande), 
                        ecran.getText().length());
            } else if (fonctionok == 14) {      // commande EXP
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(CommandesMemoire.exp(commande), 
                        ecran.getText().length());
            } else {
                ecran.insert(" Erreur, appuyez sur aide pour consulter les"
                        + " commandes disponibles\n", ecran.getText().length());
            } 
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");
        

    }
    
    /**
     * On va tester quelle méthode fonctionne avec la commande entrée, si on
     * est en mode normal
     * @param commande la commande entrée
     * @return fonctionok le numéro de la fonction à utiliser
     */
    public static int fonctionAUtiliserNonMem (String commande) {
        
        int fonctionok = -1;
        // on créé des patterns permettant de détecter chaque fonction 
        // utilisable
        Pattern patMem = Pattern.compile("(\\s*)MEM(\\s*)");
        Matcher memok = patMem.matcher(commande);
        
        Pattern patAffect = Pattern.compile(".*(=\\s*[A-Z]\\s*)");
        Matcher affectok = patAffect.matcher(commande);
        
        Pattern patCalcul = 
           Pattern.compile("(\\s*-?(\\d).*(\\d)*)||(.*[(].*[)].*)");
        Matcher calculok = patCalcul.matcher(commande);
        
        Pattern PatCalcVar = Pattern.compile(".*[A-Z].*");
        Matcher calcvarok = PatCalcVar.matcher(commande);
        
        if (memok.matches()) {
            fonctionok = 1;     // MEM
            modeMem = true;
        } else if (affectok.matches()) {
            fonctionok = 2;     // affectation (15+15 = A)
        } else if (calcvarok.matches()) {
            fonctionok = 4;     // calcul avec variables
        } else if (calculok.matches()){
            fonctionok = 3;     // calcul normal
        }

        return fonctionok;
    }
    
    /**
     * On va tester quelle méthode fonctionne avec la commande entrée, si on
     * est en mode Mémoire
     * @param commande la commande entrée
     * @return fonctionok le numéro de la fonction à utiliser
     */
    public static int fonctionAUtiliserMem (String commande) {
        
        int fonctionok = 0;
        // on créé des patterns permettant de détecter chaque fonction 
        // utilisable
        Pattern patRaz = Pattern.compile("(\\s*)RAZ.*");
        Matcher razok = patRaz.matcher(commande);
        
        Pattern patVoir = Pattern.compile("\\s*VOIR.*");
        Matcher voirok = patVoir.matcher(commande);
        
        Pattern patQuit = Pattern.compile("\\s*QUIT\\s*");
        Matcher quitok = patQuit.matcher(commande);
        
        Pattern patAide = Pattern.compile("\\s*AIDE\\s*");
        Matcher aideok = patAide.matcher(commande);
        
        Pattern patIncr = Pattern.compile("\\s*INCR.*");
        Matcher incrok = patIncr.matcher(commande);
        
        Pattern patCar = Pattern.compile("\\s*CAR.*");
        Matcher carok = patCar.matcher(commande);
        
        Pattern patSqrt = Pattern.compile("\\s*SQRT.*");
        Matcher sqrtok = patSqrt.matcher(commande);
        
        Pattern patSom = Pattern.compile("\\s*SOM.*");
        Matcher somok = patSom.matcher(commande);
        
        Pattern patProd = Pattern.compile("\\s*PROD.*");
        Matcher prodok = patProd.matcher(commande);
        
        Pattern patMoy = Pattern.compile("\\s*MOY.*");
        Matcher moyok = patMoy.matcher(commande);
        
        Pattern patInit = Pattern.compile("(\\s*)INIT.*");
        Matcher initok = patInit.matcher(commande);
        
        Pattern patAdd = Pattern.compile("(\\s*)ADD.*");
        Matcher addok = patAdd.matcher(commande);
        
        Pattern patMul = Pattern.compile("(\\s*)MUL.*");
        Matcher mulok = patMul.matcher(commande);
        
        Pattern patExp = Pattern.compile("(\\s*)EXP.*");
        Matcher expok = patExp.matcher(commande);
        
        if (razok.matches()) {
            fonctionok = 1;     // RAZ
        } else if (voirok.matches()) {
            fonctionok = 2;     // MEM
        } else if (quitok.matches()) {
            fonctionok = 3;     // QUIT
            modeMem = false;
        } else if (aideok.matches()) {
            fonctionok = 4;     // AIDE
        } else if (incrok.matches()) {
            fonctionok = 5;     // INCR
        } else if (carok.matches()) {
            fonctionok = 6;     // CAR
        } else if (sqrtok.matches()) {
            fonctionok = 7;     // SQRT
        } else if (somok.matches()) {
            fonctionok = 8;     // SOM
        } else if (prodok.matches()) {
            fonctionok = 9;     // PROD
        } else if (moyok.matches()) {
            fonctionok = 10;
        } else if(initok.matches()) {
            fonctionok = 11;    // INIT
        } else if (addok.matches()) {
            fonctionok = 12;    // ADD
        } else if (mulok.matches()) {
            fonctionok = 13;    // MUL
        } else if (expok.matches()) {
            fonctionok = 14;    // EXP
        }
        return fonctionok;
    }
    
}

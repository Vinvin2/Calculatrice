/*
 * CommandesMemoire.java					19 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.calculatrice.Aide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regroupe toutes les commandes qui modifieront les cases m�moires 
 * de la calculatrice
 * @author S�bastien
 * @version 1.0
 */
public class CommandesMemoire {

    /** Tableau contenant toutes les cases m�moires */
    private static Variable[] casesMem = new Variable[26];

    /**
     * Si la commande est une affectation de type 15 + 4 = A, alors cette
     * m�thode est utilis�e. On r�alise l'op�ration demand�e, puis on affecte
     * le r�sultat � la variable concern�e
     * @param commande la commande � traiter
     * @return le r�sultat de la commande
     */
    public static double affectation (String commande) {
        String calculARealiser; // le calcul que l'on devra effectuer
        double resultat;        // son r�sultat
        char nomvar;            // le nom de la variable � affecter


        // TODO : moyen de prendre en compte les espaces
        calculARealiser = commande.substring(0, commande.length() - 4);

        // on fait le calcul
        resultat = Utilitaires.calculIntermediaire(calculARealiser);

        // on prend le nom de la variable
        nomvar = commande.charAt(commande.length()-1);

        // on v�rifie si la variable n'a jamais �t� initialis�e
        if (casesMem[nomvar - 65] == null) {
            // on cr�� alors cette variable
            casesMem[nomvar - 65] = new Variable(nomvar, resultat);
        } else {
            // sinon on met � jour sa valeur
            casesMem[nomvar - 65].setValeur(resultat);
        }
        return resultat;
    }

    /**
     * Remet � z�ro les cases m�moires sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si raz s'est bien pass�, une erreur sinon
     */
    public static String raz(String commande) {
        // on regarde si le pattern convient
        Pattern patRazSimple = Pattern.compile("(\\s*RAZ\\s*)([A-Z])\\s*");
        Pattern patRazMultiple = 
                Pattern.compile("(\\s*RAZ\\s*)([A-Z]..[A-Z])\\s*");
        Matcher razok = patRazSimple.matcher(commande);

        if (razok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable � initialiser
            nomvar = razok.group(2).charAt(0);
            // on l'initialise
            casesMem[nomvar - 65] = new Variable(nomvar, 0);
            return "OK\n";
        } else {

            razok = patRazMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les remet toutes � z�ro
            if (razok.matches()) {
                char nomvar1 = razok.group(2).charAt(0);
                char nomvar2 = razok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        casesMem[nomvar - 65] = new Variable(nomvar, 0);
                    }              
                } else {
                    return "Erreur, entrez une commande du type RAZ A..B\n";
                }
                return "OK\n";
            } else {
                return "Erreur, entrez une commande du type RAZ A..B\n";
            }
        }
    }

    /**
     * Affiche les noms et valeurs des variables sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     * @return les variables � afficher et leur valeur, ou une erreur si la 
     * commande n'a pas �t� entr�e correctement
     */
    public static String voir(String commande) {

        String aRetourner = "";
        // on regarde si le pattern convient
        Pattern patVoirSimple = Pattern.compile("(\\s*VOIR\\s*)([A-Z])\\s*");
        Pattern patVoirMultiple = 
                Pattern.compile("(\\s*VOIR\\s)*([A-Z]..[A-Z])\\s*");
        Matcher voirok = patVoirSimple.matcher(commande);

        if (voirok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable � afficher
            nomvar = voirok.group(2).charAt(0);
            // on l'initialise
            if (casesMem[nomvar - 65] != null) {
                aRetourner = casesMem[nomvar - 65].toString();
            } else {
                aRetourner = "Variable " + nomvar + " non d�clar�e";
            }
            return aRetourner;
        } else {
            voirok = patVoirMultiple.matcher(commande);
            // lorsqu'on a plusieurs cases en param�tres, on les affiches toutes
            if (voirok.matches()) {
                char nomvar1 = voirok.group(2).charAt(0);
                char nomvar2 = voirok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            aRetourner = aRetourner.concat
                                    (casesMem[nomvar - 65].toString() + "\n");
                        } else {
                            aRetourner = aRetourner.concat
                                    ("Variable " + nomvar + " non d�clar�e\n");
                        }
                    }  
                    return aRetourner;
                } else {
                    return "Erreur, entrez une commande du type "
                            + "\"VOIR A..G\"\n";
                }
            } else {
                return "Erreur, entrez une commande du type \"VOIR A..G\"\n";
            }
        }

    }

    /**
     * Ajoute 1 aux variables sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande a �t� entr�e correctement, une erreur sinon
     */
    public static String incr(String commande) {
        // on regarde si le pattern convient
        Pattern patIncrSimple = Pattern.compile("(\\s*INCR\\s*)([A-Z])\\s*");
        Pattern patIncrMultiple = 
                Pattern.compile("(\\s*INCR\\s*)([A-Z]..[A-Z])\\s*");
        Matcher incrok = patIncrSimple.matcher(commande);

        if (incrok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable � incr�menter
            nomvar = incrok.group(2).charAt(0);
            // on y ajoute 1 si la variable a �t� initialis�e
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                (casesMem[nomvar - 65].getValeur() + 1);
                return "OK\n";
            } else {
                return "Erreur, cette variable ne peut pas �tre"
                        + " incr�ment�e car elle\nn'est pas "
                        + "initialis�e\n";    
            }

        } else {
            incrok = patIncrMultiple.matcher(commande);
            // lorsqu'on a plusieurs cases en param�tres, on les incr toutes
            if (incrok.matches()) {
                char nomvar1 = incrok.group(2).charAt(0);
                char nomvar2 = incrok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            casesMem[nomvar - 65].setValeur
                            (casesMem[nomvar - 65].getValeur() + 1);
                        } else {
                            nomvar = nomvar2;
                            return "Attention, des variables n'ont pas pu �tre"
                            + " incr�ment�es car elles\nn'�taient pas "
                            + "initialis�es\n";
                        }
                    }
                    return "OK\n";
                } else {
                    return "Erreur, entrez les cases m�moires dans l'ordre "
                            + "alphab�tique : ex : \"INCR A..G\"\n";
                }
            } else {
                return "Erreur, entrez une commande du type \"INCR A..G\"\n";
            }
        }
    }

    /**
     * Affiche la fen�tre d'aide
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void aide(String commande) {
        // on regarde si le pattern convient
        Pattern patAide = Pattern.compile("\\s*AIDE\\s*");
        Matcher aideok = patAide.matcher(commande);
        // on affiche la fen�tre d'aide si c'est le cas
        if (aideok.matches()) {
            new Aide();
        }
    }

    /**
     * fonction CAR : modifie la valeur des variables sp�cifi�es pour leur 
     * affecter leur valeur carr�
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande a �t� entr�e correctement, une erreur sinon
     */
    public static String car(String commande) {
        // on regarde si le pattern convient
        Pattern patCarSimple = Pattern.compile("(\\s*CAR\\s*)([A-Z])\\s*");
        Pattern patCarMultiple = 
                Pattern.compile("(\\s*CAR\\s*)([A-Z]..[A-Z])\\s*");
        Matcher carok = patCarSimple.matcher(commande);

        if (carok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable � traiter
            nomvar = carok.group(2).charAt(0);
            // on la met au carr� si la variable a �t� initialis�e
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                (casesMem[nomvar - 65].getValeur()
                        * casesMem[nomvar - 65].getValeur());
                return "OK\n";
            } else {
                return "Erreur, cette variable n'est pas initialis�e\n";
            }
        } else {

            carok = patCarMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les met toutes au carr�
            if (carok.matches()) {
                char nomvar1 = carok.group(2).charAt(0);
                char nomvar2 = carok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            casesMem[nomvar - 65].setValeur
                            (casesMem[nomvar - 65].getValeur()
                                    * casesMem[nomvar - 65].getValeur());
                        } else {
                            nomvar = nomvar2;
                            return "Attention, des variables n'ont pas pu �tre"
                            + " trait�es car elles\nn'�taient pas "
                            + "initialis�es\n";
                        }
                    }
                    return "OK\n";
                } else {
                    return "Erreur, entrez les cases m�moires dans l'ordre "
                            + "alphab�tique : ex : \"INCR A..G\"\n";
                }
            } else {
                return "Erreur, entrez une commande du type : \"CAR A..G\"\n";
            }
        }
    }

    /**
     * fonction SQRT : modifie la valeur des variables sp�cifi�es pour leur 
     * affecter leur racine carr�e
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande est correcte, une erreur sinon
     */
    public static String sqrt(String commande) {
        // on regarde si le pattern convient
        Pattern patSqrtSimple = Pattern.compile("(\\s*SQRT\\s*)([A-Z])\\s*");
        Pattern patSqrtMultiple = 
                Pattern.compile("(\\s*SQRT\\s*)([A-Z]..[A-Z])\\s*");
        Matcher sqrtok = patSqrtSimple.matcher(commande);

        if (sqrtok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable � traiter
            nomvar = sqrtok.group(2).charAt(0);
            // on la met � la racine carr�e si la variable a �t� initialis�e
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                (Math.sqrt(casesMem[nomvar - 65].getValeur()));
                return "OK\n";
            } else {
                return "Erreur, cette commande ne peut pas �tre trait�e car "
                        + "la variable n'a pas �t� initialis�e\n";
            }
        } else {

            sqrtok = patSqrtMultiple.matcher(commande);
            // lorsqu'on a plusieurs cases en param�tres, on les met toutes 
            // � la racine carr�e
            if (sqrtok.matches()) {
                char nomvar1 = sqrtok.group(2).charAt(0);
                char nomvar2 = sqrtok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            casesMem[nomvar - 65].setValeur
                            (Math.sqrt(casesMem[nomvar - 65].getValeur()));
                        } else {
                            nomvar = nomvar2;
                            return "Attention, des variables n'ont pas pu �tre"
                            + " incr�ment�es car elles\nn'�taient pas "
                            + "initialis�es\n";
                        }
                    }
                    return "OK\n";
                } else {
                    return "Erreur, veuillez entrer les variables dans l'ordre"
                            + " alphab�tique : ex : \"SQRT A..G\"\n"; 
                }
            } else {
                return "Erreur, veuillez entrer une commande du type :"
                        + " \"SQRT A..G\"\n";
            }
        }
    }

    /**
     * fonction SOM : effectue la somme des variables sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     * @return la somme obtenue
     */
    public static double som(String commande) {
        // on regarde si le pattern convient
        Pattern patSom = Pattern.compile("(\\s*SOM\\s*)([A-Z]..[A-Z])\\s*");

        Matcher somok = patSom.matcher(commande);
        double resultat;        // resultat de la somme � renvoyer
        resultat = 0;

        // si c'est le cas, on va effectuer la somme des variables concern�es
        if (somok.matches()) {
            // noms des variables entr�es en param�tre
            char nomvar1 = somok.group(2).charAt(0);
            char nomvar2 = somok.group(2).charAt(3);

            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    if (casesMem[nomvar - 65] != null) {
                        resultat += casesMem[nomvar - 65].getValeur();
                    }
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        resultat += casesMem[nomvar - 65].getValeur();
                    }
                } 
            }
        }

        return resultat;
    }

    /**
     * fonction PROD : effectue le produit des variables sp�cifi�es et le
     * renvoie
     * @param commande la commande entr�e par l'utilisateur
     * @return le produit obtenu
     */
    public static double prod(String commande) {
        // on regarde si le pattern convient
        Pattern patProd = Pattern.compile("(\\s*PROD\\s*)([A-Z]..[A-Z])\\s*");

        Matcher prodok = patProd.matcher(commande);
        double resultat;        // resultat de la somme � renvoyer
        resultat = 1;

        // si c'est le cas, on va effectuer la somme des variables concern�es
        if (prodok.matches()) {
            // noms des variables entr�es en param�tre
            char nomvar1 = prodok.group(2).charAt(0);
            char nomvar2 = prodok.group(2).charAt(3);

            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {

                    if (casesMem[nomvar - 65] != null) {
                        resultat *= casesMem[nomvar - 65].getValeur();
                    }
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        resultat *= casesMem[nomvar - 65].getValeur();
                    }
                } 
            }
        }

        return resultat;
    }

    /**
     * fonction MOY : effectue la moyenne des variables sp�cifi�es et la
     * renvoie
     * @param commande la commande entr�e par l'utilisateur
     * @return le produit obtenu
     */
    public static double moy(String commande) {
        // on regarde si le pattern convient
        Pattern patMoy = Pattern.compile("(\\s*MOY\\s*)([A-Z]..[A-Z])\\s*");

        Matcher moyok = patMoy.matcher(commande);
        double resultat;        // resultat de la somme � renvoyer
        resultat = 0;


        // si c'est le cas, on va effectuer la moyenne des variables concern�es
        if (moyok.matches()) {
            // noms des variables entr�es en param�tre
            char nomvar1 = moyok.group(2).charAt(0);
            char nomvar2 = moyok.group(2).charAt(3);

            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {

                    if (casesMem[nomvar - 65] != null) {
                        resultat += casesMem[nomvar - 65].getValeur();
                    } else {
                        resultat = Double.NaN;
                    }
                } 
                resultat = resultat / (nomvar2 - nomvar1 + 1);
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        resultat += casesMem[nomvar - 65].getValeur();
                    } else {
                        resultat = Double.NaN;
                    }
                } 
                resultat = resultat / (nomvar1 - nomvar2 + 1);
            }
        }

        return resultat;
    }

    /**
     * fonction INIT : initialise les variables sp�cifi�es � la valeur sp�cifi�e
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande a �t� entr�e correctement, une erreur sinon
     */
    public static String init(String commande) {
        // on regarde si le pattern convient
        Pattern patInitSimple = 
       Pattern.compile("(\\s*INIT\\s*)([A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");        
        Pattern patInitMultiple = 
Pattern.compile("(\\s*INIT\\s*)([A-Z]..[A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");

        Matcher initok = patInitSimple.matcher(commande);


        if (initok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable � initialiser
            nomvar = initok.group(2).charAt(0);
            // on l'initialise
            casesMem[nomvar - 65] = new Variable(nomvar, 
                    Double.parseDouble(initok.group(3)));
            return "OK\n";
        } else {

            initok = patInitMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les remet toutes � z�ro
            if (initok.matches()) {
                char nomvar1 = initok.group(2).charAt(0);
                char nomvar2 = initok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        casesMem[nomvar - 65] = new Variable(nomvar, 
                                Double.parseDouble(initok.group(3)));
                    }
                    return "OK\n";
                } else {
                    return "Erreur, veuillez entrer les variables dans l'ordre"
                            + " alphab�tique : ex : \"INIT A..G 10.17\"\n"; 
                }
            } else {
                return "Erreur, veuillez entrer une commande du type "
                        + "\"INIT A..D 2\"\n";
            }
        }
    }

    /**
     * fonction ADD : Ajoute la somme sp�cifi�e aux valeurs des 
     * variables sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande a �t� entr�e correctement, une erreur sinon
     */
    public static String add(String commande) {
        // on regarde si le pattern convient
        Pattern patAddSimple = 
        Pattern.compile("(\\s*ADD\\s*)([A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");        
        Pattern patAddMultiple = 
 Pattern.compile("(\\s*ADD\\s*)([A-Z]..[A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");

        Matcher addok = patAddSimple.matcher(commande);


        if (addok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable dont on doit modifier la valeur
            nomvar = addok.group(2).charAt(0);
            // on modifie sa valeur en ajoutant le r�el sp�cifi�
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].
                setValeur(casesMem[nomvar - 65].getValeur() +
                        Double.parseDouble(addok.group(3)));
                return "OK\n";
            } else {
                return "Erreur, la variable entr�e n'est pas initialis�e\n";
            }
        } else {

            addok = patAddMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on modifie chacune de leur
            // valeur
            if (addok.matches()) {
                char nomvar1 = addok.group(2).charAt(0);
                char nomvar2 = addok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            casesMem[nomvar - 65].setValeur
                            (casesMem[nomvar - 65].getValeur() +
                                    Double.parseDouble(addok.group(3)));
                        } else {
                            nomvar = nomvar2;
                            return "Attention, des variables n'ont pas pu �tre "
                            + "trait�es car elles n'ont pas �t� "
                            + "initialis�es \n";
                        }
                    }              
                    return "OK\n";
                } else {
                    return "Erreur, veuillez entrer les variables dans l'ordre"
                            + " alphab�tique : ex : \"ADD A..G 5\"\n"; 
                }
            } else {
                return "Erreur, veuillez entrer une commande du type "
                        + "\"ADD A..D 4\"\n";
            }
        }
    }

    /**
     * fonction MUL : Multiplie les valeurs des variables sp�cifi�es par le r�el
     * sp�cifi�
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande a �t� correctement entr�e, une erreur sinon
     */
    public static String mul(String commande) {
        // on regarde si le pattern convient
        Pattern patMulSimple = 
        Pattern.compile("(\\s*MUL\\s*)([A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");        
        Pattern patMulMultiple = 
 Pattern.compile("(\\s*MUL\\s*)([A-Z]..[A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");

        Matcher mulok = patMulSimple.matcher(commande);


        if (mulok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable dont on doit modifier la valeur
            nomvar = mulok.group(2).charAt(0);
            // on modifie sa valeur en ajoutant le r�el sp�cifi�
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].
                setValeur(casesMem[nomvar - 65].getValeur() *
                        Double.parseDouble(mulok.group(3)));
                return "OK\n";
            } else {
                return "Attention, des variables n'ont pas pu �tre "
                        + "trait�es car elles n'ont pas �t� "
                        + "initialis�es\n";
            }
        } else {

            mulok = patMulMultiple.matcher(commande);
            // lorsqu'on a plusieurs cases en param�tres, on modifie chacune de leur
            // valeur
            if (mulok.matches()) {
                char nomvar1 = mulok.group(2).charAt(0);
                char nomvar2 = mulok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {

                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            casesMem[nomvar - 65].setValeur
                            (casesMem[nomvar - 65].getValeur() *
                                    Double.parseDouble(mulok.group(3)));
                        } else {
                            nomvar = nomvar2;
                            return "Attention, des variables n'ont pas pu �tre "
                            + "trait�es car elles n'ont pas �t� "
                            + "initialis�es \n";
                        }
                    }              
                    return "OK\n";
                } else {
                    return "Erreur, veuillez entrer les variables dans l'ordre"
                            + " alphab�tique : ex : \"MUL A..G 4\"\n"; 
                }
            } else {
                return "Erreur, veuillez entrer une commande du type "
                        + "\"MUL A..D 5\"\n";
            }
        }
    }

    /**
     * fonction EXP : Modifie la valeur d'une variable pour y ins�rer cette 
     * valeur � la puissance sp�cifi�e
     * @param commande la commande entr�e par l'utilisateur
     * @return OK si la commande a �t� correctement ex�cut�e, une erreur sinon
     */
    public static String exp(String commande) {
        // on regarde si le pattern convient
        Pattern patExpSimple = 
        Pattern.compile("(\\s*EXP\\s*)([A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");        
        Pattern patExpMultiple = 
 Pattern.compile("(\\s*EXP\\s*)([A-Z]..[A-Z])\\s*([+-]?\\d+(\\0056\\d+)?)\\s*");

        Matcher expok = patExpSimple.matcher(commande);


        if (expok.matches()) {
            char nomvar;    

            // on r�cup�re le nom de la variable dont on doit modifier la valeur
            nomvar = expok.group(2).charAt(0);
            // on modifie sa valeur en ajoutant le r�el sp�cifi�
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                (Math.pow(casesMem[nomvar - 65].getValeur(),
                        Double.parseDouble(expok.group(3))));
                return "OK\n";
            } else {
                return "Attention, des variables n'ont pas pu �tre "
                        + "trait�es car elles n'ont pas �t� "
                        + "initialis�es \n";
            }
        } else {

            expok = patExpMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on modifie chacune de leur
            // valeur
            if (expok.matches()) {
                char nomvar1 = expok.group(2).charAt(0);
                char nomvar2 = expok.group(2).charAt(3);

                if (nomvar1 <= nomvar2) {
                    for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                        if (casesMem[nomvar - 65] != null) {
                            casesMem[nomvar - 65].setValeur
                            (Math.pow(casesMem[nomvar - 65].getValeur(),
                                    Double.parseDouble(expok.group(3))));
                        } else {
                            nomvar = nomvar2;
                            return "Attention, des variables n'ont pas pu �tre "
                            + "trait�es car elles n'ont pas �t� "
                            + "initialis�es \n";
                        }
                    }              
                    return "OK\n";
                } else {
                    return "Erreur, veuillez entrer les variables dans l'ordre"
                            + " alphab�tique : ex : \"EXP A..G 5\"\n"; 
                }
            } else {
                return "Erreur, veuillez entrer une commande du type "
                        + "\"EXP A..D 5\"\n";
            }
        }
    }
}

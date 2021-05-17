/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

import arbres.expr.Expr;
import arbres.expr.OpBinaire;

/**
 *
 * @author Priscilla
 */
public class Calculator {

    // attributs, au lieu de variables locales à la méthode
    private Tokenizer tokenizer;
    private Token token;
    public TableVariables tables = new TableVariables();
    

    /**
     * Permet de faire tous les calculs nécéssaire
     *
     * @param line la ligne entrée par l'utilisateur
     * @return le résultat
     * @throws SyntaxErrorException l'erreur de syntaxe
     * @throws EvaluationErrorException l'erreur de calcul
     */
    public Expr evaluation(String line)
            throws SyntaxErrorException, EvaluationErrorException {
        tokenizer = new Tokenizer(line);
        token = tokenizer.get();
        Expr value = get_expr_value();
        checkSyntax(token.isEnd(), "Ce n'est pas fini");
        return value;
    }

    private Expr get_expr_value() throws SyntaxErrorException {
        Expr total = get_factor_value();
        while (token.isSymbol("+") || token.isSymbol("-")) {
            boolean add = token.isSymbol("+");
            token = tokenizer.get();
            if (add) {
                total = Expr.binaire(total, OpBinaire.PLUS, get_factor_value());
            } else {
                total = Expr.binaire(total, OpBinaire.MOINS, get_factor_value());
            }
        }
        return total;
    }

    private int get_number_value() throws SyntaxErrorException {
        checkSyntax(token.isNumber(), "Number expected");
        int value = token.value();
        token = tokenizer.get();
        return value;
    }

    private Expr get_factor_value() throws SyntaxErrorException {
        Expr total = get_parenthese_value();
        //System.out.println("Total debut : "+total.valeur(tables));
        while (token.isSymbol("*") || token.isSymbol("/")) {
            boolean mul = token.isSymbol("*");
            token = tokenizer.get();
            Expr denominateur = get_parenthese_value();
            if (mul) {
                total = Expr.binaire(total, OpBinaire.MULT,denominateur);
            } else {
                checkArithmetic(denominateur.valeur(tables) != 0, "Division par 0 impossible");
                total = Expr.binaire(total, OpBinaire.DIV,denominateur);
            }
        }
       // System.out.println("Total fin : "+total.valeur(tables));
        return total;
    }

    private Expr get_parenthese_value() throws SyntaxErrorException, EvaluationErrorException {
        Expr total = Expr.constante(0);
        boolean facteur = false;
        boolean negatif = false;
        if (token.isSymbol("-")) {
            negatif = true;
            token = tokenizer.get();
        }
        //Traite les nombre
        if (token.isNumber()) {
            total = Expr.constante(get_number_value());
            facteur = true;
        } //Traite les variables
        else if (token.isWord()) {
            total = get_variable_value();
            facteur = true;
        } //Traite les parenthèses
        else if (token.isSymbol("(")) {
            token = tokenizer.get();
            total = get_expr_value();
            if (token.isSymbol(")")) {
                token = tokenizer.get();
                facteur = true;
            }
        }
        checkSyntax(facteur, "Number expected!!!!");
        if (negatif) {
            return Expr.binaire(total, OpBinaire.MULT, Expr.constante(-1));
        }
        return total;
    }

    private Expr get_variable_value() throws SyntaxErrorException {
        //Expr total = Expr.constante(0);
        String variable = token.word();
        token = tokenizer.get();
        if (token.isSymbol("=")) {
            token = tokenizer.get();
            Expr total = get_expr_value();
            if (tables.varPresente(variable)) {
                tables.remplacer(variable, total.valeur(tables));
            } else {
                tables.affecter(variable, total.valeur(tables));
            }
            return Expr.affectation(variable, total);
        }
        checkArithmetic(tables.varPresente(variable), "La variable n'existe pas");
        return Expr.variable((variable));
    }

    /**
     * Verifie si la syntaxe est bonne
     *
     * @param verification vérifie si les conditions sont valides
     * @param messageErreur le message d'erreur
     * @throws SyntaxErrorException l'erreur de syntaxe
     */
    void checkSyntax(boolean verification, String messageErreur) throws SyntaxErrorException {
        if (!verification) {
            throw new SyntaxErrorException(messageErreur);
        }
    }

    void checkArithmetic(boolean verification, String messageErreur) throws EvaluationErrorException {
        if (!verification) {
            throw new EvaluationErrorException(messageErreur);
        }
    }

    boolean differentZero(Expr nb) {
        return nb != Expr.constante(0);
    }
}

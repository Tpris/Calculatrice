/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

/**
 *
 * @author Priscilla
 */
public class Token {

    final TokenType type;
    final String string;

    /**
     * Constructeur
     *
     * @param type le type de l'élément
     * @param string l'élément
     */
    public Token(TokenType type, String string) {
        this.type = type;
        this.string = string;
    }

    /**
     * Verifie si c'est un nombre
     *
     * @return vrai si c'est un nombre sinon faux
     */
    public boolean isNumber() {
        return type == TokenType.NUMBER;
    }

    /**
     * Converti le chiffre de la chaine en valeur numerique
     *
     * @return la valeur du chiffre
     */
    int value() {
        return Integer.parseInt(string);
    }

    /**
     * Verifie si c'est un symbole et le compare à un symbole donné
     *
     * @param string le symbole avec qui il est comparé
     * @return vrai si c'est le symbole cherché sinon faux
     */
    boolean isSymbol(String string) {
        return type == TokenType.SYMBOL && this.string.equals(string);
    }

    /**
     * Verifie si c'est la fin
     *
     * @return vrai si c'est la fin sinon faux
     */
    public boolean isEnd() {
        return type == TokenType.END;
    }

    boolean isWord() {
        return type == TokenType.WORD;
    }

    String word() {
        return this.string;
    }
}

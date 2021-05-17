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
class Tokenizer implements Supplier<Token> {

    final String SYMBOLS = "+-*/()=";
    String line;
    int next;

    /**
     * Constructeur
     *
     * @param line la ligenentrée par l'utilisateur
     */
    public Tokenizer(String line) {
        this.line = line;
        this.next = 0;
    }

    /**
     * Retourne les éléments de la chaine sous forme de Token
     *
     * @return un caractère et son type = un nouvel objet Token
     */
    @Override
    public Token get() {
        // ignorer les espaces
        while (next < line.length()
                && Character.isSpaceChar(line.charAt(next))) {
            next++;
        }
        // on est au bout ?
        if (next >= line.length()) {
            return new Token(TokenType.END, "");
        }
        // délégation du travail à une méthode par type,
        // déterminé par le premier caractère

        char first = line.charAt(next);
        if (Character.isDigit(first)) {
            return getNumber();
        } else if (Character.isLetter(first)) {    // début de mot
            return getWord();                      //
        } else if (SYMBOLS.indexOf(first) >= 0) {
            return getSymbol();
        } else {
            next++;
            return new Token(TokenType.INVALID, Character.toString(first));
        }
    }

    /**
     * retourne un nouveau Token de type NUMBER
     *
     * @return un Token de type NUMBER
     */
    private Token getNumber() {
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(line.charAt(next));
            next++;
        } while (next < line.length()
                && Character.isDigit(line.charAt(next)));
        return new Token(TokenType.NUMBER, builder.toString());
    }

    private Token getWord() {
        StringBuilder builder = new StringBuilder();
        if(isValidFirstCharForWord(line.charAt(next))){
        do{
            builder.append(line.charAt(next));
            next++;
        } while (next < line.length()
                && isValidCharForWord(line.charAt(next)));
        }
        return new Token(TokenType.WORD, builder.toString());
    }

    static boolean isValidFirstCharForWord(char c) {
        return Character.isLetter(c);
    }

    static boolean isValidCharForWord(char c) {
        /*return c != '/' && c != '*'&& c != ' '&& c != '+' 
                && c != '-' && c != '(' && c != ')'&& c!='=';*/
        return Character.isLetter(c) || Character.isDigit(c) || c=='_';
    }

    /**
     * retourne un nouveau Token de type SYMBOL
     *
     * @return un Token de type SYMBOL
     */
    private Token getSymbol() {
        String string = Character.toString(line.charAt(next));
        next++;
        return new Token(TokenType.SYMBOL, string);
    }
}

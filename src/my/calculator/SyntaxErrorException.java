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
public class SyntaxErrorException extends Exception {

    /**
     * Constructeur de SyntaxErrorException
     * @param message le message d'erreur
     */
    public SyntaxErrorException(String message) {
        super(message);
    }
}

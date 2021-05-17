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
public class EvaluationErrorException extends ArithmeticException {

    /**
     * Constructeur
     * @param message
     */
    public EvaluationErrorException(String message){
        super(message);
    }
}

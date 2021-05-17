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
interface Supplier<T> {
    /**
     * retourne l'élément attendu
     * @return l'élément T
     */
    T get();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Priscilla
 */
public class CalculatorIT {
    
    @Test
    public void testSums() throws SyntaxErrorException, EvaluationErrorException {
        Calculator calc = new Calculator();
        assertEquals(123, calc.evaluation("  123 ").valeur(calc.tables));
        assertEquals(4, calc.evaluation("2+2").valeur(calc.tables));
        assertEquals(1, calc.evaluation("421-20-400").valeur(calc.tables));
    }
    
    @Test
    public void testMulDiv() throws SyntaxErrorException, EvaluationErrorException {
        Calculator calc = new Calculator();
        assertEquals(4, calc.evaluation("2*2").valeur(calc.tables));
        assertEquals(2, calc.evaluation("4/2").valeur(calc.tables));
        assertEquals(10, calc.evaluation("4*5/2").valeur(calc.tables));
        assertEquals(5, calc.evaluation("4+2/2").valeur(calc.tables));
        assertEquals(20, calc.evaluation("5*5-5").valeur(calc.tables));
    }

    @Test
    public void testDivisionZero() throws SyntaxErrorException {
        Calculator calc = new Calculator();
        try {
            calc.evaluation("1 + 2/0 + 3");
            fail();
        } catch (EvaluationErrorException ex) {
        }
    }
    
    @Test
    public void testSyntax() throws SyntaxErrorException {
        Calculator calc = new Calculator();
        try {
            calc.evaluation("*");
            fail();
        } catch (SyntaxErrorException ex) {}
        try {
            calc.evaluation("/");
            fail();
        } catch (SyntaxErrorException ex) {}
        try {
            calc.evaluation("+");
            fail();
        } catch (SyntaxErrorException ex) {}
        try {
            calc.evaluation("-");
            fail();
        } catch (SyntaxErrorException ex) {}
    }

    @Test
    public void testParenthese() throws SyntaxErrorException, EvaluationErrorException {
        Calculator calc = new Calculator();
        assertEquals(50, calc.evaluation("5*(5+5)").valeur(calc.tables));
        assertEquals(50, calc.evaluation("(5+5)*5").valeur(calc.tables));
        assertEquals(100, calc.evaluation("(15-5)*(5+5)").valeur(calc.tables));
    }

    @Test
    public void testNegatif() throws SyntaxErrorException, EvaluationErrorException {
        Calculator calc = new Calculator();
        assertEquals(-4, calc.evaluation("-4").valeur(calc.tables));
        assertEquals(1, calc.evaluation("5-4").valeur(calc.tables));
        assertEquals(-6, calc.evaluation("-(5+5)+4").valeur(calc.tables));
        assertEquals(10, calc.evaluation("5*(-3+5)").valeur(calc.tables));
        assertEquals(100, calc.evaluation("(15+-5)*(5+5)").valeur(calc.tables));
        assertEquals(100, calc.evaluation("(15+(-5))*(5+5)").valeur(calc.tables));
    }

    @Test
    public void testAssignments() throws SyntaxErrorException {
        Calculator c = new Calculator();
        try {
            c.evaluation("a");
            fail();
        } catch (EvaluationErrorException ex) {}
        assertEquals(12, c.evaluation("num = 3*4").valeur(c.tables));
        assertEquals(12, c.evaluation("num").valeur(c.tables));
        assertEquals(2, c.evaluation("den = 2").valeur(c.tables));
        assertEquals(2, c.evaluation("den").valeur(c.tables));
        assertEquals(6, c.evaluation("num / den").valeur(c.tables));
        assertEquals(10, c.evaluation("(a = 2+1) + (b = 2*3 + 1)").valeur(c.tables));
        assertEquals(3, c.evaluation("a").valeur(c.tables));
        assertEquals(7, c.evaluation("b").valeur(c.tables));
        assertEquals(10, c.evaluation("a+b").valeur(c.tables));
        assertEquals(4, c.evaluation("b-a").valeur(c.tables));
        assertEquals(21, c.evaluation("a*b").valeur(c.tables));
        assertEquals(4, c.evaluation("a=a+1").valeur(c.tables));
        try {
            c.evaluation("a*c");
            fail();
        } catch (EvaluationErrorException ex) {}
    }
}

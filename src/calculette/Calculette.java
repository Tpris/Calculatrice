/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculette;

import java.util.Scanner;
import my.calculator.SyntaxErrorException;
import my.calculator.EvaluationErrorException;
import my.calculator.Calculator;
import arbres.expr.Expr;
import my.calculator.TableVariables;

/**
 *
 * @author Priscilla
 */
public class Calculette {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testBoucleInteraction();
    }

    public static void testBoucleInteraction() {
        Calculator calculator = new Calculator();
        //TableVariables vars = new TableVariables();
        Scanner in = new Scanner(System.in);
        System.out.println("Calculette v5.0");
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) {
                break;
            }
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("\\q")) {
                break;
            }
            try {
                Expr value = calculator.evaluation(line);
                //System.out.println(value.valeur(calculator.tables));
                afficherAvecValeur(value, calculator.tables);
                afficherCode(value);
            } catch (SyntaxErrorException ex) {
                System.out.format("! Incorrect syntax %s\n",
                        ex.getMessage());
            } catch (EvaluationErrorException ex) {
                System.out.format("! Evaluation failed %s\n",
                        ex.getMessage());
            }
        }
        System.out.println("Bye.");

    }
    private static void afficherAvecValeur(Expr n, TableVariables vars) {
        System.out.format("> %s = %d\n",
                n.description(),
                n.valeur(vars));
    }
     private static void afficherCode(Expr n) {
        System.out.format("> %s \n",
                n.getCode(1));
    }
}

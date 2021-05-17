/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbres.expr;

/**
 *
 * @author Priscilla
 */
public class ExprAffectation implements Expr {

    String nom;
    Expr expr;
    public ExprAffectation(String a, Expr expr) {
        this.nom = a;
        this.expr = expr;
    }

    @Override
    public int valeur(Environnement env) {
       return env.valeur(this.nom);
    }

    @Override
    public String description() {
        return String.format("%s = %s",this.nom, expr.description());
    }

    @Override
    public String getCode(int numRegistre) {
        return String.format("%s \n  st    r%d,%s", 
                this.expr.getCode(numRegistre),numRegistre, this.nom);
    }
    
}

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
public class ExprVariable implements Expr {
    
    private final String varible;

    public ExprVariable(String a) {
        this.varible = a;
    }
    @Override
    public String description(){
      return String.format("%s",this.varible);
    }

    @Override
    public int valeur(Environnement env) {
        return env.valeur(this.varible) ;
    }

    @Override
    public String getCode(int numRegistre) {
        return String.format("ld    r%d,%s", numRegistre, this.varible);
    }
}

package arbres.expr;

public interface Expr {
    
    public static Expr affectation(String a, Expr expr){
        return new ExprAffectation(a,expr);
    }

    public static Expr variable(String a){
        return new ExprVariable(a);
    }
    /**
     * Evalue l'expression dans un environnement
     * @param env
     * @return la valeur de l'expression
     */
    int valeur(Environnement env);
    
    // "factories" pour fabriquer des expression.
    
    public static Expr constante(int valeur) {
        return new ExprConstante(valeur);
    }
    public static Expr binaire(Expr gauche, OpBinaire op, Expr droite) {
        return new ExprBinaire(gauche, op, droite);
    }

    public String description();
    
        default public String getCode() {
        return getCode(1);
    }

    String getCode(int numRegistre);
}

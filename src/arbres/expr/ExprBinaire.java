package arbres.expr;

class ExprBinaire implements Expr {

    private final OpBinaire op;
    private final Expr gauche;
    private final Expr droite;

    public ExprBinaire(Expr gauche, OpBinaire op, Expr droite) {
        this.op = op;
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int valeur(Environnement env) {
        return op.appliquer(gauche.valeur(env), droite.valeur(env));
    }

    @Override
    public String description() {
        return String.format("(%s %s %s)",
                gauche.description(), op, droite.description());
    }

    public String space(int n) {
        String space = "";
        for (int i = 0; i < n; i++) {
            space += "            ";
        }
        return space;
    }

    @Override
    public String getCode(int numRegistre) {
        int numR2 = numRegistre + 1;
        String code = "Erreur";
        if (GeqD()) {
            code = String.format("%s \n  ", gauche.getCode(numRegistre));
        } else if (description().charAt(1) != ('(')) {
            code = String.format("%s \n  %s \n  ",
                    droite.getCode(numRegistre), gauche.getCode(numR2));
        } else {
            code = String.format("%s \n  %s \n  ",
                    gauche.getCode(numRegistre), droite.getCode(numR2));
        }
        switch (op) {
            case PLUS:
                code += "add   ";
                break;
            case MOINS:
                code += "sub   ";
                break;
            case MULT:
                code += "mul   ";
                break;
            case DIV:
                code += "div   ";
                break;
        }
        if (GeqD()) {
            code += String.format("r%d,r%d", numRegistre, numRegistre);
        } else {
            code += String.format("r%d,r%d", numRegistre, numR2);
        }
        return code;
    }

    private boolean GeqD() {
        return gauche.description().equals(droite.description());
    }
}

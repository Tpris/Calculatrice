package my.calculator;

import java.util.HashMap;
import java.util.Map;


public class TableVariables implements arbres.expr.Environnement {
    
    Map<String,Integer> variables = new HashMap<>();
    
    @Override
    public void affecter(String name, int value) {
        variables.put(name, value);
    }
    
    @Override
    public void remplacer(String name, int value) {
        variables.replace(name, value);
    }
    
    @Override
    public int  valeur(String name) {
        return variables.get(name);
    }
    @Override
    public boolean varPresente(String var) {
        return variables.containsKey(var);
    }
}

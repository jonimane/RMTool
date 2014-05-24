/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model;

/**
 *
 * @author jonimane
 */
public enum Prioridade {
    Alto,
    Normal,
    Baixo;
    
    private Byte n;

    public static Prioridade parse(Byte tipo) {
        for (Prioridade t : Prioridade.values() ) {
            if( t.toByte().equals( tipo ) )
            {
                return t;
            }
        }
        
        return null;
    }
    
    private Prioridade() {
        n = Defaults.i ++;
    }
    
    private static class Defaults
    {
        private static byte i = 0;
    }
    
    public Byte toByte()
    {
        return n;
    }
}

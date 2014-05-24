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
public enum Situacao {
    Concluido("Concluído"),
    Aberto("Em Aberto"),
    Andamento("Em Andamento"),
    Fechado("Fechado");
    
    private Byte n;
    private String rotulo;
    
    public static Situacao parse(Byte tipo) {
        for (Situacao t : Situacao.values() ) {
            if( t.toByte().equals( tipo ) )
            {
                return t;
            }
        }
        
        return null;
    }
    
    private Situacao( String r ) {
        rotulo = r;
        n = Situacao.Defaults.i ++;
    }

    @Override
    public String toString() {
        return rotulo;
    }
    
    public Byte toByte()
    {
        return n;
    }
    
    private static class Defaults
    {
        private static byte i = 0;
    }
}

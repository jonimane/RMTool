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
public enum TipoRequisito {
    Funcional(""),
    NaoFuncional("Não Funcional"),
    Usuario("Usuário"),
    Sistema("");
    
    private Byte n;
    private String nome;
    
    public static TipoRequisito parse(Byte tipo) {
        for (TipoRequisito t : TipoRequisito.values() ) {
            if( t.toByte().equals( tipo ) )
            {
                return t;
            }
        }
        
        return null;
    }
    
    private TipoRequisito( String t )
    {
        if( t.length() == 0 )
        {
            t = name();
        }
        
        n = Defaults.i ++;
        nome = t;
    }
    
    @Override
    public String toString()
    {
        return nome;
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

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
    Funcional( (byte) 0, ""),
    NaoFuncional( (byte) 1, "Não Funcional"),
    Usuario( (byte) 2, "Usuário"),
    Sistema( (byte) 3, "");
    
    private Byte n;
    private String nome;
    
    public static TipoRequisito parse(Byte tipo) {
        for (TipoRequisito t : TipoRequisito.values() ) {
            if( t.toByte() == tipo )
            {
                return t;
            }
        }
        
        return null;
    }
    
    private TipoRequisito( Byte numero, String t )
    {
        if( t.length() == 0 )
        {
            t = name();
        }
        
        n = numero;
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
}

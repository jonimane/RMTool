/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model;

import javax.persistence.Transient;

/**
 *
 * @author jonimane
 */
public interface NomeEditavel {
    public NomeEditavel setNomeEditavel( String nome );
    public String getNomeEditavel();
    public boolean salvar();
}

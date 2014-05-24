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
public class AbstractController implements Controller {
    @Override
    public void fechar() {
        TabManager.getInstance().fechar( this );
    }
    
}

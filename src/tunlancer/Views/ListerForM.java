/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author siwar
 */
public class ListerForM  extends BaseForm{
     Form current;
     public ListerForM (Resources res) {
        super("Annonce", BoxLayout.y());
         Toolbar tb = new Toolbar(true);
         current  = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Annonces");
        getContentPane().setScrollVisible(false);
        
   } 
}

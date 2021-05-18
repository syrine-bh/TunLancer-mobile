/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Hiba
 */
public class HomeForm extends SideMenuBaseForm{
    public HomeForm(Resources res) throws IOException {
        super(BoxLayout.y());

        
       
        setupSideMenu(res);
    }
    
   

    
}
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import tunlancer.Models.Competence;
import tunlancer.MyApplication;
import tunlancer.Services.ServiceCompetence;

/**
 *
 * @author asus
 */
public class EditCompetence {
   Form f;
    TextField titre;
    TextField specialite;
        TextField domaine;
           TextField id;
    TextField description;
    private Form parentForm;

    Button btnajout,btnaff;

    public EditCompetence(Form form, Competence c3)  {
    Dialog i = new InfiniteProgress().showInifiniteBlocking();
    ImageViewer g = new ImageViewer ();
  // EncodedImage encb = EncodedImage.create();
   // g.setImage(encb);
        f = new Form("Modifier Competence");
        f.getToolbar().addCommandToLeftBar("Back", MyApplication.theme.getImage("back-command.png"), (e) -> {
        parentForm.showBack();
        });
        i.dispose();
        titre = new TextField("","Titre");
        domaine = new TextField("","Domaine");
        id = new TextField("","Id");
      
        btnajout = new Button("Modifier");
                btnaff=new Button("Annuler");
         f.add(new Label("Titre: "));        
        f.add(titre);
       f.add(new Label("Domaine: "));  
         f.add(domaine);
           f.add(new Label("Id: "));  
         f.add(id);
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServiceCompetence ser = new ServiceCompetence ();
         Competence t = new Competence( Integer.parseInt(id.getText()),titre.getText(), domaine.getText());
            ser.editCompetence(t);
             Dialog.show("Competence Modifiée", "Votre Competence est modifiée avec succés", "OK", "Cancel");
         ProfileOther a=new ProfileOther(parentForm, 0);
        a.getF().show();
        });
        btnaff.addActionListener((e)->{
        int memberId = 0;
        ProfileOther a=new ProfileOther(parentForm, memberId);
        a.getF().show();
        });
    }

   
   
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTitre() {
        return titre;
    }

    public void setTitre(TextField description) {
        this.titre = titre;
    }

    public TextField getDomaine() {
        return domaine;
    }

    public void setDomaine(TextField Domaine) {
        this.domaine = domaine;
    }
     public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }
    
}

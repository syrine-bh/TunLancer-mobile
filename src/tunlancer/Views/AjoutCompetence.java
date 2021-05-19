/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

/**
 *
 * @author asus
 */
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import java.io.IOException;
import tunlancer.Models.Competence;
import tunlancer.Models.Formation;
import tunlancer.MyApplication;
import tunlancer.Services.ServiceCompetence;
import tunlancer.Services.ServiceFormation;


public class AjoutCompetence {

    Form f;
    TextField titre;
    TextField specialite;
        TextField domaine;
    TextField description;
    private Form parentForm;

    Button btnajout,btnaff;

    public AjoutCompetence(int memberId)  {
    Dialog i = new InfiniteProgress().showInifiniteBlocking();
    ImageViewer g = new ImageViewer ();
  //  EncodedImage encb = EncodedImage.create();
   // g.setImage(encb);
        f = new Form("Ajouter Competence");
        f.getToolbar().addCommandToLeftBar("Back", MyApplication.theme.getImage("back-command.png"), (e) -> {
        parentForm.showBack();
        });
        i.dispose();
        titre = new TextField("","Titre");
        domaine = new TextField("","Domaine");
       
      
        btnajout = new Button("Ajouter");
                btnaff=new Button("Annuler");
         f.add(new Label("Titre: "));        
        f.add(titre);
       f.add(new Label("Domaine: "));  
         f.add(domaine);
         
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServiceCompetence ser = new ServiceCompetence ();
         Competence t = new Competence( 0,titre.getText(), domaine.getText());
            ser.ajoutCompetence(t);
             Dialog.show("Competence ajouté", "Votre Competence est ajouté avec succés", "OK", "Cancel");
   ProfileOther a=new ProfileOther(parentForm, 0);
        a.getF().show();
        });
        btnaff.addActionListener((e)->{
        ProfileView a=new ProfileView(parentForm, memberId);
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

    public TextField getSpecialite() {
        return specialite;
    }

    public void setSpecialite(TextField specialite) {
        this.specialite = specialite;
    }
      public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }
}

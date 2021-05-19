/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.text.SimpleDateFormat;

import tunlancer.Services.ServiceExperience;
import tunlancer.Models.Experiences;


/**
 *
 * @author bhk
 */
public class AjoutExperience {

    Form f;
    TextField societe;
    TextField poste;
        TextField periode;
    TextField description;
    private Form parentForm;
    private Experiences member;

    Button btnajout,btnaff;

    public AjoutExperience(int memberId) {
       //  this.member = ServiceExperience.getInstance().getExperience(memberId);
                Dialog i = new InfiniteProgress().showInifiniteBlocking();

        f = new Form("Ajouter Experience");
        f.getToolbar().addCommandToLeftBar("Back", tunlancer.MyApplication.theme.getImage("back-command.png"), (e) -> {
        parentForm.showBack();
        });
        i.dispose();
        poste = new TextField("","poste");
        societe = new TextField("","societe");
        Container dateContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Picker datedeb = new Picker();
        Label temp = new Label("date de debut");
        dateContainer.add(temp);
        dateContainer.add(datedeb);
        Container dateContainer2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Picker datefin = new Picker();
        Label temp2 = new Label("date de fin");
        dateContainer2.add(temp2);
        dateContainer2.add(datefin);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
     
        btnajout = new Button("Ajouter");
                btnaff=new Button("Annuler");
         f.add(new Label("poste: "));        
        f.add(poste);
          f.add(new Label("societe: "));  
        f.add(societe);
       
          f.add(dateContainer);
         
         f.add(dateContainer2);
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            
             boolean valid = true;
            if (poste.getText().equals("")) {
                poste.setText("champ vide!");
                poste.setVisible(true);
                valid = false;
            } else {
                poste.setText("");

            }
            if (societe.getText().equals("")) {
                societe.setText("champ vide !");
                societe.setVisible(true);
                valid = false;
            } else {
                societe.setText("");

            }
            if (datefin.getText().equals("")) {
                datefin.setText("Champ invalide !");
                datefin.setVisible(true);
                valid = false;
            } else {
                datefin.setText("");

            }
            if (datedeb.getText().equals("")) {
                datedeb.setText("champ vide!");
                datedeb.setVisible(true);
                valid = false;
            } else {
                datedeb.setText("");

            }
              if (!valid) {
                return;
              }

           ServiceExperience ser = new ServiceExperience();
    Experiences t = new Experiences(0, poste.getText(), societe.getText(), datedeb.getText(),datefin.getText());
            ser.ajoutExperience(t);
             Dialog.show("Experience ajouté", "Votre experience est ajouté avec succés", "OK", "Cancel");
ProfileOther a=new ProfileOther(parentForm, memberId);
        a.getF().show();
        });
        btnaff.addActionListener((e)->{
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

    public TextField getSociete() {
        return societe;
    }

    public void setSociete(TextField societe) {
        this.societe = societe;
    }
     public TextField getPoste() {
        return poste;
    }

    public void setPoste(TextField poste) {
        this.poste = poste;
    }
  public TextField getPeriode() {
        return periode;
    }

    public void setPeriode(TextField periode) {
        this.periode = periode;
    }
      public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;
import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.ParseException;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.text.SimpleDateFormat;
import tunlancer.Models.Competence;
import tunlancer.Models.Formation;
import tunlancer.MyApplication;
import tunlancer.Services.ServiceFormation;



public class ModifierFormView {
    Form form;
    TextField titre;
    TextField specialite;
        TextField periode;
    TextField description;
    private Form parentForm;
     TextField id;

    Button btnajout,btnaff;

    public ModifierFormView(Form form, Formation c3) {
                Dialog i = new InfiniteProgress().showInifiniteBlocking();
       
        form = new Form(" Modifier Formation");
        form.getToolbar().addCommandToLeftBar("Back", MyApplication.theme.getImage("back-command.png"), (e) -> {
        parentForm.showBack();
        });
        i.dispose();
        titre = new TextField("","Titre");
        specialite = new TextField("","Specialite");
        Picker tfDuedate = new Picker();
     //   SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        description = new TextField("","Description");
       id = new TextField("","Id");
      
        btnajout = new Button("Modifier");
                btnaff=new Button("Annuler");
         form.add(new Label("Titre: "));        
        form.add(titre);
         form.add(new Label("Specialite: "));  
        form.add(specialite);
        form.add(new Label("Periode: "));  
        form.add(tfDuedate);
        form.add(new Label("Description: "));  
        form.add(description);
        form.add(new Label("Id: "));  
        form.add(id);
        form.add(btnajout);
        form.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServiceFormation ser = new ServiceFormation();
      Formation t = new Formation( Integer.parseInt(id.getText()),titre.getText(), specialite.getText(), tfDuedate.getText(),description.getText());
            ser.editFormation(t);
             Dialog.show("Formation Modifiée", "Votre Formation est modifiée avec succés", "OK", "Cancel");
   ProfileView a=new ProfileView(parentForm, 0);
       a.getF().show();
        });
        btnaff.addActionListener((e)->{
                    int memberId = 0;
       ProfileOther a=new ProfileOther(parentForm, memberId);
       a.getF().show();
        });
    }

   



   
    public Form getF() {
        return form;
    }

    public void setF(Form form) {
        this.form = form;
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

      public TextField getPeriode() {
        return periode;
    }

    public void setPeriode(TextField periode) {
        this.periode = periode;
    }}

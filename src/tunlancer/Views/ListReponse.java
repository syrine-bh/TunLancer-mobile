/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import tunlancer.Models.Replies;
import tunlancer.Models.Topics;
import tunlancer.Services.ServiceReponse;

/**
 *
 * @author cyrinaa belguith
 */
public class ListReponse extends Form{
    
    public ArrayList<Replies> comments;
    Form current;
    
    public ListReponse(Form previous, Topics p){
        
        setTitle("List Comments");

        setLayout(BoxLayout.y());
        TextField contenu = new TextField("", "Comment contenu");
        Button btnValider = new Button("Add Comment");
        Button Modifbtn = new Button("Modif valider ");
               
        addAll(contenu, btnValider,Modifbtn);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((contenu.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Replies c = new Replies(contenu.getText());
                        if (ServiceReponse.getInstance().addComment(c)) {
                            Dialog.show("connectedd", "succed", new Command("OK"));
                            new ListReponse(previous, p).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
        
        comments = ServiceReponse.getInstance().getComments(p.getId());
        for (Replies obj : comments) {

            System.out.println("Comments=> " + p.getContenu());
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("Content : " + obj.getContenu());


            addAll(spTitle, Delete, Modif, sp);
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServiceReponse.getInstance().deleteComment(obj.getId());
                new ListReponse(previous, p).show();
            });
        }
        
    }
    
}

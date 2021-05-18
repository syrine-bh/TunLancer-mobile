/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import tunlancer.Models.Concour;
import tunlancer.Models.Participation;
import tunlancer.Models.Video;
import tunlancer.Services.ServiceConcours;
import tunlancer.utils.UserSession;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hiba
 */
public class NewParticipationForm extends BaseForm {
  String embededurl;
    ServiceConcours sc = ServiceConcours.getInstance();
    ArrayList<Concour> concours;
Form current;
    public NewParticipationForm(Resources res, Concour c, Form parentForm) {
super("Concours", BoxLayout.y());
current=this;


      Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Publications");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        if(c.getIsVideo()==true){
        addTab(swipe, res.getImage("video.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");}else{
        addTab(swipe, res.getImage("quiz.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");}

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        Component.setSameSize(spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
         Label FormTitle = new Label("Add New Video");
        FormTitle.getAllStyles().setAlignment(CENTER);
        TextField Title = new TextField("", "Title", 15, TextField.EMAILADDR);
        TextField url = new TextField("", "URL", 15, TextField.EMAILADDR);
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());
        BrowserComponent browser = new BrowserComponent();
        browser.setVisible(false);
        url.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                if (!url.getText().isEmpty()) {
                    embededurl = "https://www.youtube.com/embed/";
                    String code = url.getText().substring(url.getText().length() - 11);
                    embededurl = embededurl + code;
                    browser.setURL(embededurl);
                    browser.setVisible(true);
                }
            }
        });
        Button Postvideo = new Button("Post Video");
        Postvideo.setUIID("LoginButton");
        Postvideo.addActionListener(e -> {
            if ((url.getText().length() == 0) || (Title.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all fields", new Command("OK"));
            } else {
                Video v = new Video(embededurl, Title.getText(), new Date(System.currentTimeMillis()), UserSession.instance.getU());
                Participation cp = new Participation(c, UserSession.instance.getU(), new Date(System.currentTimeMillis()), v);
                sc.participate(v, cp);
                try {
                    new ParticipationForm(res, c, new ConcoursForm(res)).show();
                } catch (IOException ex) {

                }
            }
        });
//        Button btnListConcours = new Button("List Concours");
//        
//        btnListConcours.addActionListener(e-> new ListConcoursForm(current,res).show());
        addAll(FormTitle,Title,url,dateLabel,Postvideo);

//     














    }
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }
////
    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addConcoursToForm(Image img, String nom) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(nom);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta
                       
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(nom, FontImage.MATERIAL_INFO));

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
       
}

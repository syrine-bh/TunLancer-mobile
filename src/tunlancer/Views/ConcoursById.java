/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ImageViewer;
import static com.codename1.components.ImageViewer.IMAGE_FILL;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.codename1.util.DateUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import tunlancer.Models.Concour;
import tunlancer.Models.Quiz;
import tunlancer.Models.Users;
import tunlancer.Models.Video;
import tunlancer.Services.ServiceConcours;
import tunlancer.utils.UserSession;

/**
 *
 * @author Hiba
 */
public class ConcoursById extends BaseForm {

    ServiceConcours SC;
    ArrayList<Concour> concours;
    ArrayList<Quiz> quiz;
    Form current;
        Date t1 = new Date(System.currentTimeMillis());
            long d, h, m, s;



    public ConcoursById(Concour c, Form previous, Resources res,Users u) {
        super("Concours", BoxLayout.y());
        current = this;

        concours = new ArrayList<>();
        quiz=new ArrayList<>();
        SC = new ServiceConcours();
        concours = (ArrayList<Concour>) SC.getAllConcours();
        quiz=(ArrayList<Quiz>) SC.getAllQuiz();
        System.out.println(c);
//
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Publications");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();
        Dialog ip = new InfiniteProgress().showInifiniteBlocking();

        Label spacer1 = new Label();
 if(c.getIsVideo()==true){
        addTab(swipe, res.getImage("video.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");}else{
        addTab(swipe, res.getImage("quiz.jpg"), spacer1, "Trouvez l'emploi parfait que vous méritez.");}
 
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        Component.setSameSize(spacer1);
        add(LayeredLayout.encloseIn(swipe));
 if(c.getIsVideo()==true){

        Tabs t = new Tabs();
        Map<Concour, Label> timers = new HashMap<>();
//        for (Concour comp : cs.getAllConcours()) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

            SpanLabel l1 = new SpanLabel("From: \n" + sdf.format(c.getDateDebut()));
            SpanLabel l2 = new SpanLabel("To: \n" + sdf.format(c.getDateFin()));

        System.out.println(UserSession.instance.getU().getNom());

        l1.getAllStyles().setAlignment(CENTER);
        l2.getAllStyles().setAlignment(CENTER);
        Component.setSameHeight(l1, l2);
        Component.setSameWidth(l1, l2);
        Container cdates = new Container(BoxLayout.xCenter());
        cdates.addAll(l1, l2);
        Label l3 = new Label(c.getSujet());
        l3.getAllStyles().setFgColor(0xF36B08);
        l3.getAllStyles().setAlignment(CENTER);
        Button Details = new Button("View Details");
        Details.setUIID("LoginButton");
        Button Participate = new Button("Participate", "LoginButton");
        Details.setUIID("LoginButton");
        Participate.getAllStyles().setBgColor(0x246A73);

        Details.setUIID("LoginButton");
        Participate.getAllStyles().setBgColor(0x246A73);
        Participate.addActionListener(e -> {
            if (UserSession.instance.getU().getRole().contains("teeeest")) {
                Dialog d1Dialog = new Dialog();
                Container userContainer = new Container(BoxLayout.yCenter());
                String linkProfilePic = "http://127.0.0.1:8000/assets/img/users/" + UserSession.instance.getU().getPhoto();

                Label user = new Label("Talented Account");
                user.getAllStyles().setFgColor(0x00FF00);
                user.getAllStyles().setBorder(Border.createDoubleBorder(1, 0x00FF00));
                EncodedImage enc;
                enc = (EncodedImage) res.getImage("round-mask.png");

                Image roundMask = Image.createImage(enc.getWidth(), enc.getHeight(), 0xff000000);
                Graphics gr = roundMask.getGraphics();
                gr.setColor(0xffffff);
                gr.fillArc(0, 0, enc.getWidth(), enc.getWidth(), 0, 360);
                Image profilePic = URLImage.createToStorage(enc, linkProfilePic, linkProfilePic);
                profilePic = profilePic.scaled(enc.getWidth(), enc.getHeight());
                Object mask = roundMask.createMask();
                profilePic = profilePic.applyMask(mask);
                ImageViewer im = new ImageViewer(profilePic);
                userContainer.addAll(im, user);
                userContainer.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
                userContainer.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
                userContainer.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
                userContainer.getUnselectedStyle().setPadding(10, 10, 10, 10);
                d1Dialog.add(userContainer);

                d1Dialog.showPopupDialog(Participate);

            }

                      else if(SC.alreadyParticipated(c.getId(), UserSession.instance.getU().getId())==true){
                Dialog d1Dialog = new Dialog("Vous avez déja participé!");
                d1Dialog.showPopupDialog(Participate);
                }
                else {
                          new NewParticipationForm(res, c, this).show();
                }
        });
        Details.addActionListener(e -> {
                try {
                    new ParticipationForm(res, c, this).show();
                } catch (IOException ex) {

                }
        });
        Label timerLabel = new Label();
        timerLabel.getAllStyles().setAlignment(CENTER);
        timerLabel.getAllStyles().setFgColor(0xF36B08);
        timerLabel.getAllStyles().setUnderline(true);
        timerLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
            timerLabel.setPropertyValue("maskName", String.valueOf(Math.abs(c.getDateFin().getTime() - t1.getTime())));
            if (DateUtil.compare(c.getDateFin(), t1) == -1) {
                timerLabel.setText("Le Concours est terminé");
            } else {
                timers.put(c, timerLabel);
            }
        Container all = new Container(BoxLayout.yCenter());
        Container top = new Container(BoxLayout.yCenter());
             if (DateUtil.compare(c.getDateFin(), t1) == -1) {
        Participate.setVisible(false);
                java.util.List<Video> win = SC.ConcourRanksList(c);
                if (win.size() > 0) {

                    String linkProfilePic = "http://127.0.0.1:8000/assets/img/users/" + win.get(0).getOwner().getPhoto();
                    String linkProfilePic2 = "http://127.0.0.1:8000/assets/img/users/" + win.get(1).getOwner().getPhoto();
                    String linkProfilePic3 = "http://127.0.0.1:8000/assets/img/users/" + win.get(2).getOwner().getPhoto();
                    EncodedImage enc,enc2,enc3;
                    enc = (EncodedImage) res.getImage("dog.jpg");
                    enc2 = (EncodedImage) res.getImage("dog.jpg");
                    enc3 = (EncodedImage) res.getImage("dog.jpg");
                    Image roundMask = Image.createImage(enc.getWidth()*2/3, enc.getHeight()*2/3, 0xff000000);
                    Image roundMask2 = Image.createImage(enc2.getWidth()*2/3, enc2.getHeight()*2/3, 0xff000000);
                    Image roundMask3 = Image.createImage(enc3.getWidth()*2/3, enc3.getHeight()*2/3, 0xff000000);
                    Graphics gr = roundMask.getGraphics();
                    Graphics gr2 = roundMask2.getGraphics();
                    Graphics gr3 = roundMask3.getGraphics();
                    gr.setColor(0xffffff);
                    gr.fillArc(0, 0, enc.getWidth()*2/3, enc.getWidth()*2/3, 0, 360);
                    gr2.setColor(0xffffff);
                    gr2.fillArc(0, 0, enc2.getWidth()*2/3, enc2.getWidth()*2/3, 0, 360);
                    gr3.setColor(0xffffff);
                    gr3.fillArc(0, 0, enc3.getWidth()*2/3, enc3.getWidth()*2/3, 0, 360);
                    Image profilePic = URLImage.createToStorage(enc, linkProfilePic, linkProfilePic);
                    Image profilePic2 = URLImage.createToStorage(enc2, linkProfilePic2, linkProfilePic2);
                    Image profilePic3 = URLImage.createToStorage(enc3, linkProfilePic3, linkProfilePic3);
                    profilePic = profilePic.scaled(enc.getWidth()*2/3, enc.getHeight()*2/3);
                    profilePic2 = profilePic2.scaled(enc2.getWidth()*2/3, enc2.getHeight()*2/3);
                    profilePic3 = profilePic3.scaled(enc3.getWidth()*2/3, enc3.getHeight()*2/3);
                    Object mask = roundMask.createMask();
                    Object mask2 = roundMask2.createMask();
                    Object mask3 = roundMask3.createMask();
                    profilePic = profilePic.applyMask(mask);
                    profilePic2 = profilePic2.applyMask(mask2);
                    profilePic3 = profilePic3.applyMask(mask3);
                    ImageViewer im = new ImageViewer(profilePic);
                    ImageViewer im2 = new ImageViewer(profilePic2);
                    ImageViewer im3 = new ImageViewer(profilePic3);
                    refreshTheme();
                    Label phrase= new Label("Nos champions");
                            phrase.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                    phrase.getAllStyles().setAlignment(CENTER);

                    Label profilePicLabel = new Label(SC.ConcourRanksList(c).get(0).getOwner().getNom()+" "+SC.ConcourRanksList(c).get(0).getOwner().getPrenom());
                    Label vote=new Label(SC.ConcourRanksList(c).get(0).getNbVote().size()+" votes");
                    Label vote2=new Label(SC.ConcourRanksList(c).get(1).getNbVote().size()+" votes");
                    Label vote3=new Label(SC.ConcourRanksList(c).get(2).getNbVote().size()+" votes");
                    vote.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    vote.getAllStyles().setAlignment(CENTER);
                    vote2.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    vote2.getAllStyles().setAlignment(CENTER);
                    vote3.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    vote3.getAllStyles().setAlignment(CENTER);

                    Label profilePicLabel2 = new Label(SC.ConcourRanksList(c).get(1).getOwner().getNom()+" "+SC.ConcourRanksList(c).get(1).getOwner().getPrenom());
                    Label profilePicLabel3 = new Label(SC.ConcourRanksList(c).get(2).getOwner().getNom()+" "+SC.ConcourRanksList(c).get(2).getOwner().getPrenom());
                    profilePicLabel.getAllStyles().setAlignment(CENTER);
                    profilePicLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    profilePicLabel2.getAllStyles().setAlignment(CENTER);
                    profilePicLabel2.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    profilePicLabel3.getAllStyles().setAlignment(CENTER);
                    profilePicLabel3.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                    top.addAll(phrase,im,profilePicLabel,vote,im2,profilePicLabel2,vote2,im3,profilePicLabel3,vote3);
                }
    }
                                     addAll(Details, Participate, cdates, timerLabel,top);
            t.addTab("", all);

 UITimer uit = new UITimer(() -> {
            for (Map.Entry<Concour, Label> entry : timers.entrySet()) {
                d = Long.parseLong(entry.getValue().getPropertyValue("maskName").toString());
                d = d - 1000;
                entry.getValue().setPropertyValue("maskName", String.valueOf(d));
                h = d / 3600000;
                m = ((d / 1000) % 3600) / 60;
                s = (d / 1000) % 60;

                entry.getValue().setText(twoDigits(h) + ":" + twoDigits(m) + ":" + twoDigits(s));
            }
            refreshTheme();
        });
        uit.schedule(1000, true, this);
         ip.dispose();
 
   
   

 }else
 {


       setTitle("Quiz");

        Button Participate = new Button("Participate", "LoginButton");
        Participate.getAllStyles().setBgColor(0x246A73);
                                  Button btnListQuiz = new Button("List Quiz");

        Participate.getAllStyles().setBgColor(0x246A73);
        Participate.addActionListener(e -> {
            System.out.println(c.getId());
            System.out.println(UserSession.instance.getU().getId());
                      if(SC.alreadyParticipated(c.getId(), UserSession.instance.getU().getId())==true){
                Dialog d1Dialog = new Dialog("Already Participated!");
                d1Dialog.showPopupDialog(Participate);
                }
                      else if(c.getIsVideo()==true) { 
                          new NewParticipationForm(res, c, this).show();

                      }
                      else{
        
        
            new ListQuizForm(current,res,c,u).show();
                      }
        
        });
        add(Participate);

            
                

        }
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }
//

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
  public static String twoDigits(long v) {
        return v < 10 ? "0" + v : "" + v;
    }
    
}

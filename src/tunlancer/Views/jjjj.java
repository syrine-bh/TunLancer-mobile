/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ImageViewer;
import static com.codename1.components.ImageViewer.IMAGE_FILL;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import tunlancer.Models.Concour;
import tunlancer.Services.ServiceConcours;
import tunlancer.utils.UserSession;

/**
 *
 * @author Hiba
 */
public class jjjj extends BaseForm {

    long d, h, m, s;
    Date t1 = new Date(System.currentTimeMillis());
    ServiceConcours cs = ServiceConcours.getInstance();

    public jjjj(Concour c, Form previous, Resources res) throws IOException {
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
        addTab(swipe, res.getImage("back.png"), spacer1, "Trouvez l'emploi parfait que vous méritez.");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        Component.setSameSize(spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
        ImageViewer breadcumb = new ImageViewer();
        Image im1 = res.getImage("logo.png");
        breadcumb.setImage(im1);
        breadcumb.setImageInitialPosition(IMAGE_FILL);
        breadcumb.getAllStyles().stripMarginAndPadding();
        setUIID("CompForm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        Tabs t = new Tabs();
        Map<Concour, Label> timers = new HashMap<>();
//        for (Concour comp : cs.getAllConcours()) {
//            SpanLabel l1 = new SpanLabel("From: \n" + sdf.format(comp.getDateDebut()));
//            SpanLabel l2 = new SpanLabel("To: \n" + sdf.format(comp.getDateFin()));
        SpanLabel l1 = new SpanLabel("From: \n" + c.getNom());
        SpanLabel l2 = new SpanLabel("To: \n" + c.getSujet());
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
        Participate.addActionListener(e -> {
            if (UserSession.instance.getU().getRole().contains("Freelancer")) {
                Dialog d1Dialog = new Dialog();
                Container userContainer = new Container(BoxLayout.yCenter());
                Label user = new Label("Talented Account");
                user.getAllStyles().setFgColor(0x00FF00);
                user.getAllStyles().setBorder(Border.createDoubleBorder(1, 0x00FF00));
                String linkProfilePic = "http://127.0.0.1:8000/assets/img/users/" + UserSession.instance.getU().getPhoto();
                EncodedImage enc;
                enc = (EncodedImage) res.getImage("logo.png");
                Image roundMask = Image.createImage(enc.getWidth(), enc.getHeight(), 0xff000000);
                Graphics gr = roundMask.getGraphics();
                gr.setColor(0xffffff);
                gr.fillArc(0, 0, enc.getWidth(), enc.getWidth(), 0, 360);
                System.out.println("user");
                System.out.println(UserSession.instance.getU().getNom());
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
//                else if(cs.alreadyParticipated(comp.getId(), UserSession.instance.getU().getId())==true){
//                Dialog d1Dialog = new Dialog("Already Participated!");
//                d1Dialog.showPopupDialog(Participate);
//                }
//                else {
//                    try {
//                        new NewParticipationForm(res, comp, this).show();
//                    } catch (IOException ex) {
//
//                    }
//                }
        });
        Details.addActionListener(e -> {
//                try {
//                    new ParticipationForm(res, comp, this).show();
//                } catch (IOException ex) {
//
//                }
        });
        Label timerLabel = new Label();
        timerLabel.getAllStyles().setAlignment(CENTER);
        timerLabel.getAllStyles().setFgColor(0xF36B08);
        timerLabel.getAllStyles().setUnderline(true);
        timerLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
//            timerLabel.setPropertyValue("maskName", String.valueOf(Math.abs(comp.getDateFin().getTime() - t1.getTime())));
//            if (DateUtil.compare(comp.getDateFin(), t1) == -1) {
//                timerLabel.setText("Concour is Over");
//            } else {
//                timers.put(comp, timerLabel);
//            }
        Container all = new Container(BoxLayout.yCenter());
        Container top = new Container(BoxLayout.yCenter());
//             if (DateUtil.compare(comp.getDateFin(), t1) == -1) {
        Participate.setVisible(false);
//                java.util.List<Video> win = cs.ConcourRanksList(comp);
//                if (win.size() > 0) {
//
//                    String linkProfilePic = "http://127.0.0.1:8000/assets/img/users/" + win.get(0).getOwner().getPhoto();
//                    EncodedImage enc;
//                    enc = (EncodedImage) res.getImage("logo.png");
//                    Image roundMask = Image.createImage(enc.getWidth()*2/3, enc.getHeight()*2/3, 0xff000000);
//                    Graphics gr = roundMask.getGraphics();
//                    gr.setColor(0xffffff);
//                    gr.fillArc(0, 0, enc.getWidth()*2/3, enc.getWidth()*2/3, 0, 360);
//                    Image profilePic = URLImage.createToStorage(enc, linkProfilePic, linkProfilePic);
//                    profilePic = profilePic.scaled(enc.getWidth()*2/3, enc.getHeight()*2/3);
//                    Object mask = roundMask.createMask();
//                    profilePic = profilePic.applyMask(mask);
//                    ImageViewer im = new ImageViewer(profilePic);
//                    refreshTheme();
//                    Label profilePicLabel = new Label("Winner: "+cs.ConcourRanksList(comp).get(0).getOwner().getPhoto());
//                    profilePicLabel.getAllStyles().setAlignment(CENTER);
//                    top.addAll( im,profilePicLabel);
//                }
//            }
        all.addAll(timerLabel, cdates, l3, Details, Participate, top);
        t.addTab("", all);
//        }
        addAll(t);
//        addAll(breadcumb, everything);
//        everything.addAll(t);
//        everything.getAllStyles().setMarginTop(30);
//        addAll(breadcumb, everything);
//        setupSideMenu(res);
//        registerAnimated(this);
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
    }

    public static String twoDigits(long v) {
        return v < 10 ? "0" + v : "" + v;
    }

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

    }


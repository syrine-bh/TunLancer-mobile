/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;


import com.codename1.facebook.*;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.Switch;
import com.codename1.messaging.Message;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.codename1.util.DateUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import tunlancer.Models.Concour;
import tunlancer.Models.Participation;
import tunlancer.Models.Video;
import tunlancer.Services.ServiceConcours;
import static tunlancer.Views.jjjj.twoDigits;
import tunlancer.utils.UserSession;

/**
 *
 * @author Anis
 */
public class ParticipationForm extends Form {
    long d, h, m, s;
    ServiceConcours cs = ServiceConcours.getInstance();
    public ParticipationForm(Resources res, Concour c, Form parentForm) throws IOException {
        super(BoxLayout.y());
//        this.getAllStyles().setBgColor(0x0c0527);
//        getToolbar().addMaterialCommandToLeftBar(
//                "",
//                FontImage.MATERIAL_ARROW_BACK,
//                (ev) -> parentForm.showBack());
        Container compContainer = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        Label subLabel = new Label(c.getNom());
        subLabel.getAllStyles().setFgColor(0xE5BFE5);
        subLabel.getAllStyles().setAlignment(CENTER);
        refreshTheme();
        Label timerLabel = new Label();
        timerLabel.getAllStyles().setFgColor(0xF36B08);
        Date t1 = new Date(System.currentTimeMillis());
        compContainer.add(BorderLayout.NORTH, subLabel);
        d = Math.abs(c.getDateFin().getTime() - t1.getTime());
        if (DateUtil.compare(c.getDateFin(), t1) == -1) {
            timerLabel.setText("Le Concours est terminé!");
        } else {
            UITimer uit = new UITimer(() -> {
                d = d - 1000;
                h = d / 3600000;
                m = ((d / 1000) % 3600) / 60;
                s = (d / 1000) % 60;
                timerLabel.setText(twoDigits(h) + ":" + twoDigits(m) + ":" + twoDigits(s));
            });
            uit.schedule(1000, true, this);
        }
        compContainer.add(CENTER, timerLabel);
        FloatingActionButton title = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD_ALERT);
        title.addActionListener(e -> {
            Dialog d = new Dialog("Ranking");

            Container ranks = new Container(BoxLayout.y()) {
                @Override
                protected Dimension calcPreferredSize() {
                    Dimension d = super.calcPreferredSize();
                    d.setHeight(Display.getInstance().getDisplayHeight() * 30 / 100);
                    d.setWidth(Display.getInstance().getDisplayWidth() * 30 / 100);
                    return d;
                }
            };
            for (Video v : cs.ConcourRanksList(c)) {
                String linkProfilePic = "http://127.0.0.1:8000/assets/img/users/" + v.getOwner().getPhoto();
                EncodedImage enc = null;
                enc = (EncodedImage) res.getImage("logo.png");
                Image roundMask = Image.createImage(enc.getWidth() * 2 / 3, enc.getHeight() * 2 / 3, 0xff000000);
                Graphics gr = roundMask.getGraphics();
                gr.setColor(0xffffff);
                gr.fillArc(0, 0, enc.getWidth() * 2 / 3, enc.getWidth() * 2 / 3, 0, 360);
                Image profilePic = URLImage.createToStorage(enc, linkProfilePic, linkProfilePic);
                profilePic = profilePic.scaled(enc.getWidth() * 2 / 3, enc.getHeight() * 2 / 3);
                Object mask = roundMask.createMask();
                profilePic = profilePic.applyMask(mask);
                ImageViewer im = new ImageViewer(profilePic);
                Label profilePicLabel = new Label(v.getOwner().getNom()+" "+v.getOwner().getPrenom());
                profilePicLabel.getAllStyles().setAlignment(CENTER);
                profilePicLabel.getAllStyles().setMarginBottom(30);
                profilePicLabel.getAllStyles().setFgColor(0x000000);
                ranks.addAll(im, profilePicLabel);
            }
            d.setLayout(BoxLayout.yCenter());
            d.add(ranks);
            d.showPopupDialog(title);
        });
        title.setUIID("LoginButton");
        title.getAllStyles().setBgColor(0xF36B08);
        compContainer.add(BorderLayout.EAST, title);
        compContainer.getAllStyles().setPadding(20, 20, 20, 20);
        Tabs videoTabs = new Tabs();
        List<Participation> ps = cs.ConcourParticipationList(c);
        for (Participation p : ps) {
            Container videoContainer = new Container(BoxLayout.y()) {
                @Override
                protected Dimension calcPreferredSize() {
                    Dimension d = super.calcPreferredSize();
                    d.setHeight(Display.getInstance().getDisplayHeight() * 70 / 100);
                    d.setWidth(Display.getInstance().getDisplayWidth() * 90 / 100);
                    return d;
                }
            };
            Container DetailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String linkProfilePic = "http://127.0.0.1:8000/assets/img/users/" + p.getVideo_id().getOwner().getPhoto();
            System.out.println("lien photo "+p.getVideo_id().getOwner());
            EncodedImage enc = null;
            enc = (EncodedImage) res.getImage("round-mask.png");
            Image roundMask = Image.createImage(enc.getWidth() / 2, enc.getHeight() / 2, 0xff000000);
            Graphics gr = roundMask.getGraphics();
            gr.setColor(0xffffff);
            gr.fillArc(0, 0, enc.getWidth() / 2, enc.getWidth() / 2, 0, 360);
            Image profilePic = URLImage.createToStorage(enc, linkProfilePic, linkProfilePic);
            profilePic = profilePic.scaled(enc.getWidth() / 2, enc.getHeight() / 2);
            Object mask = roundMask.createMask();
            profilePic = profilePic.applyMask(mask);
            Label UsernameLabel = new Label("   " + p.getVideo_id().getOwner().getNom(), profilePic);
            Label TitleLabel = new Label(p.getVideo_id().getTitle());
            Label DateLabel = new Label(p.getDate_participation().toString());
            DateLabel.getAllStyles().setFgColor(0x990099);
            DateLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_SMALL));
            DetailsContainer.addAll(UsernameLabel, DateLabel, TitleLabel);

            BrowserComponent browser = new BrowserComponent() {
                @Override
                protected Dimension calcPreferredSize() {
                    Dimension d = super.calcPreferredSize();
                    d.setHeight(Display.getInstance().getDisplayWidth() * 60 / 100);
                    d.setWidth(Display.getInstance().getDisplayWidth() * 90 / 100);
                    return d;
                }
            };
            browser.setURL(p.getVideo_id().getUrl());
            videoContainer.add(DetailsContainer);
            browser.getAllStyles().setMarginLeft(10);
            browser.getAllStyles().setMarginRight(10);
            Container votingContainer = new Container(BorderLayout.center());
            Switch s1 = new Switch();
            s1.getUnselectedStyle().setFgColor(0xff0000);
            s1.getSelectedStyle().setBgColor(0x990099);
            Label Heart = new Label();
            Heart.getAllStyles().setFgColor(0x990099);
            Label numVotes = new Label(String.valueOf(p.getVideo_id().getNbVote().size()));
            Label voteicon = new Label();
            voteicon.getAllStyles().setFgColor(0x990099);
            FontImage.setMaterialIcon(voteicon, FontImage.MATERIAL_THUMB_UP, 3);
            s1.addActionListener((e) -> {
                if (s1.isOn()) {

                    numVotes.setText(String.valueOf(Integer.valueOf(numVotes.getText()) + 1));
                    FontImage.setMaterialIcon(Heart, FontImage.MATERIAL_FAVORITE, 4);
                    cs.Vote(UserSession.instance.getU().getId(), p.getVideo_id().getId());
                    return;
                }
                if (s1.isOff()) {
                    numVotes.setText(String.valueOf(Integer.valueOf(numVotes.getText()) - 1));
                    FontImage.setMaterialIcon(Heart, FontImage.MATERIAL_FAVORITE_OUTLINE, 4);
                }
                cs.unVote(UserSession.instance.getU().getId(), p.getVideo_id().getId());
                return;
            });
            if (p.getVideo_id().getNbVote().contains(UserSession.instance.getU().getId())) {
                s1.setValue(true);
                FontImage.setMaterialIcon(Heart, FontImage.MATERIAL_FAVORITE, 4);
            }
            if (DateUtil.compare(c.getDateFin(), t1) == -1) {
                s1.setVisible(false);
                Heart.setVisible(false);
            } else {
                FontImage.setMaterialIcon(Heart, FontImage.MATERIAL_FAVORITE_OUTLINE, 4);
            }
            votingContainer.getAllStyles().setMarginTop(30);
            votingContainer.add(BorderLayout.CENTER_BEHAVIOR_CENTER, BoxLayout.encloseXCenter(s1, Heart));
            votingContainer.add(BorderLayout.EAST, BoxLayout.encloseX(numVotes, voteicon));
            ShareButton share = new ShareButton();
            share.setUIID("LoginButton");
            share.getAllStyles().setBorder(Border.createRoundBorder(30, 30));
            share.getAllStyles().setBgColor(0xF36B08);
            FontImage.setMaterialIcon(share, FontImage.MATERIAL_SHARE, 4);
            share.setText("");
            share.setTextToShare(p.getVideo_id().getUrl());
            Container shareContainer = new Container(BoxLayout.xCenter());
            shareContainer.add(share);
            Button email = new Button("Send Email To User");
            email.getAllStyles().setBorder(Border.createRoundBorder(30, 30));
            email.setUIID("LoginButton");
            FontImage.setMaterialIcon(email, FontImage.MATERIAL_EMAIL, 4);
            email.addActionListener(e -> {
                Message m1 = new Message("");
                Display.getInstance().sendMessage(new String[]{p.getVideo_id().getOwner().getEmail()}, "I love Your Video", m1);
            });
            shareContainer.add(email);
            videoContainer.add(browser);
            videoContainer.add(votingContainer);
            videoContainer.add(shareContainer);
            videoTabs.addTab("", videoContainer);
        }
        videoTabs.getAllStyles().setPadding(20, 20, 20, 20);
        add(compContainer);
        add(videoTabs);
        setScrollableY(true);
        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("logo.png").scaled(10, 10);
        Image selectedWalkthru = res.getImage("logo.png").scaled(10, 10);
        if (videoTabs.getTabCount() > 0) {
            RadioButton[] rbs = new RadioButton[videoTabs.getTabCount()];
            FlowLayout flow = new FlowLayout(CENTER);
            flow.setValign(CENTER);
            Container radioContainer = new Container(flow);
            for (int iter = 0; iter < rbs.length; iter++) {
                rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
                rbs[iter].setPressedIcon(selectedWalkthru);
                rbs[iter].setUIID("Label");
                rbs[iter].setEnabled(false);
                radioContainer.add(rbs[iter]);
            }
            rbs[0].setSelected(true);
            videoTabs.addSelectionListener((i, ii) -> {
                if (!rbs[ii].isSelected()) {
                    rbs[ii].setSelected(true);
                }
            });
            add(radioContainer);
        }
    }

}

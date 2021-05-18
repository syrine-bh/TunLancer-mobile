/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

import tunlancer.Models.Concour;
import tunlancer.Models.Quiz;
import tunlancer.Models.Users;
import tunlancer.Services.ServiceConcours;

/**
 *
 * @author Hiba
 */
public class ListConcoursForm extends BaseForm {

    Form current;

    public ListConcoursForm(Form previous, Resources res) {
        super("Concours", BoxLayout.y());
        current = this;

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Concours");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("back.png"), spacer1, "Participez dans nos concours pour augmentez vos chances");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        Component.setSameSize(spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
        setTitle("List Concours");

        ArrayList<Concour> listConcours = (ArrayList<Concour>) ServiceConcours.getInstance().getAllConcours();
        for (Concour c : listConcours) {
            String urlImage = "http://127.0.0.1:8000/images/uploads/" + c.getImageName();
//            Quiz q = new Quiz();
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            int height = Display.getInstance().convertToPixels(11.5f);
            int width = Display.getInstance().convertToPixels(15f);
            Button Image = new Button(urlim.fill(width, height));
            Image.setUIID("Label");

            Container cnt = BorderLayout.west(Image);
            TextArea ta = new TextArea(c.getNom() + "\n" + c.getDateDebut());
            System.out.println(c.getNom());
            ta.setEditable(false);
            Label Details = new Label();
            Details.setUIID("NewsTOPLINE");
            Style DetailsStyle = new Style(Details.getUnselectedStyle());
            DetailsStyle.setBgColor(0xf7ad02);
            FontImage nFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_COMMENT, DetailsStyle);
            Details.setIcon(nFontImage);
            Details.setTextPosition(LEFT);
            Details.addPointerPressedListener(l -> {
                System.out.println("DETAAAAILS");
                    Users u =new Users();
                new ConcoursById(c, previous, res,u).show();
            });
//                ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerimg = new Container();
//                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//                
            cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(ta),
                    BoxLayout.encloseY(Details)));
            add(cnt);

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
//
//        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
//        likes.setTextPosition(RIGHT);
//        if (!liked) {
//            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//        } else {
//            Style s = new Style(likes.getUnselectedStyle());
//            s.setFgColor(0xff2d55);
//            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//            likes.setIcon(heartImage);
//        }
//        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

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

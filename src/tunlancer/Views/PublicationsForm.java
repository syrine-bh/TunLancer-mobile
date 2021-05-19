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
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CN;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import tunlancer.Models.Publication;
import tunlancer.Services.ServicePublication;
import tunlancer.utils.Statics;

/**
 *
 * @author Cyrina
 */
public class PublicationsForm extends BaseForm {

    ServicePublication SP;
    ArrayList<Publication> publications;

    public PublicationsForm(Resources res) {
        super("Publications", BoxLayout.y());

        publications = new ArrayList<>();
        SP = new ServicePublication();
        publications = SP.getAllPublications();

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

        addTab(swipe, res.getImage("back.png"), new Label(), "Trouvez l'emploi parfait que vous méritez.", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        Component.setSameSize(spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));

        Container Cpost = new Container(BoxLayout.y());

        Tabs Cstories = new Tabs();
        Cstories.setSwipeActivated(true);
        Cstories.setAnimateTabSelection(true);
        Cstories.setEagerSwipeMode(true);
        Cstories.setTabsContentGap(CN.convertToPixels(20, true));
        Cstories.setUIID("Container");
        Cstories.setTabUIID("Container");
        Cstories.getTabsContainer().setUIID("Container");
        Cstories.getContentPane().setUIID("Container");

        //code ici 
        //affichage des publications a partir du webservice
        for (int i = 0; i < publications.size(); i++) {
            String urlimage = Statics.BASE_IMG_URL + "publications/" + publications.get(i).getImage_name();
            if (publications.get(i).getType() == 0) {
                EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("loading.png"), false);
                Image image = URLImage.createToStorage(enc3, "stock" + publications.get(i).getImage_name() + ".png", urlimage);

                Label navigate = new Label("navigate");
                navigate.getAllStyles().setFgColor(0xC12222);
                Container single = addButton(publications.get(i), image, publications.get(i).getIdU(), publications.get(i).getDescription(), navigate);
                Cpost.add(single);
            } else {
                EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("loading.png"), false);
                Image image = URLImage.createToStorage(enc3, "stock" + publications.get(i).getImage_name() + ".png", urlimage);
                Cstories.addTab("", addStory(image, publications.get(i).getIdU(), publications.get(i).getDescription(), false, 0, 0));

            }

        }

        Cstories.setSelectedIndex(1);
        Label lsee = new Label("Nouveautés");
        lsee.getAllStyles().setFgColor(0x5f5f5f);

        Container h = new Container(new BorderLayout());
        Container f = new Container(BoxLayout.y());
        Button add = new Button("Ajouter une publication !");

        add.addPointerPressedListener((e) -> {
            AddPublication np = new AddPublication(res);
            np.show();
        });

        Label lstorie = new Label("S  T  O  R  I  E  S");
        lstorie.getAllStyles().setFgColor(0x000000);
        f.add(lsee);
        f.add(lstorie);
        h.add(BorderLayout.WEST, f);
        h.add(BorderLayout.EAST, add);

        Label ltrend = new Label("T E N D A N C E S");
        ltrend.getAllStyles().setFgColor(0x000000);

        Label lwatch = new Label("les dernieres publications de notre communauté");
        lwatch.getAllStyles().setFgColor(0x5f5f5f);

        add(h);
        add(Cstories);
        add(new Label(""));
        add(ltrend);
        add(lwatch);
        add(Cpost);

    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);

        Label comments = new Label(commentsStr);

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
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private Container addStory(Image img, LinkedHashMap<Object, Object> user, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(34.5f);
        int width = Display.getInstance().convertToPixels(28f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        //Container cnt = BorderLayout.west(image);
        Container cnt = new Container(BoxLayout.y());
        cnt.add(image);
        Label lusername = new Label((String) user.get("username"));
        lusername.getAllStyles().setFgColor(0x5f5f5f);
        cnt.add(lusername);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea ua = new TextArea((String) user.get("firstname") + " " + (String) user.get("lastname"));
        ua.setUIID("NewsTopLine");
        ua.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0x5f5f5f);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        image.addActionListener(e -> ToastBar.showMessage((String) user.get("Prenom") + " " + (String) user.get("Nom") + " : \n" + title, FontImage.MATERIAL_INFO));
        return cnt;

    }

    private Container addButton(Publication publication, Image img, LinkedHashMap<Object, Object> user, String title, Label navigate) {
        int height = Display.getInstance().convertToPixels(61.5f);
        int width = Display.getInstance().convertToPixels(60f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.center(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea ua = new TextArea((String) user.get("Nom") + " " + (String) user.get("Prenom"));
        ua.setUIID("NewsTopLine");
        ua.setEditable(false);

        Label likes = new Label(" Likes  ", "NewsBottomLine");
        likes.getAllStyles().setFgColor(0x5f5f5f);
        likes.setTextPosition(RIGHT);

        Style s = new Style(likes.getUnselectedStyle());
        s.setFgColor(0x5f5f5f);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
        likes.setIcon(heartImage);

        Label comments = new Label(" Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
        comments.getAllStyles().setFgColor(0x5f5f5f);

        Container cua = new Container(new BorderLayout());

        cua.add(BorderLayout.WEST, ua);
        cua.add(BorderLayout.EAST, navigate);

        Label spacer1 = new Label("aa");
        spacer1.getAllStyles().setFgColor(0xFFFFFF);
        cnt.add(BorderLayout.NORTH, cua);
        cnt.add(BorderLayout.SOUTH,
                BoxLayout.encloseY(
                        BoxLayout.encloseX(likes, comments),
                        ta,
                        spacer1
                ));

        return cnt;

    }

    private Container addPublicationToForm(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));

        return cnt;
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.InputStream;
import java.util.Random;
import tunlancer.Models.Publication;
import tunlancer.Services.ServicePublication;

/**
 *
 * @author Cyrina
 */
public class modifierPublication  extends BaseForm {
     private ServicePublication SP;
    String GlobalPath = "";
    String GlobalExtension = "";
    int changed=0;
     public modifierPublication(Resources res, Publication current) {
        super(current.getDescription(), BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier votre publication");
        getContentPane().setScrollVisible(false);
           this.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
            PublicationsForm l = new PublicationsForm(res);
            l.showBack();
        });
           //super.addSideMenu(res);
        Tabs swipe = new Tabs();

        addTab(swipe, res.getImage("back.png"), new Label(), "Trouvez l'emploi parfait que vous méritez.", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, new Label(), new Label());
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        Container f = new Container(BoxLayout.y());

        TextField description = new TextField("", "écrivez votre description", 50, TextField.ANY);
        description.getAllStyles().setFgColor(0x5f5f5f);
        description.getAllStyles().setPaddingBottom(10);
        description.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(50).
                stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
        description.getAllStyles().setMarginLeft(1);
        description.getAllStyles().setMarginRight(1);
        description.getAllStyles().setMarginTop(1);

        TextField localisation = new TextField("", "Localisation", 50, TextField.ANY);
        localisation.getAllStyles().setFgColor(0x5f5f5f);
        localisation.getAllStyles().setPaddingBottom(3);
        localisation.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(50).
                stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
        localisation.getAllStyles().setMarginLeft(1);
        localisation.getAllStyles().setMarginRight(1);
        localisation.getAllStyles().setMarginTop(1);

        TextField tup = new TextField("", "Path");

        tup.getAllStyles().setFgColor(0x5f5f5f);
        Button upload = new Button("importer");
        Label limport = new Label("aucun fichier est  selectioné");
        upload.addPointerPressedListener((ei) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", e2 -> {
                    String file = (String) e2.getSource();
                    System.out.println("file name :" + file);
                    if (file == null) {
                        System.out.println("No file was selected");
                    } else {
                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                        }
                        if ("txt".equals(extension)) {
                            FileSystemStorage fs = FileSystemStorage.getInstance();
                            try {
                                InputStream fis = fs.openInputStream(file);
                                System.out.println(Util.readToString(fis));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }
                        } else {
                            //moveFile(file,)
                            String path = file.substring(7);
                            System.out.println("Selected file :" + file.substring(44) + "\n" + "path :" + path);
                            limport.setText("file imported");
                            limport.getAllStyles().setFgColor(0x69E781);
                            tup.setText(path);
                            GlobalPath = path;
                            GlobalExtension = file.substring(file.lastIndexOf(".") + 1);
                            changed=1;
                        }
                    }
                });
            }
        });
        Button submitPost = new Button("  Poster ");

        submitPost.addPointerPressedListener((e) -> {  
           /* {if (changed==0){
                        current.setDescription(description.getText());
                        if(ServicePublication.getInstance().EditPost(current)){
                            System.out.println("current:"+current);
                            newPosts l = new newPosts(res);
                            l.showBack();
                        }
                    }else{ */

            Random rand = new Random();
            int upperbound = 7483647;
            int int_random = rand.nextInt(upperbound);
            String Fullname = "MobileGenerated_" + int_random + "." + GlobalExtension;
            System.out.println(Fullname);

            boolean moving = moveFile(GlobalPath, "C:/wamp64/www/TunLancer/public/images/publications/" + Fullname);

            Publication p = new Publication();
            p.setImage_name(Fullname);
            p.setArchive(0);
            p.setType(0);
            p.setIdUser(1);
            p.setLocalisation(localisation.getText());
            p.setDescription(description.getText());

            if (ServicePublication.getInstance().addPublication(p)) {

                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("Publication ajoutée");
                status.show();

                PublicationsForm l = new PublicationsForm(res);
                l.showBack();
            };
       
        });

        Button submitStory = new Button("  Ajouter a votre story", res.getImage("photo.png"));
        Container csub = new Container(BoxLayout.x());
        Label archiv = new Label("Archiver");
        archiv.getAllStyles().setFgColor(0xC12222);

        csub.addAll(submitStory, submitPost, archiv);

        Container cimport = new Container(new BorderLayout());

        cimport.add(BorderLayout.WEST, limport);
        cimport.add(BorderLayout.EAST, upload);

        f.addAll(localisation,description, cimport);

        add(f);
        csub.getAllStyles().setMarginTop(750);
        add(csub);
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

    private Container addButtonStory(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(34.5f);
        int width = Display.getInstance().convertToPixels(28f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        //Container cnt = BorderLayout.west(image);
        Container cnt = new Container(BoxLayout.y());
        cnt.add(image);

        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea ua = new TextArea("");
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

        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
        return cnt;

    }

    public boolean moveFile(String sourcePath, String targetPath) {
        System.out.println("inside movefile");
        File fileToMove = new File(sourcePath);
        return fileToMove.renameTo(new File(targetPath));
    }

}

    

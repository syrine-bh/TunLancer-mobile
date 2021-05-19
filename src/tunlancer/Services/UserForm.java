/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import com.codename1.components.ScaleImageLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;



/**
 * GUI builder created Form
 *
 * @author Tiburcio
 */
public class UserForm extends com.codename1.ui.Form {
       private String url = "http://localhost:8000/Login/";
    private ConnectionRequest connectionRequest;
   

    public UserForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public UserForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        showFormElements();
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("UserForm");
        setName("UserForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    private void showFormElements() {
        this.setScrollable(false);
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        showData(this);
    }

    private void showData(UserForm form) {
        String token = (String) Storage.getInstance().readObject("token");
        if(token == null || token.equals("")){
            showIfNotLoggedIn(form);
        } else {
            showIfLoggedIn(form);
        }
    }

    private void showIfNotLoggedIn(UserForm form) {
        try {
            form.getContentPane().removeAll();
            Storage.getInstance().writeObject("token", "");
            
            ScaleImageLabel myPic = new ScaleImageLabel();
            Image img = Image.createImage("/anonimo.jpg");
            myPic.setIcon(img);
            Dimension d = new Dimension(50, 50);
            myPic.setPreferredSize(d);
            
            form.add(myPic);
            
            form.add(new Label("User not connected"));
            
            Button buttonLogin = new Button("Login");
            buttonLogin.addActionListener((e) -> {
                facebookLogin(form);
            });
            form.add(buttonLogin);
            
            form.revalidate();
            //form.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showIfLoggedIn(UserForm form) {
        String token = (String) Storage.getInstance().readObject("token");
        System.out.println("ACEEESSSS TOKEN "+ token);
        FaceBookAccess.setToken(token);
            final User me = new User();
            //import slfj-1.7.25 w restfb w taw yekhdem jawou behy 
            //ah w now u have all the data try to make a good display cuz i suck at it w gimme ideas
            
            FacebookClient facebookClient = new DefaultFacebookClient(token, "490b349f7387626f63ea4b4e2b3d2c2f", Version.LATEST);
            com.restfb.types.User user = facebookClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", "id,about,email,address,birthday,name,picture{url},first_name,last_name"));
            System.out.println(user.getEmail()+" , "+user.getFirstName()+" , "+user.getLastName()+" ,"+user.getName()
            +" , "+user.getAgeRange());
            try {

                FaceBookAccess.getInstance().getUser("me",me,  new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String miNombre = me.getName();
                        
                        
                        form.getContentPane().removeAll();
                        
                        form.add(new Label(miNombre));
                        
                        Button buttonLogout = new Button("Logout");
                        Button confirmerBtn = new Button("Login to app ");
                               confirmerBtn.addActionListener(e -> {
            connectionRequest = new ConnectionRequest();
            connectionRequest.setPost(false);
            connectionRequest.setUrl(url + user.getFirstName() + "-" + user.getLastName());
            connectionRequest.addResponseListener(a -> {

                try {
                    String resultat = new String(connectionRequest.getResponseData());

                    JSONArray jsonarray = new JSONArray(resultat);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name = jsonobject.getString("firstname");
                        String url = jsonobject.getString("lastname");
                        final int id = jsonobject.getInt("id");
                      //  MEMBER_ID = id;
                        System.out.println(name + "  , " + url + " ; id : " + id);
                    }
                  //  if (MEMBER_ID != 0) {
                        Form page2 = new Form("Welcome");
                        Button back = new Button("Back");
                        back.addActionListener(b -> {
                            show();
                        });
                        page2.add(back);
                        page2.show();

                  //  } else {
                        Dialog.show("Wrong credentials", "Error", "OK", "Cancel");

                   // }

                } catch (JSONException ex) {

                }

            });
            NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        });
                        buttonLogout.addActionListener((e) -> {
                            facebookLogout(form);
                            showIfNotLoggedIn(form);
                        });
//leave the image import as it is
                        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
                        URLImage background = URLImage.createToStorage(placeholder, "fbuser.jpg",
                                "https://graph.facebook.com/v2.11/me/picture?access_token=" + token);
                        background.fetch();
                        ScaleImageLabel myPic = new ScaleImageLabel();
                        myPic.setIcon(background);
                        form.add(confirmerBtn);
                        form.add(myPic);
                        form.add(buttonLogout);
                        
                        form.revalidate();
                        //form.show();
                    }

                    
                });
            } catch (IOException ex) {
                ex.printStackTrace();
                showIfNotLoggedIn(form);
            }
    }

    private void facebookLogout(UserForm form) {
        String clientId = "160006897984731";
        String redirectURI = "http://localhost/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "490b349f7387626f63ea4b4e2b3d2c2f";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);

        //trigger the login if not already logged in
        fb.doLogout();
        Storage.getInstance().writeObject("token", "");
        showIfNotLoggedIn(form);
    }
    
    private void facebookLogin(UserForm form) {
        //use your own facebook app identifiers here   
        //These are used for the Oauth2 web login process on the Simulator.
        String clientId = "160006897984731";
        String redirectURI = "http://whitedisplay.com/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "490b349f7387626f63ea4b4e2b3d2c2f";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
      
        //Sets a LoginCallback listener
//        
//        fb.setCallback(new LoginCallback() {
//            @Override
//            public void loginFailed(String errorMessage) {
//                System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//                Storage.getInstance().writeObject("token", "");
//                showIfNotLoggedIn(form);
//            }
//
//            @Override
//            public void loginSuccessful() {
//                System.out.println("YYYYYYYEEEEEEEEEESSSSSSSSS");
//                String token = fb.getAccessToken().getToken();
//                Storage.getInstance().writeObject("token", token);
//                showIfLoggedIn(form);
//            }
//            
//        });
        //trigger the login if not already logged in
        fb.setCallback(null);
        if(!fb.isUserLoggedIn()){
            fb.doLogin();
        }else{
            //get the token and now you can query the facebook API
            String token = fb.getAccessToken().getToken();
            
            
            Storage.getInstance().writeObject("token", token);
            showIfLoggedIn(form);
            
        }
       

    }
}

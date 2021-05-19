/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.utils;

/**
 *
 * @author asus
 */
// Install the Java helper library from twilio.com/docs/java/install

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
      public static final String ACCOUNT_SID = "ACbf3394b997ef61cad446d094d7bf7cea";
    public static final String AUTH_TOKEN = "94dec8a3c40f3e1931e127eca4232337";
       public void sendSms(){
    // Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure
  


       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21626203571"),
                new com.twilio.type.PhoneNumber("+16122604142"),
                "Felicitations! Vous etes Maintenant Inscrit à Tunlancer")
           .create();

       System.out.println(message.getSid());
    
}
}
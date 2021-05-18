/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.utils;


import tunlancer.Models.Users;

/**
 *
 * @author Anis
 */
public class UserSession {
     public static UserSession instance;

    private Users u;

    public Users getU() {
        return u;
    }

   

    @Override
    public String toString() {
        return "UserSession{" +
                "u=" + u +
                '}';
    }

    public UserSession(Users u) {
        this.u = u;

    }

    public static UserSession getInstance(Users u) {
        if(instance == null) {
            instance = new UserSession(u);
        }
        return instance;
    }

    

    public void cleanUserSession() {
        instance=null;
    }


    }


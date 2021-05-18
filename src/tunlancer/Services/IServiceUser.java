/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Services;

import tunlancer.Models.Users;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IServiceUser {
    public void AddUser(Users u);
    public int login(String email, String password);
    public List <Users> AfficheUser();
    public void deleteUser(Users u);
    public void updateUser(Users u);
    public boolean findUtilisateur(String mail);
     public void updateMotdepasse(String mail, String mdp);
     public  void findByid(int id);
  
}

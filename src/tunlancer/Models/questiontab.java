/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import utils.MaConnexion;

/**
 *
 * @author Hiba
 */
public class questiontab {

    private String questions;
    private int id;
    private int question_id;
    private Quiz quiz;
    private reponsetab[] reponses = new reponsetab[4];
    private int numReponses = 0;
    private String answer;

    public questiontab(String questions, int question_id,String answer)  {
        this.questions = questions;
        this.question_id = question_id;
        this.answer=answer;
//        String SQL = "SELECT reponses,statu FROM reponsetab WHERE question_id=" + this.question_id;
//        Connection connection = MaConnexion.getInstance().getConnection();
//        PreparedStatement pst = connection.prepareStatement(SQL);
//        ResultSet result = connection.createStatement().executeQuery(SQL);
//        while (result.next()) {
//            String reponse = result.getString("reponses");
//            boolean statu = result.getBoolean("statu");
//            reponses[numReponses] = new reponsetab(reponse, statu);
//            numReponses++;

//        }
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public reponsetab[] getReponses() {
        return reponses;
    }

    public void setReponses(reponsetab[] reponses) {
        this.reponses = reponses;
    }

    public int getNumReponses() {
        return numReponses;
    }

    public void setNumReponses(int numReponses) {
        this.numReponses = numReponses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public questiontab() {
    }

    public questiontab(String questions, int id, int question_id) {
        this.questions = questions;
        this.id = id;
        this.question_id = question_id;
    }
    

    @Override
    public String toString() {
//        String display="";
//        String letters="ABCD ";
//        for (int i=0;i<numReponses;i++){
//           display+=reponses[i].toString(letters.charAt(i))+"\n";
//        }
        return questions;
//                +"\n\n"+display;
    }

    public boolean isCorrect (char letter){
        String letters="ABCD";
        int pos=letters.indexOf(letter)+1;
        return reponses[pos].isStatu();
                 
    }
    

}
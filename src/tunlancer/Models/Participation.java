

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;




import java.util.Date;

public class Participation {
    private int id;
    private Concour concour_id;
    private Users user_id;
    private Date date_participation;
    private Video video_id;

    public Participation(int id, Concour concour_id, Users user_id, Date date_participation, Video video_id) {
        this.id = id;
        this.concour_id = concour_id;
        this.user_id = user_id;
        this.date_participation = date_participation;
        this.video_id = video_id;
    }

    public Participation(Concour concour_id, Users user_id, Date date_participation, Video video_id) {
        this.concour_id = concour_id;
        this.user_id = user_id;
        this.date_participation = date_participation;
        this.video_id = video_id;
    }

    public Participation() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getConcour_id() {
        return concour_id.getId();
    }

    public void setConcour_id(Concour concour_id) {
        this.concour_id = concour_id;
    }

    public String getUser_id() {
        return user_id.getNom();
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Date getDate_participation() {
        return date_participation;
    }

    public void setParticipation_date(Date date_participation) {
        this.date_participation = date_participation;
    }

    public Video getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Video video_id) {
        this.video_id = video_id;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", concour_id=" + concour_id +
                ", user_id=" + user_id +
                ", participation_date=" + date_participation +
                ", video_id=" + video_id +
                '}';
    }
}

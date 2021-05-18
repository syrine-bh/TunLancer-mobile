/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hiba
 */
public class Video {
    private int id;
    private String url;
    private String title;
    private Date publish_date;
    private Users owner;
    private List<Integer> Votes;

    public Video() {
    }
    public List<Integer> getNbVote() {
        return Votes;
    }

    public Video(String url, String title, Date publish_date) {
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
    }

    public Video(String url, String title, Date publish_date, Users owner) {
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.owner = owner;
    }

    public Video(int id, String url, String title, Date publish_date) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
    }
//    private static final Logger LOG = Logger.getLogger(Video.class.getName());

    public Video(int id, String url, String title, Date publish_date, Users owner) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.owner = owner;
    }
    

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public Users getOwner() {
        return owner;
    }
  public void setVotes(List<Integer> Votes) {
        this.Votes = Votes;
    }
    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.url);
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.publish_date);
        hash = 41 * hash + Objects.hashCode(this.owner);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.publish_date, other.publish_date)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Video{" + "id=" + id + ", url=" + url + ", title=" + title + ", publish_date=" + publish_date + ", owner=" + owner + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.Models;

import java.util.Objects;

/**
 *
 * @author Hiba
 */
public class Vote {
    private Users user_id;
    private Video video_id;

    public Vote(Users user_id, Video video_id) {
        this.user_id = user_id;
        this.video_id = video_id;
    }

    public Vote() {
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Video getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Video video_id) {
        this.video_id = video_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.user_id);
        hash = 17 * hash + Objects.hashCode(this.video_id);
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
        final Vote other = (Vote) obj;
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        if (!Objects.equals(this.video_id, other.video_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vote{" + "user_id=" + user_id + ", video_id=" + video_id + '}';
    }

    
    
    
}

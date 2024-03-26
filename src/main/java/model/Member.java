package model;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        //무한루프에 빠지지 않도록 체크
        if(!team.getMembers().contains(this)){
            team.getMembers().add(this);
        }
    }
    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Member() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }


}

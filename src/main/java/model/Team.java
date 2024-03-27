package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

//    public void addMember(Member member){
//        this.members.add(member);
//        if(member.getTeam() != this){ //무한루프에 빠지지 않도록 체크
//            member.setTeam(this);
//        }
//    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Team() {

    }

    public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
 }

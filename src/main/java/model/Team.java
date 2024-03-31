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

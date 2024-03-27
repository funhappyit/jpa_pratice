package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @Column(name="MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

//    @ManyToMany
//    @JoinTable(name="MEMBER_PRODUCT",
//    joinColumns = @JoinColumn(name="MEMBER_ID"),
//    inverseJoinColumns = @JoinColumn(name="PRODUCT_ID"))
//    private List<Product> products = new ArrayList<Product>();


//    @OneToOne
//    @JoinColumn(name="LOCKET_ID")
//    private Locker locker;
//
//    @ManyToOne
//    @JoinColumn(name="TEAM_ID")
//    private Team team;

//    public Locker getLocker() {
//        return locker;
//    }
//
//    public void setLocker(Locker locker) {
//        this.locker = locker;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//        //무한루프에 빠지지 않도록 체크
//        if(!team.getMembers().contains(this)){
//            team.getMembers().add(this);
//        }
//    }
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

//    public Team getTeam() {
//        return team;
//    }


}

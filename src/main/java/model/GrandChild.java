package model;

import javax.persistence.*;

//손자
//@Entity
public class GrandChild {

    @Id @GeneratedValue
    @Column(name="GRANDCHILD_ID")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="CHILD_ID")
    private Child child;




}

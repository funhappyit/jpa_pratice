package model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//부모
//@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name="PARENT_ID")
    private String id;
    private String name;

    @ManyToMany
    @JoinTable(name="PARENT_CHILD",
            joinColumns = @JoinColumn(name="PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name="CHILD_ID"))
    private List<Child> child = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

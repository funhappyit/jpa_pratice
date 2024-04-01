package model;
import javax.persistence.*;

@Entity
public class Member{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded Period workPeriod; // 근무 시간
    @Embedded Address homeAddress;// 집 주조

}

package model;
import javax.persistence.*;

@Entity
public class Member{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded Address address;//임베디드 타입 포함
    @Embedded PhoneNumber phoneNumber; //임베디드 타입 포함

}

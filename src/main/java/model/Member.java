package model;
import javax.persistence.*;

@Entity
public class Member{

    @Id
    @Column(name="member_id")
    @GeneratedValue
    private Long id;
    private String name;

    @Embedded Address homeAddress;//임베디드 타입 포함

    //@AttributeOverride로 속성 재정의
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",column = @Column(name="COMPANY_CITY")),
            @AttributeOverride(name="street",column = @Column(name="COMPANY_STREET"))
    })
    Address companyAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }
}

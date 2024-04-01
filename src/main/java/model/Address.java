package model;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;


@Embeddable
@Access(AccessType.FIELD)
public class Address {

    private String city;

    protected Address(){}//JPA에서 기본 생성자는 필수다.

    //생성자로 초기 값을 설정한다.
    public Address(String city){
        this.city = city;
    }

    public String getCity() {
        return city;
    }


}

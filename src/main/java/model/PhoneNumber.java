package model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {
    String areaCode;
    String plusFour;
    @ManyToOne PhoneServiceProvider provider; //엔티티 참조

}

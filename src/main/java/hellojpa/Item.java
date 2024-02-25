package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 매핑 // JOINED 전략을 사용
@DiscriminatorColumn // DTYPE 컬럼을 사용
public class Item {

    @Id @GeneratedValue // 기본 키 매핑 // IDENTITY 전략을 사용
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

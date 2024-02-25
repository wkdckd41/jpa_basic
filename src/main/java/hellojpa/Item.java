package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 상속 매핑 // JOINED 전략을 사용
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 매핑 // SINGLE_TABLE 전략을 사용
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 상속 매핑 // TABLE_PER_CLASS 전략을 사용
public abstract class Item { // 추상 클래스로 만들어야 한다. // 추상 클래스로 만들지 않으면 구분 컬럼을 사용할 수 없다.

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

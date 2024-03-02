package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) // cascade = CascadeType.ALL 옵션 추가 // 영속성 전이: 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) // 고아 객체 제거
    // 고아 객체 제거: 부모 엔티티의 컬렉션에서 자식 엔티티의 참조만 제거하면 자식 엔티티가 자동으로 삭제된다.
    // 고아 객체 제거는 참조하는 곳이 하나일 때 사용해야 한다. 참조하는 곳이 여러군데에서 참조하면 다른 곳에서도 참조하고 있는지 확인해야 한다.
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

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

    public List<Child> getChildList() {
        return childList;
    }
}

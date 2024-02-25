package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 읽기 전용으로 설정
    private Team team;

    @OneToOne // 일대일 관계
    @JoinColumn(name = "LOCKER_ID") // 외래키가 있는 곳에 걸어준다.
    private Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

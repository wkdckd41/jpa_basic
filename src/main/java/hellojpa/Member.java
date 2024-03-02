package hellojpa;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;


//    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 // 즉시 로딩은 예상하지 못한 SQL이 발생할 수 있다. 실무에서 사용X
//    // 즉시 로딩은 JPQL에서 N+1문제가 발생할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); //단방향 연관관계 설정, 참조 저장
            em.persist(team);

            em.flush(); //강제로 DB에 쿼리를 날림
            em.clear(); //영속성 컨텍스트를 초기화

            Team findTeam = em.find(Team.class, team.getId());//영속성 컨텍스트에 있는지 확인
            List<Member> members = findTeam.getMembers(); //팀에 있는 멤버들을 가져옴

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //영속성 컨텍스트를 종료
        }
        emf.close();
    }
}

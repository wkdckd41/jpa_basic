package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");

            team.getMembers().add(member);

            em.persist(team);

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //영속성 컨텍스트를 종료
        }
        emf.close();
    }
}

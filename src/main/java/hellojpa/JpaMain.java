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

            //준영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

//            em.detach(member); 특정 엔티티만 준영속상태 전환
            em.clear(); // 영속성 컨텍스트를 완전히 초기화

            Member member2 = em.find(Member.class, 150L);

            System.out.println("=======================================================================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //영속성 컨텍스트를 종료
        }
        emf.close();
    }
}

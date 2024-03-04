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
            member.setHomeAddress(new Address("city1", "street", "10000"));
            member.setWorkPeriod(new Period());

            em.persist(member); //영속성 컨텍스트에 저장

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) { //예외 처리
            tx.rollback(); //트랜잭션 롤백
            e.printStackTrace(); //예외 출력
        } finally {
            em.close(); //영속성 컨텍스트를 종료
        }
        emf.close();
    }
}

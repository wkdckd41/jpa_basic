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

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush(); //강제로 DB에 쿼리를 날림
            em.clear(); //영속성 컨텍스트를 초기화

            //조회
            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam(); // 객체 그래프 탐색
            System.out.println("findTeam = " + findTeam.getName());

            Team newTeam = em.find(Team.class, 100L); // 새로운 팀을 조회
            findMember.setTeam(newTeam); //연관관계 수정

            tx.commit(); //트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //영속성 컨텍스트를 종료
        }
        emf.close();
    }
}

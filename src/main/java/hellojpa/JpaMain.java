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
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

//            member.getAddressHistory().add(new Address("old1", "street", "10000"));
//            member.getAddressHistory().add(new Address("old2", "street", "10000"));

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));


            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===========START===========");

            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

            //homeCity -> newCity
            findMember.setHomeAddress(new Address
                    ("newCity", findMember.getHomeAddress().getStreet(), findMember.getHomeAddress().getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //old1 -> newCity1
//            findMember.getAddressHistory().remove(new Address("old1", "street", "10000")); //equals와 hashCode를 구현하지 않으면 제대로 삭제되지 않는다.
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000")); //equals와 hashCode를 구현하지 않으면 중복 데이터가 들어갈 수 있다.

            System.out.println("===========END===========");

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

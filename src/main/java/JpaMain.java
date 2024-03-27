import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // [엔티티 메니저 팩토리]- 생성 JPA
        // 엔티티 메니저 팩토리는 애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        //[엔티티 메니저] - 생성
        //엔티티 메니저를 사용해서 엔티티를 데이터베이스에 등록/수정/삭제/조회할 수 있다.
        EntityManager em = emf.createEntityManager();
        //[트랜잭션] - 획득
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin(); //[트랜잭션] -시작
            find(em); //비즈니스 로직 실행
            tx.commit(); //[트랜잭션] - 커밋
        }catch (Exception e){
            tx.rollback(); //[트랜잭션] - 롤백
        }finally {
            em.close(); //[엔티티 메니저] - 종료
        }
        emf.close(); //[엔티티 매니저 팩토리] - 종료
    }


    //비즈닌스 로직
//    private static void logic(EntityManager em){
//        //팀 1 저장
//        Team team1 = new Team("team1","팀1");
//        em.persist(team1);
//
//        //회원1 저장
//        Member member1 = new Member("member1","회원1");
//        member1.setTeam(team1); //연관관계 설정 member1-> team1
//        em.persist(member1);
//
//        //회원2 저장
//        Member member2 = new Member("member2","회원2");
//        member2.setTeam(team1); //연관관계 설정 member2->team1
//        em.persist(member2);
//    }

    //JPQL 조인 검색
//    private static void queryLogicJoin(EntityManager em){
//        String jpq1 = "select m from Member m join m.team t where "+"t.name=:teamName";
//
//        List<Member> resultList = em.createQuery(jpq1,Member.class)
//                .setParameter("teamName","팀1")
//                .getResultList();
//
//        for(Member member:resultList){
//            System.out.println("[query] member.username="+member.getUsername());
//        }
//    }
    //수정
//    private static void updateRelation(EntityManager em){
//        //새로운 팀2
//        Team team2 = new Team("team2","팀2");
//        em.persist(team2);
//
//        //회원1에 새로운 팀2 설정
//        Member member = em.find(Member.class,"member1");
//        member.setTeam(team2);
//    }
//    //연관관계를 삭제하는 코드
//    private static void deleteRelation(EntityManager em){
//        Member member1 = em.find(Member.class,"member1");
//        Member member2 = em.find(Member.class,"member2");
//        member1.setTeam(null);
//        member2.setTeam(null);
//        Team team = em.find(Team.class,"team1");
//        em.remove(team);
//
//    }

    //일대다 방향으로 객체 그래프 탐색
    public static void biDirection(EntityManager em){
        Team team = em.find(Team.class,"team1");
        List<Member> members = team.getMembers(); //(팀->회원) 객체 그래프 탐색

        for(Member member:members){
            System.out.println("member.username"+member.getUsername());
        }
    }

    //양방향 연관관계의 주의점
    public static void testSaveNonOwner(EntityManager em){
        //회원1 저장
        Member member1 = new Member("member1","회원1");
        em.persist(member1);

        //회원2 저장
        Member member2 = new Member("member2","회원2");
        em.persist(member2);

        Team team1 = new Team("team1","팀1");
        //주인이 아닌 곳만 연관관계 설정
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);

    }

//    private static void test순수한객체_양방향(EntityManager em) {
//        //팀1
//        Team team1 = new Team("team1","팀1");
//        Member member1 = new Member("member1","회원1");
//        Member member2 = new Member("member2","회원2");
//
//
//        member1.setTeam(team1); //연관관계 설정 member1 -> team1
//        team1.getMembers().add(member1);
//        member2.setTeam(team1); //연관관계 설정 member2 -> team1
//        team1.getMembers().add(member2);
//        List<Member> members = team1.getMembers();
//        System.out.println("members.size="+members.size());
//
//    }
//    private static void testORM_양방향(EntityManager em){
//        //팀1 저장
//        Team team1 = new Team("team1","팀1");
//        em.persist(team1);
//
//        Member member1 = new Member("member1","회원1");
//
//        //양방향 연관관계 설정
//        member1.setTeam(team1); //연관관게 설정 member1->team1
//        team1.getMembers().add(member1); //연관관계 설정 team1->member1
//        em.persist(member1);
//
//        Member member2 = new Member("member2","회원2");
//
//        //양방향 연관관계 설정
//        member2.setTeam(team1); //연관관계 설정 member2->team1
//        team1.getMembers().add(member2); // 연관관계 team->member2
//        em.persist(member2);
//    }
//    public static void testORM_양방향_리팩토링(EntityManager em){
//        Team team1 = new Team("team1","팀1");
//        em.persist(team1);
//
//        Member member1 = new Member("member1","회원1");
//        member1.setTeam(team1); //양방향 설정
//        em.persist(member1);
//
//        Member member2 = new Member("member2","회원2");
//        member2.setTeam(team1);
//        em.persist(member2);
//    }

    //저장
//    public static void save(EntityManager em){
//        Product productA = new Product();
//        productA.setId("productA");
//        productA.setName("상품A");
//        em.persist(productA);
//
//        Member member1 = new Member();
//        member1.setId("member1");
//        member1.setUsername("회원1");
//        member1.getProducts().add(productA); //연관관계 설정
//        em.persist(member1);
//
//    }

//    public static void find(EntityManager em){
//        Member member = em.find(Member.class,"member1");
//        List<Product> products = member.getProducts(); //객체 그래프 탐색
//        for(Product product : products){
//            System.out.println("product.name="+product.getName());
//        }
//    }

//    public static void findInverse(EntityManager em){
//        Product product = em.find(Product.class,"productA");
//        List<Member> members = product.getMembers();
//        for(Member member:members){
//            System.out.println("member = "+member.getUsername());
//        }
//    }

//    public static void save(EntityManager em){
//        //회원 저장
//        Member member1 = new Member();
//        member1.setId("member1");
//        member1.setUsername("회원1");
//        em.persist(member1);
//
//        //상품 저장
//        Product productA = new Product();
//        productA.setId("productA");
//        productA.setName("상품1");
//        em.persist(productA);
//
//        //회원상품 저장
//        MemberProduct memberProduct = new MemberProduct();
//        memberProduct.setMember(member1);
//        memberProduct.setProduct(productA);
//        memberProduct.setOrderAmount(2);
//
//        em.persist(memberProduct);
//    }

//    public static void find(EntityManager em){
//        //기본 키 생성
//        MemberProductId memberProductId = new MemberProductId();
//        memberProductId.setMember("member1");
//        memberProductId.setProduct("productA");
//
//        MemberProduct memberProduct = em.find(MemberProduct.class,memberProductId);
//
//        Member member = memberProduct.getMember();
//        Product product = memberProduct.getProduct();
//        System.out.println("member = "+member.getUsername());
//        System.out.println("product = "+product.getName());
//        System.out.println("orderAmount = "+memberProduct.getOrderAmount());
//
//    }
    public static void save(EntityManager em){
        //회원 저장
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원");
        em.persist(member1);

        //상품 저장
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        //주문 저장
        Order order = new Order();
        order.setMember(member1);
        order.setProduct(productA);
        order.setOrderAmount(2);
        em.persist(order);
    }

    public static void find(EntityManager em){
        Long orderId = 1L;
        Order order = em.find(Order.class,orderId);

        Member member = order.getMember();
        Product product = order.getProduct();

        System.out.println("member = "+member.getUsername());
        System.out.println("product = "+product.getName());
        System.out.println("orderAmount = "+order.getOrderAmount());

    }

}

import model.Member;
import model.Team;

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
            deleteRelation(em); //비즈니스 로직 실행
            tx.commit(); //[트랜잭션] - 커밋
        }catch (Exception e){
            tx.rollback(); //[트랜잭션] - 롤백
        }finally {
            em.close(); //[엔티티 메니저] - 종료
        }
        emf.close(); //[엔티티 매니저 팩토리] - 종료
    }

    //비즈닌스 로직
    private static void logic(EntityManager em){
        //팀 1 저장
        Team team1 = new Team("team1","팀1");
        em.persist(team1);

        //회원1 저장
        Member member1 = new Member("member1","회원1");
        member1.setTeam(team1); //연관관계 설정 member1-> team1
        em.persist(member1);

        //회원2 저장
        Member member2 = new Member("member2","회원2");
        member2.setTeam(team1); //연관관계 설정 member2->team1
        em.persist(member2);
    }

    //JPQL 조인 검색
    private static void queryLogicJoin(EntityManager em){
        String jpq1 = "select m from Member m join m.team t where "+"t.name=:teamName";

        List<Member> resultList = em.createQuery(jpq1,Member.class)
                .setParameter("teamName","팀1")
                .getResultList();

        for(Member member:resultList){
            System.out.println("[query] member.username="+member.getUsername());
        }
    }
    //수정
    private static void updateRelation(EntityManager em){
        //새로운 팀2
        Team team2 = new Team("team2","팀2");
        em.persist(team2);

        //회원1에 새로운 팀2 설정
        Member member = em.find(Member.class,"member1");
        member.setTeam(team2);
    }
    //연관관계를 삭제하는 코드
    private static void deleteRelation(EntityManager em){
        Member member1 = em.find(Member.class,"member1");
        Member member2 = em.find(Member.class,"member2");
        member1.setTeam(null);
        member2.setTeam(null);
        Team team = em.find(Team.class,"team1");
        em.remove(team);

    }

}

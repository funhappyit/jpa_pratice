package JpaMain;

import model.Member;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpaMainTest {
    //더미 데이터 생성
//    @BeforeEach
//    void setup(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabook");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//
//        // 팀 생성
//        Team team = entityManager.find(Team.class, 1L); // 이미 영속화된 팀 엔티티를 가져옴
//        if (team == null) { // 팀이 없으면 새로 생성
//            team = Team.builder().name("팀1").build();
//            entityManager.persist(team);
//        }
//
//        // 회원 생성 및 팀 연결
//        for(int i=0;i<10;i++){
//            Member member = Member.builder().username("member"+i).age(i).team(team).build();
//            entityManager.persist(member);
//        }
//
//        entityManager.getTransaction().commit();
//
//        entityManager.close();
//        entityManagerFactory.close();
//    }
    @Test
    void testNPlusOneIssue(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabook");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Member> members = entityManager.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();

        for (Member member : members) {
            System.out.println("Member: " + member.getUsername() + ", Team: " + member.getTeam().getName());
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


    }



}
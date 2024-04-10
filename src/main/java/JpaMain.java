import model.*;
import org.h2.engine.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
            joinQuery (em); //비즈니스 로직 실행
            tx.commit(); //[트랜잭션] - 커밋
        }catch (Exception e){
            System.out.println(e.getMessage());
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
//                .setParameter("teamName"
//    //JPQL 조인 검색
////    private static void queryLogicJoin(EntityManager em){
////        String jpq1 = "select m from Member m join m.team t where "+"t.name=:teamName";
////
////        List<Member> resultList = em.createQuery(jpq1,Member.class)
////                .setParameter("teamName","팀1"),"팀1")
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
//        Team team = em.find(Team.class,"team1");
//        List<Member> members = team.getMembers(); //(팀->회원) 객체 그래프 탐색
//
//        for(Member member:members){
//            System.out.println("member.username"+member.getUsername());
//        }
    }

    //양방향 연관관계의 주의점
    public static void testSaveNonOwner(EntityManager em){
        //회원1 저장
//        Member member1 = new Member("member1","회원1");
//        em.persist(member1);
//
//        //회원2 저장
//        Member member2 = new Member("member2","회원2");
//        em.persist(member2);
//
//        Team team1 = new Team("team1","팀1");
//        //주인이 아닌 곳만 연관관계 설정
//        team1.getMembers().add(member1);
//        team1.getMembers().add(member2);
//
//        em.persist(team1);

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
//     public static void save(EntityManager em){
//         //회원 저장
//         Member member1 = new Member();
//         member1.setId("member1");
//         member1.setUsername("회원");
//         em.persist(member1);
//
//         //상품 저장
//         Product productA = new Product();
//         productA.setId("productA");
//         productA.setName("상품1");
//         em.persist(productA);
//
//         //주문 저장
//         Order order = new Order();
//         order.setMember(member1);
//         order.setProduct(productA);
//         order.setOrderAmount(2);
//         em.persist(order);
//     }

   // public static void find(EntityManager em){
        // Long orderId = 1L;
        // Order order = em.find(Order.class,orderId);
        //
        // Member member = order.getMember();
        // Product product = order.getProduct();
        //
        // System.out.println("member = "+member.getUsername());
        // System.out.println("product = "+product.getName());
        // System.out.println("orderAmount = "+order.getOrderAmount());

   // }
//    public static void save(EntityManager em){
//        Movie movie = new Movie();
//        movie.setActor("송중기");
//        movie.setDirector("감독");
//        movie.setName("아이템");
//        movie.setPrice(10000);
//        movie.setStockQuantity(2);
//        em.persist(movie);
//
//    }

//    public static void save(EntityManager em){
//        Parent parent = new Parent();
//        parent.setId1("myId1"); //식별자
//        parent.setId2("myId2");
//        parent.setName("parentName");
//        em.persist(parent);
//    }

//    public static void find(EntityManager em){
//        ParentId parentId = new ParentId("myId1","myId2");
//        Parent parent = em.find(Parent.class,parentId);
//        System.out.println("parent---->"+parent.getName());
//    }
//    public static void save(EntityManager em){
//        Parent parent = new Parent();
//        ParentId parentId = new ParentId("myId1","myId2");
//        parent.setId(parentId);
//        parent.setName("parentName");
//        em.persist(parent);
//    }

//    public static void find(EntityManager em){
//        ParentId parentId = new ParentId("myId1","myId2");
//        Parent parent = em.find(Parent.class,parentId);
//
//        System.out.println(parent);
//    }

//    public static void save(EntityManager em){
//        Board board = new Board();
//        board.setTitle("제목");
//        em.persist(board);
//
//        BoardDetail boardDetail = new BoardDetail();
//        boardDetail.setContent("내용");
//        boardDetail.setBoard(board);
//        em.persist(boardDetail);
//    }
    public static void printUserAndTeam(EntityManager em){
//        Member member = em.find(Member.class,"member1");
//        Team team = member.getTeam();
//        System.out.println("회원이름:"+member.getUsername());
//        System.out.println("소속팀:"+team.getName());
    }

    public static void saveNoCascade(EntityManager em){
        //부모 저장
        Parent parent = new Parent();
        em.persist(parent);

        //1번 자식 저장
        Child child = new Child();
        child.setParent(parent); //자식 -> 부모 연관관계 설정
        parent.getChildren().add(child); //부모->자식
        em.persist(child);

        //2번 자식 저장
        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);
        em.persist(child2);
    }

    private static void saveWithCasecade(EntityManager em){
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent); //연관관계 추가
        child2.setParent(parent); //연관관계 추가
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        em.persist(parent);
    }
    private static void remove(EntityManager em){
        Parent findParent = em.find(Parent.class,1L);
        Child findChild1 = em.find(Child.class,2L);
        Child findChild2 = em.find(Child.class,3L);

        em.remove(findChild1);
        em.remove(findChild2);
        em.remove(findParent);
   //     findParent.getChildren().clear();
    }
    private static void save(EntityManager em){
//        Member member = new Member();
//       //임베디드 값 타입
//        member.setHomeAddress(new Address("통영","몽돌해수욕장","660-123"));
//
//        //기본값 타입 컬렉션
//        member.getFavoriteFoods().add("짬뽕");
//        member.getFavoriteFoods().add("짜장");
//        member.getFavoriteFoods().add("탕수육");
//
//        //임베디드 값 타입 컬렉션
//        member.getAddressHistory().add(new Address("서울","강남","123-123"));
//        member.getAddressHistory().add(new Address("서울","강북","000-000"));
//
//        em.persist(member);
    }

   // private static void use(EntityManager em){
        // Member member = new Member();
        // Member member2 = new Member();
        // Address address = member.getHomeAddress();
        // //회원 1의 주소값을 조회해서 새로운 주소값을 생성
        // Address newAddress = new Address(address.getCity());
        // member2.setHomeAddress(newAddress);
   // }

 //   public static void find(EntityManager em){
//        Member member = em.find(Member.class,1L);
//        Address homeAddress = member.getHomeAddress();
//        Set<String> favoriteFoods = member.getFavoriteFoods();
//        for(String favoriteFood:favoriteFoods){
//            System.out.println("favoriteFood = "+favoriteFood);
//        }
//        List<Address> addressHistory = member.getAddressHistory();
//        addressHistory.get(0);

  //  }
    public static void update(EntityManager em){
//        Member member = em.find(Member.class,1L);
//
//        //1.임베디드 값 타입 수정
//        member.setHomeAddress(new Address("새로운도시","신도시","123456"));
//
//        //2.기본값 타입 컬렉션 수정
//        Set<String> favoriteFoods = member.getFavoriteFoods();
//        favoriteFoods.remove("탕수육");
//        favoriteFoods.add("치킨");
//
//        //3.임베디드 값 타입 컬렉션 수정
//        List<Address> addressHistory = member.getAddressHistory();
//        addressHistory.remove(new Address("서울","기존 주소","123-123"));
//        addressHistory.add(new Address("새로운도시","신도시","123456"));


   }
//    public static void find(EntityManager em){
//        //쿼리 생성
//        String jpql = "select m from Member as m where m.name='kim'";
//        List<Member> resultList = em.createQuery(jpql,Member.class).getResultList();
//        System.out.println(resultList);
//
//    }
    public static void criteriaFind(EntityManager em){
        //Criteria 사용 준비
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);


        //루트 클래스(조회를 시작할 클래스)
        Root<Member> m = query.from(Member.class);

        //쿼리 실행
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"),"kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();

    }
    public static void queryDSL(EntityManager em){
        //준비
    }
    public static void nativeSQL(EntityManager em){
        String sql = "SELECT ID,AGE,TEAM_ID,NAME FROM MEMBER WHERE NAME='kim'";
        List<Member> resultList = em.createNativeQuery(sql,Member.class).getResultList();

    }

//    public static void typeQuery(EntityManager em){
//        TypedQuery<Member>  query = em.createQuery("SELECT m FROM Member m",Member.class);
//        List<Member> resultList = query.getResultList();
//        for(Member member:resultList){
//            System.out.println("member city="+member.getCity()+"street=>"+member.getStreet()+"zipcode"+member.getZipcode());
//        }
//    }
    public static void query(EntityManager em){
        Query query = em.createQuery("SELECT m.name,m.zipcode from Member m");
        List resultList = query.getResultList();
        for(Object o :resultList){
            Object[] result = (Object[]) o; //결과가 둘 이상이면 Object[] 반환
            System.out.println("username="+result[0]);
            System.out.println("age = "+result[1]);
        }
    }
    public static void paramQuery(EntityManager em){
        String usernameParam = "test";
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.name=:name",
                Member.class);
        query.setParameter("name",usernameParam);
        List<Member> resultList = query.getResultList();
        for(int i=0;i<resultList.size();i++){
            System.out.println(resultList.get(i).getCity());
        }

    }

    public static void paramPositionQuery(EntityManager em){
        String usernameParam = "test";
        List<Member> members = em.createQuery("SELECT m FROM Member m where m.name=?1",Member.class)
                            .setParameter(1,usernameParam)
                .getResultList();
        for(int i=0;i<members.size();i++){
            System.out.println(members.get(i).getCity());
        }
    }
    public static void paramManyQuery(EntityManager em){
        Query query = em.createQuery("SELECT m.name,m.city FROM Member m");
        List resultList = query.getResultList();

        Iterator iterator = resultList.iterator();
        while(iterator.hasNext()){
            Object[] row = (Object[]) iterator.next();
            String username = (String)row[0];
            String city = (String) row[1];
            System.out.println("username---->"+username);
            System.out.println("city-------->"+city);
        }
    }

    private static List<UserDTO> newUseAgo(EntityManager em){
        List<Object[]> resultList = em.createQuery("SELECT m.name,m.city FROM Member m").getResultList();

        //객체 변환 작업
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(Object[] row:resultList){
            UserDTO userDTO = new UserDTO((String)row[0],(String)row[1]);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    //New 명령어 사용후
    private static void newUseAfter(EntityManager em){
     TypedQuery<UserDTO> query = em.createQuery("SELECT new model.UserDTO(m.name,m.city) FROM Member m",UserDTO.class);
     List<UserDTO> resultList = query.getResultList();
    }
    //페이징 API
    private static void pageingAPI(EntityManager em){
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.name DESC ",Member.class);

        query.setFirstResult(10);
        query.setMaxResults(20);
        query.getResultList();

    }
    //집합과 정렬
//    private static void groupByAPI(EntityManager em){
//        Query query = em.createQuery("select t.name,COUNT(m.age),SUM(m.age),AVG(m.age),MAX(m.age),MIN(m.age) from Member m LEFT JOIN m.team t GROUP BY t.name");
//        List resultList = query.getResultList();
//
//        Iterator iterator = resultList.iterator();
//        while(iterator.hasNext()){
//            Object[] row = (Object[]) iterator.next();
//            System.out.println(row[0]);
//            System.out.println(row[1]);
//            System.out.println(row[2]);
//            System.out.println(row[3]);
//            System.out.println(row[4]);
//            System.out.println(row[5]);
//
//        }
//    }
//
//
//    private static void InnerJoin(EntityManager em){
//        String teamName = "팀A";
//        String query = "SELECT m FROM Member m INNER JOIN m.team t WHERE t.name = :teamName";
//
//        List<Member> members = em.createQuery(query,Member.class)
//                .setParameter("teamName",teamName)
//                .getResultList();
//
//        for(Member member:members){
//            System.out.println("[query] member.username="+member.getZipcode());
//        }
//    }
//
//    private static void InnerJoin2(EntityManager em){
//        String teamName = "팀A";
//        String query = "SELECT m,t FROM Member m JOIN m.team t";
//        List<Object[]> result = em.createQuery(query).getResultList();
//        for(Object[] row:result){
//            Member member = (Member) row[0];
//            Team team = (Team) row[1];
//            System.out.println("member_id"+member.getId()+"age"+member.getAge()+"city"+member.getCity()+"name"+member
//            .getName());
//            System.out.println("street"+member.getStreet()+"team_id"+member.getTeam());
//            System.out.println("team_id"+team.getId()+"name"+team.getName());
//
//        }
//
//    }
//
//    private static void FetchJoin(EntityManager em){
//        String jpql = "select m from Member m join fetch m.team";
//        List<Member> members = em.createQuery(jpql,Member.class).getResultList();
//        for(Member member:members){
//            //페치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안함
//            System.out.println("name="+member.getName()+","+"teamname="+member.getTeam().getName());
//        }
//    }

//    private static void CollectionFetchJoin(EntityManager em){
//        String jpql = "select distinct t from Team t join fetch t.members where t.name='팀A'";
//        List<Team> teams = em.createQuery(jpql,Team.class).getResultList();
//        for(Team team:teams){
//            System.out.println("teamname = "+team.getName()+", team="+team);
//            for(Member member : team.getMembers()){
//                //패치 조인으로 팀과 회원을 함께 조회해서 지연 로딩 발생 안함
//                 System.out.println("username="+member.getName()+"member="+member);
//            }
//
//        }
//    }
    private static void CriteriaQuery(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder(); //Criteria 쿼리 빌더

        //Criteria 생성, 반환 타입 지정
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);
        cq.select(m);

        TypedQuery<Member> query = em.createQuery(cq);
        List<Member> members = query.getResultList();
    }
    private static void CriteriaSearchQuery(EntityManager em){
         CriteriaBuilder cb = em.getCriteriaBuilder();

         CriteriaQuery<Member> cq = cb.createQuery(Member.class);

         Root<Member> m = cq.from(Member.class);

         //검색 조건 정의
        Predicate usernameEqual = cb.equal(m.get("name"),"회원1");

        Order ageDesc = cb.desc(m.get("age"));

        //쿼리 생성
        cq.select(m)
                .where(usernameEqual)
                .orderBy(ageDesc);

        List<Member> resultList = em.createQuery(cq).getResultList();
   }
   private static void CriteriaTuple(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<Member> m = cq.from(Member.class);
        cq.multiselect(
                m.get("name").alias("username"),
                m.get("age").alias("age")
        );
        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> resultlist= query.getResultList();
        for(Tuple tuple : resultlist){
            //튜플 별칭으로 조회
            String username = tuple.get("username",String.class);
            Integer age = tuple.get("age",Integer.class);
            System.out.println("username=====>"+username+"age=======>"+age);
        }
   }
   private static void TupleEntitySearch(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Member> m = cq.from(Member.class);
        cq.select(cb.tuple(
                m.alias("m"),//회원 엔티티, 별칭 m
                m.get("name").alias("username") //단순 값 조회
        ));
        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> resultList = query.getResultList();
        for(Tuple tuple:resultList){
            Member member = tuple.get("m",Member.class);
            String username = tuple.get("username",String.class);
            System.out.println("member=======>"+member+"====username==========>"+username);
        }
   }
   private static void GroupBy(EntityManager em){
       CriteriaBuilder cb = em.getCriteriaBuilder();
       CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
       Root<Member> m = cq.from(Member.class);

       Expression maxAge = cb.max(m.<Integer>get("age"));
       Expression minAge = cb.min(m.<Integer>get("age"));

       cq.multiselect(m.get("team").get("name"),maxAge,minAge);
       cq.groupBy(m.get("team").get("name"));

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> resultList = query.getResultList();
       for(Object[] row:resultList){
            Object member = (Object) row[0];
           Object maage = (Object) row[1];
//            Team team = (Team) row[1];
            System.out.println("member_team_name"+member+"member maxAge"+maage);
//            .getName());
//            System.out.println("street"+member.getStreet()+"team_id"+member.getTeam());
//            System.out.println("team_id"+team.getId()+"name"+team.getName());
//
//        }
       }
   }
   private static void having(EntityManager em){
       CriteriaBuilder cb = em.getCriteriaBuilder();
       CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
       Root<Member> m = cq.from(Member.class);

       Expression maxAge = cb.max(m.<Integer>get("age"));
       Expression minAge = cb.min(m.<Integer>get("age"));

       cq.multiselect(m.get("team").get("name"),maxAge,minAge);
       cq.groupBy(m.get("team").get("name"))
       .having(cb.gt(minAge,10));

       TypedQuery<Object[]> query = em.createQuery(cq);
       List<Object[]> resultList = query.getResultList();
       for(Object[] row:resultList){
           Object member = (Object) row[0];
           Object maage = (Object) row[1];
           Object minage = (Object) row[2];
//            Team team = (Team) row[1];
           System.out.println("member_team_name"+member+"member maxAge"+maage+"member minAge"+minage );
//            .getName());
//            System.out.println("street"+member.getStreet()+"team_id"+member.getTeam());
//            System.out.println("team_id"+team.getId()+"name"+team.getName());
//
//        }
       }
   }

   private static void joinQuery(EntityManager em){
       CriteriaBuilder cb = em.getCriteriaBuilder();
       CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
       Root<Member> m = cq.from(Member.class);

       Join<Member,Team> t = m.join("team",JoinType.INNER);
       cq.multiselect(m,t).where(cb.equal(t.get("name"),"팀A"));

       TypedQuery<Object[]> query = em.createQuery(cq);
       List<Object[]> resultList = query.getResultList();
//       for(Object[] row:resultList){
//           Object member = (Object) row[0];
//           Object maage = (Object) row[1];
//           Object minage = (Object) row[2];
//           System.out.println("member_team_name"+member+"member maxAge"+maage+"member minAge"+minage );
//       }
   }





}

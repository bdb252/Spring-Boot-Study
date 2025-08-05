package myjpa1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember1 {
	public static void main(String[] args) {
		
		//이와 같이 영속성을 생성한다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		/*
		순수 JPA를 사용할때 이정도 코드가 있다는 것만 확인하고 넘어가면 된다.
		차후 Spring-Data-JPA에서는 아래 부분은 모두 추상화되어 직접 작성할 필요가 없기 때문이다.
		 */
		try {
			//트랜잭션 시작
			transaction.begin();
			//테이블 및 시퀀스 생성을 위한 인스턴스 생성
			//인수생성자를 통해 초기화된 값이 테이블에 레코드로 입력된다.
			Member1 member1 = new Member1("홍길동", LocalDate.now());
			//영속성 개체로 Member1을 전달하여 추가한다.
			em.persist(member1);
			//JPA가 오라클에 테이블을 생성하면서 동기화된다.
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			em.close();
		}
		
		emf.close();
	}
}

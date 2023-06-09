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

		Team team = new Team();
		team.setName("TeamA");
		em.persist(team);

		Member member = new Member();
		member.setUsername("member1");
		member.setTeam(team);
		em.persist(member);

		Member findMember = em.find(Member.class, member.getId());

		Team findTeam = findMember.getTeam();
			System.out.println("findTeam.getName() = " + findTeam.getName());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}

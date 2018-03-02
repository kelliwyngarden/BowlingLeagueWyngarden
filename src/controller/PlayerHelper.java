package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Player;


public class PlayerHelper {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BowlingLeagueWyngarden");

	public void addPlayer(Player toAdd) {
		// TODO Auto-generated method stub

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
	}

	public List<Player> showAllPlayers() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Player> allResults = em.createQuery("select ph from Player ph", Player.class);
		List<Player> allPlayer = allResults.getResultList();
		em.close();
		return allPlayer;
	}

	public void deletePlayer(Player toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Player> typedQuery = em.createQuery(
				"select ph from Player ph where ph.firstName = :selectedfirstName,lirstName = :selectedlirstName  ",
				Player.class);
		typedQuery.setParameter("selectedfirstName", toDelete.getFirstName());
		typedQuery.setParameter("selectedlastName", toDelete.getLastName());
		typedQuery.setMaxResults(1);
		Player result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public void cleanUp() {
		emfactory.close();
	}
}
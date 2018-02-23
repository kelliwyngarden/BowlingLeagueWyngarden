package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	public void cleanUp() {
		emfactory.close();
	}
}
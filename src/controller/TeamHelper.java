package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Team;

public class TeamHelper {
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BowlingLeagueWyngarden");

		public void addTeam(Team toAdd) {
			// TODO Auto-generated method stub

			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(toAdd);
			em.getTransaction().commit();
			em.close();
		}
		
		public List<Team> showAllTeams() {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			TypedQuery<Team> allResults = em.createQuery("select t from Team t ", Team.class);
			List<Team> allTeam = allResults.getResultList();
			em.close();
			return allTeam;
		}
		public void deleteTeam(Team toDelete) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Team> typedQuery = em.createQuery("select th from Team th where th.teamName = :selectedTeamName", Team.class);
			typedQuery.setParameter("selectedTeamName", toDelete.getTeamName());
			typedQuery.setMaxResults(1);
			Team result = typedQuery.getSingleResult();
			em.remove(result);
			em.getTransaction().commit();
			em.close();
		}
		
		public Team SearchForTeamByName(String team) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Team> typedQuery = em.createQuery("select t from Team t where t.teamName = :selectedTeamName", Team.class);
			typedQuery.setParameter("selectedTeamName", team);
			typedQuery.setMaxResults(1);
			Team result = typedQuery.getSingleResult();
			em.remove(result);
			em.getTransaction().commit();
			em.close();
			return result;
		}
		
		
		
		
	
		public void cleanUp() {
			emfactory.close();
		}
		}

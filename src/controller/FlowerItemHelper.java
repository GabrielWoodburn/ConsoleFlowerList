/**
 * @author Gabriel Woodburn - gwoodburn
 * CIS175 - Spring 2021
 * 2/09/21
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Flower;

public class FlowerItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FlowerList");

	public void insertItem(Flower li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<Flower> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<Flower> allItems = em.createQuery("SELECT i FROM Flower i").getResultList();
		return allItems;
	}

	public void deleteItem(Flower toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Flower> typedQuery = em.createQuery(
				"select li from Flower li where li.type = :selectedType and li.color = :selectedColor",
				Flower.class);
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedColor", toDelete.getColor());

		// we only want one result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list item
		Flower result = typedQuery.getSingleResult();

		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();

	}

	public Flower searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Flower found = em.find(Flower.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(Flower toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Flower> searchForItemByType(String flowerType) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Flower> typedQuery = em.createQuery("select li from Flower li where li.type = :selectedType", Flower.class);
		typedQuery.setParameter("selectedType", flowerType);

		List<Flower> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<Flower> searchForItemByItem(String flowerColor) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Flower> typedQuery = em.createQuery("select li from Flower li where li.color = :selectedColor", Flower.class);
		typedQuery.setParameter("selectedColor", flowerColor);

		List<Flower> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp(){
		emfactory.close();
	}

}
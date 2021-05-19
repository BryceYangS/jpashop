package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
			return;
		}

		em.merge(item);
	}

	@Transactional
	public void updateItem(Long itemId, Book param) {
		Item findItem = findOne(itemId);
		findItem.setPrice(param.getPrice());
		findItem.setStockQuantity(param.getStockQuantity());
		findItem.setName(param.getName());
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}
}

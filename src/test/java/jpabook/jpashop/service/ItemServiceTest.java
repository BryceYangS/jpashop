package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

	@Autowired
	ItemService itemService;
	@Autowired
	EntityManager em;

	@Test
	public void 아이템_저장() throws Exception {
		// given
		Item item = new Item();
		item.setName("itemA");

		// when
		itemService.saveItem(item);

		// then
		em.flush();
		assertThat(itemService.findOne(item.getId())).isEqualTo(item);
	}

}
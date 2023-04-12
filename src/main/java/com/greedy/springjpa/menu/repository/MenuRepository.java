package com.greedy.springjpa.menu.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.greedy.springjpa.menu.entity.Menu;

import java.util.List;

@Repository
public class MenuRepository {

	public Menu findMenuByCode(EntityManager entityManager, int menuCode) {
		
		//기준클래스, 찾을 대상
		//find라는 기능이 pk값을 통해 조회해 올 수 있는 기능이다.
		//Menu라는 엔티티의 형태로 바꿔서 (entityManager 매핑)해서 반환(menuCode) 해 준다.
	return entityManager.find(Menu.class, menuCode);
	}

	public List<Menu> findAllMenu(EntityManager entityManager) {
		String jqpl = "SELECT m FROM Menu as m ORDER BY m.menuCode ASC";

		//하나가 아니니까 getResultList List로 반환하도록 한다.

		return entityManager.createQuery(jqpl, Menu.class).getResultList();
	}


}
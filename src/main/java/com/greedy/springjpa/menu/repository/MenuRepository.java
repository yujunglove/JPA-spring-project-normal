package com.greedy.springjpa.menu.repository;

import javax.persistence.EntityManager;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import com.greedy.springjpa.menu.entity.Menu;

import java.util.List;
import java.util.stream.Collectors;

@Repository

public class MenuRepository {

	//메뉴 코드대로 1개씩만 가져오기
	public Menu findMenuByCode(EntityManager entityManager, int menuCode) {
		
		//기준클래스, 찾을 대상
		//find라는 기능이 pk값을 통해 조회해 올 수 있는 기능이다.
		//Menu라는 엔티티의 형태로 바꿔서 (entityManager 매핑)해서 반환(menuCode) 해 준다.
	return entityManager.find(Menu.class, menuCode);
	}



	//메뉴 전체 조회하기
	public List<Menu> findAllMenu(EntityManager entityManager) {
		String jqpl = "SELECT m FROM Menu as m ORDER BY m.menuCode ASC";

		//하나가 아니니까 getResultList List로 반환하도록 한다.

		return entityManager.createQuery(jqpl, Menu.class).getResultList();
	}

	//메뉴 선택바에 넣을수 있음
	public List<Category> findAllCategory(EntityManager entityManager) {


		String jqpl = "SELECT c FROM Category as c ORDER BY c.categoryCode ASC";

		return  entityManager.createQuery(jqpl, Category.class).getResultList();
	}

	//새로운 메뉴 등록
	public void registNewMenu(EntityManager entityManager, Menu menu) {

		entityManager.persist(menu);
	}

	//메뉴 수정
	public void modifyMenu(EntityManager entityManager,Menu menu) {

		/*전달 받은 메뉴 정보를 통해 해당 엔티티를 먼저 조회한다.*/
		Menu selectedMenu = entityManager.find(Menu.class,menu.getMenuCode());
		/*조회 된 메뉴 객체를 수정한다. 만약 이름 밖에없다면 저것만 적기 */
		selectedMenu.setMenuName(menu.getMenuName());

	}

	public void modifyMenuStatus(EntityManager entityManager,Menu menu) {
		Menu seletedMenuCode = entityManager.find(Menu.class,menu.getMenuCode());
		seletedMenuCode.setOrderableStatus(menu.getOrderableStatus());
	}

	public void removeMenu(EntityManager entityManager, Menu menu) {
		// EntityManager를 사용하여 메뉴 엔티티 조회
		Menu selectedMenu = entityManager.find(Menu.class,menu.getMenuCode());

		try{
			entityManager.remove(selectedMenu);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Menu> searchMenuByKeyword(EntityManager entityManager,String keyword) {
		return entityManager.createQuery("SELECT m FROM Menu m WHERE m.menuName LIKE :keyword", Menu.class)
				.setParameter("keyword", "%" + keyword + "%")
				.getResultList();
	}





}
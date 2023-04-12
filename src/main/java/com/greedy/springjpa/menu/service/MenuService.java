package com.greedy.springjpa.menu.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.entity.Category;
import com.greedy.springjpa.menu.entity.Menu;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.repository.MenuRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
	
	private MenuRepository menuRepository;
	private ModelMapper modelMapper; //이것도 빈 등록해야 한다.
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//어떤 값을 전달 받겠습니다. 빈으로 등록되었다면 여기로 등록해 준다.
	public MenuService(MenuRepository menuRepository,ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}
	/*영속성 객체(엔티티)를 그대로 사용하면 데이터가 훼손 될 가능성이 있으므로 비영속 객체로 변경해서 반환한다.
	 * */
	
	public MenuDTO findMenuByCode(int menuCode) {
		
		//이건 1개
		//Menu -> MenuDTO로 변환할수 있는 ModelMapper 라이브러리 의존성 추가 후 사용
		return modelMapper.map(menuRepository.findMenuByCode(entityManager, menuCode),MenuDTO.class);
	}



	public List<MenuDTO> findAllMenu() {


		List<Menu> menuList = menuRepository.findAllMenu(entityManager);

		//스트림 사용
		//map이 ()안에 결과를 수행해서 반환하는 것 다시 한번 스트림을 리스트로 바꿔주세요 collect List를 변화 시키겠습니다.
		//스트림으로 가공한 다음 다시 리스트로 변환 시켰다.

		return menuList.stream().map(menu -> modelMapper.map(menu,MenuDTO.class)).collect(Collectors.toList());
	}
	public List<CategoryDTO> findAllCategory() {

		List<Category> categoryList = menuRepository.findAllCategory(entityManager);

		return categoryList.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());

	}

	//방금 Repository에서 정의한 메소드를 호출한다.
	//비영속인 MenuDTO로 전달을 받는다.

	/* 스프링에서는 트랜잭션 처리를 지원한다.
	* 어노테이션으로 @Transactional을 선언하는 선언적 트랜잭션이 보편적인 방식이다.
	* 클래스 레벨과 메소드 레벨에 작성 될 수 있고 클래스 레벨에 작성 시 하위 모든 메소드에 적용된다.
	* 어노테이션이 선언 되면 메소드 호출 시 자동으로 프록시 객체가 생성되며 해당 프록시 객체는 정상 수행 여부에 따라
	* commit, rollback 처리를 한다. *///전달되는 값이 메뉴엔티티가 될수 있도록 Map을 사용하여서 변환 한다.
	@Transactional
	public void registNewMenu(MenuDTO newMenu) {

		menuRepository.registNewMenu(entityManager, modelMapper.map(newMenu, Menu.class));
	}


	@Transactional
	public void modifyMenu(MenuDTO menu) {

		menuRepository.modifyMenu(entityManager, modelMapper.map(menu, Menu.class));

	}

	//상태 변경 바꾸기
	@Transactional
	public void modifyMenuStatus(MenuDTO menu) {

		menuRepository.modifyMenuStatus(entityManager, modelMapper.map(menu, Menu.class));

	}

	@Transactional
	public void removeMenu(MenuDTO menu) {
		// MenuDTO 객체를 Menu 엔티티로 매핑
		menuRepository.removeMenu(entityManager,modelMapper.map(menu, Menu.class));
	}

	@Transactional
	public List<MenuDTO> searchMenuByKeyword(String keyword) {
		List<Menu> menuList = menuRepository.searchMenuByKeyword(entityManager, keyword);
		return menuList.stream()
				.map(menu -> modelMapper.map(menu, MenuDTO.class))
				.collect(Collectors.toList());
	}


}

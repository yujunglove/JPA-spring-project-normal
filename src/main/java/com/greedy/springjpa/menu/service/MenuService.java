package com.greedy.springjpa.menu.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return menuList.stream().map(menu -> modelMapper.map(menu,MenuDTO.class)).collect(Collectors.toList());
	}
	
	
}

package com.greedy.springjpa.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private MenuService menuService;
	
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	//7번, 10번 등이 들어가든 간에 변수로 전달해서 들어간다.
//	       <td th:text="${ menu.menuCode }"></td>
//        <td th:text="${ menu.menuName }"></td>
//        <td th:text="${ menu.menuPrice }"></td>
//        <td th:text="${ menu.categoryCode }"></td>
//        <td th:text="${ menu.orderableStatus }">
	//MenuDTO menu = menuService.findMenuByCode(menuCode); 안에 있는거니까 저렇게 써주자
	@GetMapping("/{menuCode}")
	public String findMenuByCode(@PathVariable int menuCode, Model model) {
		
		MenuDTO menu = menuService.findMenuByCode(menuCode);
//		System.out.println("menu = " +menu);

		model.addAttribute("menu", menu);
		return "menu/one";
	}

}

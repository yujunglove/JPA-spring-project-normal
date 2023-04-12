package com.greedy.springjpa.menu.controller;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.service.MenuService;

import java.util.List;

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

	@GetMapping("/list")
	public  String findAllMenu(Model model){
		//수행된 결과를
		List<MenuDTO> menuList = menuService.findAllMenu();
		//모델에 추가해주면
		model.addAttribute("menuList",menuList);
		//forwarding 했을 때 사용 할 수 있다.
		return "menu/list";
	}

	@GetMapping("/regist")
	public void registPage() {

	}


	@GetMapping(value="category", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<CategoryDTO> findCategoryList() {

		return menuService.findAllCategory();
	}

	@PostMapping("/regist")
	public String registMenu(@ModelAttribute MenuDTO newMenu) {

		menuService.registNewMenu(newMenu);

		return "redirect:/menu/list";
	}
	@GetMapping("/modify")
	public void modifyPage() {

	}

	@PostMapping("/modify")
	public String MenuModify(@ModelAttribute MenuDTO menu) {

		menuService.modifyMenu(menu);

		//수정되고 나서 수정 된 메뉴의 페이지로 이동한다.
		return "redirect:/menu/" + menu.getMenuCode();

	}
	
	@GetMapping("/remove")
	public void removePage() {
	}
	
	@PostMapping("/remove")
	public String removeCodePage(@ModelAttribute MenuDTO menu) {

		menuService.removeMenu(menu);
		//수정되고 나서 수정 된 메뉴의 페이지로 이동한다.
		return "redirect:/menu/list";

	}

	@GetMapping("/searchMenu")
	public String searchMenu(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model)  {

		List<MenuDTO> menuList = menuService.searchMenuByKeyword(keyword);
		model.addAttribute("menuList", menuList);

		return "menu/searchMenu";
	}

	@PostMapping("/searchMenu")
	public String searchMenuPost(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model)  {
		List<MenuDTO> menuList = menuService.searchMenuByKeyword(keyword);
		model.addAttribute("menuList", menuList);
		return "menu/list";
	}







}

package com.elmerikinnunen.shoppinglist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elmerikinnunen.shoppinglist.domain.DepartmentRepository;
import com.elmerikinnunen.shoppinglist.domain.Product;
import com.elmerikinnunen.shoppinglist.domain.ProductRepository;
import com.elmerikinnunen.shoppinglist.domain.User;
import com.elmerikinnunen.shoppinglist.domain.UserRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productrepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/login")
		public String login() {
		// System.out.print(" ------------------ Käyttäjän nimi: " + userRepository.findByUsername(username));
		return "login";
	}
	
	@RequestMapping(value="/shoppinglist/{username}", method = RequestMethod.GET)
		public String shoppinglist(Model model, @PathVariable("username") String username) {
		User user = userRepository.findByUsername(username);
		model.addAttribute(productrepository.findByUserId(user.getId()));
		return "shoppinglist";
	}
		
		//@RequestMapping(value="/shoppinglist/{username}", method = RequestMethod.GET)
		//	public @ResponseBody List<Product> getProducts(@PathVariable("username")String username) {
		//	User user = userRepository.findByUsername(username);
		//	return productrepository.findByUserId(user.getId());
		//}
	
		//@RequestMapping(value="/products", method = RequestMethod.GET)
		//	public @ResponseBody List <Product> shoppinglistRest(){
		//	return (List<Product>) productrepository.findAll();
		//}
	
		@RequestMapping(value="/products", method = RequestMethod.GET)
		public @ResponseBody List<Product> getProducts(@PathVariable("username")String username) {
			User user = userRepository.findByUsername(username);
			return productrepository.findByUserId(user.getId());
		}
		
		@RequestMapping(value="/product/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional <Product> findProductById(@PathVariable("id")Long productId){
			return productrepository.findById(productId);
		}
	
	@RequestMapping(value="/add")
		public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("departments", departmentRepository.findAll());
		return "/addProduct";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
		public @ResponseBody String save (Product product, String username) {
		Long userId = userRepository.findByUsername(username).getId();
		product.setUserId(userId);
		productrepository.save(product); 
		return "redirect:shoppinglist/{username}";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
		public String deleteProduct(@PathVariable("id")Long productId) {
		productrepository.deleteById(productId);
		return "redirect:../shoppinglist";
	}
	
	@RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
		public String editProduct(@PathVariable("id")Long productId, Model model) {
		model.addAttribute("product", productrepository.findById(productId));
		model.addAttribute("departments", departmentRepository.findAll());
		return "edit";
	}
	
}

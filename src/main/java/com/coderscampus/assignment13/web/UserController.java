package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRepository accountRepo;

	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {
		
		model.put("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String postCreateUser (User user) {
		System.out.println(user);
		user.setCreatedDate(LocalDate.now());
		userService.saveUser(user);
		return "redirect:/register";
	}
	
	@GetMapping("/users")
	public String getAllUsers (ModelMap model) {
		Set<User> users = userService.findAll();
		
		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}
		
		return "users";
	}
	
	@GetMapping("/users/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
		model.put("users", Arrays.asList(user));
		model.put("user", user);
		return "users";
	}
	
	@PostMapping("/users/{userId}")
	public String postOneUser (User user) {
		userService.saveUser(user);
		return "redirect:/users/"+user.getUserId();
	}


	@PostMapping("/users/{userId}/delete")
	public String deleteOneUser (@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}

	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getAccount(ModelMap model,
							 @PathVariable Long userId,
							 @PathVariable Long accountId){
		Account account = accountService.findByAccountId(accountId);
		model.put("account",account);
		return "account";
	}
	@PostMapping("/users/account/{userId}")
	public String saveAccount(Account account, @PathVariable Long userId){
		accountService.saveAccount(account);
		return "redirect:/users/" + userId;
	}

	@GetMapping("/users/{userId}/accounts")
	public String createAccount(ModelMap model, User user){
		model.put("account", new Account());
		return "createAccount";
	}

	@PostMapping("/users/{userId}/accounts")
	public String saveNewAccount(Account account, User user, @PathVariable Long userId){
		user = userService.findById(user.getUserId());
		user.getAccounts().add(account);
		accountRepo.save(account);
		userService.saveUser(user);
		return "redirect:/users/" + user.getUserId();
	}
}

package com.votes.web;

import java.util.List;
import java.util.Objects;

import com.votes.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.votes.entity.Product;
import com.votes.entity.User;
import com.votes.repositories.ProductRepository;

@Controller
public class DashboardController {

    @Autowired
    RoleRepository roleRepo;
    @Autowired
    private ProductRepository productRepo;


    @GetMapping("/")
    public String rootView() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, ModelMap model) {
        List<Product> products = productRepo.findByUser(user);
        model.put("products", products);
        System.out.println();
        //System.out.println("Rolul userului este : " + roleRepo.getById(user.getId()).getRole() + " Iar username-ul cu care este conectat este : " + user.getUsername());
        //if (Objects.equals(roleRepo.getById(user.getId()).getRole(), "USER")) {
			//System.out.println("Rolul userului este : " + roleRepo.getById(user.getId()).getRole() + " Iar username-ul cu care este conectat este : " + user.getUsername());
		if(user.getId()!=1){
			return "dashboard";
        }else {
            return "redirect:/ViewAllProducts";
		}

    }
}

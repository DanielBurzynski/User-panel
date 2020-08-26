package pl.spring.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.spring.springtest.entity.Company;
import pl.spring.springtest.entity.Product;
import pl.spring.springtest.repository.CompanyRepository;
import pl.spring.springtest.repository.ProductRepository;

@Controller
public class OrderController {



    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/order")
    public String showOrder(Model model){


        return "order";
    }

    @GetMapping("/createorder")
    public String createOrder(Model model, Company company, Product product){


        model.addAttribute("companies",companyRepository.findAll());
        model.addAttribute("products" , productRepository.findAll());


        return "createorder";
    }






}

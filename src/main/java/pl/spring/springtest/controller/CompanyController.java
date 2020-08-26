package pl.spring.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.spring.springtest.entity.Company;

import pl.spring.springtest.repository.CompanyRepository;
import pl.spring.springtest.repository.ProductRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class CompanyController {


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/list")
    private String getAll(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "/company";
    }


    @GetMapping("/add")
    public String showSignUpForm(Company company) {
        return "add";
    }


    @PostMapping("/add")
    public String addCompany(@Valid Company company, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "/add";
        }
        companyRepository.save(company);
        model.addAttribute("companies", companyRepository.findAll());
        return "redirect:/list";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("company", company);
        return "/update";
    }


    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable("id") long id,
                                @Valid Company company,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            company.setId(id);
            return "/update";
        }

        companyRepository.save(company);
        model.addAttribute("companies", companyRepository.findAll());
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") long id, Model model) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        companyRepository.delete(company);
        model.addAttribute("companies", companyRepository.findAll());

        return "redirect:/list";
    }


    @GetMapping("/order/{id}")
    public String selectForm(@PathVariable("id") long id, Model model) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        model.addAttribute("company", company);


        return "/order";
    }

    @RequestMapping(value = "add/{id}", method = RequestMethod.GET)
    public String add(@PathVariable("id") Long id, HttpSession session) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        session.setAttribute("company", company);

        return "redirect:/cart/index";
    }


}




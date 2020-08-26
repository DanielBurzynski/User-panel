package pl.spring.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.spring.springtest.entity.Product;
import pl.spring.springtest.repository.ProductRepository;
import pl.spring.springtest.service.ServiceProduct;
import pl.spring.springtest.service.impl.ServiceProductImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    ServiceProduct serviceProduct;





    @RequestMapping("/product")
    public String viewHomePage(Model model) {
        return viewPage(model, 1, "name", "asc");
    }

    @RequestMapping("/product/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir) {

        Page<Product> page = serviceProduct.listAll(pageNum, sortField, sortDir);

        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", listProducts);

        return "product";
    }



    //Formularz dodania produktu
    @GetMapping("/addproduct")
    public String showAddProductForm(Product product) {
        return "addproduct";
    }


    //Dodanie firmy do bazy danych validacja powrót do listy firm
    @PostMapping("/addproduct")
    public String addNewProduct(@Valid Product product, Errors errors, Model model) {
        if(errors.hasErrors()){
            return "addproduct";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/product";
    }

    //Usunięcie firmy poprzez id
    @GetMapping("/addproduct/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll());

        return "redirect:/product";
    }


    //Edycja firmy , skorzystanie z argumentu metody, long id,
    @GetMapping("/product/edit/{id}")
    public String showEditProductForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id ));

        model.addAttribute("product", product);
        return "edit";
    }


    //Aktualizacja firmy , skorzystanie z argumentu metody long id,
    @PostMapping("/product/edit/{id}")
    public String updateCompany(@PathVariable("id") long id,
                                @Valid Product product,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            product.setId(id);
            return "edit";
        }

        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/product";
    }

}

package pl.spring.springtest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.spring.springtest.component.mailer.Message;
import pl.spring.springtest.entity.Company;
import pl.spring.springtest.entity.Item;
import pl.spring.springtest.repository.CompanyRepository;
import pl.spring.springtest.repository.ProductRepository;
import pl.spring.springtest.service.ServiceProduct;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    ServiceProduct serviceProduct;

    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "cart/index";
    }


    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") Long id, HttpSession session) {

        productRepository.findAll();

        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(serviceProduct.findAllById(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");

            int index = this.exists(id, cart);

            if (index == -1) {
                cart.add(new Item(serviceProduct.findAllById(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/index";
    }


    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = this.exists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    private int exists(Long id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }


    @RequestMapping(value = "add/{id}", method = RequestMethod.GET)
    public String add(@PathVariable("id") Long id, HttpSession session) {

        productRepository.findAll();


        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        session.setAttribute("company", company);

        return "redirect:/cart/index";
    }

}

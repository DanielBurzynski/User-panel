package pl.spring.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.spring.springtest.entity.Company;
import pl.spring.springtest.entity.Product;
import pl.spring.springtest.repository.CompanyRepository;
import pl.spring.springtest.service.CompanyService;

import java.util.List;

public class CompanyServiceImpl  implements CompanyService {


   @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findCompanyById(Long id) {
        return companyRepository.findCompanyById(id);
    }



}

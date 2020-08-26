package pl.spring.springtest.service;

import pl.spring.springtest.entity.Company;

import java.util.List;

public interface CompanyService {



    List<Company> findCompanyById(Long id);
}

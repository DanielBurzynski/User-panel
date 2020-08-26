package pl.spring.springtest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.spring.springtest.entity.Company;

import java.util.List;


@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {


 List<Company> findCompanyById(Long id);

 List<Company> findAll();
 List<Company>findAllById(Long id);


}

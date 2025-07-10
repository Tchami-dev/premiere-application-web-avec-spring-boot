package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class EmployeeProxy {

    @Autowired
    private CustomProperties props;    

    /**
    * Get all employees
    * @return An iterable of all employees
    */

    public Iterable<Employee> getEmployees(){
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees"; 
           

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<> ();

         ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                Employee.class,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
                );

         log.debug("Get Employees call " + response.getStatusCode().toString());
        // log.debug(" Create Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee getEmploy(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmploy'");
    }

    public void deleteEmployee(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }

    public Employee updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    public Employee createEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEmployee'");
    }

      /**
     * @param e
     * @return
     */
    /*public Employee createEmployee(Employee e) {
    String baseApiUrl;
    String createEmployeeUrl = baseApiUrl + "/employee";
    String baseApiUrl = props.getApiUrl();
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Employee> request = new HttpEntity<Employee>(e);
    ResponseEntity<Employee> response = restTemplate.exchange(
        createEmployeeUrl,
        HttpMethod.POST,
        request,
        Employee.class);

    log.debug("Create Employee call " + response.getStatusCode().toString());

    return response.getBody();
}*/

  /*@Repository
    public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

    }*/

}
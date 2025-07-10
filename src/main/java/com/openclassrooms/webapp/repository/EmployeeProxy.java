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
    private CustomProperties props;       //injection du beans CustomProperties sous le nom de props

    /**
    * Get all employees
    * @return An iterable of all employees
    */

    public Iterable<Employee> getEmployees(){                            //gènere la liste des employées
        String baseApiUrl = props.getApiUrl();                           //récupère l'URL de la BD de l' API à partie de l' objet props(instance de classe configuré par @Configuration Propertie ) 
        String getEmployeesUrl = baseApiUrl + "/employees";               //construction complet de l'URL pour récupérer les employés de la BD/h2-console
           

        RestTemplate restTemplate = new RestTemplate();                    //création d'une instance de restTemplate permettent de communiquer avec l'API au moyen des requêtes get, post, put, delete 
        HttpEntity<Employee> request = new HttpEntity<> ();                 //création d'une requête HTTP vide contenant un objet Employee 



         ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(   //Utilisation de restTemplate pour faire une applel HTTP get à URL pour récupérer la liste des employés. d'ou la déclaration d'une variable de types response
                getEmployeesUrl,                                                   // cible l'appel get
                HttpMethod.GET,                                                     // indique que je veux faire une reqête get
                //* Employee.class*/
                null,                                                               //ne passe aucune requête
                new ParameterizedTypeReference<Iterable<Employee>>() {}               //indication du type exacte attendu
                );

         log.debug("Get Employees call " + response.getStatusCode().toString());                //vérification de l'appel HTTP vers l'API
        // log.debug(" Create Employees call " + response.getStatusCode().toString());

        return response.getBody();                                                   //renvoie de la liste à l'appelant
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
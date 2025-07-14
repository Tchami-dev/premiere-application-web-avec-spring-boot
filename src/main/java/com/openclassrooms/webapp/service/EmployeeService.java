package com.openclassrooms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;           //injection du beans EmployeeProxy sous ne nom de employeeProxy

    /* ce code permet de recupérer unn employé précis à partie de son id */
    public Employee getEmployee(Integer id) {      
        return employeeProxy.getEmployee(id);              //fait appel à une methode qui communique par url avec l' API afin de retourner un employé
    }

    public Iterable<Employee> getEmployees() {             //retourne une liste d'employés
        return employeeProxy.getEmployees();                // appel la méthode getEmployees() définie dans employé.Proxy qui communique avec l'API pour 
    }

    public void deleteEmployee( Integer id) {                    //supprime un employé spécifié à partie de son id
        employeeProxy.deleteEmployee(id);                        // effectue une suppression d'employé au moyen de la méthode employeeProxy
        log.info("l'employé avec id={} a été supprimé", id);
    }


    /* ce code permet de gèrer les enregistrement et mise à jour des employés */
     public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;                            // reçoit un objet employé est le retoune (en mise à jour ou au statut de nouveau), puis le stocke dans la variable
      log.info("utilisateur first name =  {} last name ={} mail = {} password = {}", employee.getFirstName(), employee.getLastName(), employee.getMail(), employee.getPassword());                                       // enregistrement des donnnées dans la couche service + repository

        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        employee.setLastName(employee.getLastName().toUpperCase());

        /* ce code vérifier id de l'employé pour pouvoir excécuter la bonne condition */
        if(employee.getId() == null) {
            // Si l'id est nul, alors c'est un nouvel employé.
            savedEmployee = employeeProxy.createEmployee(employee);
        } else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }
    
        return savedEmployee;
    }

}
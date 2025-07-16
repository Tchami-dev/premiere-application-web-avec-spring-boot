package com.openclassrooms.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.service.EmployeeService;

@Controller
public class EmployeeController{

  @Autowired
  private EmployeeService service;             //injection du beans EmployeeService sous le nom de service

  /* ce code gère la soumission d'enregistrement des employées via un formulaire*/

  @PostMapping("/saveEmployee")                                             // repond aux requêtes HTTP POST envoyées par URL 
   public ModelAndView saveEmployee(@ModelAttribute Employee employee){     //affiche un employée enregistré obligatoirement dans un champs de formulaire
    service.saveEmployee(employee);                                         // enregistrement des donnnées dans la couche service + repository
    return new ModelAndView("redirect:/");                                  //redirige l'utilisateur vers la page d'accueil
      }

      /* ce code permet d'afficher le contenu de la BD/h2-console sur la page accueil */

 @GetMapping("/")                                                    //reponse à des requêtes http get  vers url
public String acceuil(Model model) {                                 // model permet de renvoyer des vue sur HTML 
    Iterable<Employee> employees = service.getEmployees();           // appel la methode getEmployees pour avoir la liste des employees
    model.addAttribute("employees", employees);                     // permission d'accès et d'affichage à la vue template
    return "acceuil";                                              //retour de la liste des employées
}

  

/* ce code affiche un formulaire de création d'un nouveau employé */
 @GetMapping("/home")                                          // gère les requêtes HTTP GET envoyer par l' URL de "home"
  public String showForm(Model model) { 
  
  // méthode permattant de renvoyer le nouveau nom à la vue HTML 
   model.addAttribute("employee", new Employee()); 
                      // céer un objet et l'ajoute dans le modèle sous le nom de employé 
   return "home";                                             // retourne le nouveau nom au fichier fichier "home" HTML (sans .html)
}

@PostMapping("/deleteEmployee/{id}")
public String deleteEmployee(@PathVariable("id") Integer id) {
    service.deleteEmployee(id);
    return "redirect:/";
}

@GetMapping("/editEmployee/{id}")
public String showEditEmployeeForm(@PathVariable ("id") Integer id , Model model) {
    Employee employee = service.getEmployeeById(id);
    if (employee == null){
      return "redirect:/acceuil";
    }
    model.addAttribute("employee", employee);
    return "home"; 
}

@PostMapping("/updateEmployee")
public String updateEmployee(@ModelAttribute Employee employee){
    service.updateEmployee(employee.getId(), employee);
    return "acceuil"; 

}
  




}


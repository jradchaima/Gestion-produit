package org.sid.web;

import java.util.Collection;
import java.util.Optional;

import org.sid.entities.Produit;
import org.sid.metier.IProduitMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProduitController {
	 @Autowired
	  private IProduitMetier produitMetier;
		@RequestMapping("/lesproduits")
		public String index(Model model) {
			Collection <Produit> listproduits = produitMetier.consulterListProduit();
		    model.addAttribute("listproduits", listproduits);
		     
		    return "index";
		}
		@RequestMapping("/ajouter")
		public String ajouter(Model model) {
		
			   Produit produit = new Produit();
			    model.addAttribute("produit", produit);
			     
			    return "ajouter_produit";
		}
		@PostMapping("/enregister")
		public String EnregistrerProduit(@ModelAttribute("produit") Produit produit) {
		 produitMetier.saveProduit(produit);
		     
		    return "redirect:/lesproduits";
		}
		@GetMapping(value="/editer/{id}")
		public String afficherEditproduit(@PathVariable(name = "id") Long id,Model model) {
		 
		    Optional <Produit> produit = produitMetier.findProduitById(id);
		    model.addAttribute("produit", produit);
		     
		    return "editer_produit";
		}
		@GetMapping("/supprimer/{id}")
		public String deleteProduct(@PathVariable(value = "id") Long id) {
			 produitMetier.deleteProduit(id);
		    return "redirect:/lesproduits"; 
		}
	
}

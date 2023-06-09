package coms.journalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import coms.journalproject.Repository.Usersrepository;
import coms.journalproject.entity.Users;

@Controller
public class userscontroller {

	@Autowired
	private Usersrepository repos;
	
	@RequestMapping("/home")
	public String home() {
		return "home.jsp";
	}
	
	// ************* Basic Crud Operations *******************
	
	// Create and Update
	@RequestMapping("/addEntry")
	public String addUpdateJournalEntry(Users users) {
		
		// save or update journal entry in db
		repos.save(users);
		
		return "home.jsp";
		
	}
	
	// Get All Records
	@RequestMapping("/getAllEntries")
	public ModelAndView getAllJournalEntries() {
		ModelAndView mv = new ModelAndView();
		
		// Get All Entries list from db
		List<Users> entries = repos.findAll();
		
		mv.addObject("entries",entries);
		mv.setViewName("getAllEntries.jsp");
		
		return mv;
	}
	
	@RequestMapping("/getEntry")
	public ModelAndView getJournalEntry(@RequestParam int id) {
		ModelAndView mv = new ModelAndView();
		
		// Get a specific entry using id from db
		Users journalEntry = repos.findById(id).orElse(new Users());
		
		mv.addObject("entry", journalEntry);
		mv.setViewName("getEntry.jsp");
		
		return mv;
	}
	
	@RequestMapping("/deleteEntry")
	public String deleteJournalEntry(@RequestParam int id) {
		
	
		// Delete a specific entry using id from db
		repos.deleteById(id);
		
		return "home.jsp";
	}
}

package coms.journalproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import coms.journalproject.Repository.Usersrepository;
import coms.journalproject.controller.userscontroller;
import coms.journalproject.entity.*;
@SpringBootTest
class JournalProjectApplicationTests {

	@Mock
	Usersrepository journalEntryRepositoryMock;
	
	@InjectMocks
	userscontroller journalEntryControllerMock;
	
	@Test
	void testHome() {
		String result = journalEntryControllerMock.home();
		
		assertEquals("home.jsp", result);
	}
	
	@Test
	void testAddUpdateJournalEntry() {
		Users journalEntry = new Users();
		journalEntry.setId(1);
		journalEntry.setTitle("My Vacation to Finland.");
		journalEntry.setCategory("Travel");
		
		Mockito.when(journalEntryRepositoryMock.save(Mockito.any())).thenReturn(journalEntry);
		
		String result = journalEntryControllerMock.addUpdateJournalEntry(journalEntry);
		assertEquals("home.jsp", result);
	}
	
	@Test
	void testGetAllEntries() {
		
		Users journalEntry1 = new Users();
		journalEntry1.setId(1);
		journalEntry1.setTitle("My Vacation to Finland.");
		journalEntry1.setCategory("Travel");
		
		Users journalEntry2 = new Users();
		journalEntry2.setId(2);
		journalEntry2.setTitle("My Health Tips.");
		journalEntry2.setCategory("Health");
		
		List<Users> list = new ArrayList<>();
		list.add(journalEntry1);
		list.add(journalEntry2);		
		
		Mockito.when(journalEntryRepositoryMock.findAll()).thenReturn(list);
		
		ModelAndView resultObj = journalEntryControllerMock.getAllJournalEntries();
		
		assertEquals("getAllEntries.jsp", resultObj.getView().toString());
	}


}

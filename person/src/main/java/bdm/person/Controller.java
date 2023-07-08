package bdm.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class Controller {

    @Autowired
    private PersonService personService;
    
    @GetMapping("/health_check")
    public ResponseEntity<Void> healthCcheck() {
       return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
    	Person person = personService.getPersonById(id);
    	if (person != null) {
    		return ResponseEntity.ok(person);
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Person>> getPersons() {
    	List<Person> person = personService.getAllPersons();
    	if (person != null) {
    		return ResponseEntity.ok(person);
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @GetMapping("/all-males")
    public ResponseEntity<List<Person>> getAllMales() {
    	List<Person> person = personService.findByGender("male");
    	if (person != null) {
    		return ResponseEntity.ok(person);
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(id, person);
        if (updatedPerson != null) {
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

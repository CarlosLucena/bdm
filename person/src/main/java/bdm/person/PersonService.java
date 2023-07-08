package bdm.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> findByGender(String gender) {
        return personRepository.findByGender(gender);
    }

    public Person getPersonById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            existingPerson.setName(person.getName());
            existingPerson.setAge(person.getAge());
            existingPerson.setEmail(person.getEmail());
            existingPerson.setGender(person.getGender());
            return personRepository.save(existingPerson);
        } else {
            return null;
        }
    }

    public boolean deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            personRepository.delete(optionalPerson.get());
            return true;
        } else {
            return false;
        }
    }
}

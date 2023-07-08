package bdm.person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="persons")
public class Person {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotBlank(message = "Name is required")
    private String name; // (No special characters - !@#$%^&*()_+=  allowed) 
	@NotBlank(message = "Age is required")
    private Short age;  // (Should not be negative number) 
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email.")
    private String email;  // (Must have valid email format) 
	@NotBlank(message = "Gender is required")
    private String gender; // (Must be Male, Female, Other)

	public Person() {}
	public Person(String name, Short age, String email, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
	}
}

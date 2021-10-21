package at.htl.repository;

import at.htl.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    public PersonRepository() {
    }

    @Transactional
    public List<Person> getAllPersons() {
        return listAll();
    }

    @Transactional
    public void create(Person person) {
        try {
            this.getEntityManager().merge(person);
        }
        catch(NullPointerException ex) {
            System.out.println("Person is null!");
        }
    }


    @Transactional
    public Person getByLastName(String searchLastName) {
        return find("lastName", searchLastName).singleResult();
    }

    @Transactional
    public Person getById(Long id) {
        return findById(id);
    }
}

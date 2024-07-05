package qwins.spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import qwins.spring.models.Book;
import qwins.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String email){
        return jdbcTemplate.query("select * from person where email =? ", new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public Person show(int id){
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("insert into person(fio, year_of_birth, email) values( ?, ?, ?)", person.getFio(),
                person.getYearOfBirth(), person.getEmail());
    }

    public List<Book> booksAssigned(int id){
        List<Book>  books = jdbcTemplate.query("select * from book where person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        if(books.size() == 0) return null;
        else return books;
    }

    public void update(int id, Person person){
        jdbcTemplate.update("update person set fio = ?, year_of_birth = ?, email = ? where id = ?", person.getFio(),
                person.getYearOfBirth(), person.getEmail(), person.getId());

    }

    public void delete(int id){
        jdbcTemplate.update("delete from person where id = ?", id);
    }

}

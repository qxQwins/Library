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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void save(Book book){
        jdbcTemplate.update("insert into book(name, author, year) values(?, ?, ?)", book.getName(), book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book book){
        jdbcTemplate.update("update book set name = ?, author = ?, year = ? where id = ?", book.getName(), book.getAuthor(),
                book.getYear(), id);
    }
    public Person getBookOwner(int id){
        return jdbcTemplate.query("SELECT person.* from book join person on book.person_id = person.id " +
                        "where Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void taken(int id, Person person){
        jdbcTemplate.update("update book set person_id = ? where id = ?", person.getId(), id);
    }
    public void free(int id){
        jdbcTemplate.update("update book set person_id = null where id = ?", id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from book where id = ?", id);
    }
}

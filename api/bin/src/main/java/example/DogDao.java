package example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class DogDao extends AbstractDAO<Dog> {
  public DogDao(SessionFactory factory) {
    super(factory);
  }

  public List<Dog> findAll() {
      return list(namedQuery("example.dog.findAll"));
  }
}

package example;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/dogs")
@Produces(MediaType.APPLICATION_JSON)
public class DogResource {
    private DogDao dao;

    public DogResource(DogDao dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Dog> getAllDogs() {
        return dao.findAll();
    }
}

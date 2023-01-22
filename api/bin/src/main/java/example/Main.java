package example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class Main extends Application<DbConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "dev";
    }

    @Override
    public void initialize(Bootstrap<DbConfiguration> bootstrap) {
        // nothing to do yet
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DbConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
        DogDao dao = new DogDao(hibernate.getSessionFactory());
        final DogResource resource = new DogResource(dao);
        environment.jersey().register(resource);
    }

    private HibernateBundle<DbConfiguration> hibernate = new HibernateBundle<DbConfiguration>(Dog.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DbConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
}

package by.rudenkodv.library.book.data.springboot;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "by.rudenkodv.library.book.data.service")
public class MongoConfig extends AbstractMongoConfiguration {
    
    @Value("${mongo.host}")
    private String host;
    
    @Value("${mongo.port}")
    private Integer port;
    
    @Value("${mongo.dbname}")
    private String dataBaseName;
    
    @Override
    protected String getDatabaseName() {
        return "library";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient();
    }
}

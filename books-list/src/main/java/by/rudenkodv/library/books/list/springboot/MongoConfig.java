package by.rudenkodv.library.books.list.springboot;

import com.mongodb.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableMongoAuditing
@EnableReactiveMongoRepositories //(basePackages = "by.rudenkodv.library.books.list.service.repository")
public class MongoConfig extends AbstractMongoConfiguration {
    
    @Value("${mongo.host}")
    private String host;
    
    @Value("${mongo.port}")
    private Integer port;
    
    @Value("${mongo.dbname}")
    private String dataBaseName;
    
    @Override
    protected String getDatabaseName() {
        return dataBaseName;
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        return (MongoClient) MongoClients.create();
    }
}

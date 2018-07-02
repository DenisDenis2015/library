package by.rudenkodv.library.books.list.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableMongoRepositories(basePackages = "by.rudenkodv.library.books.list.service.repository")
public class MongoConfig /*extends AbstractMongoConfiguration*/ {
    
    @Value("${mongo.host}")
    private String host;
    
    @Value("${mongo.port}")
    private Integer port;
    
    @Value("${mongo.dbname}")
    private String dataBaseName;
    
/*    @Override
    protected String getDatabaseName() {
        return dataBaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

    @Override
    protected String getMappingBasePackage() {
        return "by.rudenkodv.library.books.list.model";
    }*/
}

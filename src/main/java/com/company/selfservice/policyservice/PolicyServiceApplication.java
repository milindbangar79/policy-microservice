package com.company.selfservice.policyservice;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan({"com.company.selfservice.policyservice"})
@Profile("dev")
public class PolicyServiceApplication {
	
public static void main(String[] args) {
	SpringApplication.run(PolicyServiceApplication.class,args);
}
	@Bean
    public Mongo mongofunc() throws Exception {
		Map<String, String> env = System.getenv();
		String mongodbhost = env.get("MONGODB_HOST"); 
		String mongodbport = env.get("MONGODB_PORT");
        MongoClient mongoClient = new MongoClient(mongodbhost,Integer.parseInt(mongodbport.trim()));//localhost, 27017
        System.out.println("mongodbport.substring(0, mongodbport.length()-2):"+mongodbport.substring(0, mongodbport.length()-1));
        //UserCredentials userCredentials = new UserCredentials("", "");
        return mongoClient;
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongofunc(),"policiesdb");
        return mongoTemplate;
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/addressservice").allowedOrigins("*");
                    registry.addMapping("/customerservice").allowedOrigins("*");
                    registry.addMapping("/userservice").allowedOrigins("*");
                    registry.addMapping("/policies/beneficiary/add").allowedOrigins("*");
                    registry.addMapping("/policies/beneficiary/del").allowedOrigins("*").allowedMethods("DELETE").allowedHeaders("*");
                    registry.addMapping("/**").allowedOrigins("*");
                }
            };
        }
    }


package com.example.solr.demosolragain;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = {"com.example.solr.demosolragain"})
//        basePackages = "com.baeldung.spring.data.solr.repository",
//        basePackages = "com.example.solr.demosolr.repository",
//        namedQueriesLocation = "classpath:solr-named-queries.properties")
@ComponentScan
public class SolrConfig {
    @Bean
    public SolrClient solrClient() {
//        return new HttpSolrClient("http://localhost:8983/solr");

        String urlString = "http://localhost:8983/solr";
        SolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
        return solrClient;

    }


    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}
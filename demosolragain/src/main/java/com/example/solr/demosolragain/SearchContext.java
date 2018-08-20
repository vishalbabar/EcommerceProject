//package com.example.solr.demosolragain;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.solr.repository.config.EnableSolrRepositories;
//
//import javax.annotation.Resource;
//
//
//@Configuration
//@EnableSolrRepositories
//public class SearchContext {
//
//    @Resource
//    private Environment environment;
//
//    @Bean
//    public SolrServer solrServer(@Value("${solr.host}") String solrHost) {
//        return new HttpSolrServer(solrHost);
//    }
//
//}

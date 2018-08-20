package com.example.solr.demosolragain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends SolrCrudRepository<Searchentity,String> {
    Searchentity findOneById(String id);
    public List<Searchentity> findByType(String type);
    List<Searchentity> findByProductNameContains(String searchTerm);
    public List<Searchentity> findByProductNameContainsOrDescriptionContainsOrTypeContains(String title ,String description, String type);
public List<Searchentity> findByProductNameContainsOrDescriptionContainsAndTypeContains(@Boost(4) String title , String description, String type);
    public List<Searchentity> findByDescriptionContainsOrProductNameContainsAndTypeContains( String description,@Boost(4) String title , String type);
    public List<Searchentity> findByTypeContainsAndProductNameContainsOrDescriptionContains( String type, String description ,@Boost(4) String productName);
    public List<Searchentity> findByProductNameContainsOrDescriptionContains(@Boost(3) String title ,String description);
}

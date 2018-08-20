package com.example.solr.demosolragain;

import com.example.solr.demosolragain.searchDTO.SearchDTO;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {


    @Autowired
    private SearchRepository searchRepository;

    public Searchentity  saveproduct(Searchentity searchentity){

        Searchentity searchentity1 = searchRepository.save(searchentity);
        return searchentity1;
    }

    public Searchentity getById(String productId){
        Searchentity searchentity = new Searchentity();
        searchentity = searchRepository.findOneById(productId);
        return searchentity;
    }


    public List<SearchDTO> findByType(String type){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByType(type).forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }


    public List<SearchDTO> findByProductNameContainsOrDescriptionContainsAndType(String title ,String type){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByProductNameContainsOrDescriptionContains(title,title).forEach(searchentity -> {
            if(searchentity.getType().equals(type)) {
                SearchDTO searchDTO = new SearchDTO();
                BeanUtils.copyProperties(searchentity, searchDTO);
                searchDTOList.add(searchDTO);
            }
        });
        return searchDTOList;
    }


    public List<SearchDTO> findByProductNameContains(String searchTerm){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByProductNameContains(searchTerm).forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }

    public List<SearchDTO> findByProductNameContainsOrDescriptionContainsOrTypeContains(String searchTerm , String type){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByProductNameContainsOrDescriptionContainsOrTypeContains(searchTerm,searchTerm,type).forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }


    public List<SearchDTO> findByProductNameContainsOrDescriptionContainsAndTypeContains(String searchTerm , String type){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByProductNameContainsOrDescriptionContainsAndTypeContains(searchTerm,searchTerm,type).forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }


    public List<SearchDTO> findByDescriptionContainsOrProductNameContainsAndTypeContains( String searchTerm , String type ){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByDescriptionContainsOrProductNameContainsAndTypeContains(searchTerm,searchTerm,type).forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }

    public List<SearchDTO> findByTypeContainsAndDescriptionContainsOrProductNameContains( String type, String searchTerm){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        List<Searchentity> searchentityList = new ArrayList<>();
        searchRepository.findByTypeContainsAndProductNameContainsOrDescriptionContains(type,searchTerm,searchTerm).forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }




    public List<SearchDTO> findAll(){
        List<SearchDTO> searchDTOList =new ArrayList<>();
        searchRepository.findAll().forEach(searchentity -> {
            SearchDTO searchDTO = new SearchDTO();
            BeanUtils.copyProperties(searchentity,searchDTO);
            searchDTOList.add(searchDTO);
        });
        return searchDTOList;
    }
}

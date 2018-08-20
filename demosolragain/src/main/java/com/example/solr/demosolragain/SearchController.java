package com.example.solr.demosolragain;

import com.example.solr.demosolragain.searchDTO.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SearchController {

    @Autowired
    SearchService searchService;


    @RequestMapping(method = RequestMethod.POST, value = "/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Searchentity searchentity){
        Searchentity searchentity1 = new Searchentity();
        searchentity1 = searchService.saveproduct(searchentity);
        return new ResponseEntity<String>(searchentity1.getId(), HttpStatus.CREATED);

    }



    @RequestMapping(method = RequestMethod.GET, value = "/getproductbytype/{type}")
    public ResponseEntity<List<SearchDTO>> findByType(@PathVariable String type){
        List<SearchDTO> searchDTOList = searchService.findByType(type);
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getproductbyname/{searchTerm}")
    public ResponseEntity<List<SearchDTO>> findByProductNameContains(@PathVariable String searchTerm){
        List<SearchDTO> searchDTOList = searchService.findByProductNameContains(searchTerm);
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/searchPnameDescType/{searchTerm}/{type}")
    public ResponseEntity<List<SearchDTO>> findByProductNameContainsOrDescriptionContainsOrTypeContains(@PathVariable String searchTerm, @PathVariable String type){
        List<SearchDTO> searchDTOList = searchService.findByProductNameContainsOrDescriptionContainsOrTypeContains(searchTerm,type);
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchPnameDescAndType/{searchTerm}/{type}")
    public ResponseEntity<List<SearchDTO>> findByProductNameContainsOrDescriptionContainsAndTypeContains(@PathVariable String searchTerm, @PathVariable String type){
        List<SearchDTO> searchDTOList = searchService.findByProductNameContainsOrDescriptionContainsAndType(searchTerm,type);
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/searchDescPnameAndType/{searchTerm}/{type}")
    public ResponseEntity<List<SearchDTO>> findByDescriptionContainsOrProductNameContainsAndTypeContains(@PathVariable String searchTerm, @PathVariable String type){
        List<SearchDTO> searchDTOList = searchService.findByDescriptionContainsOrProductNameContainsAndTypeContains(searchTerm,type);
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/searchTypeAndDescPname/{searchTerm}/{type}")
    public ResponseEntity<List<SearchDTO>> findByTypeContainsAndDescriptionContainsOrProductNameContains(@PathVariable String searchTerm, @PathVariable String type){
        List<SearchDTO> searchDTOList = searchService.findByTypeContainsAndDescriptionContainsOrProductNameContains(type,searchTerm);
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/get/{productId}")
    public ResponseEntity<Searchentity> getProduct(@PathVariable("productId") String productId) {
        Searchentity searchentity =  searchService.getById(productId);
        return new ResponseEntity<Searchentity>(searchentity, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getallproducts")
    public ResponseEntity<List<SearchDTO>> getAllProducts(){
        List<SearchDTO> searchDTOList = searchService.findAll();
        return new ResponseEntity<List<SearchDTO>>(searchDTOList,HttpStatus.OK);
    }

}

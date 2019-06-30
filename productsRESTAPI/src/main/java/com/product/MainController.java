package com.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.product.Products.inputData;

@RestController
public class MainController {
    Constants constants = new Constants();

    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    @RequestMapping(value = "/api/products/autocomplete", method = RequestMethod.POST)
    public HashSet<String> autoComplete(@RequestBody Map<String,String> body){

        List<String> inputParams = new ArrayList<String>();
        HashSet<String> outputData = new HashSet<String>();
        for(String key: body.keySet())
            inputParams.add(body.get(key));

        for(Products p : inputData){

            if(constants.PRODUCTID.contains(inputParams.get(0))){
                if(p.getProductId().length() >= inputParams.get(1).length() && p.getProductId().substring(0,inputParams.get(1).length()).contains(inputParams.get(1)))
                    outputData.add(p.getProductId());
            }

            if(constants.TITLE.contains(inputParams.get(0))){
                if(p.getTitle().length() >= inputParams.get(1).length() && p.getTitle().substring(0,inputParams.get(1).length()).contains(inputParams.get(1)))
                    outputData.add(p.getTitle());
            }

            if(constants.BRANDID.contains(inputParams.get(0))){
                if(p.getBrandId().length() >= inputParams.get(1).length() && p.getBrandId().substring(0,inputParams.get(1).length()).contains(inputParams.get(1)))
                    outputData.add(p.getBrandName());
            }

            if(constants.BRANDNAME.contains(inputParams.get(0))){
                if(p.getBrandName().length() >= inputParams.get(1).length() && p.getBrandName().substring(0,inputParams.get(1).length()).contains(inputParams.get(1)))
                    outputData.add(p.getBrandName());
            }

            if(constants.CATEGORYID.contains(inputParams.get(0))){
                if(p.getCategoryId().length() >= inputParams.get(1).length() && p.getCategoryId().substring(0,inputParams.get(1).length()).contains(inputParams.get(0)))
                    outputData.add(p.getProductId());
            }

            if(constants.CATEGORYNAME.contains(inputParams.get(1))){
                if(p.getCategoryName().length() >= inputParams.get(1).length() && p.getCategoryName().substring(0,inputParams.get(1).length()).contains(inputParams.get(1)))
                    outputData.add(p.getCategoryName());
            }
        }
        System.out.println("AutoComplete Endpoint Complete");
        return outputData;
    }


    @RequestMapping(value = "/api/products/search", method = RequestMethod.POST)
    public LinkedHashSet<Products> productSearch(@RequestBody SearchQueryBody body){

        LinkedHashSet<Products> result = new LinkedHashSet<>();

        for(Products p : inputData){
            for(ConditionVariables c: body.getConditions()){

                if(constants.BRANDNAME.equals(c.type)){
                    for(String l : c.getValues()){
                        if(p.getBrandName().equals(l))
                            result.add(p);
                    }
                }
                else if(constants.CATEGORYNAME.equals(c.type)){
                    for(String l : c.getValues()){
                        if(p.getCategoryName().equals(l))
                            result.add(p);
                    }
                }

                else if(constants.PRODUCTID.equals(c.type)){
                    for(String l : c.getValues()){
                        if(p.getProductId().equals(l))
                            result.add(p);
                    }
                }


                else if(constants.TITLE.equals(c.type)){
                    for(String l :c.getValues()){
                        if(p.getTitle().equals(l))
                            result.add(p);
                    }
                }

                else if(constants.BRANDID.equals(c.type)){
                    for(String l : c.getValues()){
                        if(p.getBrandId().equals(l))
                            result.add(p);
                    }
                }

                else if(constants.CATEGORYID.equals(c.type)) {
                    for (String l : c.getValues()) {
                        if (p.getCategoryId().equals(l))
                            result.add(p);
                    }
                }
            }
        }

        ArrayList<Products> list = new ArrayList<>();
        for(Products p : result)
            list.add(p);

        int start = body.getPagination().from;
        int end = body.getPagination().size;


        LinkedHashSet<Products> finalResult = new LinkedHashSet<>();
        for(int size = start-1;size<start+end-1;size++){
            finalResult.add(list.get(size));
        }

        System.out.println("Product Search Endpoint Complete");
        return finalResult;
    }

    @RequestMapping(value = "/api/products/keywords", method = RequestMethod.POST)
    public Keywords keyWordsCount(@RequestBody HashMap<String, List<String>> body){

        List<String> keywords = new ArrayList<>();
        for(String key: body.keySet()){
            keywords = body.get(key);
        }

        System.out.println(keywords.size());
        HashMap<String, Integer> map = new HashMap<>();

        for(Products p : inputData){
            for(String keyword : keywords){
                if(p.getTitle().contains(keyword)){
                    if(map.containsKey(keyword))
                        map.put(keyword, map.get(keyword) + 1);
                    else
                        map.put(keyword,1);
                }
            }
        }

        ArrayList<KeyWordFrequencies> list = new ArrayList<>();
        for(String word : map.keySet()){
            KeyWordFrequencies kwf = new KeyWordFrequencies(word, map.get(word));
            list.add(kwf);
        }

        Keywords result = new Keywords(list);

        System.out.println("KeyWords Endpoint Complete");
        return result;
    }
}

package com.lct.springbootjar.springbootes.controller.elastic;

import com.lct.springbootjar.springbootes.service.elastic.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author :lct
 * @date : 2024/4/7
 */
@RestController
@RequestMapping(value = "/elastic")
public class ElasticController {

    private final ElasticService elasticService;

    @Autowired
    public ElasticController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @RequestMapping(value = "/queryAll", method = {RequestMethod.GET, RequestMethod.POST})
    public void testController(String indexName, Integer page, Integer size) throws IOException {
        elasticService.getInfoForIndex(indexName, page, size);
        System.out.println("testController");
    }
}

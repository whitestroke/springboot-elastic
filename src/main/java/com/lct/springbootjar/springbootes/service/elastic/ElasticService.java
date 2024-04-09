package com.lct.springbootjar.springbootes.service.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.util.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Function;

/**
 * @author :lct
 * @date : 2024/4/7
 */
@Service
public class ElasticService {

    private final ElasticsearchClient esClient;

    @Autowired
    public ElasticService(@Qualifier(value = "elasticsearchClient") ElasticsearchClient esClient) {
        this.esClient = esClient;
    }

    public void getInfoForIndex(String indexName, Integer page, Integer size) throws IOException {
        /*SearchResponse<String> search = esClient.search(s -> s
                        .index(indexName)
                        .from((page - 1) * size)
                        .size(size)
                        .query(q -> q
                                .matchAll(t -> t.boost(1F))),
                String.class);*/



        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(indexName)
                .from((page - 1) * size)
                .size(size)
                .query(q -> q
                        .matchAll(t -> t.boost(1F))));
        String string = searchRequest.toString();
        System.out.println(string);

        SearchResponse<String> search1 = esClient.search(searchRequest, String.class);

        for (Hit<String> hit: search1.hits().hits()) {
            System.out.println(hit.source());
        }
    }

}

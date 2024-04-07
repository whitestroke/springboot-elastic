package com.lct.springbootjar.springbootes.service.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        SearchResponse<String> search = esClient.search(s -> s
                        .index(indexName)
                        .from((page - 1) * size)
                        .size(size)
                        .query(q -> q
                                .matchAll(t -> t.boost(1F))),
                String.class);

        for (Hit<String> hit: search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

}

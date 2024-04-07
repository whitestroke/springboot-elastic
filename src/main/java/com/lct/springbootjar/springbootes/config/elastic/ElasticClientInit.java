package com.lct.springbootjar.springbootes.config.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :lct
 * @date : 2024/4/7
 */
@Configuration
public class ElasticClientInit {

    private final ElasticConfig elasticConfig;

    @Autowired
    public ElasticClientInit(ElasticConfig elasticConfig){
        this.elasticConfig = elasticConfig;
    }

    @Bean(name = "elasticsearchClient")
    public ElasticsearchClient getElasticsearchClient(){
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(elasticConfig.getUsername(), elasticConfig.getPassword()));

        RestClientBuilder builder = RestClient.builder(
                        new HttpHost(elasticConfig.getIp(), elasticConfig.getPort()))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider));

        ElasticsearchTransport transport = new RestClientTransport(
                builder.build(), new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

}

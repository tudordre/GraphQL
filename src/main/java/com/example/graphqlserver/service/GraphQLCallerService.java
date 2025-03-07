package com.example.graphqlserver.service;

import com.example.graphqlserver.dto.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphQLCallerService {
    final static String GRAPHQL_URL = "http://localhost:8080/graphql";

    public static void main(String[] args) {
        GraphQLCallerService graphQLCallerService = new GraphQLCallerService();
        List<Book> books = graphQLCallerService.getAllBooks();
        books.forEach(System.out::println);
        System.out.println();
        Book book = graphQLCallerService.getBookById("book-1");
        System.out.println(book);
    }

    public Book getBookById(String id) {
        String query = """
                    query GetBookById($id: ID!) {
                        bookById(id: $id) {
                            id
                            name
                            pageCount
                        }
                    }
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);

        try {
            JsonNode response = callGraphQL(query, variables);
            JsonNode bookNode = response.path("data").path("bookById");

            return new ObjectMapper().readValue(bookNode.toString(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks() {
        String query = """
                    query GetAllBooks {
                        books {
                            id
                            name
                            pageCount
                        }
                    }
                """;
        Map<String, Object> variables = new HashMap<>();

        try {
            JsonNode response = callGraphQL(query, variables);
            JsonNode usersNode = response.path("data").path("books");

            return new ObjectMapper().readValue(usersNode.toString(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode callGraphQL(String query, Map<String, Object> variables) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(GRAPHQL_URL);
            request.setHeader("Content-Type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("query", query);
            requestBody.put("variables", variables);

            String jsonBody = objectMapper.writeValueAsString(requestBody);
            request.setEntity(new StringEntity(jsonBody));
            try (CloseableHttpResponse response = httpClient.execute(request);
                 InputStream content = response.getEntity().getContent()) {
                return objectMapper.readTree(content);
            }
        }
    }
}

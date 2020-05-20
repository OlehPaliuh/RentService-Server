package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.Location;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ApartmentSearchRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Apartment> searchApartmentsQuery(String searchString) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Apartment.class)
                .get();

        // Create a Lucene Full Text Query
        org.apache.lucene.search.Query luceneQuery = queryBuilder.bool()
                .must(queryBuilder
                        .keyword()
                        .wildcard()
                        .onFields("title", "description", "location.route", "location.city", "location.country", "location.fullAddress")
                        .matching("*" + searchString.toLowerCase() + "*")
                        .createQuery()
                )
                .createQuery();

        javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Apartment.class);

        // Run Query and print out results to console
        List<Apartment> result = (List<Apartment>) fullTextQuery.getResultList();

        for (Apartment b : result) {
            System.out.println("-" + b);
        }
        return result;
    }
}

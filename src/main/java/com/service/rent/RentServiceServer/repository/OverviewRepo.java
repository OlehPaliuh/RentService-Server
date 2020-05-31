package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.ApartmentOverview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverviewRepo extends CrudRepository<ApartmentOverview, Long> {

    List<ApartmentOverview> getAllApartmentOverviewByApartmentId(Long apartmentId);

    List<ApartmentOverview> getAllApartmentOverviewByAccountId(Long accountId);

    ApartmentOverview getApartmentOverviewById(Long overviewId);
}

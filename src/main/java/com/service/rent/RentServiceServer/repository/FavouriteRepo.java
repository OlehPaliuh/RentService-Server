package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Favourite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavouriteRepo extends CrudRepository<Favourite, Long> {

    List<Favourite> getAllByAccount_Id(Long accountId);

    Favourite getByAccount_IdAndApartment_Id(Long accountID, Long apartmentId);
}

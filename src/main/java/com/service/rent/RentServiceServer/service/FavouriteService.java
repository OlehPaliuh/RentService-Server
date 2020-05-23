package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Favourite;
import com.service.rent.RentServiceServer.repository.FavouriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepo favouriteRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ApartmentService apartmentService;

    public Favourite creteFavourite(Long accountId, Long apartmentId) {
        Favourite favourite = new Favourite();
        favourite.setAccount(accountService.getById(accountId));
        favourite.setApartment(apartmentService.getApartmentById(apartmentId));
        return favouriteRepo.save(favourite);
    }

    public boolean removeFavourite(Long accountId, Long apartmentId) {
        Favourite favourite = favouriteRepo.getByAccount_IdAndApartment_Id(accountId, apartmentId);
         favouriteRepo.delete(favourite);
         return true;
    }

    public List<Favourite> getAllByAccountId(Long accountId) {
        return favouriteRepo.getAllByAccount_Id(accountId);
    }
}

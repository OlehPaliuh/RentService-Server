package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.Favourite;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/favourite/")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping(path = "{accountId}/all")
    public @ResponseBody
    List<Favourite> deleteFavourite(@PathVariable Long accountId) {
        return favouriteService.getAllByAccountId(accountId);
    }

    @PostMapping(path = "{accountId}/add/{apartmentId}")
    public @ResponseBody
    Favourite createFavourite(@PathVariable Long accountId, @PathVariable("apartmentId") Long apartmentId) {
        return favouriteService.creteFavourite(accountId, apartmentId);
    }

    @PostMapping(path = "{accountId}/delete/{apartmentId}")
    public @ResponseBody
    boolean deleteFavourite(@PathVariable Long accountId, @PathVariable("apartmentId") Long apartmentId) {
        return favouriteService.removeFavourite(accountId, apartmentId);
    }
}

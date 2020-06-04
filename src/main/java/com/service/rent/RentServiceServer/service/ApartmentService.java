package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.Location;
import com.service.rent.RentServiceServer.entity.dto.AccountDetailsDto;
import com.service.rent.RentServiceServer.entity.dto.ApartmentDto;
import com.service.rent.RentServiceServer.entity.dto.ApartmentFilteringDto;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import com.service.rent.RentServiceServer.entity.enums.BuildingType;
import com.service.rent.RentServiceServer.entity.enums.SortingType;
import com.service.rent.RentServiceServer.exception.AppatmentNotFound;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepo apartmentRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ApartmentSearchService apartmentSearchService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private AccountRepo accountRepo;

    public List<Apartment> getAll(SortingType sortingType) {
        List<Apartment> result = apartmentRepo.findAll();
        result = result.stream()
                       .filter(apartment -> !apartment.getOwner().isLocked())
                       .collect(Collectors.toList());
        return sortApartments(sortingType, result);
    }

    public Apartment getApartmentById(Long id) {
        return apartmentRepo.findById(id).orElseThrow(() -> new AppatmentNotFound("Appartment with id: " + id + " not found"));
    }

    public Apartment createApartment(ApartmentDto newApartment) {
        Apartment apartment = new Apartment();
        apartment.setTitle(newApartment.getTitle());
        apartment.setDescription(newApartment.getDescription());
        apartment.setNumberOfRooms(newApartment.getNumberOfRooms());
        apartment.setPrice(newApartment.getPrice());
        apartment.setTotalArea(newApartment.getTotalArea());
        apartment.setTags(newApartment.getTags());
        apartment.setStatus(ApartmentStatus.FREE);
        apartment.setStatusDateChange(LocalDateTime.now());
        apartment.setImageLinks(newApartment.getImageLinks());
        apartment.setLivingArea(newApartment.getLivingArea());
        apartment.setFloor(newApartment.getFloor());
        apartment.setAllowPets(newApartment.isAllowPets());
        apartment.setBuildingType(newApartment.getBuildingType());
        if (newApartment.getLocation() != null) {
            Location location = locationService.createLocation(newApartment.getLocation());
            apartment.setLocation(location);
        }
        Account account = accountService.getById(newApartment.getAccountId());
        account.setOwningApartmentsCount(account.getOwningApartmentsCount() == null ? 1 : account.getOwningApartmentsCount() + 1);
        apartment.setOwner(accountService.getById(newApartment.getAccountId()));
        return saveApartment(apartment);
    }

    public Apartment saveApartment(Apartment apartment) {
        return apartmentRepo.save(apartment);
    }

    public List<Apartment> getFilteredApartments(ApartmentFilteringDto apartmentFilter, String q) {
        return filterApartments(apartmentFilter, apartmentSearchService.searchApartments(q));
    }

    public List<Apartment> getFilteredApartments(ApartmentFilteringDto apartmentFilter) {
        return filterApartments(apartmentFilter, apartmentRepo.findAll());
    }

    private List<Apartment> filterApartments(ApartmentFilteringDto apartmentFilter, List<Apartment> apartments) {
        return apartments.stream()
                         .filter(a -> !apartmentFilter.isHasPhotos() || a.getImageLinks().size() > 0)
                         .filter(a -> !apartmentFilter.getAllowPets() || a.isAllowPets())
                         .filter(a -> !apartmentFilter.isNewBuilding() ||
                                      BuildingType.NEW_BUILDING.equals(a.getBuildingType()))
                         .filter(a -> !apartmentFilter.isOldBuilding() ||
                                      BuildingType.OLD_BUILDING.equals(a.getBuildingType()))
                         .filter(a -> apartmentFilter.getPriceMin() == null ||
                                      apartmentFilter.getPriceMin() <= a.getPrice())
                         .filter(a -> apartmentFilter.getPriceMax() == null ||
                                      apartmentFilter.getPriceMax() >= a.getPrice())
                         .filter(a -> apartmentFilter.getFloorMin() == null ||
                                      apartmentFilter.getFloorMin() <= a.getFloor())
                         .filter(a -> apartmentFilter.getFloorMax() == null ||
                                      apartmentFilter.getFloorMax() >= a.getFloor())
                         .filter(a -> apartmentFilter.getLivingAreaMin() == null ||
                                      apartmentFilter.getLivingAreaMin() <= a.getLivingArea())
                         .filter(a -> apartmentFilter.getLivingAreaMax() == null ||
                                      apartmentFilter.getLivingAreaMax() >= a.getLivingArea())
                         .filter(a -> apartmentFilter.getRoomsMin() == null ||
                                      apartmentFilter.getRoomsMin() <= a.getNumberOfRooms())
                         .filter(a -> apartmentFilter.getRoomsMax() == null ||
                                      apartmentFilter.getRoomsMax() >= a.getNumberOfRooms())
                         .filter(a -> apartmentFilter.getTotalAreaMin() == null ||
                                      apartmentFilter.getTotalAreaMin() <= a.getTotalArea())
                         .filter(a -> apartmentFilter.getTotalAreaMax() == null ||
                                      apartmentFilter.getTotalAreaMax() >= a.getTotalArea())
                         .filter(a -> StringUtils.isEmpty(apartmentFilter.getLandlordUsername()) ||
                                      apartmentFilter.getLandlordUsername()
                                                     .equals(a.getOwner().getUsername()))
                         .collect(Collectors.toList());
    }

    public List<Apartment> getFilteredApartments(ApartmentFilteringDto apartmentFilter, SortingType sortingType) {

        List<Apartment> apartmentsList = apartmentRepo.findAll().stream()
                                                      .filter(apartment -> !apartment.getOwner().isLocked())
                                                      .filter(a -> !apartmentFilter.isHasPhotos() || a.getImageLinks().size() > 0)
                                                      .filter(a -> !apartmentFilter.getAllowPets() || a.isAllowPets())
                                                      .filter(a -> !apartmentFilter.isNewBuilding() ||
                                                                   BuildingType.NEW_BUILDING.equals(a.getBuildingType()))
                                                      .filter(a -> !apartmentFilter.isOldBuilding() ||
                                                                   BuildingType.OLD_BUILDING.equals(a.getBuildingType()))
                                                      .filter(a -> apartmentFilter.getPriceMin() == null ||
                                                                   apartmentFilter.getPriceMin() <= a.getPrice())
                                                      .filter(a -> apartmentFilter.getPriceMax() == null ||
                                                                   apartmentFilter.getPriceMax() >= a.getPrice())
                                                      .filter(a -> apartmentFilter.getFloorMin() == null ||
                                                                   apartmentFilter.getFloorMin() <= a.getFloor())
                                                      .filter(a -> apartmentFilter.getFloorMax() == null ||
                                                                   apartmentFilter.getFloorMax() >= a.getFloor())
                                                      .filter(a -> apartmentFilter.getLivingAreaMin() == null ||
                                                                   apartmentFilter.getLivingAreaMin() <= a.getLivingArea())
                                                      .filter(a -> apartmentFilter.getLivingAreaMax() == null ||
                                                                   apartmentFilter.getLivingAreaMax() >= a.getLivingArea())
                                                      .filter(a -> apartmentFilter.getRoomsMin() == null ||
                                                                   apartmentFilter.getRoomsMin() <= a.getNumberOfRooms())
                                                      .filter(a -> apartmentFilter.getRoomsMax() == null ||
                                                                   apartmentFilter.getRoomsMax() >= a.getNumberOfRooms())
                                                      .filter(a -> apartmentFilter.getTotalAreaMin() == null ||
                                                                   apartmentFilter.getTotalAreaMin() <= a.getTotalArea())
                                                      .filter(a -> apartmentFilter.getTotalAreaMax() == null ||
                                                                   apartmentFilter.getTotalAreaMax() >= a.getTotalArea())
//                                                      .filter(a -> StringUtils.isEmpty(apartmentFilter.getLandlordUsername()) ||
//                                                                   apartmentFilter.getLandlordUsername()
//                                                                                  .equals(a.getOwner().getUsername()))
                                                      .collect(Collectors.toList());

        return sortApartments(sortingType, apartmentsList);
    }

    private List<Apartment> sortApartments(SortingType sortingType, List<Apartment> apartmentsList) {
        if (SortingType.PRICE_ASD.equals(sortingType)) {
            apartmentsList = apartmentsList.stream()
                                           .sorted(Comparator.comparingDouble(Apartment::getPrice))
                                           .collect(Collectors.toList());
        } else if (SortingType.PRICE_DESC.equals(sortingType)) {
            apartmentsList = apartmentsList.stream()
                                           .sorted(Comparator.comparingDouble(Apartment::getPrice).reversed())
                                           .collect(Collectors.toList());
        } else if (SortingType.DATE_ASD.equals(sortingType)) {
            apartmentsList = apartmentsList.stream()
                                           .sorted(Comparator.comparing(Apartment::getCreateDate,
                                                                        Comparator.nullsLast(Comparator.naturalOrder())))
                                           .collect(Collectors.toList());
        } else if (SortingType.DATE_DESC.equals(sortingType)) {
            apartmentsList = apartmentsList.stream()
                                           .sorted(Comparator.comparing(Apartment::getCreateDate,
                                                                        Comparator.nullsLast(Comparator.reverseOrder())))
                                           .collect(Collectors.toList());
        }
        return apartmentsList;
    }

    public Apartment updateStatus(Long apartmentId, ApartmentStatus status) throws NotFoundException {
        Apartment apartment = apartmentRepo.getApartmentById(apartmentId);
        if (apartment == null) {
            throw new NotFoundException("Apartment not found");
        }
        apartment.setStatus(status);
        apartment.setStatusDateChange(LocalDateTime.now());
        return apartmentRepo.save(apartment);

    }

    public boolean deleteApartment(Long apartmentId, AccountDetailsDto accountDetailsDto) throws Exception {

        Apartment apartment = apartmentRepo.getApartmentById(apartmentId);

        if (apartment == null) {
            throw new NotFoundException("Apartment not found");
        }

        if (apartment.getOwner() != null && apartment.getOwner().getId() != null && apartment.getOwner().getId().equals(
                accountDetailsDto.getId())) {

            Account account = accountRepo.getAccountById(accountDetailsDto.getId());

            account.setOwningApartmentsCount(account.getOwningApartmentsCount() > 0 ? account.getOwningApartmentsCount() - 1 : 0);
            accountRepo.save(account);

            for (String link : apartment.getImageLinks()) {
                imageService.deleteObjectFromS3(accountDetailsDto.getId(), link);
            }

            apartmentRepo.delete(apartment);
        } else {
            throw new NotFoundException("Account is not owner");
        }
        return true;
    }

    public Apartment toggleLock(Long id) {
        Apartment apartment = getApartmentById(id);
        boolean currentLockStatus = apartment.getIsLocked() != null ? apartment.getIsLocked() : false;
        apartment.setIsLocked(!currentLockStatus);
        return apartmentRepo.saveAndFlush(apartment);
    }
}

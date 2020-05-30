package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.dto.AccountDetailsDto;
import com.service.rent.RentServiceServer.entity.dto.OwnerAccountInfoDto;
import com.service.rent.RentServiceServer.service.AccountService;
import com.service.rent.RentServiceServer.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageService imageService;

    @GetMapping(path = "{accountId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public @ResponseBody
    AccountDetailsDto getAccountDetailsById(@PathVariable Long accountId) {
        return modelMapper.map(accountService.getById(accountId), AccountDetailsDto.class);
    }

    @GetMapping(path = "owner/{accountId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public @ResponseBody
    OwnerAccountInfoDto getOwnAccountInfoById(@PathVariable Long accountId) {
        return modelMapper.map(accountService.getById(accountId), OwnerAccountInfoDto.class);
    }

    @PostMapping(path = "{accountId}/updateAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    OwnerAccountInfoDto updateAvatar(@PathVariable Long accountId, @RequestParam MultipartFile file) {
        String avatarPath = imageService.uploadProfileImageToS3(accountId, file);
        return modelMapper.map(accountService.updateAvatar(accountId, avatarPath), OwnerAccountInfoDto.class);
    }

    @PostMapping(path = "{accountId}/update")
    public @ResponseBody
    OwnerAccountInfoDto updateAccount(@PathVariable Long accountId, @RequestBody OwnerAccountInfoDto accountInfo) {
        return modelMapper.map(accountService.updateAccount(accountId, modelMapper.map((accountInfo), Account.class)), OwnerAccountInfoDto.class);
    }

    @PostMapping(path = "/{accountId}/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<String> image(@PathVariable Long accountId, @RequestParam MultipartFile[] files) {
        return imageService.uploadApartmentImagesToS3(accountId, files);
    }

    @PostMapping(path = "/{accountId}/deleteOwnAccount")
    public @ResponseBody
    boolean image(@PathVariable Long accountId) {
        return accountService.deleteOwnAccountWithAllInfo(accountId);
    }
}

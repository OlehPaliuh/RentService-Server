package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {

    @Autowired
    public final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(path = "/{accountId}/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<String> image(@PathVariable Long accountId, @RequestParam MultipartFile [] files) {
        return imageService.uploadApartmentImagesToS3(accountId, files);
    }

    @GetMapping(path = "/{apartmentId}/all")
    public @ResponseBody
    void getAllImages(@PathVariable Long apartmentId) {
         imageService.getAllObjects(apartmentId);
    }

    @PostMapping(path = "/{accountId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean image(@PathVariable Long accountId, @PathParam("filePath") String filePath) throws Exception {
        return imageService.deleteObjectFromS3(accountId, filePath);
    }
}

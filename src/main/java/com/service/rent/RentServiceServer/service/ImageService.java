package com.service.rent.RentServiceServer.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ImageService {

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.bucketName}")
    private String bucketName;

    private final Regions clientRegion = Regions.EU_WEST_1;

    private final static String APARTMENT_S3_PATH = "photos/%s/apartment/";

    private final static String PROFILE_S3_PATH = "photos/%s/profile/";

    public List<String> uploadApartmentImagesToS3(Long accountId, MultipartFile[] files) {
        String path = String.format(APARTMENT_S3_PATH, accountId.toString());

        List<String> imageNames = new ArrayList<>();

        List<File> uploadFiles = new ArrayList<>();

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                    .withPathStyleAccessEnabled(true)
                    .build();

            TransferManager tm = TransferManagerBuilder.standard()
                    .withS3Client(s3Client)
                    .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
                    .build();

            // TransferManager processes all transfers asynchronously,
            // so this call returns immediately.

            for (MultipartFile file : files) {
                uploadFiles.add(convert(file));
            }

            for (File uploadFile : uploadFiles) {
                String newImageName = imageNameGenerator(uploadFile.getName());
                Upload upload = tm.upload(bucketName, path + newImageName, uploadFile);

                imageNames.add("https://" + bucketName + ".s3-" + clientRegion.getName() + ".amazonaws.com/" + path + newImageName);

                upload.waitForCompletion();
            }
        } catch (IOException | InterruptedException | SdkClientException e) {
            e.printStackTrace();
        } finally {
            for (File file : uploadFiles) {
                file.delete();
            }
        }

        return imageNames;
    }

    public String uploadProfileImageToS3(Long accountId, MultipartFile file) {
        String path = String.format(PROFILE_S3_PATH, accountId.toString());

        String imagePath = null;
        File uploadFile = null;

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                    .withPathStyleAccessEnabled(true)
                    .build();

            TransferManager tm = TransferManagerBuilder.standard()
                    .withS3Client(s3Client)
                    .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
                    .build();

            uploadFile = convert(file);

            String newImageName = imageNameGenerator(uploadFile.getName());
            Upload upload = tm.upload(bucketName, path + newImageName, uploadFile);

            imagePath = "https://" + bucketName + ".s3-" + clientRegion.getName() + ".amazonaws.com/" + path + newImageName;

            upload.waitForCompletion();
        } catch (IOException | InterruptedException | SdkClientException e) {
            e.printStackTrace();
        } finally {
            uploadFile.delete();
        }

        return imagePath;
    }

    public Boolean deleteObjectFromS3(Long accountId, String path) throws Exception {
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                    .withPathStyleAccessEnabled(true)
                    .build();

            String [] pathParts = path.split("/");
            String fileKey="";
            if(pathParts.length > 0) {
                fileKey = pathParts[pathParts.length -1];
            } else {
                throw new Exception("Error deleting");
            }

            s3Client.deleteObject(new DeleteObjectRequest(bucketName, String.format(APARTMENT_S3_PATH, accountId.toString())+fileKey));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<S3ObjectSummary> getAllObjects(Long apartmentId) {

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .withPathStyleAccessEnabled(true)
                .build();

        ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest()
                .withBucketName(bucketName)
                .withPrefix("photos/apartment/" + apartmentId.toString() + "/"));

        return objectListing.getObjectSummaries();
    }

    public File convert(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        convertedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    public String imageNameGenerator(String fileName) {

        String extension = FilenameUtils.getExtension(fileName);

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString() + "." + extension;
    }
}

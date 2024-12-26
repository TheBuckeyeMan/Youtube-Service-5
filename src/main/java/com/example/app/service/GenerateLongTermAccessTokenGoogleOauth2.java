//TODO Seporate this into its own repo to generate new long term tokens
//Notes: ONLY TO USE IF WE NEED TO GENERATE A NEW LONG TERM ACCESS TOKEN WHICH WILL BE GENERATED THEN STORED IN REPO SECRETS AS AN ENVIRONMENT VARIABLE< THIS IS INDEPENDENT OF REFRESH TOKEN AND REFRESH TOKEN IS DEPENDENT ON THE LONG TERM ACCESS TOKEN
//OAuth Creentials on google cloud must be configured before generating token. 

//ADD to ServiceTrigger.java: 

// @Value("${youtube.api.clientid}")
// private String clientId;

// @Value("${youtube.api.clientsecret}")
// private String clientSecret;

// @Value("${youtube.api.authuri}")
// private String authUri;

// @Value("${youtube.api.tokenuri}")
// private String tokenUri;

// @Value("${youtube.api.redirecturi}")
// private String redirectUri;


//String GenerateLongLivedToken = generateLongTermAccessTokenGoogleOauth2.getRefreshToken(clientId,clientSecret,authUri,tokenUri,redirectUri);

//Uncomment the below.

//You can now generate then long term access token for youtube


// package com.example.app.service;

// import java.io.StringReader;
// import java.nio.charset.StandardCharsets;
// import java.util.Collections;
// import java.util.concurrent.CompletableFuture;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
// import com.google.api.client.auth.oauth2.Credential;
// import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
// import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
// import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
// import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
// import com.google.api.client.json.gson.GsonFactory;
// import software.amazon.awssdk.core.async.AsyncResponseTransformer;
// import software.amazon.awssdk.services.s3.S3AsyncClient;
// import software.amazon.awssdk.services.s3.model.GetObjectRequest;

// public class GenerateLongTermAccessTokenGoogleOauth2 {
//     private static final Logger log = LoggerFactory.getLogger(ReadFile.class);
//     private final S3AsyncClient s3Client;
//     private S3LoggingService s3LoggingService;

//     public GenerateLongTermAccessTokenGoogleOauth2(S3AsyncClient s3Client, S3LoggingService s3LoggingService){
//         this.s3Client = s3Client;
//         this.s3LoggingService = s3LoggingService;
//     }

//     public String getBasicFileContents(String landingBucket, String titleKey){
//         try{
//             //This Creates the Get request to AWS S3
//             GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                                                                 .bucket(landingBucket)
//                                                                 .key(titleKey)
//                                                                 .build();

//             //Convert byte array to string
//             CompletableFuture<String> basicContentFuture = s3Client.getObject(getObjectRequest, AsyncResponseTransformer.toBytes()).thenApply(responseBytes -> {
//                 String basicContent = new String(responseBytes.asByteArray(),StandardCharsets.UTF_8);
//                 log.info("The content from the basic file saved in the basicContent variable is: " + basicContent);
//                 return basicContent;
//             });

//             // Wait for and return the result
//             String basicFileContent = basicContentFuture.join();
//             if (basicFileContent != null){
//                 return basicFileContent;
//             } else {
//                 log.error("Error: Basic File Content is blank, or was unable to be retrieved form source");
//                 s3LoggingService.logMessageToS3("Error: Basic File Content is blank, or was unable to be retrieved form source - line 46 on ReadFile.java: " + LocalDate.now() + " On: youtube-service-5" + ",");
//                 return "Error: Basic File Content is blank, or was unable to be retrieved form source";
//             }
//         } catch (Exception e){
//             log.error("Error Reading file form S3: {}", e.getMessage(),e);
//             s3LoggingService.logMessageToS3("Error: Error Reading file form S3 Line 50 of ReadFile.java: " + LocalDate.now() + " On: youtube-service-5" + ",");
//             return "Error: Unable to read title file contents.";
//         }
//     }
// }
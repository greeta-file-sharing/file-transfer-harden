package io.swagger.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;

@Configuration
public class AmazonConfig {

    @Value("${s3_bucket.access_key}")
    private String accessKey;

    @Value("${s3_bucket.secret_key}")
    private String secretKey;

    @Value("${s3_bucket.region}")
    private String region;

    @Value("${s3_bucket.base_url}")
    private String serviceEndpoint;
    
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public KmsClient kmsClient() {
        Region region = Region.EU_CENTRAL_1;
        //StaticCredentialsProvider staticCredentials = StaticCredentialsProvider
        //        .create(AwsBasicCredentials.create(accessKey, secretKey));

        return KmsClient.builder()
                //.credentialsProvider(staticCredentials)
                .region(region).build();
    }
}
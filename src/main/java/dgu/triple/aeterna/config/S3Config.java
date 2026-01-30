package dgu.triple.aeterna.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.s3.access-key-id:}")
    private String accessKeyId;

    @Value("${aws.s3.secret-access-key:}")
    private String secretAccessKey;

    @Value("${aws.s3.region:ap-northeast-2}")
    private String region;

    @Bean
    public S3Client s3Client() {
        S3Client.Builder builder = S3Client.builder()
                .region(Region.of(region));
        
        // 환경 변수로 자격 증명이 제공된 경우 StaticCredentialsProvider 사용
        // 그렇지 않으면 DefaultCredentialsProvider 사용 (EC2 IAM 역할 등)
        if (accessKeyId != null && !accessKeyId.isEmpty() && 
            secretAccessKey != null && !secretAccessKey.isEmpty()) {
            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
            builder.credentialsProvider(StaticCredentialsProvider.create(awsCredentials));
        } else {
            // EC2 IAM 역할 또는 환경 변수에서 자격 증명 자동 감지
            builder.credentialsProvider(DefaultCredentialsProvider.create());
        }
        
        return builder.build();
    }
}


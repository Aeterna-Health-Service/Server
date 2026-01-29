package dgu.triple.aeterna.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    /**
     * 파일을 S3에 업로드
     *
     * @param file 업로드할 파일
     * @param folder S3 내 저장할 폴더명
     * @return 업로드된 파일의 S3 URL
     */
    public String uploadFile(MultipartFile file, String folder) {
        try {
            // 파일명 생성
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null 
                    ? originalFilename.substring(originalFilename.lastIndexOf(".")) 
                    : "";
            String uniqueFilename = generateUniqueFilename(fileExtension);
            String s3Key = folder + "/" + uniqueFilename;

            // Content-Type 설정
            String contentType = getContentType(fileExtension);

            // S3에 업로드
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .contentType(contentType)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // S3 URL 생성
            String s3Url = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, s3Key);

            log.info("파일 업로드 성공: {}", s3Url);
            return s3Url;

        } catch (IOException e) {
            log.error("파일 업로드 실패: {}", e.getMessage());
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * S3에서 파일 삭제
     *
     * @param s3Url 삭제할 파일의 S3 URL
     * @return 삭제 성공 여부
     */
    public boolean deleteFile(String s3Url) {
        try {
            // URL에서 키 추출
            String s3Key = extractKeyFromUrl(s3Url);

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);

            log.info("파일 삭제 성공: {}", s3Url);
            return true;

        } catch (Exception e) {
            log.error("파일 삭제 실패: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 고유한 파일명 생성
     */
    private String generateUniqueFilename(String fileExtension) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return timestamp + "_" + uuid + fileExtension;
    }

    /**
     * 파일 확장자에 따른 Content-Type 반환
     */
    private String getContentType(String fileExtension) {
        return switch (fileExtension.toLowerCase()) {
            case ".jpg", ".jpeg" -> "image/jpeg";
            case ".png" -> "image/png";
            case ".gif" -> "image/gif";
            case ".webp" -> "image/webp";
            default -> "application/octet-stream";
        };
    }

    /**
     * S3 URL에서 키 추출
     */
    private String extractKeyFromUrl(String s3Url) {
        String prefix = String.format("https://%s.s3.%s.amazonaws.com/", bucketName, region);
        if (s3Url.startsWith(prefix)) {
            return s3Url.substring(prefix.length());
        }
        throw new IllegalArgumentException("유효하지 않은 S3 URL입니다: " + s3Url);
    }
}


//package dgu.triple.aeterna.controller;
//
//import dgu.triple.aeterna.dto.ExceptionDto;
//import dgu.triple.aeterna.dto.ResponseDto;
//import dgu.triple.aeterna.exception.ErrorCode;
//import dgu.triple.aeterna.service.S3Service;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@Slf4j
//@RestController
//@RequestMapping("/image")
//@RequiredArgsConstructor
//public class ImageController {
//
//    private final S3Service s3Service;
//
//    @PostMapping("/upload")
//    public ResponseDto<String> uploadImage(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam(value = "folder", defaultValue = "images") String folder
//    ) {
//        try {
//            if (file.isEmpty()) {
//                return new ResponseDto<>(
//                        HttpStatus.BAD_REQUEST,
//                        false,
//                        null,
//                        new ExceptionDto(ErrorCode.FILE_UPLOAD_ERROR, "파일이 비어있습니다.")
//                );
//            }
//
//            String imageUrl = s3Service.uploadFile(file, folder);
//            return ResponseDto.ok(imageUrl);
//
//        } catch (Exception e) {
//            log.error("이미지 업로드 실패: {}", e.getMessage());
//            return new ResponseDto<>(
//                    HttpStatus.INTERNAL_SERVER_ERROR,
//                    false,
//                    null,
//                    new ExceptionDto(ErrorCode.FILE_UPLOAD_ERROR, "이미지 업로드 중 오류가 발생했습니다: " + e.getMessage())
//            );
//        }
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseDto<Boolean> deleteImage(@RequestParam("imageUrl") String imageUrl) {
//        try {
//            boolean success = s3Service.deleteFile(imageUrl);
//            if (success) {
//                return ResponseDto.ok(true);
//            } else {
//                return new ResponseDto<>(
//                        HttpStatus.INTERNAL_SERVER_ERROR,
//                        false,
//                        null,
//                        new ExceptionDto(ErrorCode.SERVER_ERROR, "이미지 삭제에 실패했습니다.")
//                );
//            }
//        } catch (Exception e) {
//            log.error("이미지 삭제 실패: {}", e.getMessage());
//            return new ResponseDto<>(
//                    HttpStatus.INTERNAL_SERVER_ERROR,
//                    false,
//                    null,
//                    new ExceptionDto(ErrorCode.SERVER_ERROR, "이미지 삭제 중 오류가 발생했습니다: " + e.getMessage())
//            );
//        }
//    }
//}
//

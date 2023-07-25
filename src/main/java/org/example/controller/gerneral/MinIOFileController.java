package org.example.controller.gerneral;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class MinIOFileController {
    private final MinioClient client;

    private static final String TEST_BUCKET = "test";

    public MinIOFileController(MinioClient client) {
        this.client = client;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile file) {

        try {
            int idx = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
            String suffix = file.getOriginalFilename().substring(idx + 1);
            String fileName = UUID.randomUUID() + "." + suffix;

            // 保存文件
            client.putObject(PutObjectArgs.builder()
                    .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .bucket(TEST_BUCKET)
                    .build());
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/getUrl")
    public String getUrl(@RequestParam(name = "path") String path) {
        try {
            // 获取文件访问地址 7天失效
            String url = client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(TEST_BUCKET)
                    .object(path)
                    .method(Method.GET)
                    .expiry(7, TimeUnit.DAYS).build()
            );
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

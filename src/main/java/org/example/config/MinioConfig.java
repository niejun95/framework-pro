package org.example.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    private static final String END_POINT = "http://43.143.1.90:9091";

    private static final String USERNAME = "minioadmin";
    private static final String PASSWORD = "minioadmin";

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(END_POINT)
                .credentials(USERNAME, PASSWORD)
                .build();
    }

}

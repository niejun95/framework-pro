package org.example.controller.gerneral;

import org.example.utils.MinioUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
public class MinIOFileController {

    private static final String TEST_BUCKET = "test";

    private MinioUtil minioUtil;

    public MinIOFileController(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile file) throws Exception {
        return minioUtil.uploadFile(file, TEST_BUCKET);
    }

    @GetMapping("/getUrl")
    public String getUrl(@RequestParam(name = "path") String fileName) throws Exception {
        String objectURL = minioUtil.getObjectURL(TEST_BUCKET, fileName, 7);
        return objectURL;
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws Exception {
        InputStream in = minioUtil.getObject(TEST_BUCKET, fileName);
        int length;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        response.reset();
        response.addHeader("Content-Disposition", " attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/octet-stream");
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
    }
}

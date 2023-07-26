package org.example.utils;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import org.example.props.MinioProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ConditionalOnBean(MinioClient.class)
@Component
public class MinioUtil {

    private final MinioClient client;

    private final MinioProperties minioProperties;

    public MinioUtil(MinioClient client, MinioProperties minioProperties) {
        this.client = client;
        this.minioProperties = minioProperties;
    }


    /**
     * 创建 bucket
     *
     * @param bucketName bucket名称
     * @throws Exception 异常
     */
    public void createBucket(String bucketName) throws Exception {
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName bucket名称
     */
    public String uploadFile(MultipartFile file, String bucketName) throws Exception {
        // 判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            return null;
        }
        // 判断存储桶是否存在，不存在则创建
        createBucket(bucketName);
        // 文件名
        String originalFilename = file.getOriginalFilename();
        // 新的文件名 = 存储桶文件名_时间戳.后缀名
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = bucketName + "_" + format.format(new Date()) + "_" + new Random().nextInt(1000)
                + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 开始上传
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName).stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());
        String url = minioProperties.getEndpoint() + "/" + bucketName + "/" + fileName;
        return url;
    }

    /**
     * 获取全部 bucket
     *
     * @return
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return client.listBuckets();
    }

    /**
     * 根据 bucketName 获取信息
     *
     * @param bucketName bucket名称
     */
    public Optional<Bucket> getBucket(String bucketName) throws Exception {
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据 bucketName 删除信息
     *
     * @param bucketName bucket名称
     * @throws Exception 异常
     */
    public void removeBucket(String bucketName) throws Exception {
        client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     * @param expires    到期
     */
    public String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception {
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .method(Method.GET)
                .expiry(expires, TimeUnit.DAYS)
                .build());
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return client.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     * @param stream     文件流
     * @throws Exception 异常
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, stream.available(), -1)
                .contentType(objectName.substring(objectName.lastIndexOf(".")))
                .build());
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  对象名称
     * @param stream      流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception 异常
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, -1)
                .contentType(contextType)
                .build());
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     * @throws Exception 异常
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return client.statObject(StatObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     * @throws Exception 异常
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        client.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }
}

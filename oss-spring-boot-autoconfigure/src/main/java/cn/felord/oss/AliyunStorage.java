package cn.felord.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.InputStream;

/**
 * @author felord.cn
 * @since 2020/8/24 20:17
 */
public class AliyunStorage implements Storage {

    private final OSSClient ossClient;

    public AliyunStorage(OSSClient ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        this.ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return this.ossClient.getObject(bucketName, objectName).getObjectContent();
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        return "https://" + bucketName + "." + this.ossClient.getEndpoint() + "/" + objectName;
    }

    @Override
    public void removeObject(String bucketName, String objectName) throws Exception {
        this.ossClient.deleteObject(bucketName, objectName);
    }
}

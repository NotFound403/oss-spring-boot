package cn.felord.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Oss properties
 *
 * @author felord.cn
 * @since 2020 /8/24 18:20
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {

    private Aliyun aliyun;
    private Minio minio;


    /**
     * Minio OSS.
     */
    @Data
    public static class Minio {
        private boolean active;
        private String endpoint;
        private String accessKey;
        private String secretKey;
    }

    /**
     * Aliyun OSS.
     */
    @Data
    public static class Aliyun {
        private boolean active;
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
    }

}

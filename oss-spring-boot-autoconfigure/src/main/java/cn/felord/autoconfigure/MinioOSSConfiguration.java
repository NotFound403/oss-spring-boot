package cn.felord.autoconfigure;

import cn.felord.oss.MinioStorage;
import cn.felord.oss.Storage;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Minio oss configuration.
 *
 * @author felord.cn
 * @since 2020 /8/24 19:42
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "oss.minio", name = "active", havingValue = "true")
@ConditionalOnClass(MinioClient.class)
class MinioOSSConfiguration {

    /**
     * Minio storage storage.
     *
     * @param ossProperties the oss properties
     * @return the storage
     * @throws InvalidPortException     the invalid port exception
     * @throws InvalidEndpointException the invalid endpoint exception
     */
    @Bean
    @Qualifier("minioStorage")
    Storage minioStorage(OSSProperties ossProperties) throws InvalidPortException, InvalidEndpointException {
        OSSProperties.Minio minio = ossProperties.getMinio();
        MinioClient minioClient = new MinioClient(minio.getEndpoint(),
                minio.getAccessKey(),
                minio.getSecretKey());
        return new MinioStorage(minioClient);
    }


}

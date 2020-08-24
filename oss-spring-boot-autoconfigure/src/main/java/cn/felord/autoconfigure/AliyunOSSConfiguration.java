package cn.felord.autoconfigure;

import cn.felord.oss.AliyunStorage;
import cn.felord.oss.Storage;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Aliyun oss configuration.
 *
 * @author felord.cn
 * @since 2020 /8/24 19:42
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "oss.aliyun", name = "active", havingValue = "true")
@ConditionalOnClass(OSSClient.class)
class AliyunOSSConfiguration {


    /**
     * Aliyun storage storage.
     *
     * @param ossProperties the oss properties
     * @return the storage
     */
    @Bean
    @Qualifier("aliyunStorage")
    Storage aliyunStorage(OSSProperties ossProperties) {
        OSSProperties.Aliyun aliyun = ossProperties.getAliyun();
        OSSClient ossClient = new OSSClient(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        return new AliyunStorage(ossClient);
    }


}

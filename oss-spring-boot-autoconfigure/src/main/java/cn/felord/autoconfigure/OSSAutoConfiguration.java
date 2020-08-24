package cn.felord.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author felord.cn
 * @since 2020/8/24 21:22
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OSSProperties.class)
@Import({MinioOSSConfiguration.class, AliyunOSSConfiguration.class})
public class OSSAutoConfiguration {
}

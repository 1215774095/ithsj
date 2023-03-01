package cn.ithsj.config;

import cn.ithsj.properties.IpProperties;
import cn.ithsj.service.IpCountService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableConfigurationProperties(IpProperties.class)
@ConditionalOnMissingBean(IpCountService.class)
@Import({IpCountService.class,IpProperties.class})
@EnableScheduling
public class IpAutoConfiguration {

}

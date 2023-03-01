package cn.ithsj.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("inProperties")
@Data
@ConfigurationProperties(prefix = "tools.ip")
public class IpProperties {
     /*
     * 日志显示周期
     * */
     private long cycle=10l;

     /*
     * 是否重置周期数据
     * */
     private Boolean cycleReset=false;

     /*
     * 日志输出模式
     * */
     private String model=LogModel.DETAIL.value;

     public enum LogModel{
          DETAIL("detail"),
          SIMPLE("simple");
          private String value;

          public String getValue() {
               return value;
          }

          private LogModel(String value) {
               this.value = value;
          }
     }
}

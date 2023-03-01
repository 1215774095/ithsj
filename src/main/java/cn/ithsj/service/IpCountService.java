package cn.ithsj.service;

import cn.ithsj.properties.IpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class IpCountService {
    private Map<String,Integer> ipCountMap=new HashMap();
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private IpProperties ipProperties;
    public void count(){
        String ip = httpServletRequest.getRemoteAddr();
        Integer count = ipCountMap.get(ip);
        if(count==null){
            ipCountMap.put(ip,1);
        }else {
            ipCountMap.put(ip,ipCountMap.get(ip)+1);
        }

    }
    //#{beanName.properties}
    //@Scheduled(cron = "0/${tools.ip:5} * * * * ?")
    @Scheduled(cron = "0/#{inProperties.cycle} * * * * ?")
    public void print(){
        if (ipProperties.getModel().equals(IpProperties.LogModel.DETAIL.getValue())){
            System.out.println("-------ip监控--------");
            for (Map.Entry<String, Integer> entry : ipCountMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                String s=String.format("%18s       %5d",key,value);
                System.out.println(s);
            }
        }else if(ipProperties.getModel().equals(IpProperties.LogModel.SIMPLE.getValue())){
            System.out.println("-------ip监控----");
            for (String key : ipCountMap.keySet()) {
                String s=String.format("%18s     ",key);
                System.out.println(s);
            }
        }

        if(ipProperties.getCycleReset()){
            ipCountMap.clear();
        }
    }

}

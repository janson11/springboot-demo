package com.janson.springboot.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/10/31 7:11 PM
 */
public class ServiceNameFilter {


    public static void main(String[] args) {

        String serviceNames = "\n" +
                "{namespace=\"pt-internal\", pod=\"platform-it-manage-admin-service-6d8d7dd675-hwptx\", pod_ip=\"172.17.91.178\"}\n" +
                "{namespace=\"pt-internal\", pod=\"platform-it-manage-admin-service-6d8d7dd675-q8knp\", pod_ip=\"172.17.108.125\"}\n";

        Set<String> set = new HashSet<String>();
        String[] serviceNameArray = serviceNames.split("\n");
        for (String s : serviceNameArray) {
            s = s.replaceAll("=",":");
            JSONObject obj = JSON.parseObject(s, JSONObject.class);
            if (obj != null) {
                String pod = obj.getString("pod");
                pod = pod.substring(0, pod.length() - 17);
                set.add(pod);
            }
        }
        for (String s : set) {
            System.out.println(s);
        }


    }
}

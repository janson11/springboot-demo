package com.janson.netty.demos.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/10 4:13 下午
 */
public class SkywalkingServiceDependencyTest {

    private static final List<String> SPRIG_MVC_LIST = new ArrayList<String>(16);
    private static final List<String> REDIS_LIST = new ArrayList<String>(16);
    private static final List<String> MONGODB_LIST = new ArrayList<String>(16);
    private static final List<String> ROCKETMQ_LIST = new ArrayList<String>(16);
    private static final List<String> MYSQL_LIST = new ArrayList<String>(16);
    private static final List<String> SPRING_REST_TEMPLATE_LIST = new ArrayList<String>(16);

    public static void main(String[] args) {
        String json = "{\"data\":{\"topo\":{\"nodes\":[{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1uZW1vLXNlcnZpY2U=.1\",\"name\":\"creationtools::production-nemo-service\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"ZGlzY292ZXJ5LmNlbnRlci5jb2RlbWFvLmNuOjgw.0\",\"name\":\"discovery.center.codemao.cn:80\",\"type\":\"HttpClient\",\"isReal\":false},{\"id\":\"VXNlcg==.0\",\"name\":\"User\",\"type\":\"USER\",\"isReal\":false},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS13b3JrLXNlcnZpY2U=.1\",\"name\":\"community::production-community-work-service\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"ci1icDFjMjVlNWRjMWMyOGY0NjAwLnJlZGlzLnJkcy5hbGl5dW5jcy5jb206NjM3OQ==.0\",\"name\":\"r-bp1c25e5dc1c28f4600.redis.rds.aliyuncs.com:6379\",\"type\":\"Redis\",\"isReal\":false},{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi13b3JrY29yZS1zZXJ2aWNl.1\",\"name\":\"creationtools::production-workcore-service\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1jcmVhdGlvbi10b29scy1hcGk=.1\",\"name\":\"creationtools::production-creation-tools-api\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1iYXNlLXNlcnZpY2U=.1\",\"name\":\"community::production-community-base-service\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"name\":\"community::production-community-admin-platform\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"ZGRzLWJwMTk2M2E2ODlkNjcwYzQxMTIwLm1vbmdvZGIucmRzLmFsaXl1bmNzLmNvbTozNzE3.0\",\"name\":\"dds-bp1963a689d670c41120.mongodb.rds.aliyuncs.com:3717\",\"type\":\"MongoDB\",\"isReal\":false},{\"id\":\"b25zYWRkci5jbi1oYW5nemhvdS5tcS1pbnRlcm5hbC5hbGl5dW5jcy5jb206ODA4MA==.0\",\"name\":\"onsaddr.cn-hangzhou.mq-internal.aliyuncs.com:8080\",\"type\":\"RocketMQ\",\"isReal\":false},{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi13b3JrcHVi.1\",\"name\":\"creationtools::production-workpub\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"cGxhdGZvcm06OnByb2R1Y3Rpb24tcGxhdGZvcm0tYWNjb3VudA==.1\",\"name\":\"platform::production-platform-account\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hcGktZm9yLXdlYg==.1\",\"name\":\"community::production-community-api-for-web\",\"type\":\"SpringMVC\",\"isReal\":true},{\"id\":\"MTAwLjEwMC4yNi4xOjgwODA7MTAwLjEwMC4yNi4yOjgwODA7MTAwLjEwMC4yNS45Njo4MDgwOzEwMC4xMDAuMTcuMzo4MA==.0\",\"name\":\"100.100.26.1:8080;100.100.26.2:8080;100.100.25.96:8080;100.100.17.3:80\",\"type\":\"RocketMQ\",\"isReal\":false},{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1uZW1vLWFwaS1mb3ItYXBw.1\",\"name\":\"creationtools::production-nemo-api-for-app\",\"type\":\"SpringMVC\",\"isReal\":true}],\"calls\":[{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi13b3JrY29yZS1zZXJ2aWNl.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi13b3JrY29yZS1zZXJ2aWNl.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-ci1icDFjMjVlNWRjMWMyOGY0NjAwLnJlZGlzLnJkcy5hbGl5dW5jcy5jb206NjM3OQ==.0\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\"],\"target\":\"ci1icDFjMjVlNWRjMWMyOGY0NjAwLnJlZGlzLnJkcy5hbGl5dW5jcy5jb206NjM3OQ==.0\"},{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1uZW1vLWFwaS1mb3ItYXBw.1-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"source\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1uZW1vLWFwaS1mb3ItYXBw.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1uZW1vLXNlcnZpY2U=.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1uZW1vLXNlcnZpY2U=.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS13b3JrLXNlcnZpY2U=.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS13b3JrLXNlcnZpY2U=.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi13b3JrcHVi.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi13b3JrcHVi.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-ZGRzLWJwMTk2M2E2ODlkNjcwYzQxMTIwLm1vbmdvZGIucmRzLmFsaXl1bmNzLmNvbTozNzE3.0\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\"],\"target\":\"ZGRzLWJwMTk2M2E2ODlkNjcwYzQxMTIwLm1vbmdvZGIucmRzLmFsaXl1bmNzLmNvbTozNzE3.0\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-cGxhdGZvcm06OnByb2R1Y3Rpb24tcGxhdGZvcm0tYWNjb3VudA==.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"cGxhdGZvcm06OnByb2R1Y3Rpb24tcGxhdGZvcm0tYWNjb3VudA==.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hcGktZm9yLXdlYg==.1-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hcGktZm9yLXdlYg==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\"},{\"id\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1jcmVhdGlvbi10b29scy1hcGk=.1-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"source\":\"Y3JlYXRpb250b29sczo6cHJvZHVjdGlvbi1jcmVhdGlvbi10b29scy1hcGk=.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1iYXNlLXNlcnZpY2U=.1-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1iYXNlLXNlcnZpY2U=.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-ZGlzY292ZXJ5LmNlbnRlci5jb2RlbWFvLmNuOjgw.0\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\"],\"target\":\"ZGlzY292ZXJ5LmNlbnRlci5jb2RlbWFvLmNuOjgw.0\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-MTAwLjEwMC4yNi4xOjgwODA7MTAwLjEwMC4yNi4yOjgwODA7MTAwLjEwMC4yNS45Njo4MDgwOzEwMC4xMDAuMTcuMzo4MA==.0\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\"],\"target\":\"MTAwLjEwMC4yNi4xOjgwODA7MTAwLjEwMC4yNi4yOjgwODA7MTAwLjEwMC4yNS45Njo4MDgwOzEwMC4xMDAuMTcuMzo4MA==.0\"},{\"id\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1iYXNlLXNlcnZpY2U=.1\",\"source\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"detectPoints\":[\"CLIENT\",\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1iYXNlLXNlcnZpY2U=.1\"},{\"id\":\"VXNlcg==.0-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"source\":\"VXNlcg==.0\",\"detectPoints\":[\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\"},{\"id\":\"b25zYWRkci5jbi1oYW5nemhvdS5tcS1pbnRlcm5hbC5hbGl5dW5jcy5jb206ODA4MA==.0-Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\",\"source\":\"b25zYWRkci5jbi1oYW5nemhvdS5tcS1pbnRlcm5hbC5hbGl5dW5jcy5jb206ODA4MA==.0\",\"detectPoints\":[\"SERVER\"],\"target\":\"Y29tbXVuaXR5Ojpwcm9kdWN0aW9uLWNvbW11bml0eS1hZG1pbi1wbGF0Zm9ybQ==.1\"}]}}}";


        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject dataJsonObject = (JSONObject) jsonObject.get("data");
        JSONObject topoJsonObject = (JSONObject) dataJsonObject.get("topo");
        JSONArray nodes = (JSONArray) topoJsonObject.get("nodes");
        if (CollectionUtils.isEmpty(nodes)){
            return;
        }


        for (int i = 0; i < nodes.size(); i++) {
            JSONObject o = (JSONObject) nodes.get(i);
            if ("SpringMVC".equalsIgnoreCase(o.getString("type"))){
                String name = o.getString("name");
                int index = name.indexOf(":");
                SPRIG_MVC_LIST.add(name.substring(index+2));
            } else  if ("Redis".equalsIgnoreCase(o.getString("type"))){
                REDIS_LIST.add(o.getString("name"));
            }else  if ("MongoDB".equalsIgnoreCase(o.getString("type"))){
                MONGODB_LIST.add(o.getString("name"));
            }else  if ("RocketMQ".equalsIgnoreCase(o.getString("type"))){
                ROCKETMQ_LIST.add(o.getString("name"));
            }else  if ("Mysql".equalsIgnoreCase(o.getString("type"))){
                MYSQL_LIST.add(o.getString("name"));
            }else  if ("SpringRestTemplate".equalsIgnoreCase(o.getString("type"))){
                SPRING_REST_TEMPLATE_LIST.add(o.getString("name"));
            }
        }
        for (int i = 0; i < SPRIG_MVC_LIST.size(); i++) {
            if (i==0){
                System.out.println("SPRIG_MVC_LIST:-----");
            }
            System.out.println(SPRIG_MVC_LIST.get(i));
        }

        for (int i = 0; i < REDIS_LIST.size(); i++) {
            if (i==0){
                System.out.println("REDIS_LIST:-----");
            }
            System.out.println(REDIS_LIST.get(i));
        }

        for (int i = 0; i < MONGODB_LIST.size(); i++) {
            if (i==0){
                System.out.println("MONGODB_LIST:-----");
            }
            System.out.println(MONGODB_LIST.get(i));
        }


        for (int i = 0; i < ROCKETMQ_LIST.size(); i++) {
            if (i==0){
                System.out.println("ROCKETMQ_LIST:-----");
            }
            System.out.println(ROCKETMQ_LIST.get(i));
        }

        for (int i = 0; i < MYSQL_LIST.size(); i++) {
            if (i==0){
                System.out.println("MYSQL_LIST:-----");
            }
            System.out.println(MYSQL_LIST.get(i));
        }

        for (int i = 0; i < SPRING_REST_TEMPLATE_LIST.size(); i++) {
            if (i==0){
                System.out.println("SPRING_REST_TEMPLATE_LIST:-----");
            }
            System.out.println(SPRING_REST_TEMPLATE_LIST.get(i));
        }
    }



}

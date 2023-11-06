package com.janson.springboot.demo.test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/11/6 11:46 AM
 */
public class ServiceSetTest {

    public static void main(String[] args) {
        String service = "codecamp-marketing-client-service,\n" + "crm-center,\n" + "crp-api,\n" + "platform-account-api,\n" + "lbk-web-common,\n" + "codecamp-marketing-web-api,\n" + "codemaster-community-crm,\n" + "codemaster-codecamp-service,\n" + "crm-common,\n" + "edu-b2c-wechat-service,\n" + "lbk-web-admin,\n" + "lbk-crm-teacher-web-api,\n" + "account-api,\n" + "platform-account-api,\n" + "codecamp-marketing-web-api,\n" + "b2c-flow-admin-service,\n" + "codemaster-codecamp-service,\n" + "codecamp-marketing-client-service,\n" + "platform-order-manage-service,\n" + "account-api,\n" + "platform-account-api,\n" + "codemaster-marketing-wechat-service,\n" + "codemaster-student-api,\n" + "codecamp-marketing-web-api,\n" + "codemaster-marketing-api,\n" + "codemaster-codecamp-service,\n" + "codecamp-crm-service,\n" + "codemaster-attendance-service,\n" + "codemaster-codecamp-marketing,\n" + "creation-coconut-api,\n" + "trainer-app-api,\n" + "codemaster-marketing-api,\n" + "lbk-crm-teacher-web-api,\n" + "lion-service,\n" + "codemaster-crm-ap,\n" + "codecamp-teaching-system,\n" + "codemaster-community-crm,\n" + "codecamp-crm-service,\n" + "codemaster-report-service,\n" + "codemaster-teacher-api,\n" + "platform-account-api,\n" + "codemaster-headteacher-api,\n" + "codemaster-education-api,\n" + "tiger,\n" + "cnr-user-service,\n" + "codemaster-codecamp-service,\n" + "codemaster-crm-reception-api,\n" + "creation-cooperation-socket-service,\n" + "codemaster-student-api,\n" + "trainer-app-service,\n" + "codemaster-headteacher-service,\n" + "codemaster-markting-admin-api,\n" + "codemaster-student-app-api,\n" + "edu-community-app,\n" + "eduzone,\n" + "codemaster-attendance-service,\n" + "codemao-community-rpc,\n" + "creation-tools-api,\n" + "codemao-tanyue-mini-service,\n" + "edu-b2c-service,\n" + "codemaster-headteacher-api,\n" + "crm-rocket,\n" + "creation-cv-socket,\n" + "lbk-crm-teacher-web-api,\n" + "platform-transaction-admin-api,\n" + "community-admin-platform,\n" + "lion-service,\n" + "community-tanyue-app-api,\n" + "codemaster-crm-ap,\n" + "community-api-for-web,\n" + "creation-middle-cache-service,\n" + "b2c-flow-user-service,\n" + "community-base-service,\n" + "nemo-api-for-app,\n" + "codecamp-crm-service,\n" + "codemaster-crm-api,\n" + "nemo-admin-api,\n" + "trainer-app-service,\n" + "codemaster-crm-api,\n" + "trainer-app-service,\n" + "codemaster-codecamp-marketing,\n" + "codemaster-codecamp-service,\n" + "crm-center,\n" + "codecamp-marketing-client-service,\n" + "eduzone,\n" + "codemaster-community-crm,\n" + "platform-it-manage-admin-service\"";
        Set<String> set = new HashSet<String>(128);

        String[] split = service.split(",\n");
        System.out.println("过滤前：size:" + split.length);
        for (String s : split) {
            set.add(s);
        }

        System.out.println("过滤后：size:" + set.size());
        for (String s : set) {
            System.out.println(s);
        }

    }
}

package com.janson.springboot.demo.sentinel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/6/29 11:04 AM
 */
public class SentinelServiceTest {

    public static void main(String[] args) {
        String newServices= "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"appname\": \"message-center\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"appname\": \"codemaster-crm-center-customer\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"appname\": \"codemaster-community-crm\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"appname\": \"codemaster_headteacher_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"appname\": \"codemaster_education_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"appname\": \"codemaster-message-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"appname\": \"codemaster-report-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"appname\": \"codemao-app-admin\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"appname\": \"codemao-app-task\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 10,\n" +
                "        \"appname\": \"codemao-app-web\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 11,\n" +
                "        \"appname\": \"codemao-app-rpc\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 12,\n" +
                "        \"appname\": \"platform-accounthub\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 13,\n" +
                "        \"appname\": \"service-platform-auth\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 14,\n" +
                "        \"appname\": \"platform-account-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 15,\n" +
                "        \"appname\": \"platform-product-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 16,\n" +
                "        \"appname\": \"platform-payment-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 17,\n" +
                "        \"appname\": \"platform-shipping-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 18,\n" +
                "        \"appname\": \"platform-order-management\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 20,\n" +
                "        \"appname\": \"service-codemaster-lion\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 21,\n" +
                "        \"appname\": \"api-crm-web\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 22,\n" +
                "        \"appname\": \"codemaster-headteacher-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 23,\n" +
                "        \"appname\": \"crm-data-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 24,\n" +
                "        \"appname\": \"codemaster_crm_reception_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 25,\n" +
                "        \"appname\": \"codemaster-attendance-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 26,\n" +
                "        \"appname\": \"codemaster-teacher-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 27,\n" +
                "        \"appname\": \"codemaster-teacher-worktable-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 28,\n" +
                "        \"appname\": \"codemaster-teaching-report-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 29,\n" +
                "        \"appname\": \"cm-octopus-integrate-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 30,\n" +
                "        \"appname\": \"codemaster-classroom-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 31,\n" +
                "        \"appname\": \"codemaster-common-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 32,\n" +
                "        \"appname\": \"codemaster_attendance_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 33,\n" +
                "        \"appname\": \"codemaster_teacher_worktable_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 34,\n" +
                "        \"appname\": \"codemaster_student_app_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 35,\n" +
                "        \"appname\": \"codemaster_student_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 36,\n" +
                "        \"appname\": \"codemaster-common-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 37,\n" +
                "        \"appname\": \"service-codemaster-maolaozu\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 38,\n" +
                "        \"appname\": \"lbk-marketing-client\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 39,\n" +
                "        \"appname\": \"lbk-web-customer\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 40,\n" +
                "        \"appname\": \"ailab-mark-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 41,\n" +
                "        \"appname\": \"codemaster_codecamp_app_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 42,\n" +
                "        \"appname\": \"codemaster-codecamp-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 43,\n" +
                "        \"appname\": \"community-tanyue-app-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 44,\n" +
                "        \"appname\": \"codemaster_teacher_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 45,\n" +
                "        \"appname\": \"nct-organization-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 46,\n" +
                "        \"appname\": \"nct-staff-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 47,\n" +
                "        \"appname\": \"codemaster_codecamp_homework_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 48,\n" +
                "        \"appname\": \"nct-examination-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 49,\n" +
                "        \"appname\": \"nct-student-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 50,\n" +
                "        \"appname\": \"b2c-flow-user-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 51,\n" +
                "        \"appname\": \"edu-b2c-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 52,\n" +
                "        \"appname\": \"b2c-flow-admin-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 53,\n" +
                "        \"appname\": \"edu-b2c-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 54,\n" +
                "        \"appname\": \"lbk-crm-teacher-web-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 55,\n" +
                "        \"appname\": \"codemaster-marketing-api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 56,\n" +
                "        \"appname\": \"codemaster-markting-admin-service\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 57,\n" +
                "        \"appname\": \"codemaster_markting_admin_api\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 58,\n" +
                "        \"appname\": \"codemaster-groupbuy-service\"\n" +
                "    }\n" +
                "]";

        String oldServices = "\nmessage-center,\n" +
                "codemaster-crm-center-customer,\n" +
                "codemaster-community-crm,\n" +
                "codemaster_headteacher_api,\n" +
                "codemaster_education_api,\n" +
                "codemaster-message-service,\n" +
                "codemaster-report-service,\n" +
                "codemao-app-admin,\n" +
                "codemao-app-task,\n" +
                "codemao-app-web,\n" +
                "codemao-app-rpc,\n" +
                "platform-accounthub,\n" +
                "service-platform-auth,\n" +
                "platform-account-api,\n" +
                "platform-product-service,\n" +
                "platform-payment-service,\n" +
                "platform-shipping-service,\n" +
                "platform-order-management,\n" +
                "service-codemaster-lion,\n" +
                "api-crm-web,\n" +
                "codemaster-headteacher-service,\n" +
                "crm-data-service,\n" +
                "codemaster_crm_reception_api,\n" +
                "codemaster-attendance-service,\n" +
                "codemaster-teacher-service,\n" +
                "codemaster-teacher-worktable-service,\n" +
                "codemaster-teaching-report-service,\n" +
                "cm-octopus-integrate-service,\n" +
                "codemaster-classroom-service,\n" +
                "codemaster-common-service,\n" +
                "codemaster_attendance_api,\n" +
                "codemaster_teacher_worktable_api,\n" +
                "codemaster_student_app_api,\n" +
                "codemaster_student_api,\n" +
                "codemaster-common-api,\n" +
                "service-codemaster-maolaozu,\n" +
                "lbk-marketing-client,\n" +
                "lbk-web-customer,\n" +
                "ailab-mark-service,\n" +
                "codemaster_codecamp_app_api,\n" +
                "codemaster-codecamp-service,\n" +
                "community-tanyue-app-api,\n" +
                "codemaster_codecamp_homework_api,\n" +
                "codemaster_teacher_api,\n" +
                "nct-organization-api,\n" +
                "nct-staff-api,\n" +
                "nct-examination-service,\n" +
                "nct-student-api,\n" +
                "b2c-flow-user-service,\n" +
                "edu-b2c-service,\n" +
                "b2c-flow-admin-service,\n" +
                "edu-b2c-api,\n" +
                "lbk-crm-teacher-web-api";
        String[] split = oldServices.split("\\,");
        Set<String> oldSets = new HashSet<String>(60);
        Set<String> newSets = new HashSet<String>(16);
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            oldSets.add(s.substring(1,s.length()));
        }

        JSONArray array = JSON.parseArray(newServices);
        for (Object o : array) {
            JSONObject jsonObject = (JSONObject) o;
            String appname = jsonObject.getString("appname");
            if (oldSets.contains(appname)){
                continue;
            }
            newSets.add(appname);
        }

        for (String newSet : newSets) {
            System.out.println(newSet);
        }

    }

}

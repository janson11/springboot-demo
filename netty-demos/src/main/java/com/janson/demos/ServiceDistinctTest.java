package com.janson.demos;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/10 5:28 下午
 */
public class ServiceDistinctTest {

    private static final Set<String> set =new HashSet<String>(64);
    public static void main(String[] args) {
        String services = "\"production-auth-service\",\n" +
                "\"production-codecamp-tanyue-client\",\n" +
                "\"production-open-service-java\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-lbk-marketing-client\",\n" +
                "\"production-platform-workclub\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-platform-account-api\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-trainer-app-service\",\n" +
                "\"production-codecamp-teaching-system-cl\",\n" +
                "\"production-rocket-miao-coin-system\",\n" +
                "\"production-platform-payment-service\",\n" +
                "\"production-auth-service\",\n" +
                "\"production-rocket-ai-genie\",\n" +
                "\"production-codecamp-marketing-client-s\",\n" +
                "\"production-codecamp-rocket-client\",\n" +
                "\"production-lbk-marketing-client\",\n" +
                "\"production-codemaster-codecamp-app-api\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-codecamp-teaching-system-cl\",\n" +
                "\"production-trainer-app-service\",\n" +
                "\"production-rocket-miao-coin-system\",\n" +
                "\"production-platform-payment-service\",\n" +
                "\"production-auth-service\",\n" +
                "\"production-rocket-ai-genie\",\n" +
                "\"production-codecamp-marketing-client-s\",\n" +
                "\"production-codecamp-rocket-client\",\n" +
                "\"production-lbk-marketing-client\",\n" +
                "\"production-codemaster-codecamp-app-api\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-codemaster-marketing-api\",\n" +
                "\"production-auth-service\",\n" +
                "\"production-captcha-service\",\n" +
                "\"production-codemao-app-rpc\",\n" +
                "\"production-codemaster-groupbuy-service\",\n" +
                "\"production-codemaster-landing-service\",\n" +
                "\"production-open-service-java\",\n" +
                "\"production-platform-order-manage-service\",\n" +
                "\"production-lion-service\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-codemaster-market-service\",\n" +
                "\"production-codemaster-marketing-servic\",\n" +
                "\"production-codemaster-markting-admin-s\",\n" +
                "\"production-codemaster-maolaozu-service\",\n" +
                "\"production-platform-account-api\",\n" +
                "\"production-codemaster-attendance-servi\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-platform-order-manage-service\",\n" +
                "\"production-platform-payment-service\",\n" +
                "\"production-platform-account-api\",\n" +
                "\"production-platform-product-service\",\n" +
                "\"production-edu-exam-service\",\n" +
                "\"production-codemaster-student-api\",\n" +
                "\"production-open-service-java\",\n" +
                "\"production-codecamp-marketing-web-api\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-platform-account-api\",\n" +
                "\"production-cnr-user-service\",\n" +
                "\"production-internal-account-service\",\n" +
                "\"production-lbk-web-common\",\n" +
                "\"production-codecamp-crm-service\",\n" +
                "\"production-codecamp-teaching-system-cl\",\n" +
                "\"production-platform-payment-service\",\n" +
                "\"production-auth-service\",\n" +
                "\"production-platform-luckycat-admin-servi\",\n" +
                "\"production-open-service-java\",\n" +
                "\"production-platform-order-manage-service\",\n" +
                "\"production-codecamp-marketing-web-api\",\n" +
                "\"production-crm-center\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-lbk-marketing-client\",\n" +
                "\"production-platform-product-service\",\n" +
                "\"production-codecamp-crm-service\",\n" +
                "\"production-lbk-web-customer\",\n" +
                "\"production-lbk-web-common\",\n" +
                "\"production-open-service-java\",\n" +
                "\"production-codecamp-teaching-system-cl\",\n" +
                "\"production-platform-payment-service\",\n" +
                "\"production-codecamp-tanyue-client\",\n" +
                "\"production-codemao-app-rpc\",\n" +
                "\"production-platform-order-manage-service\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-platform-product-service\",\n" +
                "\"production-edu-center-service\",\n" +
                "\"production-codecamp-marketing-client-s\",\n" +
                "\"production-lbk-marketing-client\",\n" +
                "\"production-b2c-flow-user-service\",\n" +
                "\"production-internal-account-service\",\n" +
                "\"production-crm-common\",\n" +
                "\"production-platform-workclub\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-nemo-service\",\n" +
                "\"production-codecamp-teaching-system-cl\",\n" +
                "\"production-rocket-miao-coin-system\",\n" +
                "\"production-auth-service\",\n" +
                "\"production-codecamp-tanyue-client\",\n" +
                "\"production-edu-b2c-service\",\n" +
                "\"production-codemao-app-rpc\",\n" +
                "\"production-codemao-community-rpc\",\n" +
                "\"production-crm-center\",\n" +
                "\"production-codecamp-marketing-client-s\",\n" +
                "\"production-platform-account\",\n" +
                "\"production-community-tanyue-app-api\",\n" +
                "\"production-platform-workclub\",\n" +
                "\"production-nemo-api-for-app\",\n" +
                "\"production-codemaster-codecamp-service\",\n" +
                "\"production-codecamp-marketing-client-s\"";
        String[] split = services.split(",");
        System.out.println("去重前service size："+ split.length);
        for (int i = 0; i < split.length; i++) {
            set.add(split[i]);
        }
        System.out.println("去重后service size："+ set.size());

        for (String s : set) {
            s= s.substring(2,s.length()-1);
            System.out.println(s);
        }

    }
}

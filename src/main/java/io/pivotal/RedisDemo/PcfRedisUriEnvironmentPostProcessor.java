package io.pivotal.RedisDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PcfRedisUriEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String host = getPropertyFromVcapServices("hostname");
        if (host == null) {
            System.out.println("hostname is null");
            return;
        }

        String password = getPropertyFromVcapServices("password");
        if (password == null) {
            System.out.println("password is null");
            return;
        }

        String port = getPropertyFromVcapServices("port");
        if (port == null) {
            System.out.println("port is null");
            return;
        }

        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.redis.host", host);
        properties.put("spring.redis.password", password);
        properties.put("spring.redis.port", port);
        MapPropertySource propertySource = new MapPropertySource("pcf", properties);
        environment.getPropertySources().addFirst(propertySource);
    }

    private static String getPropertyFromVcapServices(String property) {
        try {
            String vcapJson = System.getenv("VCAP_SERVICES");
            if (vcapJson == null) {
                return null;
            }
            Map<String, List<Map<String,
                    Map<String, String>>>> vcap = new ObjectMapper().readValue(vcapJson, Map.class);
            return vcap.get("rediscloud").get(0).get("credentials").get(property);
        } catch(Exception e) {
            return null;
        }
    }

}
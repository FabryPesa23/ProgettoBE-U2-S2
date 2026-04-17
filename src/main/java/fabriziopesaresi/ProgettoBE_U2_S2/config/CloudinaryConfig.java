package fabriziopesaresi.ProgettoBE_U2_S2.config;

import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary uploader(
            @Value("${cloudinary.name}") String name,
            @Value("${cloudinary.apikey}") String key,
            @Value("${cloudinary.secret}") String secret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", key);
        config.put("api_secret", secret);
        return new Cloudinary(config);
    }
}
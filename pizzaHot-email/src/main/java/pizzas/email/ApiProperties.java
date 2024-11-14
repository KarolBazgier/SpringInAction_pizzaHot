package pizzas.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "pizzahot.api")
@Component
public class ApiProperties {
    private String url;
}

package io.mend.sast.conf;

import io.mend.sast.controller.ValidationJaxJakartaController;
import io.mend.sast.controller.XssJaxController;
import io.mend.sast.controller.cwe.cwe113_jax;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(cwe113_jax.class);
        register(ValidationJaxJakartaController.class);
        register(XssJaxController.class);
    }
}
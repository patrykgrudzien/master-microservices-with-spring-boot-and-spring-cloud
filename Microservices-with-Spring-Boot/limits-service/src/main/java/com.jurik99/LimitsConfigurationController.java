package com.jurik99;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jurik99.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {

    private final Configuration configuration;

    public LimitsConfigurationController(final Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsFromConfigurations() {
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}

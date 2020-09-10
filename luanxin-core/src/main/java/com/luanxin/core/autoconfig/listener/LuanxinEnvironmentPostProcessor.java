package com.luanxin.core.autoconfig.listener;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.ResourceUtils;

public class LuanxinEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    public static final int DEFAULT_OEDER = ConfigFileApplicationListener.DEFAULT_ORDER - 1;

    private static final String CONFIG_HOME = "LUANXIN_HOME";

    private static final String CONFIG_FILE_SUFFIX = ".properties";

    // springCloud 配置文件bootstrap
    private static final String BOOTSTRAP_FILE_NAME = "bootstrap";

    private static final String CONFIG_LOCATION = ConfigFileApplicationListener.CONFIG_ADDITIONAL_LOCATION_PROPERTY;


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (environment.getPropertySources().contains(BOOTSTRAP_FILE_NAME)) {
            return;
        }
        String location = environment.getProperty(CONFIG_LOCATION);
        String configFiles = location;
        if (StringUtils.isEmpty(location)) {
            String configHome = environment.getProperty(CONFIG_HOME);
            if (StringUtils.isEmpty(configHome)) {
                throw new IllegalArgumentException("Can't find " + CONFIG_HOME);
            }
            else {
                File file = new File(configHome);
                if (!file.isDirectory()) {
                    throw new IllegalArgumentException(configHome + "  is not directory!");
                }
                configFiles = file.getAbsolutePath() + File.separator + "etc";
            }
        }
        configFiles = resolveConfigFiles(configFiles);
        addPropertyConfig(environment, configFiles);
    }

    private void addPropertyConfig(ConfigurableEnvironment environment, String configFiles) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(CONFIG_LOCATION, configFiles);
        MapPropertySource propertySource = new MapPropertySource(CONFIG_LOCATION, configMap);

        configMap.put(CONFIG_LOCATION, configFiles);

        MutablePropertySources source = environment.getPropertySources();
        if (source.contains(CONFIG_LOCATION)) {
            source.replace(CONFIG_LOCATION, propertySource);
        }
        else {
            source.addLast(propertySource);
        }

    }

    private String resolveConfigFiles(String configFiles) {
        String[] files = configFiles.split(",");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(files).forEach(filePath -> {
            if (filePath.contains("$") || ResourceUtils.isUrl(filePath)) {
                sb.append(filePath).append(",");
                return;
            }
            File file = new File(filePath);
            if (file.isDirectory()) {
                File[] fileList = file.listFiles(child -> child.isFile() && child.getName().endsWith(CONFIG_FILE_SUFFIX));
                if (fileList == null || fileList.length <= 0) {
                    sb.append(filePath).append(",");
                }
                else {
                    Arrays.stream(fileList).forEach(f -> sb.append(f.getAbsolutePath()).append(","));
                }
            }
            else {
                sb.append(filePath).append(",");
            }
        });
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public int getOrder() {
        return DEFAULT_OEDER;
    }
}

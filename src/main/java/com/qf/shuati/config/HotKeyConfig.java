package com.qf.shuati.config;

import com.jd.platform.hotkey.client.ClientStarter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "hotkey")
public class HotKeyConfig {

    /**
     * Etcd 服务器完整地址
     */
    private String etcdServer;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 本地缓存最大数量
     */
    private int caffeineSize = 1000;

    /**
     * 批量推送key的时间间隔
     */
    private long pushPeriod = 1000L;

    /**
     * 初始化hotkey
     */
    @Bean
    public void initHotKey() {
        ClientStarter.Builder builder = new ClientStarter.Builder();
        ClientStarter starter = builder.setEtcdServer(etcdServer)
                .setAppName(appName)
                .setCaffeineSize(caffeineSize)
                .setPushPeriod(pushPeriod)
                .build();
        starter.startPipeline();
    }
}

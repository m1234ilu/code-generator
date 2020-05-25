package cn.generate.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * Table的前缀
 *
 * @author zycstart
 * @create 2020-05-10 13:04
 */
@Configuration
@Data
public class TablePrefixConf {
    /**
     * 移除表前缀
     */
    private String tableRemovePrefixes;
}

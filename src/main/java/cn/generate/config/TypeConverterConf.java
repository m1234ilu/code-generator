package cn.generate.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * SQL类型和java类型替换规则
 *
 * @author zycstart
 * @create 2020-05-10 12:52
 */
@Configuration
@Data
public class TypeConverterConf {
    @Value("${VARCHAR}")
    private String VARCHAR;
    @Value("${BIGINT}")
    private String BIGINT;
    @Value("${INT}")
    private String INT;
    @Value("${DATE}")
    private String DATE;
    @Value("${DATETIME}")
    private String DATETIME;
    @Value("${DOUBLE}")
    private String DOUBLE;
    @Value("${TEXT}")
    private String TEXT;
    @Value("${VARCHAR2}")
    private String VARCHAR2;
    @Value("${NVARCHAR2}")
    private String NVARCHAR2;

}

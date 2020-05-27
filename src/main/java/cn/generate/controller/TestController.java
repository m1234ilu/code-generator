package cn.generate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zycstart
 * @create 2020-05-27 21:50
 */
@RestController
@Api(tags = "测试接口")
@RequestMapping("/test")
public class TestController {
    private Map<Integer, String> map = new HashMap<>();

    public TestController() {
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        map.put(4, "赵六");
    }

    @ApiOperation(value = "查询用户集合",notes = "查询用户集合")
    @GetMapping("/listUsers")
    public Map getUsers(){
        return map;
    }
}

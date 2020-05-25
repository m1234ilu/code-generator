import cn.generate.App;
import cn.generate.entity.Smth1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zycstart
 * @create 2020-05-09 22:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestDB {
    @Autowired    // 自动注入，spring boot会帮我们实例化一个对象
    private JdbcTemplate jdbcTemplate;   // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查

    @Test
    public void mySqlTest() {
        String sql = "select id,ver,content from smth1";
        List<Smth1> students = jdbcTemplate.query(sql, (resultSet, i) -> {
            Smth1 smth1 = new Smth1();
            smth1.setId(resultSet.getInt("id"));
            smth1.setVer(resultSet.getInt("ver"));
            smth1.setContent(resultSet.getString("content"));
            return smth1;
        });

        System.out.println("查询成功");
        for (Smth1 s : students) {
            System.out.println(s);
        }
    }

}

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.pool.DruidDataSource;
import com.luanxin.WebApplication;
import com.netflix.discovery.converters.Auto;

@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class DemoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DruidDataSource dataSource;

    @BeforeClass
    public static void setup() {
        System.setProperty("LUANXIN_HOME", "C:\\workspace\\intellij project\\simple-web\\LUANXIN_HOME");
    }

    @Test
    public void test() throws SQLException {
        dataSource.getConnection();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        maps.forEach(System.out::println);
    }

}

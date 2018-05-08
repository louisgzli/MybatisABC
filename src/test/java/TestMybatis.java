import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import model.Person;

/**
 * @author: blissolee
 * @date:2018年5月4日 下午4:08:02
 * @version :
 * 
 */
public class TestMybatis {
	SqlSession sqlSession;

	@Before
	public void createSql() throws IOException {

		/**
		 * 1、获得 SqlSessionFactory 2、获得 SqlSession 3、调用在 mapper 文件中配置的 SQL 语句
		 */
		String resource = "sqlMapConfig.xml"; // 定位核心配置文件
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		sqlSession = sqlSessionFactory.openSession(); // 获取到 SqlSession
		System.out.println("0===========");
	}

	@Test
	public void testSelect() throws IOException {

		// 调用 mapper 中的方法：命名空间 + id
		List<Person> personList = sqlSession.selectList("UserMapper.findAll");

		for (Person p : personList) {
			System.out.println(p);
		}
		System.out.println("1===========");
	}

	@Test
	public void testInsert() {

		Person p = new Person();

		p.setName("gavin");
		p.setAge(12);

		sqlSession.insert("UserMapper.insert", p);
		sqlSession.commit();
		System.out.println("2===========");// 默认是不自动提交，必须手工提交
	}

	@Test
	public void testUpdate() {
		Person p = sqlSession.selectOne("UserMapper.get", 1); // 获得 id=2 的记录
		p.setName("jane");
		p.setAge(16);

		// sqlSession.insert("yeepay.payplus.mapper.UserMapper.update", p);
		sqlSession.update("UserMapper.update", p);
		sqlSession.commit();
		System.out.println("3===========");// 默认是不自动提交，必须手工提交
	}

	@Test
	public void testDeleteById() {
		sqlSession.delete("UserMapper.deleteById", 2);
		sqlSession.commit(); // 默认是不自动提交，必须手工提交
		System.out.println("4===========");
	}

	@Test
	public void testDeleteArray() { // 批量删除

		sqlSession.delete("UserMapper.deleteArray", new Integer[] { 3, 4 });
		sqlSession.commit(); // 默认是不自动提交，必须手工提交

	}

	@Test
	public void testDeleteList() { // 批量删除

		List<Integer> personList = new ArrayList<Integer>();

		personList.add(2);
		personList.add(3);
		personList.add(4);

		sqlSession.delete("UserMapper.deleteList", personList);
		sqlSession.commit(); // 默认是不自动提交，必须手工提交
	}
	@Test
    public void testDeleteMap() {   // 批量删除
       

        Map<String, Object> map = new HashMap<String, Object>();

        /**
         * 在通过以下两条语句测试本方法时，实际上仅删除了 id = 3、4、5 中 age = 18 的记录
         */
        map.put("ids", new Integer[]{2, 3, 4});
        map.put("age",18);

        sqlSession.delete("UserMapper.deleteMap", map);
        sqlSession.commit();            //默认是不自动提交，必须手工提交
    }
}

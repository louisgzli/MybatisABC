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
 * @date:2018��5��4�� ����4:08:02
 * @version :
 * 
 */
public class TestMybatis {
	SqlSession sqlSession;

	@Before
	public void createSql() throws IOException {

		/**
		 * 1����� SqlSessionFactory 2����� SqlSession 3�������� mapper �ļ������õ� SQL ���
		 */
		String resource = "sqlMapConfig.xml"; // ��λ���������ļ�
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		sqlSession = sqlSessionFactory.openSession(); // ��ȡ�� SqlSession
		System.out.println("0===========");
	}

	@Test
	public void testSelect() throws IOException {

		// ���� mapper �еķ����������ռ� + id
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
		System.out.println("2===========");// Ĭ���ǲ��Զ��ύ�������ֹ��ύ
	}

	@Test
	public void testUpdate() {
		Person p = sqlSession.selectOne("UserMapper.get", 1); // ��� id=2 �ļ�¼
		p.setName("jane");
		p.setAge(16);

		// sqlSession.insert("yeepay.payplus.mapper.UserMapper.update", p);
		sqlSession.update("UserMapper.update", p);
		sqlSession.commit();
		System.out.println("3===========");// Ĭ���ǲ��Զ��ύ�������ֹ��ύ
	}

	@Test
	public void testDeleteById() {
		sqlSession.delete("UserMapper.deleteById", 2);
		sqlSession.commit(); // Ĭ���ǲ��Զ��ύ�������ֹ��ύ
		System.out.println("4===========");
	}

	@Test
	public void testDeleteArray() { // ����ɾ��

		sqlSession.delete("UserMapper.deleteArray", new Integer[] { 3, 4 });
		sqlSession.commit(); // Ĭ���ǲ��Զ��ύ�������ֹ��ύ

	}

	@Test
	public void testDeleteList() { // ����ɾ��

		List<Integer> personList = new ArrayList<Integer>();

		personList.add(2);
		personList.add(3);
		personList.add(4);

		sqlSession.delete("UserMapper.deleteList", personList);
		sqlSession.commit(); // Ĭ���ǲ��Զ��ύ�������ֹ��ύ
	}
	@Test
    public void testDeleteMap() {   // ����ɾ��
       

        Map<String, Object> map = new HashMap<String, Object>();

        /**
         * ��ͨ���������������Ա�����ʱ��ʵ���Ͻ�ɾ���� id = 3��4��5 �� age = 18 �ļ�¼
         */
        map.put("ids", new Integer[]{2, 3, 4});
        map.put("age",18);

        sqlSession.delete("UserMapper.deleteMap", map);
        sqlSession.commit();            //Ĭ���ǲ��Զ��ύ�������ֹ��ύ
    }
}



import java.io.Serializable;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pqpo.utils.redis.datasource.RedisTemplate;
import com.pqpo.utils.redis.serialize.AbstractRedisSerializer;
import com.pqpo.utils.redis.serialize.JsonRedisSerializer;


@SuppressWarnings("unchecked")
public class RedisTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		RedisTemplate<String,User> redisTemplate = (RedisTemplate<String, User>) context.getBean("redisTemplate");
		AbstractRedisSerializer<User> serialize = new JsonRedisSerializer<>(User.class);
		redisTemplate.setValueSerializeTranscoder(serialize);
//		User u = new User();
//		u.setName("QiuLinmin");
//		u.setAge(24);
//		u.setScore(120);
		User u1 = redisTemplate.get("QiuLinmin");
		System.out.println(u1.getName());
		System.out.println(u1.getAge());
		System.out.println(u1.getScore());
		redisTemplate.disconnect();
		context.close();
	}
	
	public static class User implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private int age;
		private int score;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		
	}
}

package fan.config;

import grey.fable.base.text.StringUtils;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Configuration
 *
 * @author Fan
 * @since 2024/2/22 14:24
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 字符串序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // JSON 序列化
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // 默认 Key-Value 序列化类型
        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        // 重设 Key 的序列化类型
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        return redisTemplate;
    }

    @Bean
    public RedissonClient redissonClient(RedisProperties properties) {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec());

        String address = StringUtils.concat("redis://", properties.getHost(), ":" + properties.getPort());
        config.useSingleServer()
                .setAddress(address)
                .setPassword(properties.getPassword())
                .setDatabase(properties.getDatabase());
        return Redisson.create(config);
    }
}
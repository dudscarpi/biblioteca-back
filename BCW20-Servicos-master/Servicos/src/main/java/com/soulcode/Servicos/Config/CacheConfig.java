package com.soulcode.Servicos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class CacheConfig {
    // converter de json para redis e vice-versa
    private final RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {// customizar a config padrão do redis cache
        return RedisCacheConfiguration
                .defaultCacheConfig() // customizar informações padrões
                .entryTtl(Duration.ofMinutes(1)) // todos os caches tera 1 min por padrão (tempo de vida)
                .disableCachingNullValues() // não salva valores nulos
                .serializeValuesWith(serializationPair); // converte do redis p/ json e vice-versa
    }

}

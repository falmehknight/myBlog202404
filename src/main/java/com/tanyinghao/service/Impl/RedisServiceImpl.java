package com.tanyinghao.service.Impl;

import com.tanyinghao.service.RedisService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisServiceImpl
 * @Description redisService的实现类
 * @Author 谭颍豪
 * @Date 2024/5/3 17:23
 * @Version 1.0
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Override
    public Boolean setExpire(String key, long timeout, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Collection<String> getKeys(String pattern) {
        return null;
    }

    @Override
    public Boolean hasKey(String key) {
        return null;
    }

    @Override
    public <T> void setObject(String key, T value) {

    }

    @Override
    public <T> void setObject(String key, T value, long timeout, TimeUnit timeUnit) {

    }

    @Override
    public <T> T getObject(String key) {
        return null;
    }

    @Override
    public Boolean deleteObject(String key) {
        return null;
    }

    @Override
    public Long deleteObject(List<String> keys) {
        return null;
    }

    @Override
    public Long incr(String key, long delta) {
        return null;
    }

    @Override
    public Long decr(String key, long delta) {
        return null;
    }

    @Override
    public <T> void setHash(String key, String hashKey, T value) {

    }

    @Override
    public <T> Boolean setHash(String key, String hashKey, T value, long timeout, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public <T> void setHashAll(String key, Map<String, T> map) {

    }

    @Override
    public <T> Boolean setHashAll(String key, Map<String, T> map, long timeout, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public <T> T getHash(String key, String hashKey) {
        return null;
    }

    @Override
    public <T> Map<String, T> getHashAll(String key) {
        return null;
    }

    @Override
    public <T> void deleteHash(String key, T... hashKeys) {

    }

    @Override
    public Boolean hasHashValue(String key, String hashKey) {
        return null;
    }

    @Override
    public Long incrHash(String key, String hashKey, Long delta) {
        return null;
    }

    @Override
    public Long decrHash(String key, String hashKey, Long delta) {
        return null;
    }

    @Override
    public <T> Long setList(String key, T value) {
        return null;
    }

    @Override
    public <T> Long setList(String key, T value, long timeout, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public <T> Long setListAll(String key, T... values) {
        return null;
    }

    @Override
    public <T> Long setListAll(String key, long timeout, TimeUnit timeUnit, T... values) {
        return null;
    }

    @Override
    public <T> List<T> getList(String key, long start, long end) {
        return null;
    }

    @Override
    public <T> T getListByIndex(String key, long index) {
        return null;
    }

    @Override
    public Long getListSize(String key) {
        return null;
    }

    @Override
    public <T> Long deleteList(String key, long count, T value) {
        return null;
    }

    @Override
    public <T> Long setSet(String key, T... values) {
        return null;
    }

    @Override
    public <T> Long setSet(String key, long timeout, TimeUnit timeUnit, T... values) {
        return null;
    }

    @Override
    public <T> Set<T> getSet(String key) {
        return null;
    }

    @Override
    public <T> Long deleteSet(String key, T... values) {
        return null;
    }

    @Override
    public <T> Boolean hasSetValue(String key, T value) {
        return null;
    }

    @Override
    public Long getSetSize(String key) {
        return null;
    }

    @Override
    public <T> Double incrZet(String key, T value, Double score) {
        return null;
    }

    @Override
    public <T> Double decrZet(String key, T value, Double score) {
        return null;
    }

    @Override
    public <T> Long deleteZetScore(String key, T... values) {
        return null;
    }

    @Override
    public Map<Object, Double> zReverseRangeWithScore(String key, long start, long end) {
        return null;
    }

    @Override
    public <T> Double getZsetScore(String key, T value) {
        return null;
    }

    @Override
    public Map<Object, Double> getZsetAllScore(String key) {
        return null;
    }
}

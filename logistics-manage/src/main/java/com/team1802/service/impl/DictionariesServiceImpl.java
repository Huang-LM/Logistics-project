package com.team1802.service.impl;

import com.team1802.dao.DictionariesDao;
import com.team1802.dto.DictionariesAddDTO;
import com.team1802.dto.DictionariesUpdateDTO;
import com.team1802.pojo.Dictionaries;
import com.team1802.service.DictionariesService;
import com.team1802.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictionariesServiceImpl implements DictionariesService {
    @Resource
    DictionariesDao dictionariesDao;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean addDictionaries(DictionariesAddDTO dto) {
        int result = dictionariesDao.addDictionaries(new Dictionaries()
                .setName(dto.getName())
                .setParentId(dto.getParentId())
                .setCreateTime(dto.getCreateTime()));
        return result > 0;
    }

    @Override
    public boolean deleteDictionaries(int id) {
        return dictionariesDao.deleteDictionaries(id) > 0;
    }

    @Override
    public List<Dictionaries> findDictionaries() {
        String key = "Dict_0";
            boolean hasKey = redisUtil.hasKey(key);
            if (hasKey) {
                List<Dictionaries> dicts = (List<Dictionaries>) redisUtil.get(key);
                System.out.println("-------------从Redis中查询----------------");
                return dicts;
            } else {
                List<Dictionaries> dicts = dictionariesDao.findDictionaries();
                // 如果查找的用户不为空，则写入缓存
                if (dicts != null) {
                    System.out.println("------------写入缓存---------------------");
                    //写入缓存
                    redisUtil.set(key, dicts, 10);
                }
            return dicts;
        }
    }

    @Override
    public boolean updateDictionaries(DictionariesUpdateDTO dto) {
        int result = dictionariesDao.updateDictionaries(new Dictionaries()
                .setId(dto.getId())
                .setName(dto.getName())
                .setParentId(dto.getParentId()));
        return result > 0;
    }

    @Override
    public List<Dictionaries> findSonByName(String name) {
        String key = "Dict_" + name ;
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisUtil.hasKey(key);
        if(hasKey){
            long start = System.currentTimeMillis();
            List<Dictionaries> dicts = (List<Dictionaries>) redisUtil.get(key);
            long end = System.currentTimeMillis();
            System.out.println("查询redis花费的时间是:" + (end - start)+"ms");
            System.out.println("从缓存中获取数据:"+name);
            System.out.println("-----------------------------");
            return dicts;
        }else{
            long start = System.currentTimeMillis();
            List<Dictionaries> dicts = dictionariesDao.findSonByName(name);
            long end = System.currentTimeMillis();
            // 如果查找的用户不为空，则写入缓存
            if (dicts != null){
                System.out.println("查询MySQL花费的时间是:" + (end - start)+"ms");
                System.out.println("查询数据库获取数据:"+name);
                System.out.println("------------写入缓存---------------------");
                //写入缓存
                redisUtil.set(key,dicts,10);
            }
            return dicts;
        }

//        List<Dictionaries> dictSon = dictionariesDao.findSonByName(name);
//        return dictSon;
    }

    @Override
    public List<Dictionaries> findSonById(int id) {
         String key = "Dict_" + id ;
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisUtil.hasKey(key);
        if(hasKey){
            long start = System.currentTimeMillis();
            List<Dictionaries> dicts = (List<Dictionaries>) redisUtil.get(key);
            long end = System.currentTimeMillis();
            System.out.println("查询redis花费的时间是:" + (end - start)+"ms");
            System.out.println("从缓存中获取数据:"+id);
            System.out.println("-----------------------------");
            return dicts;
        }else{
            long start = System.currentTimeMillis();
            List<Dictionaries> dicts = dictionariesDao.findSonById(id);
            long end = System.currentTimeMillis();
            // 如果查找的用户不为空，则写入缓存
            if (dicts != null){
                System.out.println("查询MySQL花费的时间是:" + (end - start)+"ms");
                System.out.println("查询数据库获取数据:"+id);
                System.out.println("------------写入缓存---------------------");
                //写入缓存
                redisUtil.set(key,dicts,10);
            }
            return dicts;
        }
//        List<Dictionaries> dicts = dictionariesDao.findSonById(id);
//        return dicts;
    }
}

package com.team1802.service;

import com.team1802.dto.DictionariesAddDTO;
import com.team1802.dto.DictionariesUpdateDTO;
import com.team1802.pojo.Dictionaries;

import java.util.List;

public interface DictionariesService {
    //增
    boolean addDictionaries(DictionariesAddDTO dto);
    //删
    boolean deleteDictionaries(int id);
    //查
    List<Dictionaries> findDictionaries();
    //更改
    boolean updateDictionaries(DictionariesUpdateDTO dto);
    //查找某数据名的子id数据列表
    List<Dictionaries> findSonByName(String name);
    //查找某个id下对应的子id的数据列表
    List<Dictionaries> findSonById(int id);

}

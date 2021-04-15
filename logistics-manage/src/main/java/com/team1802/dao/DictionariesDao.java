package com.team1802.dao;

import com.team1802.dto.DictionariesAddDTO;
import com.team1802.pojo.Dictionaries;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictionariesDao {

    @Insert("Insert sys_dictionaries (name,parentId,createTime) values (#{dictionaries.name},#{dictionaries.parentId},#{dictionaries.createTime})")
    int addDictionaries(@Param("dictionaries")Dictionaries dictionaries);

    //删
    @Delete("Delete FROM sys_dictionaries where id= #{id}")
    int deleteDictionaries(@Param("id") int id);

    //修改
    @Update("Update sys_dictionaries set name = #{dictionaries.name}, parentId = #{dictionaries.parentId} where id=#{dictionaries.id}")
    int updateDictionaries(@Param("dictionaries") Dictionaries dictionaries);

    //查找所有父id为0的数据列表
    @Select("Select * from sys_dictionaries where parentId = 0")
    List<Dictionaries> findDictionaries();

    //查找某个id下对应的子id的数据列表
    //查找所有父id为0的数据列表
    @Select("Select * from sys_dictionaries where parentId = #{id}")
    List<Dictionaries> findSonById(@Param("id") int id);

    //查找某数据名的子id数据列表
    @Select("Select * from sys_dictionaries where parentId =(Select id from sys_dictionaries where name = #{name})")
    List<Dictionaries> findSonByName(@Param("name") String name);


}

package com.team1802.dao;

import com.team1802.dto.*;
import com.team1802.pojo.LogisticsInfo;
import com.team1802.pojo.State;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LogisticsInfoDao {
    @Select("Insert sys_logistics(logistics_way,mailing_type,mailing_name,mailing_phone,mailing_address,shipping_name,shipping_phone,shipping_address,createTime,note) values (#{logisticsInfo.logistics_way},#{logisticsInfo.mailing_type},#{logisticsInfo.mailing_name},#{logisticsInfo.mailing_phone},#{logisticsInfo.mailing_address},#{logisticsInfo.shipping_name},#{logisticsInfo.shipping_phone},#{logisticsInfo.shipping_address},#{logisticsInfo.createTime},#{logisticsInfo.note}); Select @@identity")
    int addLogisticsInfo(@Param("logisticsInfo") LogisticsInfo logisticsInfo);

    @Update("UPDATE sys_logistics SET verify = 1 WHERE id = #{dto.id}")
    int agreeApply(@Param("dto") LogisticsInfoAgreeApplyDTO dto);

    @Update("Update sys_logistics SET verify = 2 WHERE id = #{dto.id}")
    int refuseApply(@Param("dto") LogisticsInfoRefuseApplyDTO dto);

    @Update("Update sys_logistics set logistics_number = #{logistics_number} Where id = #{id}")
    int fillLogisticsNumber(@Param("id") int id,@Param("logistics_number") int logistics_number);

    @Insert("Insert sys_user_logistics(logistics_id,user_id) values(#{logistics_id},#{user_id})")
    int addUserLogistics(@Param("logistics_id") int logistics_id,@Param("user_id") int user_id);

    @Select("Select * From sys_logistics LIMIT #{limit} OFFSET #{offset}")
    List<LogisticsInfo> listLogistics(@Param("limit") int limit, @Param("offset") int offset);

    @Select("Select count(*) From sys_logistics")
    int getCount();

    @Select("Select * From sys_logistics Where id In (Select logistics_id From sys_user_logistics Where user_id = #{user_id})")
    List<LogisticsInfo> getLogisticsInfo(@Param("user_id") int user_id);

    @Select("Select * From sys_logistics Where logistics_number = #{logistics_number}")
    LogisticsInfo getLogisticsInfoByNumber(@Param("logistics_number") int logistics_number);

    @Delete("Delete From sys_logistics Where id = #{id}")
    int deleteLogistics(@Param("id") int id);

    @Select("Select * From sys_state Where id in (Select state_id From sys_logistics_state Where logistics_id = #{id})")
    ArrayList<State> getState(@Param("id") int id);

    @Select("Select createTime From sys_logistics_state Where logistics_id = #{id}")
    ArrayList<Timestamp> getStateTime(@Param("id") int id);

    @Insert("Insert sys_logistics_state(state_id,logistics_id,createTime) values (#{dto.state_id},#{dto.logistics_id},#{dto.create_time})")
    int addState(@Param("dto") LogisticsStateAddDTO dto);

    @Update("Update sys_logistics Set verify = #{dto.verify},logistics_number = #{dto.logistics_number},logistics_way = #{dto.logistics_way},logistics_way_number = #{dto.logistics_way_number},mailing_type = #{dto.mailing_type},mailing_name = #{dto.mailing_name},mailing_phone = #{dto.mailing_phone},mailing_address = #{dto.mailing_address},shipping_name = #{dto.shipping_name},shipping_phone = #{dto.shipping_phone},shipping_address = #{dto.shipping_address},note = #{dto.note},shipping_time = #{dto.shipping_time} Where id = #{dto.id}")
    int updateLogisticsInfo(@Param("dto") LogisticsInfoUpdateDTO dto);

    @Select("Select * From sys_logistics Where (id=#{dto.id} or #{dto.id} is null) And (verify=#{dto.verify} or #{dto.verify} is null) And (logistics_way=#{dto.logistics_way} or #{dto.logistics_way} = '') And (logistics_number=#{dto.logistics_number} or #{dto.logistics_number} is null) And (logistics_way_number=#{dto.logistics_way_number} or #{dto.logistics_way_number} = '') And (mailing_type=#{dto.mailing_type} or #{dto.mailing_type} = '') And (mailing_name=#{dto.mailing_name} or #{dto.mailing_name} = '') And (mailing_phone=#{dto.mailing_phone} or #{dto.mailing_phone} = '') And (mailing_address=#{dto.mailing_address} or #{dto.mailing_address} = '') And (shipping_name=#{dto.shipping_name} or #{dto.shipping_name} = '') And (shipping_phone=#{dto.shipping_phone} or #{dto.shipping_phone} = '') And (shipping_address=#{dto.shipping_address} or #{dto.shipping_address} = '') And (note=#{dto.note} or #{dto.note} = '') And (shipping_time=#{dto.shipping_time} or #{dto.shipping_time} is null)")
    List<LogisticsInfo> selectLogistics(@Param("dto") LogisticsSelectDTO dto);

    @Select("Select * From sys_state")
    List<State> getAllState();
}

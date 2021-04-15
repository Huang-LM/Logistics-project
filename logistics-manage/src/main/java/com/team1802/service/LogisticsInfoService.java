package com.team1802.service;

import com.team1802.dto.*;
import com.team1802.pojo.LogisticsInfo;
import com.team1802.pojo.State;
import com.team1802.vo.ListVO;

import java.text.ParseException;
import java.util.List;


public interface LogisticsInfoService {
    int applyLogisticsInfo(LogisticsInfoAddDTO dto);

    boolean agreeApply(LogisticsInfoAgreeApplyDTO dto);

    boolean refuseApply(LogisticsInfoRefuseApplyDTO dto);

    boolean addUserLogistics(int logistics_id,int user_id);

    ListVO<LogisticsInfo> listLogistics(LogisticsListDTO dto);

    List<LogisticsInfo> getLogisticsInfo(UserGetLogisticsDTO dto);

    LogisticsInfo getLogisticsByNumber(UserGetLogisticsByNumberDTO dto);

    boolean deleteLogistics(LogisticsDeleteDTO dto);

    List<State> getLogisticsState(LogisticsGetStateDTO dto);

    boolean addLogisticsState(LogisticsStateAddDTO dto);

    boolean updateLogisticsInfo(LogisticsInfoUpdateDTO dto);

    List<LogisticsInfo> selectLogistics(LogisticsSelectDTO dto);

    List<State> getAllState();
}

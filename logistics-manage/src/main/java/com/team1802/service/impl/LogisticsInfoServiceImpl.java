package com.team1802.service.impl;

import com.team1802.dao.LogisticsInfoDao;
import com.team1802.dto.*;
import com.team1802.pojo.LogisticsInfo;
import com.team1802.pojo.State;
import com.team1802.service.LogisticsInfoService;
import com.team1802.vo.ListVO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Lazy
@Service
public class LogisticsInfoServiceImpl implements LogisticsInfoService {
    @Resource
    private LogisticsInfoDao logisticsInfoDao;

    @Override
    public int applyLogisticsInfo(LogisticsInfoAddDTO dto) {
        LogisticsInfo logisticsInfo = new LogisticsInfo()
                .setMailing_name(dto.getMailing_name())
                .setShipping_name(dto.getShipping_name())
                .setLogistics_way(dto.getLogistics_way())
                .setMailing_address(dto.getMailing_address())
                .setMailing_phone(dto.getMailing_phone())
                .setMailing_type(dto.getMailing_type())
                .setShipping_address(dto.getShipping_address())
                .setShipping_phone(dto.getShipping_phone())
                .setNote(dto.getNote())
                .setCreateTime(dto.getCreateTime());
        return logisticsInfoDao.addLogisticsInfo(logisticsInfo);
    }

    @Override
    public boolean agreeApply(LogisticsInfoAgreeApplyDTO dto) {
        int result1 = logisticsInfoDao.agreeApply(dto);
        int number = new Long(System.currentTimeMillis()).intValue();
        int result2 = logisticsInfoDao.fillLogisticsNumber(dto.getId(),number);
        int result3 = logisticsInfoDao.addState(new LogisticsStateAddDTO().setState_id(1).setLogistics_id(number).setCreate_time(new Timestamp(System.currentTimeMillis())));
        return  result1 * result2 * result3 > 0;
    }

    @Override
    public boolean refuseApply(LogisticsInfoRefuseApplyDTO dto) {
        return logisticsInfoDao.refuseApply(dto) > 0;
    }

    @Override
    public boolean addUserLogistics(int logistics_id,int user_id) {
        return logisticsInfoDao.addUserLogistics(logistics_id,user_id) > 0;
    }

    @Override
    public ListVO<LogisticsInfo> listLogistics(LogisticsListDTO dto) {
        int page = dto.getPage();
        int pageSize = dto.getPageSize();
        List<LogisticsInfo> logisticsInfos = logisticsInfoDao.listLogistics(pageSize,page * pageSize);
        ListVO<LogisticsInfo> logisticsInfoListVO = new ListVO<LogisticsInfo>()
                .setPage(page)
                .setPageSize(pageSize)
                .setList(logisticsInfos)
                .setTotal(logisticsInfoDao.getCount());
        return logisticsInfoListVO;
    }

    @Override
    public List<LogisticsInfo> getLogisticsInfo(UserGetLogisticsDTO dto) {
        return logisticsInfoDao.getLogisticsInfo(dto.getUser_id());
    }

    @Override
    public LogisticsInfo getLogisticsByNumber(UserGetLogisticsByNumberDTO dto) {
        return logisticsInfoDao.getLogisticsInfoByNumber(dto.getLogistics_number());
    }

    @Override
    public boolean deleteLogistics(LogisticsDeleteDTO dto) {
        return logisticsInfoDao.deleteLogistics(dto.getId()) > 0;
    }

    @Override
    public List<State> getLogisticsState(LogisticsGetStateDTO dto) {
        ArrayList<State> list = logisticsInfoDao.getState(dto.getId());
        ArrayList<Timestamp> timestamps = logisticsInfoDao.getStateTime(dto.getId());
        for(int i=0;i<list.size();i++){
            list.get(i).setCreateTime(timestamps.get(i));
        }
        return list;
    }

    @Override
    public boolean addLogisticsState(LogisticsStateAddDTO dto) {
        return logisticsInfoDao.addState(dto) > 0;
    }

    @Override
    public boolean updateLogisticsInfo(LogisticsInfoUpdateDTO dto) {
        return logisticsInfoDao.updateLogisticsInfo(dto) > 0;
    }

    @Override
    public List<LogisticsInfo> selectLogistics(LogisticsSelectDTO dto) {
        return logisticsInfoDao.selectLogistics(dto);
    }

    @Override
    public List<State> getAllState() {
        return logisticsInfoDao.getAllState();
    }


}

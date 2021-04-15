package com.team1802.controller;

import com.team1802.dto.*;
import com.team1802.pojo.LogisticsInfo;
import com.team1802.pojo.State;
import com.team1802.service.LogisticsInfoService;
import com.team1802.vo.ListVO;
import com.team1802.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/logisticsInfo")
public class LogisticsInfoController {

    @Resource
    private LogisticsInfoService logisticsInfoService;

    @ApiOperation(value = "发送寄件申请")
    @PostMapping("/send")
    public ResultVO sendGoods(@RequestBody LogisticsInfoAddDTO dto){
        int logisticsInfoId =logisticsInfoService.applyLogisticsInfo(dto);
        boolean result = logisticsInfoService.addUserLogistics(logisticsInfoId,dto.getUser_id());
        return result ? ResultVO.SUCCESS.setMessage("发送申请成功")
                : ResultVO.FAILED.setMessage("发送申请失败");
    }

    @ApiOperation("管理员同意寄件申请")
    @PostMapping("/agree")
    public ResultVO agree (@RequestBody LogisticsInfoAgreeApplyDTO dto) {
        boolean result = logisticsInfoService.agreeApply(dto);

        return result ? ResultVO.SUCCESS.setMessage("同意成功")
                : ResultVO.FAILED.setMessage("同意失败");
    }

    @ApiOperation("管理员拒绝寄件申请")
    @PostMapping("/refuse")
    public ResultVO refuse (@RequestBody LogisticsInfoRefuseApplyDTO dto){
        boolean result = logisticsInfoService.refuseApply(dto);

        return result ? ResultVO.SUCCESS.setMessage("拒绝成功")
                : ResultVO.FAILED.setMessage("拒绝失败");
    }

    @ApiOperation("展示物流信息列表")
    @PostMapping("/listLogistics")
    public ListVO<LogisticsInfo> listLogistics(@RequestBody LogisticsListDTO dto){
        return logisticsInfoService.listLogistics(dto);
    }

    @ApiOperation("用户获得物流信息")
    @PostMapping("/getLogistics")
    public List<LogisticsInfo> getLogistics(@RequestBody UserGetLogisticsDTO dto){
        return logisticsInfoService.getLogisticsInfo(dto);
    }

    @ApiOperation("用户通过物流编号获得物流信息")
    @PostMapping("/getLogisticsByNumber")
    public LogisticsInfo getLogisticsByNumber(@RequestBody UserGetLogisticsByNumberDTO dto){
        return logisticsInfoService.getLogisticsByNumber(dto);
    }

    @ApiOperation("删除物流信息")
    @PostMapping("/delete")
    public ResultVO deleteLogistics(@RequestBody LogisticsDeleteDTO dto){
        boolean result = logisticsInfoService.deleteLogistics(dto);

        return result ? ResultVO.SUCCESS.setMessage("删除成功")
                : ResultVO.FAILED.setMessage("删除失败");
    }

    @ApiOperation("查看物流状态列表")
    @PostMapping("/state")
    public List<State> state(@RequestBody LogisticsGetStateDTO dto){
        return logisticsInfoService.getLogisticsState(dto);
    }

    @ApiOperation("新增物流状态")
    @PostMapping("/addState")
    public ResultVO addState(@RequestBody LogisticsStateAddDTO dto){
        boolean result = logisticsInfoService.addLogisticsState(dto);

        return result ? ResultVO.SUCCESS.setMessage("新增状态成功")
                : ResultVO.FAILED.setMessage("新增状态失败");
    }

    @ApiOperation("更改物流信息")
    @PostMapping("/update")
    public ResultVO update(@RequestBody LogisticsInfoUpdateDTO dto){
        boolean result = logisticsInfoService.updateLogisticsInfo(dto);

        return result ? ResultVO.SUCCESS.setMessage("修改信息成功")
                : ResultVO.FAILED.setMessage("修改信息失败。");
    }

    @ApiOperation("按条件查询物流信息")
    @PostMapping("/select")
    public List<LogisticsInfo> select(@RequestBody LogisticsSelectDTO dto){
        return logisticsInfoService.selectLogistics(dto);
    }

    @ApiOperation("得到所有物流状态")
    @GetMapping("/allState")
    public List<State> allState(){
        return logisticsInfoService.getAllState();
    }
}

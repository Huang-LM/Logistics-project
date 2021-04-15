package com.team1802.controller;

import com.team1802.dto.DictionariesAddDTO;
import com.team1802.dto.DictionariesUpdateDTO;
import com.team1802.pojo.Dictionaries;
import com.team1802.service.DictionariesService;
import com.team1802.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dictionaries")
public class DictionariesController {


    @Resource
    DictionariesService dictionariesService;

    @ApiOperation("增加字典数据")
    @PostMapping("/add")
    public ResultVO addDictionaries(@RequestBody DictionariesAddDTO dto){
        boolean result = dictionariesService.addDictionaries(dto);
        return result ? ResultVO.SUCCESS.setMessage("添加成功")
                : ResultVO.FAILED.setMessage("添加失败");
    }

    @ApiOperation("删除字典数据")
    @PostMapping("/delete")
    public ResultVO deleteDictionaries(int id){
        boolean result = dictionariesService.deleteDictionaries(id);
        return result ? ResultVO.SUCCESS.setMessage("删除成功")
                : ResultVO.FAILED.setMessage("删除失败");
    }

    @ApiOperation("查找所有父id为0的数据列表")
    @PostMapping("/find")
    public List<Dictionaries> findDictionaries(){
        List<Dictionaries> result = dictionariesService.findDictionaries();
        return result;
    }

    @ApiOperation("修改字典数据")
    @PostMapping("/update")
    public ResultVO updateDictionaries(@RequestBody DictionariesUpdateDTO dto){
        boolean result = dictionariesService.updateDictionaries(dto);
        return result ? ResultVO.SUCCESS.setMessage("修改成功")
                : ResultVO.FAILED.setMessage("修改失败");
    }

    @ApiOperation("查找某数据名的子id数据列表")
    @PostMapping("/findSonByName")
    public List<Dictionaries> findSonByName(String name){
        List<Dictionaries> result = dictionariesService.findSonByName(name);
        return result;
    }

    @ApiOperation("查找某个id下对应的子id的数据列表")
    @PostMapping("/findSonById")
    public List<Dictionaries> findSonById(int id){
        List<Dictionaries> result = dictionariesService.findSonById(id);
        return result;
    }

}

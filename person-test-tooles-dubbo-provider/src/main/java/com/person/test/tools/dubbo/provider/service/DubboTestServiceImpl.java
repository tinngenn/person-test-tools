package com.person.test.tools.dubbo.provider.service;

import com.person.test.tools.dubbo.api.DubboTestApiService;
import com.person.test.tools.dubbo.api.request.DubborRequestDTO;
import com.person.test.tools.dubbo.api.result.DubboResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;


@DubboService( timeout = 5000 , interfaceClass = DubboTestApiService.class )
@Service
@Slf4j
public class DubboTestServiceImpl implements DubboTestApiService {

    @Override
    public DubboResultDTO dubboCall(DubborRequestDTO dubborRequestDTO) {
        DubboResultDTO dubboResultDTO = new DubboResultDTO();
        dubboResultDTO.setErrCode("11111");
        dubboResultDTO.setErrMsg("请求成功！");
        try {
            log.info("请求参数dubborRequestDTO：{}", dubborRequestDTO.toString());
            dubboResultDTO.setId(dubboResultDTO.getId());
            dubboResultDTO.setErrCode("0000");
            dubboResultDTO.setErrMsg("请求处理成功！");
            dubboResultDTO.setName(dubborRequestDTO.getName());
            dubboResultDTO.setComments(dubborRequestDTO.getRemark());
            return dubboResultDTO;
        } catch (Exception e) {
            dubboResultDTO.setId(dubboResultDTO.getId());
            dubboResultDTO.setErrCode("9999");
            dubboResultDTO.setErrMsg("请求失败！");
        }
        return null;
    }
}

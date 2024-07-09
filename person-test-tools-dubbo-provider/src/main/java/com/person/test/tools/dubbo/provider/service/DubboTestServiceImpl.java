package com.person.test.tools.dubbo.provider.service;

import com.person.test.tools.dubbo.api.DubboTestApiService;
import com.person.test.tools.dubbo.api.request.DubborRequestDTO;
import com.person.test.tools.dubbo.api.result.DubboResultDTO;
import com.person.test.tools.dubbo.api.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;


@DubboService( timeout = 5000 , interfaceClass = DubboTestApiService.class )
@Service
@Slf4j
public class DubboTestServiceImpl implements DubboTestApiService {

    @Override
    public Result<DubboResultDTO> dubboCall(DubborRequestDTO dubborRequestDTO) {
        Result result = new Result();

        result.setErrorCode("1111");
        result.setErrorMsg("请求成功");
        try {
            DubboResultDTO dubboResultDTO = new DubboResultDTO();
            log.info("请求参数dubborRequestDTO：{}", dubborRequestDTO.toString());
            dubboResultDTO.setId(dubboResultDTO.getId());
            dubboResultDTO.setErrCode("0000");
            dubboResultDTO.setErrMsg("请求处理成功！");
            dubboResultDTO.setName(dubborRequestDTO.getName());
            dubboResultDTO.setComments(dubborRequestDTO.getRemark());
            result.setResult(dubboResultDTO);
            return result;
        } catch (Exception e) {
            result.setErrorCode("9999");
            result.setErrorMsg("请求失败！");
        }
        return null;
    }
}

package com.person.test.tools.dubbo.api;


import com.person.test.tools.dubbo.api.request.DubborRequestDTO;
import com.person.test.tools.dubbo.api.result.DubboResultDTO;
import com.person.test.tools.dubbo.api.result.Result;

public interface DubboTestApiService {

    /**
     * 1、Dubbo 测试接口
     *
     * @param dubborRequestDTO       请求
     * @return                      返回
     */
    Result<DubboResultDTO> dubboCall(DubborRequestDTO dubborRequestDTO);
}

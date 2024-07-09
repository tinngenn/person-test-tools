package com.person.test.tools.dubbo.consumer;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.person.test.tools.dubbo.api.DubboTestApiService;
import com.person.test.tools.dubbo.api.request.DubborRequestDTO;
import com.person.test.tools.dubbo.api.result.DubboResultDTO;
import com.person.test.tools.dubbo.api.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DubboConsumer {


    @DubboReference(retries = 0, timeout = 5000, check = false )
    public DubboTestApiService dubboTestApiService;


    public String dubboCall() {
        log.info("111111111111111");
        DubborRequestDTO dubborRequestDTO = new DubborRequestDTO();
        dubborRequestDTO.setId("007");
        dubborRequestDTO.setName("Testt");
        dubborRequestDTO.setRemark("测试dubbo服务调用");
        Result<DubboResultDTO> result= dubboTestApiService.dubboCall(dubborRequestDTO);
        log.info("22222222222222222");
        return JSONObject.toJSONString(result,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }

}

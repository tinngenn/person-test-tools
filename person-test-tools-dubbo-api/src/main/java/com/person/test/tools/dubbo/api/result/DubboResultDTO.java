package com.person.test.tools.dubbo.api.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DubboResultDTO implements Serializable {
    private static final long serialVersionUID = -42182116365324L;
    String errCode;
    String errMsg;
    String id;
    String name;
    String comments;

}

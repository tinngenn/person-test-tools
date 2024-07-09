package com.person.test.tools.dubbo.api.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DubborRequestDTO implements Serializable {
    private static final long serialVersionUID = -42151816366624L;
    String id;
    String name;
    String remark;
}

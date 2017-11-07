package com.hazendaz.beans;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import lombok.Data;

@Data
@Record(minOccurs = 1)
public class BeanIo {

    @Field(at = 0)
    private String firstName;
    @Field(at = 1)
    private String lastName;
    @Field(at = 2)
    private String title;
    @Field(at = 3)
    private String salary;
    @Field(at = 4)
    private String years;

}

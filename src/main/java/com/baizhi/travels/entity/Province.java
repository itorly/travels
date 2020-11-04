package com.baizhi.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Liu
 * @date 2020/10/9 15:38:13
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Province {
    private String id;
    private String name;
    private String tags;
    private Integer placecounts;
}

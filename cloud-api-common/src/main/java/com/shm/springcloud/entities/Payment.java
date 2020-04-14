package com.shm.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author shenhaimeng
 * @date 2020/3/20 - 0:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable{
    private Long id;
    private String serial;

    @Override
    public String toString()
    {
        return this.id +":" + this.serial;
    }
}

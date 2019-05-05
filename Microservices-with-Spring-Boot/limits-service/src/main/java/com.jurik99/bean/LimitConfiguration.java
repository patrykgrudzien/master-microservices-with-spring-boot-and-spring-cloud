package com.jurik99.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LimitConfiguration {

    private int maximum;
    private int minimum;
}

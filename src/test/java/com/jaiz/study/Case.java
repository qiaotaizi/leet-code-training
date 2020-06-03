package com.jaiz.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

/**
 * 测试用例
 * @param <T>
 * @param <V>
 */
@Getter
@Setter
@AllArgsConstructor
public class Case<T,V> {

    private T input;

    private V expect;

    /**
     * 运行一个测试用例
     * @param targetFunction 测试的目标函数
     * @return 函数运行结果
     */
    public V run(Function<T,V> targetFunction){
        return targetFunction.apply(input);
    }

}

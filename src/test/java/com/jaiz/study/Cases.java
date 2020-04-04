package com.jaiz.study;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 用例集合
 */
public class Cases<T,V> {

    private List<Case<T,V>> list=new ArrayList<>();

    public void add(T input,V expect){
        list.add(new Case<>(input,expect));
    }

    /**
     * 运行所有测试用例
     * @param target 测试目标函数
     * @param inputParamStringer 入参toString函数
     * @param resultStringer 结果和期望值的toString函数
     */
    public void run(Function<T,V> target,
                    Stringer<T> inputParamStringer,
                    Stringer<V> resultStringer){
        for (Case<T, V> c : list) {
            String inputString=inputParamStringer.toString(c.getInput());
            V got=c.run(target);
            Assert.assertEquals(
                    "input="+inputString+
                            ", want="+resultStringer.toString(c.getExpect())+
                            ", but got="+resultStringer.toString(got),
                    c.getExpect(),got);
        }
    }

}

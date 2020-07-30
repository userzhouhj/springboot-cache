package com.jun;

import com.jun.cache.custom.CustomLRUCache;
import org.junit.Test;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class TestLRU {

    @Test
    public void testLRU(){

        CustomLRUCache<String> cache = new CustomLRUCache<>(3L);

//        cache.setCache("test1","数据1");
//        cache.setCache("test2","数据2");
//        cache.setCache("test3","数据3");
//        cache.getAllCAche();
//        System.out.println();
//        System.out.println(cache.getCache("test1"));
//        System.out.println();

        //cache.getAllCAche();
    }
}

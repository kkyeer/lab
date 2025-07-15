package com.kkyeer.study.spring.mockito;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 14:40 2025/7/14
 * @Modified By:
 */
public class LMockitoHelloWorld {
    public static void main(String[] args) {
//        mockListVerify();
//        stubList();
//        stubListArgumentMatchers();
//        verifyMethodInvokeTimes();
//        orderedVerify();
    }

    private static void orderedVerify() {
        List<String> mockList = mock(List.class);
        mockList.add("first");
        mockList.add("second");
        InOrder inOrder = inOrder(mockList);
        inOrder.verify(mockList).add("first");
        inOrder.verify(mockList).add("second");
        inOrder.verifyNoMoreInteractions();
    }


    private static void verifyMethodInvokeTimes() {
        List<String> mockList = mock(List.class);

        mockList.add("once");

        mockList.add("twice");
        mockList.add("twice");

        verify(mockList, times(1)).add("once");
        verify(mockList, times(2)).add("twice");

    }

    private static void stubListArgumentMatchers() {
//        List mockedList = mock(List.class);
//
//        when(mockedList.get(anyInt())).thenReturn("element");
//        System.out.println(mockedList.get(100));

        List<String> mockedList2 = mock(List.class);
        when(mockedList2.contains(argThat(i -> i.equals("a")))).thenReturn(true);
        System.out.println(mockedList2.contains("a"));
        System.out.println(mockedList2.contains("b"));
//        注意这里intThat和argThat不能混用
        when(mockedList2.get(intThat(i -> Objects.nonNull(i) && i > 0 && i < 100))).thenReturn("ok");
        System.out.println(mockedList2.get(50));
        System.out.println(mockedList2.get(1000));
    }



    public static void mockListVerify() {
        List mockedList = mock(List.class);
        mockedList.add("once");

//        验证list调用了add方法
        verify(mockedList).add("once");
//        验证list调用add方法--实际没有调用，所以会抛出异常
        verify(mockedList).add("fake");
    }

    public static void stubList() {
        List mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new IndexOutOfBoundsException());

        // return stubbed value
        System.out.println(mockedList.get(0));
        // not stub return null
        System.out.println(mockedList.get(2));
        // throw exception
        System.out.println(mockedList.get(1));
    }
}

package com.kkyeer.study.spring.mockito;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.delegatesTo;
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
//        iterated();
//        mockVoid();
//        mockVsSpy();
//        retSmartNulls();
//        argCaptor();
        lDelegateTo();
    }

    private static void lDelegateTo() {
        List realList = new ArrayList();
        realList.add("first");
        realList.add("second");
        List mockList = mock(List.class, delegatesTo(realList));

        // 报错：模拟list.get(1)返回"mock"
//        when(mockList.get(1)).thenReturn("mock");
        doReturn("mock").when(mockList).get(1);

        System.out.println(mockList.get(1));
        System.out.println(mockList.size());
        realList.add("third");
//        3
        System.out.println(mockList.size());
    }

    private static void argCaptor() {
        List mockList = mock(List.class);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        // null
        System.out.println(mockList.get(100));
        verify(mockList).get(argumentCaptor.capture());
        // 100
        System.out.println(argumentCaptor.getValue());
    }

    private static void retSmartNulls() {
        List list = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(list.get(0).toString());
    }

    private static void mockVsSpy() {
        List normalList = new ArrayList();
        normalList.add("first");
        List mockList = mock(List.class);
        List spyList = spy(normalList);

        mockList.add("once");
        // 0
        System.out.println(mockList.size());

        spyList.add("once");
        // 2 -- spy对象继承了原对象的初始状态
        System.out.println(spyList.size());
        // 1
        System.out.println(normalList.size());
        normalList.add("twice");
        // 2---normalList的后续变化不影响spy对象
        System.out.println(spyList.size());
        // spy对象允许部分mock
        doReturn(100).when(spyList).size();
        System.out.println(spyList.size());
    }

    private static void mockVoid() {
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).clear();

        list.clear();
    }

    private static void iterated() {
        List<String> mockList = mock(List.class);
        when(mockList.get(1))
                .thenReturn("first")
                .thenReturn("second")
                .thenThrow(new IndexOutOfBoundsException())
                .thenReturn("fourth");
        System.out.println(mockList.get(1));
        System.out.println(mockList.get(1));
        try {
            System.out.println(mockList.get(1));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(mockList.get(1));
        System.out.println(mockList.get(1));
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

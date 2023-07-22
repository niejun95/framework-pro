package org.example.simple;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssertListEqualsTest {

    @Test
    public void listEquals() {
        List<String> list1 = Arrays.asList("1", "2", "3", "4");
        List<String> list2 = Arrays.asList("1", "2", "3", "4");
        List<String> list3 = Arrays.asList("1", "2", "4", "3");

        Assert.assertEquals(list1, list2);
        Assert.assertNotSame(list1, list2);
        Assert.assertNotEquals(list1, list3);

    }
}

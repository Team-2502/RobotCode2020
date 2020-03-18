package com.team2502.robot2020.utils;

import com.team2502.robot2020.util.LookupTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LookupTableTest {

    private LookupTable table;

    @BeforeEach
    public void setup() {
        table = new LookupTable(0D, 0D);
        table.put(1D, 1D);
        table.put(3D, 2D);
        table.put(5D, 10D);
    }

    @Test
    public void testInverse() {
        LookupTable inverseTable = table.inverse();

        assertEquals(1, inverseTable.get(1D));
        assertEquals(3D, inverseTable.get(2D));
        assertEquals(5D, inverseTable.get(10D));
    }

    @Test
    public void testSizeAndClearAndEmpty() {
        assertEquals(4, table.size());
        assertFalse(table.isEmpty());
        table.clear();
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    @Test
    public void testInterpolation() {
        assertEquals(0, table.get(-1D));
        assertEquals(0, table.get(0D));
        assertEquals(0.5, table.get(0.5));
        assertEquals(1, table.get(1D));
        assertEquals(1.5, table.get(2D));
        assertEquals(10, table.get(10D));
    }

}

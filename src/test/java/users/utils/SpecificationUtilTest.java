package users.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpecificationUtilTest {

    @Test
    public void toLike() throws Exception {
        assertEquals("%test%", SpecificationUtil.toLike("test"));
    }

}
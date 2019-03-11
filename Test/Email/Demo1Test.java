package Email;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Demo1Test {

    @Before
    public void setUp() throws Exception {
        System.out.println("start Test");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("finish Test");
    }

    @Test
    public void fun1() throws Exception{
        SendDemo1 d = new SendDemo1();
        d.fun1();
    }
}
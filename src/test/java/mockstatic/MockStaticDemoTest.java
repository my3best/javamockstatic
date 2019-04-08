package mockstatic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StaticClass.class)
public class MockStaticDemoTest {

    @Test
    public void spy_static_method_and_capture_arguments() {
        PowerMockito.spy(StaticClass.class);

        final ArgumentCaptor<String> stringCaptor1 = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> stringCaptor2 = ArgumentCaptor.forClass(String.class);

        MockStaticDemo demo = new MockStaticDemo();
        String returnStr = demo.nonStaticMethod();

        PowerMockito.verifyStatic(StaticClass.class, Mockito.times(1));
        StaticClass.staticMethod(stringCaptor1.capture(), stringCaptor2.capture());

        Assert.assertEquals(stringCaptor1.getValue(), "mock");
        Assert.assertEquals(stringCaptor2.getValue(), "static");

        Assert.assertEquals(returnStr, "mock static");

    }

    @Test
    public void mock_static_method() {
        PowerMockito.mockStatic(StaticClass.class);

        PowerMockito.when(StaticClass.staticMethod(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                    .thenReturn("Static Method Mocked");
        MockStaticDemo demo = new MockStaticDemo();
        String returnStr = demo.nonStaticMethod();

        Assert.assertEquals(returnStr, "Static Method Mocked");
    }
}
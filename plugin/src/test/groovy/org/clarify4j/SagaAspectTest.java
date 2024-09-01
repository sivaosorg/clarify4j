package org.clarify4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.clarify4j.common.annotation.Saga;
import org.clarify4j.config.handler.SagaHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

public class SagaAspectTest {
    @Mock
    private JoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    @InjectMocks
    private SagaHandler sagaHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaga() throws Throwable {
        // Create a Method instance representing the method to be tested
        Method method = TestMethods.class.getMethod("updateUser", Long.class, String.class);

        // Mock method signature and annotation
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(method);

        // Mock method arguments
        when(joinPoint.getArgs()).thenReturn(new Object[]{1L, "John Doe"});
        when(methodSignature.getParameterNames()).thenReturn(new String[]{"userId", "newName"});

        // Call the aspect method
        sagaHandler.handle(joinPoint);

        // Verify that the logging occurred (you might mock the logging framework to assert this)
        // Here, we are just verifying that the method ran without exceptions
        verify(joinPoint, times(1)).getSignature();
    }

    // Helper class to provide the method for reflection
    public static class TestMethods {
        @Saga(expression = "User {{#userId}} is being updated with the name {{#newName}}")
        public void updateUser(Long userId, String newName) {
            // This method is just a placeholder for reflection
        }
    }
}

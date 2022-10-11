package temporal.test;

import org.junit.Rule;
import org.junit.Test;

import io.temporal.client.WorkflowOptions;
import io.temporal.testing.*;
import temporal.activities.Format;
import temporal.activities.FormatImpl;
import temporal.workflows.HelloWorldWorkflow;
import temporal.workflows.HelloWorldWorkflowImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HelloWorldWorkflowTest {

	@Rule
	public TestWorkflowRule testWorkflowRule = 
				TestWorkflowRule.newBuilder()
				.setWorkflowTypes(HelloWorldWorkflowImpl.class)
				.setDoNotStart(true)
				.build();
	
	@Test
	public void testGetGreeting() {
		System.out.println("entering test");
		testWorkflowRule.getWorker().registerActivitiesImplementations(new FormatImpl());
		testWorkflowRule.getTestEnvironment().start();
		
		HelloWorldWorkflow workflow =
				testWorkflowRule
					.getWorkflowClient()
					.newWorkflowStub(
							HelloWorldWorkflow.class, 
							WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
		
		String greetings = workflow.getGreeting("John");
		assertEquals("Hello John!", greetings);
		testWorkflowRule.getTestEnvironment().shutdown();
	}
	
    @Test
    public void testMockedGetGreeting() {
        Format formatActivities = mock(Format.class, withSettings().withoutAnnotations());
        when(formatActivities.composeGreeting(anyString())).thenReturn("Hello World!");
        testWorkflowRule.getWorker().registerActivitiesImplementations(formatActivities);
        testWorkflowRule.getTestEnvironment().start();

        HelloWorldWorkflow workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                HelloWorldWorkflow.class,
                                WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
        String greeting = workflow.getGreeting("World");
        assertEquals("Hello World!", greeting);
        testWorkflowRule.getTestEnvironment().shutdown();
    }
}

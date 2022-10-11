package temporal.test;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import temporal.shared.TemporalConfig;
import temporal.workflows.HelloWorldWorkflow;

public class InitiateHelloWorld {
	//NOTE: this way of getting the service stubs only works for locally hosted (localhost) temporal server
	public static void main(String[] args) throws Exception {
		//WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
		WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(
				WorkflowServiceStubsOptions.newBuilder().setTarget("localhost:7233").build());
	
		WorkflowClient client = WorkflowClient.newInstance(service);
		WorkflowOptions options = WorkflowOptions.newBuilder()
				.setTaskQueue(TemporalConfig.HELLO_WORLD_TASK_QUEUE)
				.build();
	
		HelloWorldWorkflow workflow = client.newWorkflowStub(HelloWorldWorkflow.class, options);
	
		String greeting = workflow.getGreeting("World");
	
		System.out.println(greeting);
		System.exit(0);
	}
	
}

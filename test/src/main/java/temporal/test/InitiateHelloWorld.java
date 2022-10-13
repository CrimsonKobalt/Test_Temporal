package temporal.test;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import temporal.shared.TemporalConfig;
import temporal.workflows.HelloWorldWorkflow;

//"CLIENT"
public class InitiateHelloWorld {
	
	public static void main(String[] args) throws Exception {
		//will use the auto-configuration from the dev-cluster to try and get the services
		WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
		/*
		 * Configuring a cluster in a 'non-default' location:
		 * 
		 * WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(
		 *		WorkflowServiceStubsOptions.newBuilder().setTarget("localhost:7233").build());
		 */
		
	
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

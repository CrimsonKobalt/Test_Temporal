package temporal.test;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import temporal.activities.FormatImpl;
import temporal.shared.TemporalConfig;
import temporal.workflows.HelloWorldWorkflowImpl;


public class TestApplication {
	
	public static void main(String[] args) {
		//will use the auto-configuration from the dev-cluster to try and get the services
		WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
		/*	
		 * Configuring a cluster in a 'non-default' location:
		 * 
		 * WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(
		 *			WorkflowServiceStubsOptions.newBuilder()
		 *										.setTarget("localhost:7233")
		 *										.build());
		 * 
		 */		
		WorkflowClient client = WorkflowClient.newInstance(service);
		WorkerFactory factory = WorkerFactory.newInstance(client);
		Worker worker = factory.newWorker(TemporalConfig.HELLO_WORLD_TASK_QUEUE);
		
		worker.registerWorkflowImplementationTypes(HelloWorldWorkflowImpl.class);
		worker.registerActivitiesImplementations(new FormatImpl());
		factory.start();
	}
}

package temporal.workflows;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import temporal.activities.Format;

public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

	private ActivityOptions options = ActivityOptions.newBuilder()
													 .setStartToCloseTimeout(Duration.ofSeconds(2))
													 .build();
	private final Format format = Workflow.newActivityStub(Format.class, options);
	
	@Override
	public String getGreeting(String name) {
		format.dieRandomly();
		String res = format.composeGreeting(name);
		return res;
	}

}

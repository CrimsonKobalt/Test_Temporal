package temporal.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Format {
	@ActivityMethod
	String composeGreeting(String name);
	
	@ActivityMethod
	void dieRandomly();
}

package temporal.activities;

import java.util.Random;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;

public class FormatImpl implements Format {

	@Override
	public String composeGreeting(String name) {
		Random rng = new Random();
		//simulate dying external API call
		//Is this how this is supposed to be done? docs unclear
		if(rng.nextBoolean()) {
			System.out.println("API call dying randomly...");
			ActivityExecutionContext ctx = Activity.getExecutionContext();
			ctx.doNotCompleteOnReturn();
			return "Failure.";
		}
		return "Hello " + name + "!";
	}

	@Override
	public void dieRandomly() {
	}
}

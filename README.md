# Test_Temporal

## How to run:

### Setting up the (Dev.) Temporal Cluster

1. Download the docker-compose image from [GitHub](https://github.com/temporalio/docker-compose.git)

```
git clone https://github.com/temporalio/docker-compose.git
```

2. Run the cluster

``` 
cd docker-compose
docker-compose up
```

### Hello-world project

```
git clone https://github.com/CrimsonKobalt/Test_Temporal.git
```

To start a worker and register it to the Temporal cluster, run the "temporal.test.TestApplication.java" file.
To simulate a call to this Hello_World service, run the "temporal.test.InitiateHelloWorld.java" file.

The worker is programmed to die silently with a 50% chance for every call, but nevertheless you will see that, eventually, you will always get an answer.

### Validating and Debugging with Temporal UI

Navigate to https://localhost:8080/

![Temporal UI without any workflows](https://user-images.githubusercontent.com/58666020/195678724-5f151e65-f4d2-4b98-a7ee-70ea4e85ce42.png)

As you can see, there are no registered workflows for now.
Let's start a client by running the temporal.test.InitiateHelloWorld.java file.
If you now refresh the Temporal UI, you will see that a workflow has been registered.

![Temporal UI with a single workflow](https://user-images.githubusercontent.com/58666020/195679645-17208237-8548-457b-971d-6074c58c9427.png)

Clicking on this will give a more detailed overview of the state of execution, in this case you can see that the workflow has been scheduled, 
but since there is no worker to execute it, it remains in a waiting state.

If we now run the worker class (by running temporal.test.TestApplication.java) you will see that the client receives its answer in a timely manner.
Checking the Temporal UI again we can see that its state has changed from "Running" to "Completed".

![Temporal UI with a single, completed workflow](https://user-images.githubusercontent.com/58666020/195680587-f4863c34-4fbc-47a0-a198-35355650c7e5.png)

If we now navigate to the more detailed view of this workflow (by clicking on it) we can see the execution details.
This should make debugging the distributed system a lot easier, since everything using Temporal is fully trackable.

![Temporal UI, Workflow detail view](https://user-images.githubusercontent.com/58666020/195681056-6d05df24-9a05-44b2-a7f0-41abe2b13f70.png)

Coming back to that 50% service failure rate. Timeouts and retries are fully configurable and also trackable.
The next time the workflow ran, it had to try three times to get the result, also fully trackable in Temporal.

![Temporal UI, Workflow detail view with failure](https://user-images.githubusercontent.com/58666020/195682208-4dc1ad1e-7817-4755-bd37-304fce420135.png)

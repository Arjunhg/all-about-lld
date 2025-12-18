Design Challenges : 
Interviewer: What design challenges do you anticipate?

‍

Candidate: The key challenges for the Parking Lot system include:

1. Efficient Slot Allocation: Ensuring vehicles are assigned to the correct slot type.

2. Tracking Vehicle Duration: Keeping a record of entry times for payment calculations.

3. Handling Payments: Supporting multiple payment methods dynamically.

4. Managing Concurrency: Ensuring simultaneous vehicle entries and exits are handled properly.

‍

Point 5: Approach :
Interviewer: How would you approach these challenges?

Candidate: I propose using design patterns effectively. Here are my strategies:

1. Factory for Vehicle Creation : 

• Allows easy extension for new vehicle types.

• Ensures consistent object creation.

2. Strategy Pattern for Payments and Parking Fares : 

• Enables flexible payment methods and Parking fares based on the vehicle type and duration for the parking.

• Easily extendable for future payment integrations and new fare strategy additions.

3. Singleton Pattern for Parking Lot Management : 

• Ensures only one instance of the parking lot exists.

4. Observer Pattern for Exit Notifications : 

• Notifies the system when a vehicle exits.

• Can be extended for alerts or logging.

‍
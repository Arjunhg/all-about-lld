Rules of the System : 
Setup:

• The parking lot has multiple slots available for parking.

• Different types of vehicles (bike, car, truck) can occupy different slot sizes.

• Each vehicle is issued a parking ticket upon entry.

• The system calculates the parking fee based on the duration of stay and vehicle type.

‍

Exit and Payment:

• A vehicle needs to make a payment before exiting.

• Multiple payment methods (Cash, Card, UPI) should be supported.

• Once payment is successful, the vehicle is allowed to exit, and the parking slot is freed.

‍

Illegal Actions:

• A vehicle cannot park in an already occupied slot.

• Vehicles cannot vacate without completing the payment process.

Identifying Key Components : 
Candidate: Now that we have the requirements, let’s identify the key components of our Parking Lot system:

1. Vehicle: Represents different types of vehicles.

Class: Vehicle

Description: This class represents vehicles entering the parking lot.

‍

2. Parking Lot: Manages parking slots and vehicle allocations.

Class: ParkingLot

Description: This class manages the allocation and release of parking slots.

‍

3. Parking Slot: Represents an individual parking space.

Class: ParkingSlot

Description: A slot has a type (Bike, Car, Truck) and an availability status.

‍

4. Payment System: Handles different payment methods like Credit Card, Cash, UPI etc

Class: Payment

Description: This class processes payments before exit.




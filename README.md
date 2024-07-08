# Train Booking Application

## Description
This project implements a train booking application designed to manage ticket bookings, cancellations, and seat allocations based on specific station orders and seating rules.

### Key Features
- **Single Coach**: The train has one coach with 8 seats.
- **Booking Flexibility**: Tickets can be booked from any station to any subsequent station (e.g., A to E, C to E, B to D).
- **Seat Management**: Seats are managed between stations to avoid double bookings.
- **PNR Management**: Each ticket is uniquely identified by a sequentially generated PNR.
- **Cancellation**: Supports full and partial cancellations, and manages waiting list movements.
- **Booking Summary**: Prints detailed booking and cancellation summaries.

## Design Details
- **Station Order**: A → B → C → D → E. Distance between consecutive stations is fixed.
- **Seat Allocation**: Seats can be allocated only if all requested seats are available (either confirmed or waiting list).
- **Waiting List**: Maximum of 2 waiting list seats can be allocated.
- **Cancellation Logic**: Cancelling tickets may move waiting list tickets to confirmed status if conditions are met.

## Implementation
The application is implemented in [language/framework] and structured as follows:
- **Main Components**: Ticket management, seat allocation, cancellation system.
- **Data Structures**: Arrays/lists for seats, maps for managing station orders and ticket statuses.
- **Algorithms**: Booking validation, seat allocation rules, cancellation handling.

## Usage
1. **Setup**:
   - Clone the repository:
     ```bash
     git clone https://github.com/sanjaikrp/Train-Booking.git
     cd Train-Booking
     ```
   - Install dependencies and configure environment variables as necessary.

2. **Run the Application**:
   - Start the application:
     ```bash
     npm start
     ```
   - Use the provided input format (`book`, `cancel`, `chart`) to interact with the application.

3. **Input Examples**:
   - Book tickets:
     ```plaintext
     bookAE,8
     bookAE,2
     bookAD,2
     bookAC,8
     bookCE,8
     ```
   - Cancel tickets:
     ```plaintext
     cancel,1,5
     cancel,2,2
     cancel,3,8
     cancel,4,8
     ```
   - Print booking summary and chart:
     ```plaintext
     chart
     ```

## Contributing
Contributions are welcome. Please follow these steps to contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/yourfeature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/yourfeature`).
5. Create a new Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

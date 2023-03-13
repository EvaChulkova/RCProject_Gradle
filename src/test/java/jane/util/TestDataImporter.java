package jane.util;

import jane.entity.Booking;
import jane.entity.Car;
import jane.entity.Client;
import jane.entity.User;
import jane.entity.enums.BookingStatusEnum;
import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import jane.entity.enums.PaymentStateEnum;
import jane.entity.enums.RoleEnum;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Arrays;

@UtilityClass
public class TestDataImporter {
    public void importData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        User ivanAdmin = saveUser(session, "Ivan", "Ivanov", "ivanAdmin@gmail.com", "123", RoleEnum.ADMIN);
        User annaAdmin = saveUser(session, "Anna", "Sidorova", "annaAdmin@gmail.com", "123", RoleEnum.ADMIN);

        User svetlanaUser = saveUser(session, "Svetlana", "Petrova", "sveta@gmail.com", "456", RoleEnum.CLIENT);
        User innaUser = saveUser(session, "Inna", "Nikitina", "inna@gmail.com", "456", RoleEnum.CLIENT);
        User ninaUser = saveUser(session, "Nina", "Monahova", "nina@gmail.com", "456", RoleEnum.CLIENT);
        User alekseyUser = saveUser(session, "Aleksey", "Antoch", "aleks@gmail.com", "456", RoleEnum.CLIENT);
        User viktorUser = saveUser(session, "Viktor", "Viktorov", "viktor@gmail.com", "456", RoleEnum.CLIENT);
        User petrUser = saveUser(session, "Petr", "Sidorov", "petr@gmail.com", "456", RoleEnum.CLIENT);

        Client svetlana = saveClient(session, svetlanaUser, LocalDate.of(2002, 2, 15), 136015, LocalDate.of(2038, 10, 10));
        Client inna = saveClient(session, innaUser, LocalDate.of(1984, 7, 24), 116753, LocalDate.of(2027, 1, 10));
        Client nina = saveClient(session, ninaUser, LocalDate.of(1998, 5, 18), 144096, LocalDate.of(2031, 7, 19));
        Client aleksey = saveClient(session, alekseyUser, LocalDate.of(2001, 11, 6), 201410, LocalDate.of(2036, 4, 21));
        Client viktor = saveClient(session, viktorUser, LocalDate.of(1978, 4, 17), 714002, LocalDate.of(2025, 9, 4));
        Client petr = saveClient(session, petrUser, LocalDate.of(1989, 8, 22), 315698, LocalDate.of(2029, 9, 15));

        Car audiA3 = saveCar(session, "Audi", "A3", CarColorEnum.BLACK, 4, 5450, CarStatusEnum.BOOKED, "AudiA3Image");
        Car audiRS6 = saveCar(session, "Audi", "RS6", CarColorEnum.BLACK, 5, 9450, CarStatusEnum.AVAILABLE, "AudiRS6Image");
        Car kiaRio = saveCar(session, "Kia", "Rio", CarColorEnum.WHITE, 4, 3170, CarStatusEnum.AVAILABLE, "KiaRioImage");
        Car BMWX5 = saveCar(session, "BMW", "X5", CarColorEnum.BLACK, 5, 8915, CarStatusEnum.IN_CAR_SERVICE, "BMWX5Image");
        Car yellowRenaultLogan = saveCar(session, "Renault", "Logan", CarColorEnum.BLACK, 4, 2100, CarStatusEnum.AVAILABLE, "YellowRenaultLoganImage");
        Car whiteRenaultLogan = saveCar(session, "Renault", "Logan", CarColorEnum.YELLOW, 4, 2150, CarStatusEnum.AVAILABLE, "WhiteRenaultLoganImage");
        Car mercedesGLA200 = saveCar(session, "Mercedes", "GLA 200", CarColorEnum.GREY, 5, 8900, CarStatusEnum.AVAILABLE, "MercedesGLA200Image");
        Car miniCooper = saveCar(session, "Mini", "Cooper", CarColorEnum.RED, 2, 6920, CarStatusEnum.BOOKED, "MiniCooperImage");
        Car blackVWPolo = saveCar(session, "VW", "Polo", CarColorEnum.BLACK, 4, 3450, CarStatusEnum.BOOKED, "BlackVWPoloImage");
        Car greyVWPolo = saveCar(session, "VW", "Polo", CarColorEnum.GREY, 4, 3460, CarStatusEnum.AVAILABLE, "GreyVWPoloImage");
        Car VWTouareg = saveCar(session, "VW", "Touareg", CarColorEnum.WHITE, 4, 7050, CarStatusEnum.BOOKED, "VWTouaregImage");
        Car toyotaCamry = saveCar(session, "Toyota", "Camry", CarColorEnum.BLACK, 4, 4740, CarStatusEnum.AVAILABLE, "ToyotaCamryImage");


        /*saveBooking(session, inna, kiaRio, LocalDate.of(2023, 2, 14), LocalDate.of(2023, 2, 17), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!");
        saveBooking(session, inna, blackVWPolo, LocalDate.of(2023, 3, 16), LocalDate.of(2023, 3, 19), BookingStatusEnum.APPROVED, PaymentStateEnum.NOT_PAID, "Booking is approved. To pay...");

        saveBooking(session, nina, miniCooper, LocalDate.of(2023, 2, 21), LocalDate.of(2023, 2, 27), BookingStatusEnum.COMPLETED, PaymentStateEnum.NOT_PAID, "Booking is completed. Nice to meet you again!");
        saveBooking(session, nina, miniCooper, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 18), BookingStatusEnum.APPROVED, PaymentStateEnum.PAID, "Payment was successful! Have a good trip!");

        saveBooking(session, viktor, VWTouareg, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 21), BookingStatusEnum.APPROVED, PaymentStateEnum.PAID, "Payment was successful! Have a good trip!");

        saveBooking(session, aleksey, toyotaCamry, LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 10), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!");
        saveBooking(session, aleksey, mercedesGLA200, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 14), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving");

        saveBooking(session, svetlana, audiA3, LocalDate.of(2023, 3, 13), LocalDate.of(2023, 3, 20), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving");

        saveBooking(session, petr, greyVWPolo, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 3, 27), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving");
        */

        addToBooking(session,  LocalDate.of(2023, 2, 14), LocalDate.of(2023, 2, 17), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!", inna, kiaRio);
        addToBooking(session, LocalDate.of(2023, 3, 16), LocalDate.of(2023, 3, 19), BookingStatusEnum.APPROVED, PaymentStateEnum.NOT_PAID, "Booking is approved. To pay...", inna, blackVWPolo);

        addToBooking(session, LocalDate.of(2023, 2, 21), LocalDate.of(2023, 2, 27), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!", nina, miniCooper);
        addToBooking(session, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 18), BookingStatusEnum.APPROVED, PaymentStateEnum.PAID, "Payment was successful! Have a good trip!", nina, miniCooper);

        addToBooking(session, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 21), BookingStatusEnum.APPROVED, PaymentStateEnum.PAID, "Payment was successful! Have a good trip!", viktor, VWTouareg);

        addToBooking(session, LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 10), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!", aleksey, toyotaCamry);
        addToBooking(session, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 14), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving", aleksey, mercedesGLA200);

        addToBooking(session, LocalDate.of(2023, 3, 13), LocalDate.of(2023, 3, 20), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving", svetlana, audiA3);

        addToBooking(session, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 3, 27), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving", petr, greyVWPolo);
    }

    private void addToBooking(Session session,
                              LocalDate rentalStart,
                              LocalDate rentalFinish,
                              BookingStatusEnum bookingStatus,
                              PaymentStateEnum paymentState,
                              String comment,
                              Client client,
                              Car... cars) {
        Arrays.stream(cars)
                .map(car -> Booking.builder()
                        .client(client)
                        .car(car)
                        .rentalStart(rentalStart)
                        .rentalFinish(rentalFinish)
                        .status(bookingStatus)
                        .paymentState(paymentState)
                        .comment(comment)
                        .build())
                .forEach(session::save);
    }

    /*private Booking saveBooking(Session session,
                                 Client client,
                                 Car car,
                                 LocalDate rentalStart,
                                 LocalDate rentalFinish,
                                 BookingStatusEnum bookingStatus,
                                 PaymentStateEnum paymentState,
                                 String comment
    ) {
        Booking booking = Booking.builder()
                .client(client)
                .car(car)
                .rentalStart(rentalStart)
                .rentalFinish(rentalFinish)
                .status(bookingStatus)
                .paymentState(paymentState)
                .comment(comment)
                .build();
        session.save(booking);

        return booking;
    }*/

    private Car saveCar(Session session,
                        String brand,
                        String model,
                        CarColorEnum color,
                        Integer seatAmount,
                        Integer pricePerDay,
                        CarStatusEnum carStatus,
                        String image) {
        Car car = Car.builder()
                .brand(brand)
                .model(model)
                .color(color)
                .seatAmount(seatAmount)
                .pricePerDay(pricePerDay)
                .status(carStatus)
                .image(image)
                .build();
        session.save(car);

        return car;
    }

    private Client saveClient(Session session,
                              User user,
                              LocalDate birthDate,
                              Integer drivingLicenceNo,
                              LocalDate validity) {

        Client client = Client.builder()
                .user(user)
                .birthDate(birthDate)
                .drivingLicenceNo(drivingLicenceNo)
                .validity(validity)
                .build();
        session.save(client);

        return client;
    }

    private User saveUser(Session session,
                          String firstName,
                          String lastName,
                          String login,
                          String password,
                          RoleEnum roleEnum) {

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .login(login)
                .password(password)
                .role(roleEnum)
                .build();
        session.save(user);

        return user;
    }
}

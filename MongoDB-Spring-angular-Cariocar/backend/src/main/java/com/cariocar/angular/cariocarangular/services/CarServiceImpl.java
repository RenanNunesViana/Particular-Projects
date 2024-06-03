package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.Car;
import com.cariocar.angular.cariocarangular.models.User;
import com.cariocar.angular.cariocarangular.repositories.CarRepository;
import com.cariocar.angular.cariocarangular.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    private enum Flags{
        ADD, RMV
    }

    private void
    alterCarInUser(Flags flag, Car car, String cpf){
        switch (flag){
            case ADD:
                User user = userRepository.findByCpf(cpf).orElseThrow(IllegalArgumentException::new);
                List<Car> carsList = user.getCars();

                car.setOwnerCpf(cpf);
                carRepository.save(car);

                carsList.add(car);
                user.setCars(carsList);
                userRepository.save(user);
                break;

            case RMV:
                User user2 = userRepository.findByCpf(cpf).orElseThrow(IllegalArgumentException::new);
                List<Car> carsList2 = user2.getCars();

                carsList2.remove(car);
                user2.setCars(carsList2);
                userRepository.save(user2);
                carRepository.delete(car);
        }
    }
    @Override
    public void saveCar(Car car) {
        Optional<Car> carTmp = carRepository.findById(car.getPlate());
        if(carTmp.isEmpty()) {
            alterCarInUser(Flags.ADD, car, car.getOwnerCpf());
        }else
            throw new IllegalArgumentException("car already exist");
    }

    @Override
    public Car editCar(Car car, Long plate) {
        Optional<Car> carTmp = carRepository.findById(plate);
        if(carTmp.isPresent()) {
            Car carToUpdate = carTmp.get();
            if (car.getPlate() != null)
                carToUpdate.setPlate(car.getPlate());
            if (car.getAge() != null)
                carToUpdate.setAge(car.getAge());
            if (car.getModel() != null)
                carToUpdate.setModel(car.getModel());
            carRepository.save(carToUpdate);
            return carToUpdate;
        }
        throw new IllegalArgumentException("car not found");
    }

    @Override
    public List<Car> listCar() {
       return carRepository.findAll();
    }

    @Override
    public Car getCar(Long plate) {
        return carRepository.findById(plate).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Car deleteCar(Long plate) {
        Car car = carRepository.findById(plate).orElseThrow(IllegalArgumentException::new);
        alterCarInUser(Flags.RMV, car, car.getOwnerCpf());
        return car;
    }
}

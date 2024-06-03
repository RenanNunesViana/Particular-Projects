package com.cariocar.angular.cariocarangular.observer.observers;

import com.cariocar.angular.cariocarangular.models.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class OrderLogger implements OrderObserver {

    private static final String FILE_PATH = "servicos_log.txt";

    @Override
    public void notify(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Novo serviço adicionado: " + order.getDescription());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

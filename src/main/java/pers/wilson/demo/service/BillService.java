package pers.wilson.demo.service;

import pers.wilson.demo.model.Bill;

import java.util.List;

public interface BillService {
    Bill calculate(List<String> productNames);
}

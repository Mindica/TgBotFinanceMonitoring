package ru.skillfactorydemo.tgbot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfactorydemo.tgbot.entity.Income;
import ru.skillfactorydemo.tgbot.service.CentralRussianBankService;
import ru.skillfactorydemo.tgbot.service.StatsService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CentralRussianBankService centralRussianBankService;
    private final StatsService statsService;

    @GetMapping("/getCurrencies")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return centralRussianBankService.getCurrenciesFromCbr();
    }

    @GetMapping("/getStats")
    public List<Income> getStatsAboutIncomesThatGreaterThat(@RequestParam(value = "amount")BigDecimal amount){
        return statsService.getIncomesThatGreaterThat(amount);
    }
}
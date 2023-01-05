package com.restful.microservice.currencyexchange;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    public Environment environment;

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    public CuurencyRepositoryRepo cuurencyRepositoryRepo;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String from,@PathVariable String to){


        logger.info("retrived currency exchange rate for from {} to {}",from,to);
        CurrencyExchange currencyExchange = cuurencyRepositoryRepo.findByFromAndTo(from,to);
        if(currencyExchange==null){
            throw new RuntimeException("no from to found");
        }
       String port = environment.getProperty("local.server.port");
        currencyExchange.setEnv(port);
        return currencyExchange;
    }
}

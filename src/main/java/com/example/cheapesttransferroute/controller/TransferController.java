package com.example.cheapesttransferroute.controller;

import com.example.cheapesttransferroute.dto.TransferRequest;
import com.example.cheapesttransferroute.dto.TransferResponse;
import com.example.cheapesttransferroute.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private TransferService transferService;
    private static final String HEALTH_CHECK_RESPONSE = "All Good";

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/health-check")
    public String healthCheck() { return HEALTH_CHECK_RESPONSE; }

    @PostMapping("/calculate")
    public TransferResponse calculate(@RequestBody TransferRequest request) {
        return transferService.getSelectedTransfers(request.getMaxWeight(), request.getAvailableTransfers());
    }

}

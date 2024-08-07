package dev.bengi.walletservice.controller;

import dev.bengi.walletservice.dto.TopupDTO;
import dev.bengi.walletservice.dto.UserWalletInfo;
import dev.bengi.walletservice.service.WalletService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    //Injection as Constructor
    private final Logger logger = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/topup")
    public ResponseEntity<UserWalletInfo> topup(
            @RequestHeader("X-Idempotency-Key") String idempotentKey,
            @RequestBody @Valid TopupDTO dto
    ) {

        //Controller for wallet topup
        var recreateBody = new TopupDTO(
                dto.amount(),
                Integer.valueOf(dto.userId()),
                idempotentKey);

        var result = walletService.topup(recreateBody);

        return ResponseEntity.ok(result);
    }

}

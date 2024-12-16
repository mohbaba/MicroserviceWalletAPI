package org.payment_services.wallet.infrastucture.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.data.requests.UpdatePinRequest;
import org.payment_services.wallet.application.data.responses.GetWalletResponse;
import org.payment_services.wallet.application.ports.input.walletusecases.ChangePinUseCase;
import org.payment_services.wallet.application.ports.input.walletusecases.CreateWalletUseCase;
import org.payment_services.wallet.application.ports.input.walletusecases.GetBalanceUseCase;
import org.payment_services.wallet.application.ports.input.walletusecases.GetWalletUseCase;
import org.payment_services.wallet.domain.models.Wallet;
import org.payment_services.wallet.infrastucture.adapters.input.rest.mappers.UserRestMapper;
import org.payment_services.wallet.infrastucture.adapters.input.rest.mappers.WalletRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/wallet")
@RestController
@RequiredArgsConstructor
public class WalletRestAdapter {
    private final CreateWalletUseCase createWalletUseCase;
    private final GetBalanceUseCase getBalanceUseCase;
    private final GetWalletUseCase getWalletUseCase;
    private final ChangePinUseCase changePinUseCase;
    private final WalletRestMapper walletRestMapper;
    private final UserRestMapper userRestMapper;

    @GetMapping("/balance/{owner}")
    public ResponseEntity<?> getBalance(@PathVariable("owner") String owner){
        return new ResponseEntity<>(this.getBalanceUseCase.getBalance(owner), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetWalletResponse> getWallet(@PathVariable("id") String id){
        Wallet wallet = getWalletUseCase.getWallet(id);
        return new ResponseEntity<>(this.walletRestMapper.toGetWalletResponse(wallet), HttpStatus.OK);
    }

    @PatchMapping("/updatePin/{walletId}")
    public ResponseEntity<?> updatePin(@PathVariable("walletId") String walletId, @RequestBody UpdatePinRequest updatePinRequest){
        changePinUseCase.changePin(updatePinRequest,walletId);
        return new ResponseEntity<>("Password Changed Successfully", HttpStatus.OK);

    }

}

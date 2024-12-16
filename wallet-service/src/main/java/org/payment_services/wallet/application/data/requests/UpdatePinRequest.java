package org.payment_services.wallet.application.data.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePinRequest {
    private String oldPassword;
    private String newPassword;
}

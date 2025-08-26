package vn.edu.fpt.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User's ID must be > 0")
    Long userId;

    @JsonProperty("fullname")
    String fullName;

    String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    @Size(min = 5, message = "Phone number must be at least 5 characters")
    String phoneNumber;

    String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must >= 0")
    Float totalMoney;

    @JsonProperty("shipping_method")
    String shippingMethod;

    @JsonProperty("shipping_address")
    String shippingAddress;

    @JsonProperty("payment_method")
    String paymentMethod;
}

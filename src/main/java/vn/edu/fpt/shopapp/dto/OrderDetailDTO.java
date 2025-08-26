package vn.edu.fpt.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order ID must be > 0")
    Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product ID must be > 0")
    Long productId;

    @Min(value = 0, message = "Price must be >= 0")
    Long price;

    @Min(value = 1, message = "number_of_products must be > 0")
    @JsonProperty("number_of_products")
    int numberOfProducts;

    @Min(value = 0, message = "Total money must be >= 0")
    @JsonProperty("total_money")
    int totalMoney;
    String color;
}

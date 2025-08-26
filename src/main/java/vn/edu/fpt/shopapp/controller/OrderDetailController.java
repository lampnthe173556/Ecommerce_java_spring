package vn.edu.fpt.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.shopapp.dto.OrderDetailDTO;

@RestController
@RequestMapping("${api.prefix}/order-details")
public class OrderDetailController {
    //add order detail
    @PostMapping
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            // Logic to create order detail
            return ResponseEntity.ok("Order detail created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating order detail: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable Long id) {
        try {
            // Logic to get order detail by ID
            return ResponseEntity.ok("Order detail retrieved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving order detail: " + e.getMessage());
        }
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@Valid @PathVariable Long orderId) {
        try {
            // Logic to get order details by order ID
            return ResponseEntity.ok("Order details retrieved successfully for order ID: " + orderId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving order details: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            // Logic to update order detail
            return ResponseEntity.ok("Order detail updated successfully for ID: "
                    + id + " with details: " + orderDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating order detail: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(@Valid @PathVariable Long id) {
        try {
            // Logic to delete order detail
            return ResponseEntity.ok("Order detail deleted successfully for ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting order detail: " + e.getMessage());
        }
    }
}

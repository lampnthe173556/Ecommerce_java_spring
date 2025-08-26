package vn.edu.fpt.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.shopapp.models.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
}

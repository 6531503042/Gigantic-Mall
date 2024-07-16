package com.gigantic.admin.Controller;

import com.gigantic.DTO.OrderDTO;
import com.gigantic.admin.Exception.DuplicateOrderException;
import com.gigantic.admin.Repository.OrderRepository;
import com.gigantic.admin.Service.Impl.OrderServiceImpl;
import com.gigantic.entity.Orders.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing orders.
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private OrderServiceImpl services;

    /**
     * Creates an order and handles duplicate entry scenarios.
     *
     * @param orderDTO The order to be created
     * @return ResponseEntity containing the created order
     * @throws Exception if an error occurs during order creation
     */
    @PostMapping("/create")
    public ResponseEntity<Order> createOrders(@RequestBody OrderDTO orderDTO) throws Exception {
        try {

            //Checking if orderDTO is null ?
            if (orderDTO == null) {
                throw new Exception("Order can't be null");
            }

            //Checking if customerId is null or 0 ?
            if (orderDTO.getCustomerId() == null || orderDTO.getCustomerId() == 0) {
                throw new Exception("Customer id can't be null or 0");
            }

            //Checking if payment method is null ?
            if (orderDTO.getPaymentMethod() == null) {
                throw new Exception("Payment method can't be null");
            }

            //Checking if order status is null ?
            if (orderDTO.getStatus() == null) {
                throw new Exception("Order status can't be null");
            }

            //Checking if order tracks is null ?
            if (orderDTO.getOrderTracks() == null) {
                throw new Exception("Order tracks can't be null");
            }
        } catch (DataIntegrityViolationException ex) {
            // If a duplicate entry is found in the database
            // Log the error or perform any necessary actions
            handleDuplicateEntry(ex);
            throw ex; // Re-throw the exception for higher-level handling if needed
        } catch (Exception e) {
            // Handle other exceptions
            logError(e);
            throw e; // Re-throw the exception for higher-level handling if needed
        }


        // Mapping DTO to Entity using service method
        Order order = services.toEntity(orderDTO);

        Order createOrder = services.save(order);
        return ResponseEntity.ok(createOrder);
    }

    /**
     * Handles the situation when a duplicate entry is found in the database.
     * Can be used for logging or any specific actions related to duplicates.
     *
     * @param ex The DataIntegrityViolationException thrown
     */
    private void handleDuplicateEntry(DataIntegrityViolationException ex) {
        // Add custom handling logic here, such as logging the error
        System.out.println("Duplicate entry found: " + ex.getMessage());
    }

    /**
     * Logs the error for further analysis.
     *
     * @param e The exception to log
     */
    private void logError(Exception e) {
        // Log the error for further analysis or debugging
        System.err.println("Error occurred: " + e.getMessage());
    }

}
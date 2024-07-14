package com.gigantic.admin.Controller;

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

    /**
     * Creates an order and handles duplicate entry scenarios.
     *
     * @param order The order to be created
     * @return ResponseEntity containing the created order
     * @throws Exception if an error occurs during order creation
     */
    @PostMapping("/create")
    public ResponseEntity<Order> createOrders(@RequestBody Order order) throws Exception {
        try {
            Order createdOrder = repo.save(order);
            return ResponseEntity.ok(createdOrder);
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
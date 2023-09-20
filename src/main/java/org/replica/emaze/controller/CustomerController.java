package org.replica.emaze.controller;

import org.replica.emaze.business.domain.Customer;
import org.replica.emaze.business.domain.Order;
import org.replica.emaze.business.domain.dto.*;
import org.replica.emaze.business.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.replica.emaze.aop.annotation.Auditor;
import org.replica.emaze.business.domain.User;
import zw.co.paynow.core.Paynow;

import java.util.List;

/**
 * @author :: codemaster
 * created on :: 2/4/2023
 */

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    Paynow paynow = new Paynow("16459", "c2a23ef7-99a3-4782-9b9a-b0f573c04478");


    @Auditor
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<Customer> customers=customerService.getAll();
        return ResponseEntity.ok(customers);
    }


    @Auditor
    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerOrderDTO customerOrderDTO) {
        User user= userService.getCurrentUser();
        Customer customer=null;
        boolean logged=customerOrderDTO.getLogged();
        CustomerDTO customerDTO=customerOrderDTO.getCustomer();
        ProfessionalOrderDTO professionalOrderDTO=customerOrderDTO.getProfessionalOrderDTO();
        OrderDTO[] orderDTOs=customerOrderDTO.getOrders();
            customer = customerService.getCustomerByEmailOrPhone(customerDTO.getEmail(), customerDTO.getPhone());
            if(customer==null){
                customer= new Customer();
                customer.setFirstName(customerDTO.getFirstName());
                customer.setLastName(customerDTO.getLastName());
                customer.setAddress(customerDTO.getAddress());
                customer.setAddress2(customer.getAddress2());
                customer.setEmail(customerDTO.getEmail());
                customer.setPhone(customerDTO.getPhone());
                customer.setPhone2(customer.getPhone2());
                customer.setTitle(customerDTO.getTitle());
                customer.setActive(Boolean.TRUE);
                customer.setDeliver(customerDTO.getDeliver()!=null?customerDTO.getDeliver().equalsIgnoreCase("Yes"):false);
                customer.setRegister(customerDTO.getRegister()!=null?customerDTO.getRegister().equalsIgnoreCase("Yes"):false);
                customer =customerService.save(customer, user);
            }


        try{

            /*if(customerOrderDTO.getPayment()){
                Payment payment = paynow.createPayment(customer.getLastName()+"#"+customer.getFirstName());
                for(OrderDTO orderDTO: orderDTOs){
                    Product product= productService.get(orderDTO.getProductID());
                    payment.add(product.getName(), (product.getPrice()* orderDTO.getQuantity()));
                }
                payment.setCartDescription("Order from "+customer.getLastName()+" "+customer.getFirstName()+" on "+new Date());
                WebInitResponse response = paynow.send(payment);
                response.getErrors().stream().forEach(System.err::println);
                if (response.success()) {
                    // Get the url to redirect the user to so they can make payment
                    String redirectUrl = response.redirectURL();

                    // Get the poll URL of the transaction
                    String pollUrl = response.pollUrl();
                    System.err.println("POLL URL::"+pollUrl);

                    StatusResponse status = paynow.pollTransaction(pollUrl);

                    if (status.isPaid()) {
                        // Yay! Transaction was paid for
                    } else {
                        System.out.println("Why you no pay?");
                    }
                } else {
                    //System.err.println("Error sending payment request.");
                    return ResponseEntity.status(422).body("Failed to make payment.");
                    // *freak out*
                }

            }*/

            if(orderDTOs!=null && orderDTOs.length>0) {
                for (OrderDTO orderDTO : orderDTOs) {
                    Order order = new Order();
                    order.setCustomer(customerService.get(customer.getId()));
                    order.setQuantity(orderDTO.getQuantity());
                    order.setDeliveryDate(orderDTO.getDeliveryDate());
                    order.setProduct(productService.get(orderDTO.getProductID()));
                    order = orderService.save(order, user);
                }
            }

            if(professionalOrderDTO!=null && professionalOrderDTO.getProfessionalID()!=null){

            }

            return  ResponseEntity.ok(customer);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @Auditor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") String id){

        try{
            User currentUser=userService.getCurrentUser();
            Customer customer=customerService.update(id, customerDTO,currentUser);
            return  ResponseEntity.ok(customerService.getAll());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Auditor
    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){
        List<Order> orders=orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @Auditor
    @GetMapping("/orders/user/")
    public ResponseEntity<?> getAllOrdersForUser(){
        User user=userService.getCurrentUser();
        if(user!=null) {
            List<Order> orders = orderService.getOrdersByUser(user);
            return ResponseEntity.ok(orders);
        }else{
            return null;
        }
    }

}

package com.uber.service.auth.service;

import com.uber.service.auth.helper.AuthPassengerDetails;
import com.uber.service.auth.repository.PassengerRepository;
import com.uber.service.entityservice.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is responsible for loading the user in the form of UserDetails object for auth.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findByEmail(email);  // email is the uniqueId for our case
        if (passenger.isPresent()) {
            return new AuthPassengerDetails(passenger.get());
        } else{
            throw new UsernameNotFoundException("Cannot find the passenger with email: " + email);
        }
    }
}

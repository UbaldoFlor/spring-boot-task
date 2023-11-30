package com.gymsystem.service.healthIndicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import com.gymsystem.service.impl.UserServiceImpl;

public class UserServiceHealthIndicator implements HealthIndicator{
	
	private final UserServiceImpl userService;
	
	public UserServiceHealthIndicator(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@Override
    public Health health() {
        boolean isMyServiceUp = userService.checkHealth();
        if (isMyServiceUp) {
            return Health.up().build();
        } else {
            return Health.down().withDetail("MyService", "not responding").build();
        }
    }
}

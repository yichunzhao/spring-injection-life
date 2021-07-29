package com.ynz.demo.springinjectionlife.front;

import com.ynz.demo.springinjectionlife.domain.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api")
@Slf4j
public class DateTimeController {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping(value = "dateTime")
    public String getCurrentDateTime(Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList());

        log.info("getCurrentDateTime; current user: {} has authorities {} ", user.getUsername(), authorities);
        return OffsetDateTime.now().toString();
    }

    @GetMapping("controller")
    public Map<String, String> getCurrentApplicationContextInfo(Principal principal) {
        log.info("getCurrentDateTime; current user: {} ", principal.getName());
        DateTimeController controller = applicationContext.getBean(DateTimeController.class);

        Map<String, String> map = new HashMap<>();
        map.put(controller.getClass().getSimpleName(), Integer.toString(controller.hashCode()));
        map.put("thread: ", Thread.currentThread().getName());

        return map;
    }

}

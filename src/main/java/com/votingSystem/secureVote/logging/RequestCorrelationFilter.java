package com.votingSystem.secureVote.logging;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
public class RequestCorrelationFilter implements Filter {
    private final String CORRELATION_ID = "correlationId";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String correlationId = UUID.randomUUID().toString();

        MDC.put(CORRELATION_ID,correlationId);


        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse ;
        httpServletResponse.setHeader("X-Correlation-id",correlationId);


        try{
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            MDC.clear();
        }


    }
}

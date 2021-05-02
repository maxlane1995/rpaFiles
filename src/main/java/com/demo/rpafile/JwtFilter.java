package com.demo.rpafile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORITIES_KEY = "roles";
    static final String ORIGIN = "Origin";
    
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println(request.getHeader(ORIGIN));
		System.out.println(request.getMethod());
		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			  String origin=request.getHeader(ORIGIN); 
		      
		        if(request.getHeader(ORIGIN) == null || request.getHeader(ORIGIN).equals("null")){
		                   
		               response.setHeader("Access-Control-Allow-Origin","*");
		               response.setHeader("Access-Control-Allow-Credentials", "true");
		               response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		        }
		        
		        if((request.getRequestURI().substring(request.getContextPath().length()).equalsIgnoreCase("/login") && request.getMethod().equals("POST")) || request.getMethod().equals("GET")||request.getMethod().equals("DELETE")||request.getMethod().equals("PUT")||request.getMethod().equals("POST")){
		            response.setHeader("Access-Control-Allow-Origin","*");
		            response.setHeader("Access-Control-Allow-Credentials", "true");
		            response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		        }
		        
		        if(request.getMethod().equals("OPTIONS")){
		               
		               try{ 
		                     response.setHeader("Access-Control-Allow-Origin","*");
		                     response.setHeader("Access-Control-Allow-Credentials", "true");
		                     response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		                     //response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With, Authorization");
		                     response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		                     response.getWriter().print("OK");
		                     response.getWriter().flush();
		               }catch(IOException e){
		                     e.printStackTrace();
		               } 
		        }else{
		            filterChain.doFilter(request, response);
		        }     
			
			
			
		    response.setStatus(HttpServletResponse.SC_OK);
		}else{
		String authHeader = request.getHeader(AUTHORIZATION_HEADER);		
	
		
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
		} else {
			try {
				String token = authHeader.substring(7);
				Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
				filterChain.doFilter(request, response);
			} catch (SignatureException e) {
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
			}
		}

		}
	}	

	
	public Authentication getAuthentication(Claims claims) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) claims.get(AUTHORITIES_KEY);		
		for (Object role : roles) {
			String roleName = role.toString().split("=")[1].replace("}", "").trim();
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
		User principal = new User(claims.getSubject(), "", authorities);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				principal, "", authorities);
		return usernamePasswordAuthenticationToken;
	}

//	private static final String AUTHORIZATION_HEADER = "Authorization";
//	private static final String AUTHORITIES_KEY = "roles";
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) res;
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization");
//		response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
//		response.addHeader("Access-Control-Allow-Headers", "access-control-allow-origin,x-requested-with, Authorization ,Content-Type,");
//		System.out.println(request.getMethod());
//		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//		    response.setStatus(HttpServletResponse.SC_OK);
//		}else{
//		String authHeader = request.getHeader(AUTHORIZATION_HEADER);
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header.");
//		} else {
//			try {
//				String token = authHeader.substring(7);
//				Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
//				request.setAttribute("claims", claims);
//				SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
//				filterChain.doFilter(request, response);
//			} catch (SignatureException e) {
//				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
//			}catch (JwtException j){
//				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
//			}
//		}
//
//		}
//	}	
//	
//	/**
//	 * Method for creating Authentication for Spring Security Context Holder
//	 * from JWT claims
//	 * 
//	 * @param claims
//	 * @return
//	 */
//	public Authentication getAuthentication(Claims claims) {
//		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//		List<String> roles = (List<String>) claims.get(AUTHORITIES_KEY);
//		for (String role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		User principal = new User(claims.getSubject(), "", authorities);
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//				principal, "", authorities);
//		return usernamePasswordAuthenticationToken;
//	}
}



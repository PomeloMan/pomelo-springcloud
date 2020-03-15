package pomelo.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

	public static final Logger logger = LoggerFactory.getLogger(AuthGlobalFilter.class);

	// 排除过滤的 uri 地址
	private static final String[] WHITE_LIST = { "/*/v2/api-docs", "/user/register", "/swagger-ui.html",
			"/swagger-resources/**", "/*/api-docs", "/api/socket/**", "/log" };

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String uriPath = request.getPath().toString();
		logger.info("request url:{}", uriPath);

		boolean pass = false;
		for (String url : WHITE_LIST) {
			if (antPathMatcher.match(url, uriPath)) {
				pass = true;
				break;
			}
		}
		// 跳过不需要验证的路径
		if (pass) {
			return chain.filter(exchange);
		}

		HttpHeaders headers = request.getHeaders();
		String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(token)) {
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}
		// 验证 token
		// 方法1：登录成功存进redis，这里直接与服务值比对
		// 方法2：通过用户服务验证
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return -100;
	}
}

package com.portal.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.JSONUtil;
import com.portal.JwtUtils;
import com.portal.RsaUtils;
import com.portal.RsaUtils;
import com.portal.entity.user.WxbMemeber;
import com.portal.vo.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //放行资源
        URI uri = request.getURI();
        String[] whiteList = {"/user/login","/search/search","/search/init","/index/listContentById","/index/findListByCid"};
        for (String w : whiteList) {
            if (uri.getPath().equals(w)){
                return chain.filter(exchange);
            }
        }

        //判断请求头里面是否有token
        String token = request.getHeaders().getFirst("token");
        if (token == null){
            //不能放行，直接响应客户端
            return response(response,new Result(false,"请登录","token"));
        }


        //校验令牌
        //加载公钥
        PublicKey publicKey = null;
        try{
            publicKey = RsaUtils.getPublicKey( ResourceUtils.getFile("classpath:rsa.pub").getPath());
        }catch (Exception e){
            e.printStackTrace();
        }

        //校验令牌
        try {
            WxbMemeber wxbMemeber = (WxbMemeber) JwtUtils.getInfoFromToken(token,publicKey, WxbMemeber.class);
            //传递用户信息  请求头
            ServerHttpRequest newRequest = request.mutate().header("token", JSONUtil.toString(wxbMemeber)).build();
            //放行
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);

        } catch (MalformedJwtException e) {
            e.printStackTrace();
            return response(response,new Result(false,"非法令牌","token"));
        }catch (ExpiredJwtException e){
            return response(response,new Result(false,"令牌过期","token"));
        }catch (Exception e){
            return response(response,new Result(false,"其它异常","token"));
        }
    }

    @Override
    public int getOrder() {

        return 0;
    }

    private Mono<Void> response(ServerHttpResponse response, Result res){
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try{
            jsonStr = objectMapper.writeValueAsString(res);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        DataBuffer wrap = response.bufferFactory().wrap(jsonStr.getBytes());

        return response.writeWith(Flux.just(wrap));
    }
}

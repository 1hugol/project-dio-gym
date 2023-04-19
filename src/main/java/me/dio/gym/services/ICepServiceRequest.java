package me.dio.gym.services;

import me.dio.gym.controllers.response.QueryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep-api", url = "https://viacep.com.br/ws")
public interface ICepServiceRequest {
    @GetMapping("/{cep}/json/")
    public QueryResponse getAddress(@PathVariable("cep") String cep);
}

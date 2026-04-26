package com.diego.clientes_api.controller;

import com.diego.clientes_api.model.Cliente;
import com.diego.clientes_api.repository.ClienteRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteExistente = repository.findById(id).orElseThrow();

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());

        return repository.save(clienteExistente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

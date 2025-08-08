package com.bootcamp.service;

import com.bootcamp.model.Cliente;
import com.bootcamp.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long usuario_id) {
        return clienteRepository.findById(usuario_id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long usuario_id, Cliente cliente) {
        cliente.setUsuario_id(usuario_id);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long usuario_id) {
        clienteRepository.deleteById(usuario_id);
    }
}

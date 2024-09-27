package br.edu.infnet.catalogoveiculos.service;

import br.edu.infnet.catalogoveiculos.model.Veiculo;
import br.edu.infnet.catalogoveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> listarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo adicionar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }
}
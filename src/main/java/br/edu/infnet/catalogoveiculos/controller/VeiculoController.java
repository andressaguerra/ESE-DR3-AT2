package br.edu.infnet.catalogoveiculos.controller;

import br.edu.infnet.catalogoveiculos.model.Veiculo;
import br.edu.infnet.catalogoveiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> listarPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.listarPorId(id);
        return veiculo.isPresent() ? ResponseEntity.ok(veiculo.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Veiculo adicionar(@RequestBody Veiculo veiculo) {
        return veiculoService.adicionar(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoOptional = veiculoService.listarPorId(id);
        if (veiculoOptional.isPresent()) {
            Veiculo veiculoUpdate = veiculoOptional.get();
            veiculoUpdate.setAno(veiculo.getAno());
            veiculoUpdate.setMarca(veiculo.getMarca());
            veiculoUpdate.setModelo(veiculo.getModelo());
            Veiculo veiculoUpdated = veiculoService.adicionar(veiculoUpdate);
            return ResponseEntity.ok(veiculoUpdated);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Veiculo> deletar(@PathVariable Long id) {
        if (veiculoService.listarPorId(id).isPresent()) {
            veiculoService.deletar(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
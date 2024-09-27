package br.edu.infnet.catalogoveiculos;

import br.edu.infnet.catalogoveiculos.model.Veiculo;
import br.edu.infnet.catalogoveiculos.repository.VeiculoRepository;
import br.edu.infnet.catalogoveiculos.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogoVeiculosApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private VeiculoService veiculoService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testAdicionar() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Porsche");
        veiculo.setModelo("911 Carrera");
        veiculo.setAno(2021);

        when(veiculoService.adicionar(any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Porsche\",\"modelo\":\"911 Carrera\",\"ano\":2021}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Porsche"))
                .andExpect(jsonPath("$.modelo").value("911 Carrera"))
                .andExpect(jsonPath("$.ano").value(2021));
    }

    @Test
    public void testListar() throws Exception {
        mockMvc.perform(get("/veiculos")).andExpect(status().isOk());
    }

    @Test
    public void testListarPorId() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Porsche");
        veiculo.setModelo("911 Carrera");
        veiculo.setAno(2021);

        when(veiculoService.listarPorId(1L)).thenReturn(Optional.of(veiculo));

        mockMvc.perform(get("/veiculos/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Porsche"))
                .andExpect(jsonPath("$.modelo").value("911 Carrera"))
                .andExpect(jsonPath("$.ano").value(2021));
    }

    @Test
    public void testDeletar() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Porsche");
        veiculo.setModelo("911 Carrera");
        veiculo.setAno(2021);

        when(veiculoService.listarPorId(1L)).thenReturn(Optional.of(veiculo));

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAtualizar() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Porsche");
        veiculo.setModelo("911 Carrera");
        veiculo.setAno(2021);

        when(veiculoService.listarPorId(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoService.adicionar(any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(put("/veiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Porsche\",\"modelo\":\"911 Carrera\",\"ano\":2022}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").value(2022));
    }
}

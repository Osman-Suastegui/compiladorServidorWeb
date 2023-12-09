package demoSpringBoot.demo;

import demoSpringBoot.demo.lexico.MainLexico;
import demoSpringBoot.demo.lexico.tablaDeSimbolos.ManejadorErrores;
import demoSpringBoot.demo.sintactico.Sentences.Sent.NodoAST;
import demoSpringBoot.demo.sintactico.Sentences.Sent.NodoLeer;
import demoSpringBoot.demo.sintactico.Sentences.Sent.NodoPrograma;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compiladorController")
public class compiladorController {

    ManejadorErrores manejadorErrores = ManejadorErrores.obtenerInstancia();
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/compilar")
    public List<String> compilar(@RequestBody Map<String,String[][]> micodigo) throws Exception {
        NodoPrograma.limpiarInstancia();
        String file = "C:\\Users\\osman\\OneDrive\\Desktop\\SpringBootApps\\demoSpringBoot\\demo\\src\\main\\java\\contenidoCompilador.txt";
//        abre el archivo y escribe el codigo
        String[][] codigo = micodigo.get("codigo");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < codigo.length; i++) {
                for (int j = 0; j < codigo[i].length; j++) {
                    bw.write(codigo[i][j]);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error al escribir el archivo");
        }

        MainLexico lexico = new MainLexico(file);
        List<String > misErrores = manejadorErrores.getErrores();
        manejadorErrores.setErrores(new ArrayList<>());
        return misErrores;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/obtenerArbolSintactico")
    public NodoAST obtenerArbolSintactico(){
        return NodoPrograma.obtenerInstancia();
    }


}
